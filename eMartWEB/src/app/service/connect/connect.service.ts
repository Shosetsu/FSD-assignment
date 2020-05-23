import { Injectable } from '@angular/core';
import { SessionControllerService } from '../session/session-controller.service';
import { Constants } from 'src/app/constans/constans';
import { MessageService } from '../message/message.service';
import { Message } from 'src/app/bean/message';
import { Location } from '@angular/common';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ConnectService {

  constructor(private session: SessionControllerService, private messageService: MessageService, private location: Location, private router: Router) { }

  async fetchData(server, apiName, method, requestData) {

    let url = Constants.serverAddress + "/" + server + apiName;

    if (method === 'GET' && requestData) {
      let queryArray = [];
      Object.keys(requestData).forEach((key) => { if (requestData[key]) queryArray.push(key + '=' + encodeURIComponent(requestData[key])); });
      url += '?' + queryArray.join('&');
    }

    return fetch(url, {
      headers: {
        'Access-Control-Allow-Origin': window.location.hostname,
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': Constants.authPrefix + this.session.getAuthKey()
      },
      method: method,
      body: method === 'GET' ? null : requestData
    }).then((fetchResult) => {
      return fetchResult.json();
    }).then((json) => {
      if (Constants.debugMode) console.log(json);

      if (json['status'] === Constants.res_nothing) {
        return json['data'];
      }

      switch (json['status']) {
        case Constants.res_error:
          this.messageService.addMsg(new Message("warning", json['messageList'][0]));
          break;
        case Constants.res_reload:
          // TODO
          break;
        case Constants.res_error_reload:
          // TODO
          break;
        case Constants.res_timeout:
          // TODO
          break;
        case Constants.res_back:
          // this.location.back();
          this.router.navigate(["404"]);
          break;
        default:
          // TODO
          break;
      }
      return null;
    }).catch((err) => {
      if (Constants.debugMode) console.error(err);
      this.messageService.addMsg(new Message("danger", "System Error"));
      return null;
    });
  }
}

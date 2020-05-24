import { Location } from '@angular/common';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Message } from 'src/app/bean/message';
import { Constants } from 'src/app/constans/constans';
import { MessageService } from '../message/message.service';
import { SessionControllerService } from '../session/session-controller.service';

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
      body: method === 'GET' ? null : requestData ? JSON.stringify(requestData) : null
    }).then((fetchResult) => {
      return fetchResult.json();
    }).then((json) => {
      if (Constants.debugMode) console.log(json);

      if (json['status'] === Constants.res_nothing) {
        return json['data'] ? json['data']: true;
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
        case 404:
          this.router.navigate(["404"]);
          break;
        default:
          // TODO
          break;
      }
      return false;
    }).catch((err) => {
      if (Constants.debugMode) console.error(err);
      this.messageService.addMsg(new Message("danger", "System Error"));
      return false;
    });
  }
}

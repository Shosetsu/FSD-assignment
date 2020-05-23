import { Injectable } from '@angular/core';
import { SessionControllerService } from '../session/session-controller.service';
import { Constants } from 'src/app/constans/constans';
import { MessageService } from '../message/message.service';
import { Message } from 'src/app/bean/message';

@Injectable({
  providedIn: 'root'
})
export class ConnectService {

  constructor(private session: SessionControllerService, private messageService: MessageService) { }

  async fetchData(server, apiName, method, requestData) {

    let url = Constants.serverAddress + "/" + server + apiName;

    if (method == "GET") {
      let queryArray = [];
      Object.keys(requestData).forEach(function (key) { return queryArray.push(key + '=' + encodeURIComponent(requestData[key])); });
      url += '?' + queryArray.join('&');
    }

    return fetch(url, {
      headers: {
        'Access-Control-Allow-Origin': 'http://localhost:4200',
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': Constants.authPrefix + this.session.getAuthKey()
      },
      method: method,
      body: requestData
    }).then((fetchResult) => {
      return fetchResult.json();
    }).then((json) => {
      if (Constants.debugMode) console.log(json);
      if (json['status'] !== 'success') {
        this.messageService.addMsg(new Message("warning", json['messageList'][0]));
        return 'failure';
      }
      return json;
    }).catch((err) => {
      if (Constants.debugMode) console.error(err);
      this.messageService.addMsg(new Message("danger", "System Error"));
      return 'failure';
    });
  }
}

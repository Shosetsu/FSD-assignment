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

  async fetchData(server, path, method, requestData) {
    return fetch(server + path, {
      headers: {
        'Access-Control-Allow-Origin': 'http://localhost:4200',
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'sessionKey': this.session.getSessionKey(),
        'accountId': this.session.getAccountId()
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

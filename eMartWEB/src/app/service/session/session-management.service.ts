import { Injectable } from '@angular/core';
import { CustomerInfo } from 'src/app/bean/CustomerInfo';
import { Message } from 'src/app/bean/message';
import { Constants } from 'src/app/constans/constans';
import { ConnectService } from '../connect/connect.service';
import { MessageService } from '../message/message.service';
import { SessionControllerService } from './session-controller.service';

@Injectable({
  providedIn: 'root'
})
export class SessionManagementService {

  constructor(private sessionControllerService: SessionControllerService, private messageService: MessageService, private connect: ConnectService) { }


  async login(id, password): Promise<string> {
    if (Constants.debugMode) console.log("#Log in " + id);

    let processResult = 'success';
    let result = new CustomerInfo();

    await this.connect.fetchData(Constants.authServer, "/auth/login", "POST", JSON.stringify({ id: id, password: password }))
      .then((json) => {
        if (Constants.debugMode) console.log(json);
        if (json === 'failure') {
          processResult = 'failure';
          return;
        }
        result.init(json['data']);
        localStorage['_ssid'] = result.sessionKey + "|" + result.accountId;
        this.sessionControllerService.init(result);
      }).catch((err) => {
        if (Constants.debugMode) console.error(err);
        processResult = 'failure';
        this.messageService.addMsg(new Message("danger", "System Error"));
      });

    return processResult;
  }

  logout(id, sessionKey) {
    if (Constants.debugMode) console.log("#Log out " + id);
    localStorage['_ssid'] = "";
    this.sessionControllerService.clearSession();

    fetch(Constants.authServer + "/auth/logout", {
      headers: Constants.fetchHeader,
      method: "POST",
      body: JSON.stringify({ id: id, sessionKey: sessionKey })
    }).catch((err) => {
      if (Constants.debugMode) console.error(err);
    });
  }

  checkLoginStatus(ssid) {
    if (Constants.debugMode) console.log("#Auto Log in " + ssid.split("|")[1]);
    let result = new CustomerInfo();

    fetch(Constants.authServer + "/auth/login?ssId=" + encodeURI(ssid), {
      headers: Constants.fetchHeader,
      method: "GET",
      cache: 'no-cache'
    }).then((fetchResult) => {
      return fetchResult.json();
    }).then((json) => {
      if (Constants.debugMode) console.log(json);
      if (json['status'] === 'success') {
        if (Constants.debugMode) console.log("#Auto Log in " + ssid.split("|")[1] + " success!");
        result.init(json['data']);
        this.sessionControllerService.init(result);
      }
    }).catch((err) => {
      if (Constants.debugMode) console.error(err);
    });

  }

}
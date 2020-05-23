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

    await this.connect.fetchData('auth', '/login', 'POST', JSON.stringify({ id: id, password: password }))
      .then((json) => {
        if (Constants.debugMode) console.log(json);
        if (json === 'failure') {
          processResult = 'failure';
          return;
        }
        result.init(json['data']);
        localStorage['_ssid'] = result.authKey;
        this.sessionControllerService.init(result);
      });

    return processResult;
  }

  logout() {
    if (Constants.debugMode) console.log("#Log out ");
    localStorage['_ssid'] = "";
    this.sessionControllerService.clearSession();

    this.connect.fetchData('auth', '/logout', 'POST', null);
  }

  checkLoginStatus(ssid) {
    if (Constants.debugMode) console.log("#Auto Log in ");
    let result = new CustomerInfo();

    this.connect.fetchData('auth', '/login', 'GET', null).then((json) => {
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
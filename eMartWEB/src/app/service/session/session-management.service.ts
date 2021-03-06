import { Injectable } from '@angular/core';
import { CustomerInfo } from 'src/app/bean/CustomerInfo';
import { Constants } from 'src/app/constans/constans';
import { ConnectService } from '../connect/connect.service';
import { MessageService } from '../message/message.service';
import { SessionControllerService } from './session-controller.service';

@Injectable({
  providedIn: 'root'
})
export class SessionManagementService {

  constructor(private sessionControllerService: SessionControllerService, private messageService: MessageService, private connect: ConnectService) { }


  async login(id, password): Promise<boolean> {
    if (Constants.debugMode) console.log("#Log in " + id);

    let result = new CustomerInfo();

    return await this.connect.fetchData('auth', '/login', 'POST', { id: id, password: password })
      .then((data) => {
        if (!data) {
          return false;
        }
        result.init(data);
        this.sessionControllerService.init(result);

        localStorage['_ssid'] = result.authKey;
        localStorage['tempT'] = btoa(result.accountType);

        return true;
      });
  }

  logout() {
    if (Constants.debugMode) console.log("#Log out ");

    this.connect.fetchData('auth', '/logout', 'POST', null).then((data) => {
      localStorage['_ssid'] = "";
      localStorage['tempT'] = "";
      this.sessionControllerService.clearSession();
      window.location.href = "/";
    });

  }

  checkLoginStatus() {
    let result = new CustomerInfo();
    result.authKey = localStorage['_ssid'];
    result.accountId = JSON.parse(atob(localStorage['_ssid'].split('.')[1]))['jti'];
    if (localStorage['tempT']) {
      result.accountType = atob(localStorage['tempT']);
    }
    this.sessionControllerService.init(result);
    if (Constants.debugMode) console.log("#Auto Log in " + result.accountId);

    this.connect.fetchData('auth', '/login', 'GET', null).then((data) => {
      if (data) {
        if (Constants.debugMode) console.log("#Auto Log in " + result.accountId + " success!");
        result.accountType = data;
        localStorage['tempT'] = btoa(data);
        this.sessionControllerService.init(result);
      }
    });

  }

}
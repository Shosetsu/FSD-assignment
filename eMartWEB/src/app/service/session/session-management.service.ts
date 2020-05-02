import { Injectable } from '@angular/core';
import { CustomerInfo } from 'src/app/bean/CustomerInfo';
import { SessionControllerService } from './session-controller.service';
import { Constants } from 'src/app/constans/constans';

@Injectable({
  providedIn: 'root'
})
export class SessionManagementService {

  constructor(private sessionControllerService: SessionControllerService) { }


  login(id, password): string {
    if (Constants.debugMode) console.log("#Log in " + id);

    //TODO connect server

    localStorage['_ssid'] = "osk-55dsa-31";
    let result = new CustomerInfo('S', id, 'osk-55dsa-31')
    if (id == "Admin") result.accountType = "M";
    this.sessionControllerService.init(result);
    return 'success';
  }

  logout(id, sessionKey) {
    if (Constants.debugMode) console.log("#Log out " + id);
    localStorage['_ssid'] = "";
    this.sessionControllerService.clearSession();

    //TODO connect server

  }

  checkLoginStatus(sessionKey): CustomerInfo {
    let result = new CustomerInfo('S', 'Setsu', sessionKey);
    if (Constants.debugMode) console.log("#Auto Log in " + result.accountId);

    //TODO connect server

    this.sessionControllerService.init(result);
    return result;
  }

}
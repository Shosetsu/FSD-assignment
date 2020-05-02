import { Injectable } from '@angular/core';
import { CustomerInfo } from 'src/app/bean/CustomerInfo';
import { SessionControllerService } from './session-controller.service';

@Injectable({
  providedIn: 'root'
})
export class SessionManagementService {

  constructor(private sessionControllerService: SessionControllerService) { }


  login(id, password): string {
    console.log("#Log in " + id);

    //TODO connect server

    localStorage['_ssid'] = "osk-55dsa-31";
    let result = new CustomerInfo('S', id, 'osk-55dsa-31')
    if (id == "Admin") result.accountType = "M";
    this.sessionControllerService.init(result);
    return 'success';
  }

  logout(id, sessionKey) {
    console.log("#Log out " + id);
    localStorage['_ssid'] = "";
    this.sessionControllerService.clearSession();

    //TODO connect server

  }

  checkLoginStatus(sessionKey): CustomerInfo {
    let result = new CustomerInfo('B', 'Setsu', sessionKey);
    console.log("#Auto Log in " + result.accountId);

    //TODO connect server


    this.sessionControllerService.init(result);
    return result;
  }

}
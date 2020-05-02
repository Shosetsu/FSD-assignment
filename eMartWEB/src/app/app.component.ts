import { Component } from '@angular/core';
import { SessionManagementService } from './service/session/session-management.service';
import { CustomerInfo } from './bean/CustomerInfo';
import { SessionControllerService } from './service/session/session-controller.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'eMartWEB';

  loginInfo: CustomerInfo;


  constructor(private sessionControllerService: SessionControllerService, private sessionManagementService: SessionManagementService) {
    this.loginInfo = new CustomerInfo();
    let sessionKey = localStorage['_ssid'];
    if (sessionKey) {
      let loginInfo = this.sessionManagementService.checkLoginStatus(sessionKey);
      if (loginInfo.accountType) {
        this.loginInfo = loginInfo;
      }
    }
    this.sessionControllerService.init(this.loginInfo);
  }
}

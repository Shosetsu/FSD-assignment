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

  constructor(private sessionControllerService: SessionControllerService, private sessionManagementService: SessionManagementService) {
    let ssid = localStorage['_ssid'];
    if (ssid && ssid.indexOf("|") > -1) {
      this.sessionManagementService.checkLoginStatus(ssid);
    } else {
      this.sessionControllerService.init(new CustomerInfo());
    }
  }
}

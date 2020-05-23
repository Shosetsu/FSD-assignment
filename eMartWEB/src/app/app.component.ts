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
    try {
      this.sessionManagementService.checkLoginStatus();
    } catch (e) {
      this.sessionControllerService.init(new CustomerInfo());
    }
  }
}

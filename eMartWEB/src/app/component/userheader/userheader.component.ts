import { Component, Output, EventEmitter, Input, NgZone } from '@angular/core';
import { CustomerInfo } from 'src/app/bean/CustomerInfo';
import { SessionControllerService } from 'src/app/service/session/session-controller.service';
import { Router } from '@angular/router';
import { GoodManagementService } from 'src/app/service/goods/good-management.service';
import { SessionManagementService } from 'src/app/service/session/session-management.service';

@Component({
  selector: 'app-userheader',
  templateUrl: './userheader.component.html',
  styleUrls: ['./userheader.component.css']
})
export class UserheaderComponent {
  menuOpen: boolean = false;

  signUpFlag: boolean;
  loginFlag: boolean;

  constructor(private sessionControllerService: SessionControllerService,
    private sessionManagementService: SessionManagementService,
    private router: Router,
    private goodService: GoodManagementService) { }

  signUp(openFlag: boolean) {
    this.signUpFlag = openFlag;
  }

  login(openFlag: boolean) {
    this.loginFlag = openFlag;
  }

  openAccountInfo() {
    this.router.navigateByUrl('account/detail/' + this.getAccountId());
  }

  filter(category: string) {

  }

  logout() {
    this.sessionManagementService.logout();
  }

  isRole(role: string) {
    return this.sessionControllerService.isRole(role);
  }
  getAccountId(): string {
    return this.sessionControllerService.getAccountId();
  }
  getCategoryList(): string[] {
    return this.goodService.getCategoryList();
  }
}

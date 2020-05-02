import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CustomerDetail } from 'src/app/bean/CustomerDetail';
import { AccountManagementService } from 'src/app/service/account/account-management.service';
import { SessionControllerService } from 'src/app/service/session/session-controller.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-account-detail',
  templateUrl: './account-detail.component.html',
  styleUrls: ['./account-detail.component.css']
})
export class AccountDetailComponent {
  customerDetail: CustomerDetail;
  constructor(private route: ActivatedRoute, private accountService: AccountManagementService, private sessionService: SessionControllerService) {
    this.route.params.subscribe(para => {
      let id = para['uid'] ? "" + para['uid'] : sessionService.getAccountId();
      this.customerDetail = this.accountService.getAccountDetail(id, sessionService.getSessionKey());
    });
    this.changedDetail = new CustomerDetail();
  }

  changeFlag: boolean;
  changedDetail: CustomerDetail;

  newPassword: string;
  oldPassword: string;

  updateFlag: boolean;
  submited: boolean;

  isRole(role: string): boolean {
    return this.changeFlag ? this.changedDetail.isRole(role) : this.customerDetail.isRole(role);
  }

  isOwner(): boolean {
    return this.customerDetail.accountId === this.sessionService.getAccountId();
  }

  openChange() {
    this.changeFlag = true;
    this.changedDetail.init(this.customerDetail);
    this.changedDetail.GSTIN = "";
    this.changedDetail.bankDetail = "";
  }

  discard(form: NgForm) {
    this.changeFlag = false;
    this.changedDetail = new CustomerDetail();
    this.submited = false;
    form.reset();
  }

  changeDetail(form: NgForm) {
    if (form.form.invalid) {
      this.submited = true;
      return;
    }

    let result = this.accountService.updateAccountDetail(this.changedDetail, this.sessionService.getSessionKey(), this.oldPassword);
    if (result.status == 0) {
      this.customerDetail = result.newDetail;
      this.discard(form);
    }
  }


}

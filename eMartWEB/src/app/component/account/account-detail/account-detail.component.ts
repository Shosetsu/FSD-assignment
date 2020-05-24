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
    this.customerDetail = new CustomerDetail();
    this.changedDetail = new CustomerDetail();

    //load detail
    this.route.params.subscribe(para => {
      let id = para['uid'] ? "" + para['uid'] : sessionService.getAccountId();
      this.accountService.getAccountDetail(id).then(data => {
        this.customerDetail.init(data);
      });
    });
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
    this.changedDetail.gstin = "";
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

    this.accountService.updateAccountDetail(this.changedDetail, this.oldPassword).then(data => {
      if (data) {
        this.discard(form);
        location.reload();
      }
    })
  }
}

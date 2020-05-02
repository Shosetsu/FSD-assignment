import { Injectable } from '@angular/core';
import { CustomerDetail } from 'src/app/bean/CustomerDetail';
import { SessionControllerService } from '../session/session-controller.service';
import { CustomerInfo } from 'src/app/bean/CustomerInfo';
import { Constants } from 'src/app/constans/constans';

@Injectable({
  providedIn: 'root'
})
export class AccountManagementService {

  constructor(private sessionService: SessionControllerService) { }

  regist(formData: {
    userId: string,
    password: string,
    rePassword: string,
    email: string,
    telNumber: string,
    asSeller: boolean,
    coName: string,
    postalAddr: string,
    GSTIN: string,
    bankDetail: string
  }) {
    if (Constants.debugMode) console.log('#Regsit user ' + formData.userId);
    //TODO connect server

    // regist new session info
    let newSessionKey = new Date().toLocaleString() + "_0";
    localStorage['_ssid'] = newSessionKey;
    this.sessionService.init(new CustomerInfo(formData.asSeller ? "S" : "B", formData.userId, newSessionKey));
    return 0;
  }

  unregist(userId: string, sessionKey: string, password: string): number {
    if (Constants.debugMode) console.log('#Unregsit user ' + userId + '|' + sessionKey);
    //TODO connect server

    localStorage['_ssid'] = "";
    this.sessionService.clearSession();
    return 0;
  }

  findAccount(mail: string): number {
    if (Constants.debugMode) console.log("#Find Account " + mail);
    //TODO connect server

    return 0;
  }


  getAccountDetail(accountId: string): CustomerDetail {
    if (Constants.debugMode) console.log("#Get account detail " + accountId);
    //TODO connect server


    return new CustomerDetail("B", accountId, "setsu@test.com", "01-555-666", "Test Seller Company", "7 Alma Villa Rise", "PKS59-50", "6288-****-****-*888-**8");

  }

  updateAccountDetail(customerDetail: CustomerDetail, sessionKey: string, password: string): { status: number, newDetail?: CustomerDetail } {
    if (Constants.debugMode) console.log("#Update account detail " + customerDetail.accountId);
    //TODO connect server

    // regist new session info
    let newSessionKey = new Date().toLocaleString() + "_2";
    localStorage['_ssid'] = newSessionKey;
    this.sessionService.init(new CustomerInfo(customerDetail.accountType, customerDetail.accountId, newSessionKey));
    return { status: 0, newDetail: customerDetail };
  }
}

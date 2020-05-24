import { Injectable } from '@angular/core';
import { CustomerDetail } from 'src/app/bean/CustomerDetail';
import { CustomerInfo } from 'src/app/bean/CustomerInfo';
import { Constants } from 'src/app/constans/constans';
import { ConnectService } from '../connect/connect.service';
import { SessionControllerService } from '../session/session-controller.service';
import { SessionManagementService } from '../session/session-management.service';
import { UpdateAccountInfoRequestData } from './UpdateAccountInfoRequestData';

@Injectable({
  providedIn: 'root'
})
export class AccountManagementService {

  constructor(private sessionService: SessionControllerService, private sessionMS: SessionManagementService, private connect: ConnectService) { }

  async register(formData: {
    accountId: string,
    password: string,
    email: string,
    telNumber: string,
    asSeller: boolean,
    coName: string,
    postalAddr: string,
    gstin: string,
    bankDetail: string
  }): Promise<boolean> {
    if (Constants.debugMode) console.log('#Register user ' + formData.accountId);

    return await this.connect.fetchData('account', "/register", "POST", formData).then(data => {
      //register success auto login
      if (data) {
        return this.sessionMS.login(formData.accountId, formData.password);
      }
      //failure
      return false;
    });
  }

  async unregist(password: string): Promise<boolean> {
    if (Constants.debugMode) console.log('#Unregsit user');
    //TODO connect server

    return await this.connect.fetchData('account', "/unregister", "POST", { 'password': password }).then(data => {
      //success
      if (data) {
        localStorage['_ssid'] = "";
        localStorage['tempT'] = "";
        this.sessionService.clearSession();
        window.location.href = "/";

        return true;
      }
      //failure
      return false;
    });
  }

  findAccount(mail: string): number {
    if (Constants.debugMode) console.log("#Find Account " + mail);

    this.connect.fetchData('account', "/findaccount", "POST", { 'email': mail });

    return 0;
  }

  async getSellerCreateDate(accountId: string): Promise<Date> {
    if (Constants.debugMode) console.log("#Get account seller date " + accountId);

    return await this.connect.fetchData('account', "/query/sellerdate", "GET", { 'tid': accountId });
  }

  async getAccountDetail(accountId: string): Promise<CustomerDetail> {
    if (Constants.debugMode) console.log("#Get account detail " + accountId);

    return await this.connect.fetchData('account', "/query", "GET", { 'tid': accountId });

  }

  async updateAccountDetail(customerDetail: CustomerDetail, password: string): Promise<any> {
    if (Constants.debugMode) console.log("#Update account detail " + customerDetail.accountId);

    let req = new UpdateAccountInfoRequestData(customerDetail.accountId,
      password, customerDetail.email, customerDetail.telNumber,
      customerDetail.accountType, customerDetail.coName,
      customerDetail.postalAddr, customerDetail.gstin, customerDetail.bankDetail);

    return await this.connect.fetchData('account', "/update", "PUT", req);
  }
}

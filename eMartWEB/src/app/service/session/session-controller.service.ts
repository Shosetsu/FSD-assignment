import { Injectable } from '@angular/core';
import { CustomerInfo } from 'src/app/bean/CustomerInfo';

@Injectable({
  providedIn: 'root'
})
export class SessionControllerService {
  private customerInfomation: CustomerInfo;

  private redirectUrl: string;

  setRedirectUrl(url: string) {
    this.redirectUrl = url;
  }

  getRedirectUrl(): string {
    let url = this.redirectUrl;
    this.redirectUrl = null;
    return url;
  }

  constructor() {
    this.init(new CustomerInfo);
  }

  public init(customerInfomation: CustomerInfo) {
    if (!this.customerInfomation) {
      this.customerInfomation = customerInfomation;
    } else {
      this.customerInfomation.init(customerInfomation);
    }
  }

  public getAuthKey(): string {
    return this.customerInfomation.authKey;
  }
  public getAccountType(): string {
    return this.customerInfomation.accountType;
  }
  public getAccountId(): string {
    return this.customerInfomation.accountId;
  }
  public isRole(role: string): boolean {
    return this.customerInfomation.isRole(role);
  }
  public clearSession(): void {
    this.init(new CustomerInfo);
  }
}

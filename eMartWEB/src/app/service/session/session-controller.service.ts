import { Injectable } from '@angular/core';
import { CustomerInfo } from 'src/app/bean/CustomerInfo';
import { SessionManagementService } from './session-management.service';
import { CustomerDetail } from 'src/app/bean/CustomerDetail';

@Injectable({
  providedIn: 'root'
})
export class SessionControllerService {
  private customerInfomation: CustomerInfo;

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
  
  public getSessionKey(): string {
    return this.customerInfomation.sessionKey;
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

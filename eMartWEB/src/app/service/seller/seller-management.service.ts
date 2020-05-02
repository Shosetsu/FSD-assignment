import { Injectable } from '@angular/core';
import { GoodInfo } from 'src/app/bean/GoodInfo';
import { Constants } from 'src/app/constans/constans';

@Injectable({
  providedIn: 'root'
})
export class SellerManagementService {

  constructor() { }

  isSeller(accountId: string, sessionKey: string): boolean {
    if (Constants.debugMode) console.log("#Check Sales Id: " + accountId);
    //TODO server connect

    return true;
  }


  getSalesList(accountId: string, sessionKey: string): GoodInfo[] {
    if (Constants.debugMode) console.log("#Get Sales List for " + accountId);
    //TODO server connect


    return [new GoodInfo("100000", "Item1", "NO-1(tm)", ["test", "No1"], "first good, asdf asdfasdf asdf asdfasdf asdfasdfasdf asdfasdf asdfasdfasdf", 56800.66, 99, "Seller1", new Date(2020, 3, 23, 12, 6, 54)),
    new GoodInfo("100001", "Item2", "last company", ["test", "tool"], "2nd good, etcetcetcetcetcetcetcetcetce tcetcetcetcetcetcet cetcetcetcetcetcetcetcetcetc", 8993.50, 0, "Seller2", new Date(2020, 3, 23, 18, 6, 56)),
    new GoodInfo("100002", "Item3", "last company", ["tool", "food"], "3rd good", 999.75, 4, "Setsu", new Date(2020, 3, 25, 18, 6, 58))];
  }

  /**
   * 
   * @param processType 0:update,1:insert
   */
  updateSalesItemInformation(accountId: string, sessionKey: string, item: GoodInfo, processType: number = 0): number {
    if (Constants.debugMode) console.log("#" + ['Update', 'Insert'][processType] + " Sales Item for " + accountId);
    //TODO server connect


    return 1;
  }

  /**
   * 
   * @param status 0:normal,1:blocked,2:archiveed
   */
  setStatus(sessionKey: string, goodId: string, status: number): number {
    if (Constants.debugMode) console.log("#Change id[" + goodId + "] block status to " + Constants.goodStatus[status]);
    //TODO server connect

    return 1;
  }

  async downloadSalesReport() {
    //TODO server connect
  }

  async downloadTaxReport() {
    //TODO server connect
  }
}

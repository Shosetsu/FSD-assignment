import { Injectable } from '@angular/core';
import { GoodInfo } from 'src/app/bean/GoodInfo';
import { Constants } from 'src/app/constans/constans';
import { ConnectService } from '../connect/connect.service';

@Injectable({
  providedIn: 'root'
})
export class SellerManagementService {

  constructor(private connect: ConnectService) { }

  async isSeller(accountId: string): Promise<boolean> {
    if (Constants.debugMode) console.log("#Check Sales Id: " + accountId);

    return await this.connect.fetchData('seller', "/checkstatus", "GET", { 'sid': accountId });
  }


  async getSalesList(accountId: string): Promise<GoodInfo[]> {
    if (Constants.debugMode) console.log("#Get Sales List for " + accountId);

    return await this.connect.fetchData('seller', "/saleslist", "GET", { 'sid': accountId });
  }

  async getSellerOverviewInDate(accountId: string, range: string = 'all'): Promise<{ count: number, amount: number }> {
    if (Constants.debugMode) console.log("#Get account sales info in " + range);

    return await this.connect.fetchData('seller', "/overview", "GET", { 'sid': accountId, 'date': range });
  }

  /**
   * 
   * @param status 0:normal,1:blocked,2:archiveed
   */
  async setStatus(goodId: string, status: number): Promise<any> {
    if (Constants.debugMode) console.log("#Change id[" + goodId + "] block status to " + Constants.goodStatus[status]);

    return await this.connect.fetchData('seller', "/item/status", "PUT", { 'iid': goodId, 'status': status });
  }

  /**
   * 
   * @param processType 0:update,1:insert
   */
  async updateSalesItemInformation(item: GoodInfo, processType: number = 0): Promise<any> {
    if (Constants.debugMode) console.log("#" + ['Update', 'Insert'][processType] + " Sales Item ");

    return await this.connect.fetchData('seller', "/item", "POST", item);
  }

  async downloadSalesReport() {
    //TODO 
  }

  async downloadTaxReport() {
    //TODO 
  }
}

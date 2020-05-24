import { Injectable } from '@angular/core';
import { GoodInfo } from 'src/app/bean/GoodInfo';
import { Message } from 'src/app/bean/message';
import { OrderDetail } from 'src/app/bean/OrderDetail';
import { OrderInfo } from 'src/app/bean/OrderInfo';
import { Constants } from 'src/app/constans/constans';
import { ConnectService } from '../connect/connect.service';
import { MessageService } from '../message/message.service';
import { SessionControllerService } from '../session/session-controller.service';
import { PurchaseRequestData } from './PurchaseRequestData';

@Injectable({
  providedIn: 'root'
})
export class OrderManagementService {

  purchaseList: OrderDetail[] = [];

  constructor(
    private msgService: MessageService,
    private session: SessionControllerService,
    private connect: ConnectService) { }

  async getOrderList(startRow?: number): Promise<OrderInfo[]> {
    if (Constants.debugMode) console.log("#Get account Order list ");

    return await this.connect.fetchData('order', "/all", "GET", { 'sr': startRow });
  }

  async getOrderDetail(oid: string): Promise<OrderDetail> {
    if (Constants.debugMode) console.log("#Get Order Detail " + oid + " for");

    return await this.connect.fetchData('order', "/detail", "GET", { 'oid': oid });
  }

  getPurchaseList() { return this.purchaseList };

  addPurchase(good: GoodInfo, accountId: string, count: number = 1): number {
    //check
    if (good.owner === this.session.getAccountId()) {
      this.msgService.addMsg(new Message("warning", "Cannot purchase own item."));
      return;
    }
    if (!count) {
      this.msgService.addMsg(new Message("warning", "Cannot purchase 0 item."));
      return 0;
    }
    if (good.stock < 1) {
      this.msgService.addMsg(new Message("warning", "Sold out. Cannot to purchase."));
      return 0;
    }

    if (good.stock < count) {
      this.msgService.addMsg(new Message("warning", "Over stock. Cannot to purchase."));
      return 0;
    }

    let purchaseItem = new OrderDetail(null, good.owner, accountId,
      good.name, good.category, good.manufacturer,
      good.price, count, good.price * count, new Date(), good.id);
    this.purchaseList.push(purchaseItem);

    return 1;
  }

  async purchase() {
    let purchaseRequest = [];
    this.purchaseList.forEach(element => {
      purchaseRequest.push(new PurchaseRequestData(element.goodId, element.price, element.count));
    })

    return await this.connect.fetchData('purchase', "", "POST", purchaseRequest);
  }

  clearPurchaseList() {
    this.purchaseList.splice(0);
  }

}

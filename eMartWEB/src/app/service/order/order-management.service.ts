import { Injectable } from '@angular/core';
import { GoodInfo } from 'src/app/bean/GoodInfo';
import { Message } from 'src/app/bean/message';
import { OrderDetail } from 'src/app/bean/OrderDetail';
import { OrderInfo } from 'src/app/bean/OrderInfo';
import { MessageService } from '../message/message.service';

@Injectable({
  providedIn: 'root'
})
export class OrderManagementService {

  purchaseList: OrderDetail[] = [];

  constructor(private msgService: MessageService) { }

  getOrderList(accountId: string, sessionKey: string): OrderInfo[] {
    console.log("#Get account Order list " + accountId);
    //TODO connect server

    return [
      new OrderInfo("O-5332221", accountId, "Seller01", "TestBuy001", "No1(tm)", 10, 5000, new Date(2020, 4, 2, 12, 55, 32, 111), "100001"),
      new OrderInfo("O-5332222", "Buyer01", accountId, "TestSell002", "Bbr", 7, 7700, new Date(2020, 4, 1, 12, 55, 32, 111), "100000")
    ];

  }

  getOrderDetail(accountId: string, sessionKey: string, oid: string): OrderDetail {
    console.log("#Get Order Detail " + oid + " for" + accountId);
    //TODO connect server

    return new OrderDetail("O-5332222", "Buyer01", accountId, "TestSell002", ["Category1", "Category2"], "Bbr", 1100, 7, 7700, new Date(2020, 4, 1, 12, 55, 32, 111), "100000")
  }

  getPurchaseList() { return this.purchaseList };

  addPurchase(good: GoodInfo, accountId: string, count: number = 1): number {
    //check
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
  purchase(){

    
    this.clearPurchaseList();
  }
  
  clearPurchaseList() {
    this.purchaseList.splice(0);
  }

}

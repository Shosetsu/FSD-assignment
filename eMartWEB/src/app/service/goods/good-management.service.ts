import { Injectable } from '@angular/core';
import { Message } from 'src/app/bean/message';
import { Constants } from 'src/app/constans/constans';
import { GoodInfo } from '../../bean/GoodInfo';
import { MessageService } from '../message/message.service';
import { SessionControllerService } from '../session/session-controller.service';

@Injectable({
  providedIn: 'root'
})
export class GoodManagementService {

  private cartList: GoodInfo[];

  private categoryList: string[];
  private manufacturerList: string[];

  constructor(private sessionService: SessionControllerService, private msgService: MessageService) {
    this.cartList = this.selectCartListFromServer();
    this.categoryList = this.selectCategoryListFromServer();
    this.manufacturerList = this.selectManufacturerListFromServer();
  }

  getCartList(): GoodInfo[] {
    return this.cartList;
  }

  getCategoryList(): string[] {
    return this.categoryList;
  }

  getManufacturerList(): string[] {
    return this.manufacturerList;
  }

  updateCartList() {
    this.updateCartListToServer();
  }

  addCart(good: GoodInfo, count: number = 1) {
    //check
    if(good.owner===this.sessionService.getAccountId()){
      this.msgService.addMsg(new Message("warning", "Cannot add own item to cart."));
      return;
    }

    if (!count) {
      this.msgService.addMsg(new Message("warning", "Cannot add 0 item to the cart."));
      return;
    }

    if (good.stock < 1) {
      this.msgService.addMsg(new Message("warning", "Sold out. Cannot add to cart."));
      return;
    }

    let findFlag, skipFlag = false;
    this.cartList.forEach(e => {
      if (e.id === good.id) {
        if (e.count <= e.stock - count) {
          e.count += count;
        } else {
          this.msgService.addMsg(new Message("warning", "Over stock. Cannot add to cart."));
          skipFlag = true;
        }
        findFlag = true;
        return;
      }
    });

    if (skipFlag) return;

    if (!findFlag) {
      let targetGood = good.clone();
      targetGood.count = count;
      this.cartList.push(targetGood);
    }

    if (Constants.debugMode) console.log("#add cart: " + good.id + " x" + count);
    //this.msgService.addMsg(new Message("primary", "Added " + good.name + " (x " + count + ") to Cart."));
    this.updateCartListToServer();
    return;
  }

  private selectCartListFromServer(): GoodInfo[] {
    this.sessionService.getSessionKey();
    if (Constants.debugMode) console.log("#load cartlist: " + this.sessionService.getAccountId());

    //TODO server connect
    return [];
  }

  private updateCartListToServer() {

    //TODO server connect use good.id
  }

  private selectCategoryListFromServer(): string[] {
    this.sessionService.getSessionKey();
    if (Constants.debugMode) console.log("#load categorylist");

    //TODO server connect
    return ['category1', 'category2', 'category3'];
  }

  private selectManufacturerListFromServer(): string[] {
    this.sessionService.getSessionKey();
    if (Constants.debugMode) console.log("#load manufacturerList");

    //TODO server connect
    return ['manufacturer4', 'manufacturer5', 'manufacturer6'];
  }

  queryGoods(filterRules): GoodInfo[] {
    if (filterRules) {
      //...
    }
    //TODO server connect

    return [new GoodInfo("100000", "Item1", "NO-1(tm)", ["test", "No1"], "first good, asdf asdfasdf asdf asdfasdf asdfasdfasdf asdfasdf asdfasdfasdf", 56800.66, 99, "Seller1", new Date(2020, 3, 23, 12, 6, 54)),
    new GoodInfo("100001", "Item2", "last company", ["test", "tool"], "2nd good, etcetcetcetcetcetcetcetcetce tcetcetcetcetcetcet cetcetcetcetcetcetcetcetcetc", 8993.50, 0, "Seller2", new Date(2020, 3, 23, 18, 6, 56)),
    new GoodInfo("100002", "Item3", "last company", ["tool", "food"], "3rd good", 999.75, 4, "Setsu", new Date(2020, 3, 25, 18, 6, 58))];
  }

  queryGood(goodId: string): GoodInfo {
    if (Constants.debugMode) console.log("#Query Item-" + goodId);
    //TODO server connect

    return new GoodInfo(goodId, "Item1", "NO-1(tm)",
      ["category2", "category3"], "first good, asdf asdfasdf asdf asdfasdf asdfasdfasdf asdfasdf asdfasdfasdf",
      56800.66, 99, "Seller1", new Date(2020, 3, 23, 12, 6, 54));
  }
}
import { Injectable } from '@angular/core';
import { FilterInfo } from 'src/app/bean/FilterInfo';
import { Message } from 'src/app/bean/message';
import { Constants } from 'src/app/constans/constans';
import { GoodInfo } from '../../bean/GoodInfo';
import { ConnectService } from '../connect/connect.service';
import { MessageService } from '../message/message.service';
import { SessionControllerService } from '../session/session-controller.service';
import { CartDataRequest } from './CartRequestData';

@Injectable({
  providedIn: 'root'
})
export class GoodManagementService {

  private cartList: GoodInfo[] = [];
  private categoryList: string[] = [];
  private manufacturerList: string[] = [];

  constructor(private sessionService: SessionControllerService, private msgService: MessageService, private connect: ConnectService) {
    this.selectCategoryListFromServer();
    this.selectManufacturerListFromServer();
  }

  getCartList(): GoodInfo[] {
    if (this.sessionService.isRole("BS")) {
      this.selectCartListFromServer();
    }
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
    if (good.owner === this.sessionService.getAccountId()) {
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
      let targetGood = new GoodInfo().init(good);
      targetGood.count = count;
      this.cartList.push(targetGood);
    }

    if (Constants.debugMode) console.log("#add cart: " + good.id + " x" + count);
    //this.msgService.addMsg(new Message("primary", "Added " + good.name + " (x " + count + ") to Cart."));
    this.updateCartListToServer();
    return;
  }

  public selectCartListFromServer() {
    if (Constants.debugMode) console.log("#load cartlist: " + this.sessionService.getAccountId());

    this.connect.fetchData('cart', "", "GET", null).then(data => {
      this.cartList.splice(0);
      data.forEach(element => {
        let item = new GoodInfo();
        item.init(element);
        this.cartList.push(item);
      });
    });
  }

  private updateCartListToServer() {
    let putCartList = [];
    this.cartList.forEach(element => {
      putCartList.push(new CartDataRequest(element.id, element.count));
    });

    this.connect.fetchData('cart', "", "PUT", putCartList);
  }

  private async selectCategoryListFromServer() {
    if (Constants.debugMode) console.log("#load categorylist");

    this.connect.fetchData('martquery', "/category", "GET", null).then(data => {
      if (data) {
        this.categoryList.splice(0);
        data.forEach(element => this.categoryList.push(element));
      }
    });
  }

  private async selectManufacturerListFromServer() {
    if (Constants.debugMode) console.log("#load manufacturerList");

    this.connect.fetchData('martquery', "/manufacturer", "GET", null).then(data => {
      if (data) {
        this.manufacturerList.splice(0);
        data.forEach(element => this.manufacturerList.push(element));
      }
    });
  }

  async queryGoods(filterRules: FilterInfo): Promise<GoodInfo[]> {
    if (Constants.debugMode) console.log("#Query Item List: " + filterRules);


    return this.connect.fetchData('martquery', "/list", "GET", filterRules);
  }

  async queryGood(goodId: string): Promise<GoodInfo> {
    if (Constants.debugMode) console.log("#Query Item Detail - " + goodId);

    return this.connect.fetchData('martquery', "/detail", "GET", { 'gid': goodId });
  }
}
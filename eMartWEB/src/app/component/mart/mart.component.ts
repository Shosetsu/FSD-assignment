import { Component, OnInit } from '@angular/core';
import { GoodManagementService } from '../../service/goods/good-management.service';
import { GoodInfo } from 'src/app/bean/GoodInfo';
import { SessionControllerService } from 'src/app/service/session/session-controller.service';
import { CustomerInfo } from 'src/app/bean/CustomerInfo';
import { Router } from '@angular/router';
import { Message } from 'src/app/bean/message';
import { MessageService } from 'src/app/service/message/message.service';
import { OrderManagementService } from 'src/app/service/order/order-management.service';
import { OrderDetail } from 'src/app/bean/OrderDetail';

@Component({
  selector: 'app-mart',
  templateUrl: './mart.component.html',
  styleUrls: ['./mart.component.css']
})
export class MartComponent implements OnInit {

  searchKey: string;

  goodList: GoodInfo[];

  loginInfo: CustomerInfo;

  categoryList: string[];
  manufacturerList: string[];

  filterDisplay: boolean;
  filter_category: string = "";
  filter_price_from: number = null;
  filter_price_to: number = null;
  filter_manufacturer: string = "";

  detailOpenFlag: boolean;

  messageList: Message[];

  constructor(
    private sessionControllerService: SessionControllerService,
    private router: Router,
    private goodManagementService: GoodManagementService,
    private msgService: MessageService,
    private orderService: OrderManagementService) {
    this.messageList = this.msgService.getMessageList();
  }

  ngOnInit() {
    this.goodList = this.goodManagementService.queryGoods(null);
    this.categoryList = this.goodManagementService.getCategoryList();
    this.manufacturerList = this.goodManagementService.getManufacturerList();
  }

  openDetail(good: GoodInfo) {
    this.router.navigateByUrl('/mart/detail/' + good.id);
  }

  addCart(good: GoodInfo) {
    this.goodManagementService.addCart(good);
  }

  purchase(good: GoodInfo) {
    if (this.orderService.addPurchase(good, this.sessionControllerService.getAccountId())) {
      this.router.navigate(['purchase']);
    }
  }

  filter() {

  }

  isRole(role: string) {
    return this.sessionControllerService.isRole(role);
  }
}

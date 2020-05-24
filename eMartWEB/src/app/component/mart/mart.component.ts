import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerInfo } from 'src/app/bean/CustomerInfo';
import { FilterInfo } from 'src/app/bean/FilterInfo';
import { GoodInfo } from 'src/app/bean/GoodInfo';
import { Message } from 'src/app/bean/message';
import { MessageService } from 'src/app/service/message/message.service';
import { OrderManagementService } from 'src/app/service/order/order-management.service';
import { SessionControllerService } from 'src/app/service/session/session-controller.service';
import { GoodManagementService } from '../../service/goods/good-management.service';

@Component({
  selector: 'app-mart',
  templateUrl: './mart.component.html',
  styleUrls: ['./mart.component.css']
})
export class MartComponent implements OnInit {

  goodList: GoodInfo[];

  loginInfo: CustomerInfo;

  categoryList: string[];
  manufacturerList: string[];

  searchKey: string;

  filterDisplay: boolean;
  filterCategory: string = "";
  filterManufacturer: string = "";
  filterPriceFrom: number = null;
  filterPriceTo: number = null;

  detailOpenFlag: boolean;

  messageList: Message[];

  constructor(
    private sessionControllerService: SessionControllerService,
    private router: Router,
    private route: ActivatedRoute,
    private goodManagementService: GoodManagementService,
    private msgService: MessageService,
    private orderService: OrderManagementService) {
    this.messageList = this.msgService.getMessageList();
    this.route.queryParams.subscribe(param => {
      this.searchKey = param['k'] ? param['k'] : "";
      this.filterCategory = param['c'] ? param['c'] : "";
      this.filterManufacturer = param['m'] ? param['m'] : "";
      this.filterPriceFrom = param['pf'] ? param['pf'] : null;
      this.filterPriceTo = param['pt'] ? param['pt'] : null;

      let filter = new FilterInfo(0, this.searchKey, this.filterCategory, this.filterManufacturer, this.filterPriceFrom, this.filterPriceTo);
      this.goodManagementService.queryGoods(filter).then(data => {
        this.goodList = data;
      });
    });
  }

  ngOnInit() {
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
    let queryParamsMap = {};
    if (this.searchKey) queryParamsMap['k'] = this.searchKey;
    if (this.filterCategory) queryParamsMap['c'] = this.filterCategory;
    if (this.filterManufacturer) queryParamsMap['m'] = this.filterManufacturer;
    if (this.filterPriceFrom) queryParamsMap['pf'] = this.filterPriceFrom;
    if (this.filterPriceTo) queryParamsMap['pt'] = this.filterPriceTo;

    this.router.navigate(['mart'], { 'queryParams': queryParamsMap });
  }

  isRole(role: string) {
    return this.sessionControllerService.isRole(role);
  }
}

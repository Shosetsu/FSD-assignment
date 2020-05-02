import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderDetail } from 'src/app/bean/OrderDetail';
import { OrderManagementService } from 'src/app/service/order/order-management.service';
import { SessionControllerService } from 'src/app/service/session/session-controller.service';

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.css']
})
export class OrderDetailComponent {

  orderDetailList: OrderDetail[];
  accountId: string;

  purchaseFlag: boolean;


  constructor(
    private orderService: OrderManagementService,
    private location: Location,
    private session: SessionControllerService,
    private route: ActivatedRoute,
    private router: Router) {

    this.accountId = session.getAccountId();

    if (route.routeConfig.path === 'purchase') {
      this.orderDetailList = orderService.getPurchaseList();
      if (this.orderDetailList.length == 0) {
        router.navigate(["404"]);
      }
      this.purchaseFlag = true;

    } else {
      route.params.subscribe(para => {
        this.orderDetailList = [orderService.getOrderDetail(session.getAccountId(), session.getSessionKey(), para['oid'])];
      });
    }
  }

  back() {
    if (this.purchaseFlag) {
      this.orderService.clearPurchaseList();
    }
    this.location.back();
  }

  purchase(){    
    this.orderService.purchase();
  }
}

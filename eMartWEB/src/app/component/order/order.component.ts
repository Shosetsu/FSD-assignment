import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OrderInfo } from 'src/app/bean/OrderInfo';
import { OrderManagementService } from 'src/app/service/order/order-management.service';
import { SessionControllerService } from 'src/app/service/session/session-controller.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent  {

  orderList: OrderInfo[];
  id: string;

  constructor(
    private orderService: OrderManagementService,
    private location: Location,
    private session: SessionControllerService,
    private router: Router) {
    this.orderList = orderService.getOrderList();
    this.id = session.getAccountId();
  }

  openDetail(od: OrderInfo) {
    this.router.navigate(['/order/detail/' + od.orderId]);
  }

  back() {
    this.location.back();
  }
}

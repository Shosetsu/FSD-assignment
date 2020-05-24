import { Component, OnInit } from '@angular/core';
import { GoodInfo } from 'src/app/bean/GoodInfo';
import { GoodManagementService } from 'src/app/service/goods/good-management.service';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { OrderManagementService } from 'src/app/service/order/order-management.service';
import { SessionControllerService } from 'src/app/service/session/session-controller.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cartList: GoodInfo[];

  constructor(
    private goodSerivce: GoodManagementService,
    private orderService: OrderManagementService,
    private sessionService: SessionControllerService,
    private router: Router,
    private location: Location) { }

  ngOnInit() {
    this.cartList = this.goodSerivce.getCartList();
  }

  checkNumber(good: GoodInfo) {
    if (good.count > good.stock) { good.count--; return; }

    if (good.count < 1) this.remove(good);
    this.goodSerivce.updateCartList();
  }

  getAllPrice(): number {
    let all = 0;
    this.cartList.forEach(e => {
      all += e.price * e.count;
    });
    return all;
  }

  openDetail(good: GoodInfo) {
    this.router.navigateByUrl('mart/detail/' + good.id);
  }

  remove(good: GoodInfo) {
    this.cartList.splice(this.cartList.indexOf(good), 1);
    this.goodSerivce.updateCartList();
  }

  back() {
    this.location.back();
  }

  purchase() {
    let successFlag = 1;

    this.cartList.forEach(good => {
      if (successFlag) {
        successFlag = this.orderService.addPurchase(good, this.sessionService.getAccountId(), good.count);
      }
    });

    if (successFlag) {
      this.router.navigate(['purchase']);
      this.goodSerivce.purchaseLock = true;
    } else {
      this.orderService.clearPurchaseList();
    }
  }
}

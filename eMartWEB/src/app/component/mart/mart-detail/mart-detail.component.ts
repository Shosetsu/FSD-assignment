import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GoodInfo } from 'src/app/bean/GoodInfo';
import { GoodManagementService } from 'src/app/service/goods/good-management.service';
import { MessageService } from 'src/app/service/message/message.service';
import { OrderManagementService } from 'src/app/service/order/order-management.service';
import { SellerManagementService } from 'src/app/service/seller/seller-management.service';
import { SessionControllerService } from 'src/app/service/session/session-controller.service';

@Component({
  selector: 'app-mart-detail',
  templateUrl: './mart-detail.component.html',
  styleUrls: ['./mart-detail.component.css']
})

export class MartDetailComponent {

  count: number = 1;

  editFlag: boolean = false;


  goodInfo: GoodInfo;
  wTop: number;
  wLeft: number;

  constructor(
    private sessionSerivce: SessionControllerService,
    private goodService: GoodManagementService,
    private sellerService: SellerManagementService,
    private location: Location,
    private route: ActivatedRoute,
    private router: Router,
    private orderService: OrderManagementService,
    private msgService: MessageService) {
    route.params.subscribe(para => {
      this.goodService.queryGood(para['gid']).then(data => this.goodInfo = data);
    });
  }

  public isRole(role: string): boolean {
    return this.sessionSerivce.isRole(role);
  }

  public isOwner(): boolean {
    return this.goodInfo.owner === this.sessionSerivce.getAccountId();
  }

  addCart() {
    this.goodService.addCart(this.goodInfo, this.count);
  }

  buy() {
    if (this.orderService.addPurchase(this.goodInfo, this.sessionSerivce.getAccountId(), this.count)) {
      this.router.navigate(['purchase']);
    }
  }

  edit() {
    this.router.navigate(['/seller/edit/' + this.goodInfo.id]);
  }

  changeBlockStatus() {
    if (this.goodInfo.status === 0) {
      this.goodInfo.status = 1;
    } else if (this.goodInfo.status === 1) {
      this.goodInfo.status = 0;
    }
    this.sellerService.setStatus(this.goodInfo.id, this.goodInfo.status);
  }

  changeArchiveStatus() {
    if (this.goodInfo.status === 0) {
      this.goodInfo.status = 2;
    } else if (this.goodInfo.status === 2) {
      this.goodInfo.status = 0;
    }
    this.sellerService.setStatus(this.goodInfo.id, this.goodInfo.status);
  }

  sendDM() {
    this.router.navigate(['/message'], {
      queryParams: {
        s: this.goodInfo.owner
      }
    });
  }

  redirectLogin() {
    let loginBtn: HTMLLinkElement = document.querySelector('#login-btn');
    loginBtn.click();
  }

  close() {
    this.location.back();
  }
}

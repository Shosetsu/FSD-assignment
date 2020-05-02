import { Component, OnInit } from '@angular/core';
import { GoodInfo } from 'src/app/bean/GoodInfo';
import { SessionControllerService } from 'src/app/service/session/session-controller.service';
import { GoodManagementService } from 'src/app/service/goods/good-management.service';
import { Location } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderManagementService } from 'src/app/service/order/order-management.service';
import { MessageService } from 'src/app/service/message/message.service';
import { Message } from 'src/app/bean/message';

@Component({
  selector: 'app-mart-detail',
  templateUrl: './mart-detail.component.html',
  styleUrls: ['./mart-detail.component.css']
})

export class MartDetailComponent implements OnInit {

  count: number = 1;

  editFlag: boolean = false;


  goodInfo: GoodInfo;
  wTop: number;
  wLeft: number;

  constructor(
    private sessionSerivce: SessionControllerService,
    private goodSerivce: GoodManagementService,
    private location: Location,
    private route: ActivatedRoute,
    private router: Router,
    private orderService: OrderManagementService,
    private msgService:MessageService) {
    route.params.subscribe(para => {
      this.goodInfo = this.goodSerivce.queryGood(para['id']);
    });
  }

  ngOnInit() {
    setTimeout(() => {
      this.setModalAxis(true);
    }, 500);
  }

  setModalAxis(once: boolean = false) {
    let modal: HTMLElement = document.querySelector(".modal.good-detail");
    let axis = modal.getBoundingClientRect();
    modal.style.top = (window.innerHeight - axis.height) / 2 - 50 + "px";
    modal.style.left = (window.innerWidth - axis.width) / 2 + "px";

    if ((window.innerHeight - axis.height) / 2 - 50 < 20) {
      modal.style.top = "20px";
      modal.classList.add('scroll-bar');
    } else {
      modal.classList.remove('scroll-bar');
    }

    if (once) {
      setTimeout(() => {
        modal.classList.add('animation');
        modal.style.opacity = "1";
      }, 100);
      setTimeout(() => {
        modal.classList.remove('animation');
      }, 500);
    }
  }

  public isRole(role: string): boolean {
    return this.sessionSerivce.isRole(role);
  }

  public isOwner(): boolean {
    return this.goodInfo.owner === this.sessionSerivce.getAccountId();
  }

  addCart() {
    if(!this.count){
      this.msgService.addMsg(new Message("warning", "Cannot add 0 items to the cart."));
      return;
    }
    this.goodSerivce.addCart(this.goodInfo, this.count);
  }

  buy() {
    if(!this.count){
      this.msgService.addMsg(new Message("warning", "Cannot purchase 0 items."));
      return;
    }
    if (this.orderService.addPurchase(this.goodInfo, this.sessionSerivce.getAccountId(), this.count)) {
      this.router.navigate(['purchase']);
    }
  }

  changeBlockStatus() {
    this.goodSerivce.setBlockStatus(this.sessionSerivce.getSessionKey(), this.goodInfo.id, !this.goodInfo.blockFlag);
    this.goodInfo.blockFlag = !this.goodInfo.blockFlag;
  }
  
  sendDM() {
    this.router.navigate(['/message'], {
      queryParams: {
        s: this.goodInfo.owner
      }
    });
  }

  close() {
    this.location.back();
  }
}

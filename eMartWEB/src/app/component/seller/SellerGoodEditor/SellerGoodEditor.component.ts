import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { GoodInfo } from 'src/app/bean/GoodInfo';
import { GoodManagementService } from 'src/app/service/goods/good-management.service';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { SellerManagementService } from 'src/app/service/seller/seller-management.service';
import { SessionControllerService } from 'src/app/service/session/session-controller.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-SellerGoodEditor',
  templateUrl: './SellerGoodEditor.component.html',
  styleUrls: ['./SellerGoodEditor.component.css']
})
export class SellerGoodEditorComponent {

  goodInfo: GoodInfo;
  oldInfo: GoodInfo;
  categoryList: string[];

  updateFlag: boolean;

  constructor(
    private location: Location,
    private goodService: GoodManagementService,
    private sanitizer: DomSanitizer,
    private route: ActivatedRoute,
    private sellerService: SellerManagementService,
    private session: SessionControllerService
  ) {
    this.categoryList = goodService.getCategoryList();
    route.params.subscribe(pram => {
      let gid = pram['gid'];
      if (gid) {
        this.oldInfo = goodService.queryGood(gid);
        this.goodInfo = this.oldInfo.clone();
        this.updateFlag = true;
      } else {
        this.goodInfo = new GoodInfo();
      }
    });
  }

  loadImg(input: HTMLInputElement): SafeUrl {
    return (input.files && input.files[0]) ? this.sanitizer.bypassSecurityTrustUrl(URL.createObjectURL(input.files[0])) : this.updateFlag ? "/assets/good/" + this.goodInfo.id + ".jpg" : '';
  }


  ok(goodEditorForm: NgForm) {
    if (this.goodInfo.equals(this.oldInfo)) {
      this.close();
      return;
    }

    if (this.sellerService.updateSalesItemInformation(this.session.getAccountId(), this.session.getSessionKey(), this.goodInfo, this.updateFlag ? 0 : 1)) {
      this.close();
    }
  }

  close() {
    this.location.back();
  }
}

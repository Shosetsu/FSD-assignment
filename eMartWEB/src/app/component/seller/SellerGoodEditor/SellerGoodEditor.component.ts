import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { GoodInfo } from 'src/app/bean/GoodInfo';
import { GoodManagementService } from 'src/app/service/goods/good-management.service';
import { SellerManagementService } from 'src/app/service/seller/seller-management.service';
import { SessionControllerService } from 'src/app/service/session/session-controller.service';

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
    this.goodInfo = new GoodInfo();

    if (route.routeConfig.path === "edit/:gid") {
      route.params.subscribe(pram => {
        let gid = pram['gid'];
        goodService.queryGood(gid).then(data => {
          if (data) {
            this.oldInfo = data;
            this.goodInfo.init(this.oldInfo);
            this.updateFlag = true;

            //check owner
            if (this.goodInfo.owner && this.goodInfo.owner !== session.getAccountId()) {
              location.back();
            }
          } else {
            location.back();
          }
        });
      });
    }
  }

  loadImg(input: HTMLInputElement): SafeUrl {
    return (input.files && input.files[0]) ? this.sanitizer.bypassSecurityTrustUrl(URL.createObjectURL(input.files[0])) : this.updateFlag ? "/assets/good/" + this.goodInfo.id + ".jpg" : '';
  }


  ok(goodEditorForm: NgForm) {
    if (this.updateFlag && this.goodInfo.equals(this.oldInfo)) {
      this.close();
      return;
    }

    this.sellerService.updateSalesItemInformation(this.goodInfo, this.updateFlag ? 0 : 1).then(data => {
      if (data) {
        this.close();
      }
    });
  }

  close() {
    this.location.back();
  }
}

import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GoodInfo } from 'src/app/bean/GoodInfo';
import { AccountManagementService } from 'src/app/service/account/account-management.service';
import { SellerManagementService } from 'src/app/service/seller/seller-management.service';
import { SessionControllerService } from 'src/app/service/session/session-controller.service';

@Component({
  selector: 'app-seller',
  templateUrl: './seller.component.html',
  styleUrls: ['./seller.component.css']
})
export class SellerComponent {

  salesList: GoodInfo[];
  id: string;

  filterMonth: string;
  filterMonthList: Date[] = [];

  soldCount: number;
  salesAmount: number;

  constructor(
    private session: SessionControllerService,
    private accountService: AccountManagementService,
    private sellerService: SellerManagementService,
    private location: Location,
    private route: ActivatedRoute,
    private router: Router) {

    this.id = session.getAccountId();
    route.params.subscribe(para => {
      if (para['sid']) {
        this.id = para['sid'];
      }
    });

    if (!sellerService.isSeller(this.id, session.getSessionKey())) {
      router.navigateByUrl('404');
      return;
    }

    this.salesList = sellerService.getSalesList(this.id, session.getSessionKey());

    let baseDate: Date = accountService.getSellerCreateDate(this.id, session.getSessionKey());
    let nowDate: Date = new Date();

    while (baseDate <= nowDate) {
      this.filterMonthList.push(new Date(baseDate));
      baseDate.setMonth(baseDate.getMonth() + 1);
    }
    this.filterMonth = this.formatDate(this.filterMonthList[this.filterMonthList.length - 1], "yyyyMM");
    this.refreshData();
  }

  refreshData() {
    let result = this.accountService.getSellerOverviewInDate(this.session.getAccountId(), this.session.getSessionKey(), this.filterMonth);
    this.soldCount = result['count'];
    this.salesAmount = result['amount'];
  }

  downloadSales() {
    this.sellerService.downloadSalesReport();
  }

  downloadTax() {
    this.sellerService.downloadTaxReport();
  }

  linkToDetail(item: GoodInfo) {
    this.router.navigate(['seller/detail/' + item.id]);
  }

  createNewItem() {
    this.router.navigate(['seller/new']);
  }

  changeBlockStatus(item: GoodInfo) {
    if (item.status === 0) {
      item.status = 1;
    } else if (item.status === 1) {
      item.status = 0;
    }
    this.sellerService.setStatus(this.session.getSessionKey(), item.id, item.status);
  }

  changeArchiveStatus(item: GoodInfo) {
    if (item.status === 0) {
      item.status = 2;
    } else if (item.status === 2) {
      item.status = 0;
    }
    this.sellerService.setStatus(this.session.getSessionKey(), item.id, item.status);
  }

  edit(item: GoodInfo) {
    this.router.navigate(['seller/edit/' + item.id]);
  }

  back() {
    this.location.back();
  }

  isRole(role: string): boolean {
    return this.session.isRole(role);
  }

  isOwner(): boolean {
    return this.id === this.session.getAccountId();
  }

  private formatDate(date: Date, format: string) {
    format = format.replace(/yyyy/g, date.getFullYear().toString());
    format = format.replace(/MM/g, ('0' + (date.getMonth() + 1)).slice(-2));
    format = format.replace(/dd/g, ('0' + date.getDate()).slice(-2));
    format = format.replace(/HH/g, ('0' + date.getHours()).slice(-2));
    format = format.replace(/mm/g, ('0' + date.getMinutes()).slice(-2));
    format = format.replace(/ss/g, ('0' + date.getSeconds()).slice(-2));
    format = format.replace(/SSS/g, ('00' + date.getMilliseconds()).slice(-3));
    format = format.replace(/M/g, (date.getMonth() + 1).toString());
    format = format.replace(/d/g, (date.getDate()).toString());
    format = format.replace(/H/g, (date.getHours()).toString());
    format = format.replace(/m/g, (date.getMinutes()).toString());
    format = format.replace(/s/g, (date.getSeconds()).toString());
    format = format.replace(/S/g, (date.getMilliseconds()).toString());
    return format;
  };
}

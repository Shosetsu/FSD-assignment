import { NgModule } from '@angular/core';
import { AccountTypeFormatPipe } from './pipe/accountTypeFormat.pipe';
import { BankInfoFormatPipe } from './pipe/bankInfoFormat.pipe';
import { DetailFormatPipe } from './pipe/detailFormat.pipe';
import { EmptyFormatPipe } from './pipe/emptyFormat.pipe';
import { PriceFormatPipe } from './pipe/priceFormat.pipe';
import { StockAmountPipe } from './pipe/stockAmount.pipe';
import { MessageheaderComponent } from './component/messageheader/messageheader.component';
import { CommonModule } from '@angular/common';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [
    MessageheaderComponent,
    AccountTypeFormatPipe,
    BankInfoFormatPipe,
    DetailFormatPipe,
    EmptyFormatPipe,
    PriceFormatPipe,
    StockAmountPipe
  ],
  exports: [
    MessageheaderComponent,
    AccountTypeFormatPipe,
    BankInfoFormatPipe,
    DetailFormatPipe,
    EmptyFormatPipe,
    PriceFormatPipe,
    StockAmountPipe
  ]
})
export class CommonHeaderModule { }

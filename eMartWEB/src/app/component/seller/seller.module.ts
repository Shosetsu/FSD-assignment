import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { CommonHeaderModule } from 'src/app/common-header.module';
import { SellerComponent } from './seller.component';
import { SellerRoutes } from './seller.routing';

@NgModule({
  imports: [
    CommonModule,
    CommonHeaderModule,
    SellerRoutes
  ],
  declarations: [SellerComponent]
})
export class SellerModule { }

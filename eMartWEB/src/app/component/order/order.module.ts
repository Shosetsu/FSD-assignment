import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { CommonHeaderModule } from 'src/app/common-header.module';
import { OrderDetailComponent } from './order-detail/order-detail.component';
import { OrderComponent } from './order.component';
import { OrderRoutes } from './order.routing';

@NgModule({
  imports: [
    CommonModule,
    CommonHeaderModule,
    OrderRoutes
  ],
  declarations: [
    OrderComponent,
    OrderDetailComponent
  ]
})
export class OrderModule { }

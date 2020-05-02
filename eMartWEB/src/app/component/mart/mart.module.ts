import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { CommonHeaderModule } from 'src/app/common-header.module';
import { CartComponent } from './cart/cart.component';
import { MartDetailComponent } from './mart-detail/mart-detail.component';
import { MartComponent } from './mart.component';
import { MartRoutesModule } from './mart.routing';

@NgModule({
  imports: [
    CommonModule,
    CommonHeaderModule,
    BrowserModule,
    FormsModule,
    MartRoutesModule
  ],
  declarations: [
    MartComponent,
    MartDetailComponent,
    CartComponent
  ]
})
export class MartModule { }

import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { CommonHeaderModule } from 'src/app/common-header.module';
import { SellerComponent } from './seller.component';
import { SellerRoutes } from './seller.routing';
import { FormsModule } from '@angular/forms';
import { SellerGoodEditorComponent } from './SellerGoodEditor/SellerGoodEditor.component';

@NgModule({
  imports: [
    CommonModule,
    CommonHeaderModule,
    FormsModule,
    SellerRoutes
  ],
  declarations: [
    SellerComponent,
    SellerGoodEditorComponent
  ]
})
export class SellerModule { }

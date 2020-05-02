import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AccountComponent } from './component/account/account.component';
import { SellerComponent } from './component/seller/seller.component';
import { OrderComponent } from './component/order/order.component';
import { MessageComponent } from './component/message/message.component';


const routes: Routes = [
  { path: '', redirectTo: 'mart', pathMatch: 'full' },
  { path: 'seller', component: SellerComponent },
  { path: 'message', component: MessageComponent },
  { path: '**', redirectTo: 'mart', pathMatch: 'full' }];

@NgModule({
  imports: [RouterModule.forRoot(
    routes,
    { enableTracing: false }
  )],
  exports: [RouterModule]
})
export class AppRoutingModule { }

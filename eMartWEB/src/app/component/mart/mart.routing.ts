import { RouterModule, Routes } from '@angular/router';
import { MessageheaderComponent } from '../messageheader/messageheader.component';
import { CartComponent } from './cart/cart.component';
import { MartDetailComponent } from './mart-detail/mart-detail.component';
import { MartComponent } from './mart.component';
import { AuthGuard } from 'src/app/auth/auth.guard';
import { SellerGoodEditorComponent } from '../seller/SellerGoodEditor/SellerGoodEditor.component';
import { SellerCenterGuard } from 'src/app/auth/seller-center.guard';

const routes: Routes = [
  {
    path: 'mart', component: MartComponent, children: [
      { path: 'detail/:gid', component: MartDetailComponent },
      { path: '', component: MessageheaderComponent }
    ]
  },
  { path: 'cart', component: CartComponent, canActivate: [AuthGuard] }
];

export const MartRoutesModule = RouterModule.forChild(routes);

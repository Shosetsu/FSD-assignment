import { Routes, RouterModule } from '@angular/router';
import { SellerComponent } from './seller.component';
import { AuthGuard } from 'src/app/auth/auth.guard';
import { SellerCenterGuard } from 'src/app/auth/seller-center.guard';
import { SellerGoodEditorComponent } from './SellerGoodEditor/SellerGoodEditor.component';
import { MessageheaderComponent } from '../messageheader/messageheader.component';
import { MartDetailComponent } from '../mart/mart-detail/mart-detail.component';


const routes: Routes = [
  {
    path: 'seller', component: SellerComponent, canActivate: [AuthGuard], children: [
      { path: 'detail/:gid', component: MartDetailComponent },
      { path: 'edit', redirectTo: 'seller', pathMatch: 'full' },
      { path: 'edit/:gid', component: SellerGoodEditorComponent, canActivate: [AuthGuard] },
      { path: 'new', component: SellerGoodEditorComponent, canActivate: [AuthGuard] },
      { path: '**', component: MessageheaderComponent, pathMatch: 'full' }
    ]
  },
  { path: 'seller/:sid', component: SellerComponent, canActivate: [AuthGuard, SellerCenterGuard], loadChildren: "seller" },
];


export const SellerRoutes = RouterModule.forChild(routes);

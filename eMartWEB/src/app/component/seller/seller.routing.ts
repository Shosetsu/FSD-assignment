import { Routes, RouterModule } from '@angular/router';
import { SellerComponent } from './seller.component';
import { AuthGuard } from 'src/app/auth/auth.guard';

const routes: Routes = [
  { path: 'seller', component: SellerComponent, canActivate: [AuthGuard] },
];

export const SellerRoutes = RouterModule.forChild(routes);

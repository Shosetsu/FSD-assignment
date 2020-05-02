import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from 'src/app/auth/auth.guard';
import { OrderDetailComponent } from './order-detail/order-detail.component';
import { OrderComponent } from './order.component';

const routes: Routes = [
  { path: 'order', component: OrderComponent, canActivate: [AuthGuard] },
  { path: 'order/detail/:oid', component: OrderDetailComponent, canActivate: [AuthGuard] },
  { path: 'purchase', component: OrderDetailComponent, canActivate: [AuthGuard] },
];

export const OrderRoutes = RouterModule.forChild(routes);

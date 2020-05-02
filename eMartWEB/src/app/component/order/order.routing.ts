import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from 'src/app/auth/auth.guard';
import { OrderDetailComponent } from './order-detail/order-detail.component';
import { OrderComponent } from './order.component';
import { MartDetailComponent } from '../mart/mart-detail/mart-detail.component';
import { MessageheaderComponent } from '../messageheader/messageheader.component';

const routes: Routes = [
  { path: 'order', component: OrderComponent, canActivate: [AuthGuard] },
  {
    path: 'order/detail/:oid', component: OrderDetailComponent, canActivate: [AuthGuard], children: [
      { path: ':gid', component: MartDetailComponent },
      { path: '**', component: MessageheaderComponent }
    ]
  },
  { path: 'purchase', component: OrderDetailComponent, canActivate: [AuthGuard] },
];

export const OrderRoutes = RouterModule.forChild(routes);

import { Routes, RouterModule } from '@angular/router';
import { OrderComponent } from './order.component';
import { OrderDetailComponent } from './order-detail/order-detail.component';

const routes: Routes = [
  { path: 'order', component: OrderComponent },
  { path: 'order/detail/:oid', component: OrderDetailComponent },
  { path: 'purchase', component: OrderDetailComponent },
];

export const OrderRoutes = RouterModule.forChild(routes);

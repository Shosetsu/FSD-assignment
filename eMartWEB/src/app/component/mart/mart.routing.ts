import { RouterModule, Routes } from '@angular/router';
import { MessageheaderComponent } from '../messageheader/messageheader.component';
import { CartComponent } from './cart/cart.component';
import { MartDetailComponent } from './mart-detail/mart-detail.component';
import { MartComponent } from './mart.component';

const routes: Routes = [
  {
    path: 'mart', component: MartComponent, children: [
      { path: 'detail/:id', component: MartDetailComponent },
      { path: '**', component:MessageheaderComponent }
    ]
  },
  { path: 'cart', component: CartComponent }
];

export const MartRoutesModule = RouterModule.forChild(routes);

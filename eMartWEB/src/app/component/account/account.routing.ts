import { Routes, RouterModule } from '@angular/router';
import { AccountComponent } from './account.component';
import { AccountDeleteComponent } from './account-delete/account-delete.component';
import { AccountDetailComponent } from './account-detail/account-detail.component';
import { AccountListComponent } from './account-list/account-list.component';
import { BlockListComponent } from './block-list/block-list.component';

const routes: Routes = [
  {
    path: 'account', component: AccountComponent, children: [
      { path: 'detail/:id', component: AccountDetailComponent },
      { path: 'detail', component: AccountDetailComponent },
      { path: 'delete', component: AccountDeleteComponent },
      { path: 'list', component: AccountListComponent },
      { path: 'block', component: BlockListComponent },
      { path: '', redirectTo: 'detail', pathMatch: 'full' },
    ]
  }
];

export const AccountRoutes = RouterModule.forChild(routes);

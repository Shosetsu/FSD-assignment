import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from 'src/app/auth/auth.guard';
import { AccountOwnerGuard } from 'src/app/auth/account-owner.guard';
import { AccountDeleteComponent } from './account-delete/account-delete.component';
import { AccountDetailComponent } from './account-detail/account-detail.component';
import { AccountListComponent } from './account-list/account-list.component';
import { AccountComponent } from './account.component';
import { BlockListComponent } from './block-list/block-list.component';

const routes: Routes = [
  {
    path: 'account', component: AccountComponent, canActivate: [AuthGuard], canActivateChild: [AuthGuard], children: [
      { path: 'detail/:uid', component: AccountDetailComponent, canActivate: [AccountOwnerGuard] },
      { path: 'detail', component: AccountDetailComponent },
      { path: 'delete', component: AccountDeleteComponent },
      { path: 'list', component: AccountListComponent },
      { path: 'block', component: BlockListComponent },
      { path: '', redirectTo: 'detail', pathMatch: 'full' },
    ]
  }
];

export const AccountRoutes = RouterModule.forChild(routes);

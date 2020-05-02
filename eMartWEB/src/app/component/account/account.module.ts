import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonHeaderModule } from 'src/app/common-header.module';
import { AccountDeleteComponent } from './account-delete/account-delete.component';
import { AccountDetailComponent } from './account-detail/account-detail.component';
import { AccountComponent } from './account.component';
import { AccountRoutes } from './account.routing';

@NgModule({
  imports: [
    CommonModule,
    CommonHeaderModule,
    FormsModule,
    AccountRoutes
  ],
  declarations: [
    AccountComponent,
    AccountDeleteComponent,
    AccountDetailComponent,
  ]
})
export class AccountModule { }

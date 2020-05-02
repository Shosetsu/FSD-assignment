import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth/auth.guard';
import { NotFoundComponent } from './component/common/not-found.component';
import { MessageComponent } from './component/message/message.component';
import { ForbiddenComponent } from './component/common/forbidden.component';
import { NotAuthGuard } from './auth/not-auth.guard';


const routes: Routes = [
  { path: '', redirectTo: 'mart', pathMatch: 'full' },
  { path: '404', component: NotFoundComponent },
  { path: '403', component: ForbiddenComponent, canActivate: [NotAuthGuard] },
  { path: 'message', component: MessageComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: '404', pathMatch: 'full' }];

@NgModule({
  imports: [RouterModule.forRoot(
    routes,
    { enableTracing: false }
  )],
  exports: [RouterModule]
})
export class AppRoutingModule { }

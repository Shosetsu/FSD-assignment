import { NgModule } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CommonHeaderModule } from './common-header.module';
import { AccountModule } from './component/account/account.module';
import { NotFoundComponent } from './component/common/not-found.component';
import { MartModule } from './component/mart/mart.module';
import { MessageComponent } from './component/message/message.component';
import { OrderModule } from './component/order/order.module';
import { SellerModule } from './component/seller/seller.module';
import { LoginComponent } from './component/userheader/login/login.component';
import { SignupComponent } from './component/userheader/signup/signup.component';
import { UserheaderComponent } from './component/userheader/userheader.component';
import { GoodManagementService } from './service/goods/good-management.service';
import { DirectMessageService } from './service/message/direct-message.service';
import { MessageService } from './service/message/message.service';
import { OrderManagementService } from './service/order/order-management.service';
import { SessionControllerService } from './service/session/session-controller.service';
import { ForbiddenComponent } from './component/common/forbidden.component';

@NgModule({
   declarations: [
      AppComponent,
      UserheaderComponent,
      MessageComponent,
      SignupComponent,
      LoginComponent,
      NotFoundComponent,
      ForbiddenComponent
   ],
   imports: [
      CommonHeaderModule,
      BrowserModule,
      FormsModule,
      MartModule,
      AccountModule,
      OrderModule,
      SellerModule,
      AppRoutingModule,
      NgbModule
   ],
   providers: [
      { provide: 'SessionControllerService', useClass: SessionControllerService },
      { provide: 'GoodManagementService', useClass: GoodManagementService },
      { provide: 'MessageService', useClass: MessageService },
      { provide: 'DirectMessageService', useClass: DirectMessageService },
      { provide: 'OrderManagementService', useClass: OrderManagementService }
   ],
   bootstrap: [
      AppComponent
   ]
})
export class AppModule { }

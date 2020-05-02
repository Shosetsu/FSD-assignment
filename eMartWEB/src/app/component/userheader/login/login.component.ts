import { Component, EventEmitter, OnDestroy, Output } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AccountManagementService } from 'src/app/service/account/account-management.service';
import { SessionControllerService } from 'src/app/service/session/session-controller.service';
import { SessionManagementService } from 'src/app/service/session/session-management.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnDestroy {

  @Output() loginUper = new EventEmitter();

  findAccountFlag: boolean = false;

  processed: boolean = false;

  userId: string;
  password: string;

  email: string;

  constructor(
    private accountManagementService: AccountManagementService,
    private sessionManagementService: SessionManagementService,
    private sessionControllerService: SessionControllerService,
    private router: Router) { }

  ngOnDestroy() {
    this.processed = false;
  }

  login(form: NgForm) {
    if (form.form.valid) {
      let response = this.sessionManagementService.login(this.userId, this.password);
      if (response == "success") {
        this.loginUper.emit(response);
        this.processed = true;

        setTimeout(() => {
          let shadow: HTMLDivElement = document.querySelector(".modal-backdrop");
          shadow.click();
        }, 500);

        let redirectUrl = this.sessionControllerService.getRedirectUrl();
        if (redirectUrl) {
          this.router.navigateByUrl(redirectUrl);
        }
      } else {
        //...
      }

      form.reset();
    }
  }

  findAccount(form: NgForm) {
    this.accountManagementService.findAccount(this.email);
    form.reset();
    this.processed = true;
  }

  close() {
    this.loginUper.emit(false);
  }
}

import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AccountManagementService } from 'src/app/service/account/account-management.service';
import { SessionControllerService } from 'src/app/service/session/session-controller.service';

@Component({
  selector: 'app-account-delete',
  templateUrl: './account-delete.component.html',
  styleUrls: ['./account-delete.component.css']
})
export class AccountDeleteComponent {
  id: string;
  password: string;

  submited: boolean;

  constructor(private sessionService: SessionControllerService, private accountService: AccountManagementService, private router: Router) {
    this.id = sessionService.getAccountId();
  }

  unregist(form: NgForm) {
    if (form.form.invalid) {
      this.submited = true;
      return;
    }

    this.accountService.unregist(this.password);
  }

}

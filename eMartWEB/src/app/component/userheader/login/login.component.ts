import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AccountManagementService } from 'src/app/service/account/account-management.service';
import { SessionManagementService } from 'src/app/service/session/session-management.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @Output() loginUper = new EventEmitter();

  findAccountFlag: boolean = false;

  processed: boolean = false;

  userId: string;
  password: string;

  email: string;



  constructor(
    private accountManagementService: AccountManagementService,
    private sessionManagementService: SessionManagementService) { }

  ngOnInit() {
  }

  login(form: NgForm) {
    if (form.form.valid) {
      let response = this.sessionManagementService.login(this.userId, this.password);
      if (response == "success") {
        this.loginUper.emit(response);
        this.processed = true;

        setTimeout('document.querySelector(".modal-backdrop").click()', 500);
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
    this.processed = false;
  }
}

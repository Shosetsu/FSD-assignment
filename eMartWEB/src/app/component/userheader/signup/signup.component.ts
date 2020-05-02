import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AccountManagementService } from "../../../service/account/account-management.service";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})

export class SignupComponent implements OnInit {

  @Output() closer = new EventEmitter();

  userId: string;
  password: string;
  rePassword: string;
  email: string;
  telNumber: string;

  asSeller: boolean = false;
  coName: string;
  postalAddr: string;
  GSTIN: string;
  bankDetail: string;

  processed: boolean;


  constructor(private accountManagementService: AccountManagementService) {

  }

  ngOnInit() {
  }

  onSubmit(form: NgForm) {
    let result = this.accountManagementService.regist({
      userId: this.userId,
      password: this.password,
      rePassword: this.rePassword,
      email: this.email,
      telNumber: this.telNumber,
      asSeller: this.asSeller,
      coName: this.coName,
      postalAddr: this.postalAddr,
      GSTIN: this.GSTIN,
      bankDetail: this.bankDetail
    });
    if (result == 0) {
      this.processed = true;
      setTimeout('document.querySelector(".modal-backdrop").click()', 500);
    }
  }

  public close() {
    this.closer.emit(false);
  }
}

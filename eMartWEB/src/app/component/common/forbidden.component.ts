import { Component } from '@angular/core';

@Component({
  selector: 'app-forbidden',
  templateUrl: './forbidden.component.html',
  styleUrls: ['./forbidden.component.css']
})
export class ForbiddenComponent {

  constructor() { }

  redirectLogin() {
    let loginBtn: HTMLLinkElement = document.querySelector('#login-btn');
    loginBtn.click();
  }

}

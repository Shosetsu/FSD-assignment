import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountManagementService } from 'src/app/service/account/account-management.service';
import { SessionControllerService } from 'src/app/service/session/session-controller.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent {


  constructor(private sessionController: SessionControllerService) { }

  isRole(role: string): boolean {
    return this.sessionController.isRole(role);
  }


}

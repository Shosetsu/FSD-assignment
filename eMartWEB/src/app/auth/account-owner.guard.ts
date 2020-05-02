import { Location } from '@angular/common';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { SessionControllerService } from '../service/session/session-controller.service';

@Injectable({
  providedIn: 'root'
})
export class AccountOwnerGuard implements CanActivate {
  constructor(
    private sessionService: SessionControllerService,
    private location: Location) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    if (this.sessionService.getAccountType() !== "M" && this.sessionService.getAccountId() !== next.params['uid']) {
      this.location.back();
      return false;
    }

    return true;
  }

}

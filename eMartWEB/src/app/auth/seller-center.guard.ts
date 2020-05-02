import { Location } from '@angular/common';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { Constants } from '../constans/constans';
import { SessionControllerService } from '../service/session/session-controller.service';

@Injectable({
  providedIn: 'root'
})
export class SellerCenterGuard implements CanActivate {
  constructor(
    private sessionService: SessionControllerService,
    private location: Location) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    if (this.sessionService.getAccountType() !== "M" && this.sessionService.getAccountId() !== next.params['sid']) {
      this.location.back();
      return false;
    }

    return true;
  }
}

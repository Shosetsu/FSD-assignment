import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { SessionControllerService } from '../service/session/session-controller.service';
import { Location } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class NotAuthGuard implements CanActivate {
  constructor(
    private sessionService: SessionControllerService,
    private location: Location) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    let accountType = this.sessionService.getAccountType();

    if ("BSM".indexOf(accountType) !== -1) {
      this.location.back();
      return false;
    }

    return true;
  }

}

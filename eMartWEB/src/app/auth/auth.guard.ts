import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router, CanActivateChild } from '@angular/router';
import { Observable } from 'rxjs';
import { SessionControllerService } from '../service/session/session-controller.service';
import { Constans } from '../constans/constans';
import { Location } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate, CanActivateChild {
  constructor(
    private sessionService: SessionControllerService,
    private router: Router,
    private location: Location) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    let accountType = this.sessionService.getAccountType();

    if ("BSM".indexOf(accountType) == -1) {
      this.sessionService.setRedirectUrl(state.url);
      this.router.navigate(['403']);
      return false;
    }
    let role = Constans.routeAuthLevel[next.routeConfig.path];
    console.log("#Auth Gurad Ready - " + next.routeConfig.path + " : " + role);

    if (role && role.indexOf(accountType) == -1) {
      this.location.back();
      return false;
    }

    return true;
  }

  canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    return this.canActivate(childRoute, state);
  }
}

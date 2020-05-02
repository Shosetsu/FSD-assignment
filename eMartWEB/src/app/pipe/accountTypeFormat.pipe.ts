import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'accountTypeFormat'
})
export class AccountTypeFormatPipe implements PipeTransform {

  transform(accountType: string, args?: any): any {
    return { "S": "Seller & Buyer", "B": "Buyer", "M": "Admin", "A": "Anonymous" }[accountType];
  }

}

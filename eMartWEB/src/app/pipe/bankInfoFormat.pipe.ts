import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'bankInfoFormat'
})
export class BankInfoFormatPipe implements PipeTransform {

  transform(value: any, args?: any): any {
    return args + " - " + value;
  }

}

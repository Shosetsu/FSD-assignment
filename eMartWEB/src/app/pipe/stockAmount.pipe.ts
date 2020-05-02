import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'stockAmount'
})
export class StockAmountPipe implements PipeTransform {

  transform(value: any, args?: any): any {
    if (value == 0) {
      return "Sold out!"
    }
    if (value < 5) {
      return "Stock: Last " + value + "!";
    }
    return "Stock: " + value;
  }

}

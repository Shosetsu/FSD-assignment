import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'priceFormat'
})

export class PriceFormatPipe implements PipeTransform {

  transform(value: number, args?: any): any {

    return "$" + value.toFixed(2).toString().replace(/(?=\d*\.{1}\d*)(\d)(?=(\d{3})+(?!\d))/g, "$1,");
  }

}

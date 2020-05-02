import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'detailFormat'
})
export class DetailFormatPipe implements PipeTransform {

  transform(value: any, args?: any): any {
    return value.length > 60 ? value.substring(0, 57) + "..." : value;
  }

}

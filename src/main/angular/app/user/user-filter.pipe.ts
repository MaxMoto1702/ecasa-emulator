import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
    name: 'userFilter'
})
export class UserFilterPipe implements PipeTransform {

    transform(items: any[], filter: string): any {
        if (!items || !filter) {
            return items;
        }
        return items.filter(item => item.username.toUpperCase().indexOf(filter.toUpperCase()) !== -1 ||
            item.name.toUpperCase().indexOf(filter.toUpperCase()) !== -1 ||
            item.firstName.toUpperCase().indexOf(filter.toUpperCase()) !== -1 ||
            item.middleName.toUpperCase().indexOf(filter.toUpperCase()) !== -1 ||
            item.lastName.toUpperCase().indexOf(filter.toUpperCase()) !== -1 ||
            item.email.toUpperCase().indexOf(filter.toUpperCase()) !== -1 ||
            item.description.toUpperCase().indexOf(filter.toUpperCase()) !== -1);
    }

}

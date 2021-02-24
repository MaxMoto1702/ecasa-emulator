import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
    name: 'roleFilter'
})
export class RoleFilterPipe implements PipeTransform {

    transform(items: any[], filter: string): any {
        if (!items || !filter) {
            return items;
        }
        return items.filter(item => item.name.toUpperCase().indexOf(filter.toUpperCase()) !== -1 ||
            item.displayName.toUpperCase().indexOf(filter.toUpperCase()) !== -1);
    }

}

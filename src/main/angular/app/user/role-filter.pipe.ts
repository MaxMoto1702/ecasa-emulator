import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
    name: 'roleFilter'
})
export class RoleFilterPipe implements PipeTransform {

    transform(items: any[], filter: string): any {
        if (!items || !filter) {
            return items;
        }
        // filter items array, items which match and return true will be
        // kept, false will be filtered out
        return items.filter(item => item.name.indexOf(filter) !== -1 ||
            item.displayName.indexOf(filter) !== -1 ||
            item.description.indexOf(filter) !== -1);
    }

}

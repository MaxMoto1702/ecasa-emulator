import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RoleRoutingModule } from './role-routing.module';
import { RoleListComponent } from './role-list/role-list.component';
import { RoleShowComponent } from './role-show/role-show.component';
import {FormsModule} from "@angular/forms";
import { RoleFilterPipe } from './role-filter.pipe';


@NgModule({
  declarations: [RoleListComponent, RoleShowComponent, RoleFilterPipe],
    imports: [
        CommonModule,
        RoleRoutingModule,
        FormsModule
    ]
})
export class RoleModule { }

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PolicyRoutingModule } from './policy-routing.module';
import { PolicyShowComponent } from './policy-show/policy-show.component';
import {FormsModule} from "@angular/forms";
import { PolicyListComponent } from './policy-list/policy-list.component';
import { PolicyFilterPipe } from './policy-filter.pipe';


@NgModule({
  declarations: [PolicyShowComponent, PolicyListComponent, PolicyFilterPipe],
    imports: [
        CommonModule,
        PolicyRoutingModule,
        FormsModule
    ]
})
export class PolicyModule { }

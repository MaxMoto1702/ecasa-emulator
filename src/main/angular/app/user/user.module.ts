import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {UserRoutingModule} from './user-routing.module';
import {UserListComponent} from './user-list/user-list.component';
import {UserShowComponent} from './user-show/user-show.component';
import {UserEditComponent} from './user-edit/user-edit.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { UserSessionListComponent } from './user-session-list/user-session-list.component';
import { UserRoleListComponent } from './user-role-list/user-role-list.component';
import {AppModule} from "../app.module";
import {HomeComponent} from "../home/home.component";
import {RoleFilterPipe} from "./role-filter.pipe";
import { UserFilterPipe } from './user-filter.pipe';


@NgModule({
    declarations: [
        UserListComponent,
        UserShowComponent,
        UserEditComponent,
        UserSessionListComponent,
        UserRoleListComponent,
        RoleFilterPipe,
        UserFilterPipe
    ],
    imports: [
        CommonModule,
        UserRoutingModule,
        FormsModule,
        HttpClientModule
    ]
})
export class UserModule {
  constructor() {
    console.log("EcasaUser module")
  }
}

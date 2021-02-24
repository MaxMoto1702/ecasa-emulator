import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserListComponent} from "./user-list/user-list.component";
import {UserShowComponent} from "./user-show/user-show.component";
import {UserEditComponent} from "./user-edit/user-edit.component";
import {UserSessionListComponent} from "./user-session-list/user-session-list.component";
import {UserRoleListComponent} from "./user-role-list/user-role-list.component";

const routes: Routes = [
    {path: "users", component: UserListComponent},
    {path: "users/new", component: UserEditComponent},
    {path: "users/:userId", component: UserShowComponent},
    {path: "users/:userId/edit", component: UserEditComponent},
    {path: "users/:userId/sessions", component: UserSessionListComponent},
    {path: "users/:userId/roles", component: UserRoleListComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class UserRoutingModule {
    constructor() {
        console.log("EcasaUser routing module")
    }
}

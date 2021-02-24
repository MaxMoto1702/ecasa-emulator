import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RoleListComponent} from "./role-list/role-list.component";
import {RoleShowComponent} from "./role-show/role-show.component";

const routes: Routes = [
    {path: "roles", component: RoleListComponent},
    {path: "roles/:roleId", component: RoleShowComponent},

];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class RoleRoutingModule {
}

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PolicyShowComponent} from "./policy-show/policy-show.component";
import {PolicyListComponent} from "./policy-list/policy-list.component";

const routes: Routes = [
  {path: "policies", component: PolicyListComponent},
  {path: "policies/:policyId", component: PolicyShowComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PolicyRoutingModule { }

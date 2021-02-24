import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";
import {IndexComponent} from "./index/index.component";

const routes: Routes = [
    // {path: "", redirectTo: "home", pathMatch: 'full'},
    // {path: "home", component: HomeComponent},
    {path: "", component: IndexComponent},
    // {path: '**', component: PageNotFoundComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
    constructor() {
        console.log("App routing module")
    }
}

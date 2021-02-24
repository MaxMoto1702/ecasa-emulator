import {Injectable, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {IndexComponent} from './index/index.component';
import {UserModule} from "./user/user.module";
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {NavigationComponent} from './navigation/navigation.component';
import {HomeComponent} from './home/home.component';
import {HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {RoleFilterPipe} from './user/role-filter.pipe';
import {RoleModule} from "./role/role.module";
import {PolicyModule} from "./policy/policy.module";

@Injectable()
export class NoopInterceptor implements HttpInterceptor {

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        req.headers.set("Accept", "application/json")
        return next.handle(req);
    }
}

@NgModule({
    declarations: [
        AppComponent,
        IndexComponent,
        PageNotFoundComponent,
        NavigationComponent,
        HomeComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        NgbModule,
        UserModule,
        RoleModule,
        PolicyModule
    ],
    providers: [
        // {provide: HTTP_INTERCEPTORS, useClass: NoopInterceptor, multi: true}
    ],
    exports: [],
    bootstrap: [AppComponent]
})
export class AppModule {
    constructor() {
        console.log("App module")
    }
}

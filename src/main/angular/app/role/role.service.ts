import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Page} from "../page";
import {Role} from "./role";
import {User} from "../user/user";

const httpOptions = {
    headers: new HttpHeaders({
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    })
};

@Injectable({
    providedIn: 'root'
})
export class RoleService {

    private path = '/roles';
    private url = environment.serverUrl + this.path;

    constructor(private httpClient: HttpClient) {
    }

    list(filter: any, pageSize: number, currentPage: number): Observable<Page<Role>> {
        return this.httpClient.get<Page<Role>>(this.url, {
            ...httpOptions, params: {
                ...filter,
                page: currentPage ? currentPage.toString() : '0',
                size: pageSize ? pageSize.toString() : '1000',
                sort: [
                    'name,asc'
                ]
            }
        });
    }

    get(id: number): Observable<Role> {
        return this.httpClient.get<Role>(this.url + '/' + id, httpOptions);
    }
}

import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Page} from "../page";
import {User} from "./user";
import {environment} from "../../environments/environment";
import {Role} from "../role/role";

const httpOptions = {
    headers: new HttpHeaders({
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    })
};

@Injectable({
    providedIn: 'root'
})
export class UserService {

    private path = '/users';
    private url = environment.serverUrl + this.path;

    constructor(private httpClient: HttpClient) {
    }

    list(filter: any, pageSize: number, currentPage: number): Observable<Page<User>> {
        return this.httpClient.get<Page<User>>(this.url, {
            ...httpOptions, params: {
                ...filter,
                page: currentPage ? currentPage.toString() : '0',
                size: pageSize ? pageSize.toString() : '10',
                sort: [
                    'username,asc'
                ]
            }
        });
    }

    get(id: number): Observable<User> {
        return this.httpClient.get<User>(this.url + '/' + id, httpOptions);
    }

    roles(id: number): Observable<Role[]> {
        return this.httpClient.get<Role[]>(this.url + '/' + id + '/roles', httpOptions);
    }

    nonIncludedRoles(id: number): Observable<Role[]> {
        return this.httpClient.get<Role[]>(this.url + '/' + id + '/roles/nonIncluded', httpOptions);
    }

    addRole(user: User, role: Role): Observable<any> {
        return this.httpClient.put(this.url + '/' + user.id + '/roles/' + role.id, httpOptions);
    }

    addAllNonIncludedRoles(user: User): Observable<any> {
        return this.httpClient.put(this.url + '/' + user.id + '/roles/allNonIncluded', httpOptions);
    }

    removeRole(user: User, role: Role): Observable<any> {
        return this.httpClient.delete(this.url + '/' + user.id + '/roles/' + role.id, httpOptions);
    }

    removeAllRoles(user: User): Observable<any> {
        return this.httpClient.delete(this.url + '/' + user.id + '/roles/all', httpOptions);
    }

    save(user: User): Observable<any> {
        if (user.id) {
            return this.httpClient.put(this.url + '/' + user.id, user, {...httpOptions});
        } else {
            return this.httpClient.post(
                this.url, user,
                {...httpOptions});
        }
    }
}

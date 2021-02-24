import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Page} from "../page";
import {Policy} from "./policy";

const httpOptions = {
  headers: new HttpHeaders({
    'Accept': 'application/json',
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class PolicyService {

  private path = '/policies';
  private url = environment.serverUrl + this.path;

  constructor(private httpClient: HttpClient) {
  }

  list(filter: any, pageSize: number, currentPage: number): Observable<Page<Policy>> {
    return this.httpClient.get<Page<Policy>>(this.url, {
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

  get(id: number): Observable<Policy> {
    return this.httpClient.get<Policy>(this.url + '/' + id, httpOptions);
  }
}

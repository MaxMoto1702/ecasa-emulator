import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
    selector: 'ecasa-index',
    templateUrl: './index.component.html',
    styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

    constructor(private httpClient: HttpClient) {
    }

    ngOnInit(): void {
    }

    loadRoles(): void {
        this.httpClient.post(
            "/debug/loadDefaultUspnRoleModel",
            {},
            {}
        );
    }

}

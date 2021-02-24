import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {User} from "../user";
import {Session} from "../session";

@Component({
    selector: 'ecasa-user-session-list',
    templateUrl: './user-session-list.component.html',
    styleUrls: ['./user-session-list.component.css']
})
export class UserSessionListComponent implements OnInit {


    user: User = {
        credentialsExpired: false,
        description: "",
        email: "",
        enabled: false,
        expired: false,
        firstName: "",
        id: 0,
        lastName: "",
        locked: false,
        username: "",
        middleName: "",
        name: "",
        password: ""
    };
    sessions: Session[] = [];

    constructor(
        private route: ActivatedRoute,
        private http: HttpClient
    ) {
    }

    ngOnInit(): void {
        const routeParams = this.route.snapshot.paramMap;
        const userId = Number(routeParams.get('userId'));
        this.http.get<User>('/api/users/' + userId)
            .subscribe(value => {
                this.user = value;
                this.http.get<Session[]>('/debug/session', {params: {login: this.user.username}})
                    .subscribe(v => {
                        this.sessions = v;
                    })
            });
    }

    open(session: Session) {

    }
}

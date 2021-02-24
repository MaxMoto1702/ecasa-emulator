import {Component, OnInit} from '@angular/core';
import {User} from "../user";
import {ActivatedRoute, Params} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {UserService} from "../user.service";

@Component({
    selector: 'ecasa-user-show',
    templateUrl: './user-show.component.html',
    styleUrls: ['./user-show.component.css']
})
export class UserShowComponent implements OnInit {

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

    constructor(
        private route: ActivatedRoute,
        private http: HttpClient,
        private userService: UserService
    ) {
    }

    ngOnInit(): void {
        this.route.params
            .subscribe((params: Params) => {
                this.userService.get(+params['userId'])
                    .subscribe((user: User) => {
                        this.user = user;
                    });
            });
    }
}

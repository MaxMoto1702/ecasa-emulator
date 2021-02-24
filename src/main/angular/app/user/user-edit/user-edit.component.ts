import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {UserService} from "../user.service";
import {User} from "../user";

@Component({
    selector: 'ecasa-user-edit',
    templateUrl: './user-edit.component.html',
    styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {

    error: any = null;

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
    confirmPassword: string = "";

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private http: HttpClient,
        private userService: UserService
    ) {
    }

    ngOnInit(): void {
        this.route.params
            .subscribe((params: Params) => {
                if (!params.hasOwnProperty("userId")) {
                    this.user = {
                        credentialsExpired: false,
                        description: "",
                        email: "",
                        enabled: true,
                        expired: false,
                        firstName: "",
                        id: 0,
                        lastName: "",
                        locked: false,
                        username: "",
                        middleName: "",
                        name: "",
                        password: ""
                    }
                } else {
                    const param = params['userId'];
                    this.userService.get(+param)
                        .subscribe((user: User) => {
                            this.user = user;
                            this.user.password = "";
                            this.confirmPassword = "";
                        });
                }
            });
    }

    isNew() {
        return this.user.id == 0;
    }

    save() {
        this.error = null;
        if (this.changedPassword() && this.user.password != this.confirmPassword) {
            this.error = {
                message: "Пароли не совпадают"
            };
            return;
        }
        this.userService.save(this.user).subscribe(() => {
            // noinspection JSIgnoredPromiseFromCall
            this.router
                .navigate(
                    [".."],
                    {relativeTo: this.route}
                );
        }, (error) => {
            this.error = error.error;
        })
    }

    private changedPassword() {
        return this.user.password != "";
    }
}

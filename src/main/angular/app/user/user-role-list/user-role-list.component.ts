import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {UserService} from "../user.service";
import {User} from "../user";
import {Role} from "../../role/role";

@Component({
    selector: 'ecasa-user-role-list',
    templateUrl: './user-role-list.component.html',
    styleUrls: ['./user-role-list.component.css']
})
export class UserRoleListComponent implements OnInit {


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
    roles: Role[] = [];
    nonIncludedRoles: Role[] = [];
    roleFilterString: string;
    nonIncludedRoleFilterString: string;

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
                        this.loadRoles();
                        this.loadNonIncludedRoles();
                    });
            });
    }

    add(role: Role) {
        this.userService.addRole(this.user, role).subscribe(() => {
            this.loadRoles();
            this.loadNonIncludedRoles();
        });
    }

    addAllNonIncludedRoles() {
        this.userService.addAllNonIncludedRoles(this.user).subscribe(() => {
            this.loadRoles();
            this.loadNonIncludedRoles();
        });
    }

    remove(role: Role) {
        this.userService.removeRole(this.user, role).subscribe(() => {
            this.loadRoles();
            this.loadNonIncludedRoles();
        });
    }

    removeAllRoles() {
        this.userService.removeAllRoles(this.user).subscribe(() => {
            this.loadRoles();
            this.loadNonIncludedRoles();
        });
    }

    private loadRoles() {
        this.userService.roles(this.user.id).subscribe(roles => this.roles = roles);
    }

    private loadNonIncludedRoles() {
        this.userService.nonIncludedRoles(this.user.id).subscribe(roles => this.nonIncludedRoles = roles);
    }
}

import {Component, OnInit} from '@angular/core';
import {Role} from "../role";
import {ActivatedRoute, Params} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {RoleService} from "../role.service";

@Component({
    selector: 'ecasa-role-show',
    templateUrl: './role-show.component.html',
    styleUrls: ['./role-show.component.css']
})
export class RoleShowComponent implements OnInit {

    role: Role = {
        policyDisplayName: "",
        policyName: "",
        policyId: 0,
        id: 0,
        name: "",
        displayName: "",
        description: ""
    };

    constructor(
        private route: ActivatedRoute,
        private http: HttpClient,
        private roleService: RoleService
    ) {
    }

    ngOnInit(): void {
        this.route.params
            .subscribe((params: Params) => {
                this.roleService.get(+params['roleId'])
                    .subscribe((role: Role) => {
                        this.role = role;
                    });
            });
    }

}

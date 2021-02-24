import {Component, OnInit} from '@angular/core';
import {Page} from "../../page";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {Role} from "../role";
import {RoleService} from "../role.service";

@Component({
    selector: 'ecasa-role-list',
    templateUrl: './role-list.component.html',
    styleUrls: ['./role-list.component.css']
})
export class RoleListComponent implements OnInit {

    roleFilterString: string = "";
    page: Page<Role> = {content: [], number: 0, numberOfElements: 0, size: 100, totalElements: 0, totalPages: 0};

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private http: HttpClient,
        private roleService: RoleService
    ) {
    }

    ngOnInit(): void {
        this.loadList();
    }

    private loadList() {
        this.roleService.list(
            {},
            this.page.size,
            (this.page.number) + 1 - 1
        ).subscribe(
            page => {
                this.page = page;
            }
        )
    }

    open(role: Role) {
        // noinspection JSIgnoredPromiseFromCall
        this.router
            .navigate(
                [role.id],
                {relativeTo: this.route}
            );
    }

}

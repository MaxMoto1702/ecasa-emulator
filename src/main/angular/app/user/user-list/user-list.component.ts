import {Component, OnInit} from '@angular/core';
import {User} from "../user";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {Page} from "../../page";
import {UserService} from "../user.service";

@Component({
    selector: 'ecasa-user-list',
    templateUrl: './user-list.component.html',
    styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

    users: User[];

    userFilterString: string = "";

    page: Page<User> = {content: [], number: 0, numberOfElements: 0, size: 100, totalElements: 0, totalPages: 0};

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private http: HttpClient,
        private userService: UserService
    ) {
    }

    ngOnInit(): void {
        // this.http.get<Page<User>>("/api/users")
        //     .subscribe(page => this.users = page.content);
        this.loadList();
    }

    private loadList() {
        this.userService.list(
            {},
            this.page.size,
            (this.page.number ) + 1 - 1
        ).subscribe(
            page => {
                this.page = page;
            }
        )
    }

    open(user: User) {
        // noinspection JSIgnoredPromiseFromCall
        this.router
            .navigate(
                [user.id],
                {relativeTo: this.route}
            );
    }
}

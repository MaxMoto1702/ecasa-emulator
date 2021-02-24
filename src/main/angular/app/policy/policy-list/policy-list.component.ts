import {Component, OnInit} from '@angular/core';
import {Page} from "../../page";
import {Policy} from "../policy";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {PolicyService} from "../policy.service";

@Component({
    selector: 'ecasa-policy-list',
    templateUrl: './policy-list.component.html',
    styleUrls: ['./policy-list.component.css']
})
export class PolicyListComponent implements OnInit {

    policyFilterString: string = "";
    page: Page<Policy> = {content: [], number: 0, numberOfElements: 0, size: 100, totalElements: 0, totalPages: 0};

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private http: HttpClient,
        private policyService: PolicyService
    ) {
    }

    ngOnInit(): void {
        this.loadList();
    }

    private loadList() {
        this.policyService.list(
            {},
            this.page.size,
            (this.page.number) + 1 - 1
        ).subscribe(
            page => {
                this.page = page;
            }
        )
    }

    open(policy: Policy) {
        // noinspection JSIgnoredPromiseFromCall
        this.router
            .navigate(
                [policy.id],
                {relativeTo: this.route}
            );
    }

}

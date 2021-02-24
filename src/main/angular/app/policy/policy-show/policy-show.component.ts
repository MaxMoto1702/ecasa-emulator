import {Component, OnInit} from '@angular/core';
import {Policy} from "../policy";
import {ActivatedRoute, Params} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {PolicyService} from "../policy.service";

@Component({
    selector: 'ecasa-policy-show',
    templateUrl: './policy-show.component.html',
    styleUrls: ['./policy-show.component.css']
})
export class PolicyShowComponent implements OnInit {

    policy: Policy = {
        applicationDisplayName: "",
        applicationId: 0,
        applicationName: "",
        actions: [],
        // application: undefined,
        deny: false,
        role: undefined,
        semanticAnd: false,
        id: 0,
        name: "",
        displayName: "",
        description: ""
    };

    constructor(
        private route: ActivatedRoute,
        private http: HttpClient,
        private policyService: PolicyService
    ) {
    }

    ngOnInit(): void {
        this.route.params
            .subscribe((params: Params) => {
                this.policyService.get(+params['policyId'])
                    .subscribe((policy: Policy) => {
                        this.policy = policy;
                    });
            });
    }
}

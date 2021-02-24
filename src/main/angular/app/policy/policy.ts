import {Role} from "../role/role";
import {Application} from "./application";
import {Action} from "./action";

export interface Policy {
    id: number;
    name: string;
    displayName: string;
    description: string;
    deny: boolean;
    semanticAnd: boolean;
    role: Role;
    // application: Application;
    applicationId: number;
    applicationName: string;
    applicationDisplayName: string;
    actions: Action[];
}

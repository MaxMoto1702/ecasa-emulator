export interface User {
    id: number;
    username: string;
    name: string;
    firstName: string;
    middleName: string;
    lastName: string;
    email: string;
    description: string;
    password: string;
    expired: boolean;
    locked: boolean;
    credentialsExpired: boolean;
    enabled: boolean;
}

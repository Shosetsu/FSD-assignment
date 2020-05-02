export class CustomerInfo {

    constructor(
        public accountType: string = "A",
        public accountId?: string,
        public sessionKey?: string) { }

    isRole(role: string): boolean {
        return this.accountType ? role ? role.indexOf(this.accountType) != -1 : true : false;
    }

    init(other: CustomerInfo) {
        for (let key in other) {
            this[key] = other[key];
        }
    }

}

export class CustomerInfo {

    constructor(
        public accountType: string = "A",
        public accountId?: string,
        public authKey?: string) { }

    isRole(role: string): boolean {
        return this.accountType ? role ? role.indexOf(this.accountType) != -1 : true : false;
    }

    init(other) {
        for (let key in this) {
            this[key] = other[key];
        }
        return this;
    }

}

export class CustomerDetail {

    constructor(
        public accountType?: string,
        public accountId?: string,
        public email?: string,
        public telNumber?: string,
        public coName?: string,
        public postalAddr?: string,
        public gstin?: string,
        public bankDetail?: string) { }

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

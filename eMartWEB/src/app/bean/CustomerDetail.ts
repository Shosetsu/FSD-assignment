export class CustomerDetail {

    constructor(
        public accountType?: string,
        public accountId?: string,
        public email?: string,
        public telNumber?: string,
        public coName?: string,
        public postalAddr?: string,
        public GSTIN?: string,
        public bankDetail?: string) { }

    isRole(role: string): boolean {
        return this.accountType ? role ? role.indexOf(this.accountType) != -1 : true : false;
    }

    clone(): CustomerDetail {
        return new CustomerDetail().init(this);
    }

    init(other: CustomerDetail): CustomerDetail {
        for (let key in other) {
            this[key] = other[key];
        }
        return this;
    }
}

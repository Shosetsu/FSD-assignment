export class GoodInfo {
    /**
     * 
     * @param status 0:normal,1:blocked,2.archived
     */
    constructor(
        public id?: string,
        public name?: string,
        public manufacturer?: string,
        public category?: string[],
        public detail?: string,
        public price?: number,
        public stock?: number,
        public count?: number,
        public owner?: string,
        public createdDate?: Date,
        public status: number = 0) { }

    clone(): GoodInfo {
        return new GoodInfo().init(this);
    }

    init(other): GoodInfo {
        for (let key in this) {
            this[key] = other[key];
        }
        return this;
    }

    equals(other) {
        for (let key in this) {
            if (this[key] !== other[key]) {
                return false;
            }
        }
        return true;
    }
}

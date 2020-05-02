export class GoodInfo {
    public count: number;

    constructor(
        public id?: string,
        public name?: string,
        public manufacturer?: string,
        public category?: string[],
        public detail?: string,
        public price?: number,
        public stock?: number,
        public owner?: string,
        public createdDate?: Date,
        public blockFlag?: boolean) { }

    clone(): GoodInfo {
        return new GoodInfo().init(this);
    }

    init(other: GoodInfo): GoodInfo {
        for (let key in other) {
            console.log(key);
            this[key] = other[key];
        }
        return this;
    }
}

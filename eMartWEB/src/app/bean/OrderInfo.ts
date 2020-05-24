export class OrderInfo {
    constructor(
        public orderId: string,
        public seller: string,
        public buyer: string,
        public name: string,
        public manufacturer: string,
        public count: number,
        public amount: number,
        public timestamp: Date,
        public goodId: string) { }
        
    init(other) {
        for (let key in this) {
            this[key] = other[key];
        }
        return this;
    }
}

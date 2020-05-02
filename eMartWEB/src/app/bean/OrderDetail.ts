export class OrderDetail {
    constructor(
        public orderId: string,
        public seller: string,
        public buyer: string,
        public name: string,
        public category: string[],
        public manufacturer: string,
        public price: number,
        public count: number,
        public amount: number,
        public timestamp: Date,
        public goodId: string) { }
}

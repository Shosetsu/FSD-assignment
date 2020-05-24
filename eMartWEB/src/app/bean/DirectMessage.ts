export class DirectMessage {
    constructor(public sendby?: string, public sendto?: string, public text?: string, public createTime?: Date) { }

    init(other): DirectMessage {
        for (let key in other) {
            this[key] = other[key];
        }
        return this;
    }
}

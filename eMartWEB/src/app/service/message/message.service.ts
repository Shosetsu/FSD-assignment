import { Injectable } from '@angular/core';
import { Message } from 'src/app/bean/message';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private messageList: Message[];

  constructor() { this.messageList = []; }

  getMessageList(): Message[] {
    return this.messageList;
  }

  addMsg(msg: Message) {
    this.messageList.push(msg);
    
    setTimeout(() => {
      this.removeMsg(msg);
    }, 2500);
  }

  clearMsg() {
    this.messageList.splice(0, this.messageList.length);
  }

  removeMsg(msg: Message) {
    this.messageList.splice(this.messageList.indexOf(msg), 1);
  }

}

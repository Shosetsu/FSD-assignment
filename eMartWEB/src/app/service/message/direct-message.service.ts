import { Injectable } from '@angular/core';
import { DirectMessage } from 'src/app/bean/DirectMessage';
import { Constants } from 'src/app/constans/constans';
import { ConnectService } from '../connect/connect.service';

@Injectable({
  providedIn: 'root'
})
export class DirectMessageService {

  constructor(private connect: ConnectService) { }

  async getMessageList(): Promise<DirectMessage[]> {
    if (Constants.debugMode) console.log("#Get Direct Message list ");

    return await this.connect.fetchData('message', "", "GET", null);
  }

  async sendMessage(msg: DirectMessage) {
    if (Constants.debugMode) console.log("#Send DM to " + msg.sendto);

    return await this.connect.fetchData('message', '', 'POST', msg);
  }
}

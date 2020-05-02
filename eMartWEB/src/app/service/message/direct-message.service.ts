import { Injectable } from '@angular/core';
import { DirectMessage } from 'src/app/bean/DirectMessage';
import { Constants } from 'src/app/constans/constans';

@Injectable({
  providedIn: 'root'
})
export class DirectMessageService {

  constructor() { }

  getMessageList(accountId, sessionKey): DirectMessage[] {
    if (Constants.debugMode) console.log("#Get account DM list " + accountId);
    //TODO connect server


    return [new DirectMessage("test1", "Setsu", "dalkdlkfg\r\ndsadwsad\r\n", new Date(2020, 4, 1, 20, 54, 31)),
    new DirectMessage("UserID1111", "Setsu", "Textaaaaaaaaa", new Date(2020, 3, 14, 22, 14, 31)),
    new DirectMessage("System Message", "Setsu", "[Attentional] asdfghjkk", new Date(2020, 3, 14, 20, 19, 31))];
  }

  sendMessage(msg: DirectMessage): number {
    if (Constants.debugMode) console.log("#Send DM to " + msg.sendTo);
    //TODO connect server

    return 1;
  }
}

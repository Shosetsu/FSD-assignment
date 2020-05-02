import { Component, OnInit } from '@angular/core';
import { Message } from 'src/app/bean/message';
import { MessageService } from 'src/app/service/message/message.service';

@Component({
  selector: 'app-messageheader',
  templateUrl: './messageheader.component.html',
  styleUrls: ['./messageheader.component.css']
})
export class MessageheaderComponent implements OnInit {

  msgList: Message[];

  constructor(private msgService: MessageService) {
    this.msgList = this.msgService.getMessageList();
  }

  ngOnInit() {
  }

  closeMsg(msg: Message) {
    this.msgService.removeMsg(msg);
  }

}

import { Component, OnInit } from '@angular/core';
import { DirectMessageService } from 'src/app/service/message/direct-message.service';
import { DirectMessage } from 'src/app/bean/DirectMessage';
import { SessionControllerService } from 'src/app/service/session/session-controller.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent {

  dmList: DirectMessage[];

  newMessage: DirectMessage;
  sendFlag: boolean;

  constructor(
    private dmService: DirectMessageService,
    private sessionService: SessionControllerService,
    private router: Router,
    private route: ActivatedRoute,
    private location: Location) {
    route.queryParams.subscribe(params => {
      if (params['s']) {
        this.newMessage = new DirectMessage(sessionService.getAccountId(), params['s'], "", null);
        this.sendFlag = true;
      }
    });
  }

  sendMessageTo(reciver: string) {
    this.router.navigate([this.route.routeConfig.path], {
      queryParams: {
        s: reciver
      }
    });
  }

  send() {
    this.newMessage.timestamp = new Date();
    if (this.dmService.sendMessage(this.newMessage)) {
      this.back();
    }
  }

  ngOnInit() {
    this.dmList = this.dmService.getMessageList(this.sessionService.getAccountId(), this.sessionService.getSessionKey());
  }

  back() {
    this.sendFlag = false;
    this.location.back();
  }

}

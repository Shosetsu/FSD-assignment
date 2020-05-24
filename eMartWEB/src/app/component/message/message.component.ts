import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DirectMessage } from 'src/app/bean/DirectMessage';
import { DirectMessageService } from 'src/app/service/message/direct-message.service';
import { SessionControllerService } from 'src/app/service/session/session-controller.service';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

  accountId: string;

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
      this.dmList = [];
      this.accountId = sessionService.getAccountId();
      if (params['s']) {
        this.newMessage = new DirectMessage(this.accountId, params['s'], "", null);
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
    this.dmService.sendMessage(this.newMessage).then(data => {
      if (data) {
        this.back();
        this.loadMessage();
      }
    });
  }

  ngOnInit() {
    this.loadMessage();
  }

  private loadMessage() {
    this.dmService.getMessageList().then(data => {
      if (data) {
        data.forEach(e => { this.dmList.push(new DirectMessage().init(e)) });
      }
    });
  }

  back() {
    this.sendFlag = false;
    this.location.back();
  }

}

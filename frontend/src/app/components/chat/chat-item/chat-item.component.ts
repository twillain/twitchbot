import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { Message } from '../../../models/message.model';
import { MessageUserComponent } from "../message-user/message-user.component";

@Component({
  selector: 'app-chat-item',
  templateUrl: './chat-item.component.html',
  styleUrls: ['./chat-item.component.scss'],
  imports: [CommonModule, MessageUserComponent]
})
export class ChatItemComponent {
  @Input() message!: Message;
}

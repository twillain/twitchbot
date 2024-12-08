// src/app/chat/chat.component.ts
import { Component} from '@angular/core';
import { ChatHeaderComponent } from "./chat-header/chat-header.component";
import { ChatListComponent } from "./chat-list/chat-list.component";
import { ChatInputComponent } from "./chat-input/chat-input.component";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss'],
  imports: [
    ChatHeaderComponent,
    ChatListComponent,
    ChatInputComponent
  ]
})
export class ChatComponent {}

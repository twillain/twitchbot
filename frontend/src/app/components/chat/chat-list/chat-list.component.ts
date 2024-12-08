import { Component, OnInit, OnDestroy, ViewChild, ElementRef } from '@angular/core';
import { Message } from '../../../models/message.model';
import { CommonModule } from '@angular/common';
import { ChatItemComponent } from '../chat-item/chat-item.component';
import { WebSocketService } from '../../../services/websocket.service';
import { MessageUserComponent } from '../message-user/message-user.component';

@Component({
  selector: 'app-chat-list',
  templateUrl: './chat-list.component.html',
  styleUrls: ['./chat-list.component.scss'],
  imports: [
    CommonModule,
    ChatItemComponent
  ],
  providers: [WebSocketService]
})

export class ChatListComponent implements OnInit, OnDestroy {
  @ViewChild('chatList') chatList!: ElementRef;
  messages: Message[] = [];  // Tableau pour stocker les messages

  constructor(private webSocketService: WebSocketService) {
  }

  ngOnInit(): void {
    // Abonnez-vous au topic lorsque le composant est initialisé
    this.webSocketService.subscribeToTopic('/topic/chat/message', (message: any) => {
      const msg = new Message(message.message_id, 'message', message.userName, message.userId, message.message.text, new Date().toLocaleTimeString(), message.color);
      this.messages.push(msg);  // Ajoutez le message reçu à la liste
      this.scrollToBottom();
    });
  }

  ngOnDestroy(): void {
    // Libérez les ressources et désabonnez-vous si nécessaire
    // (optional, dépend de votre implémentation de WebSocket)
  }

  private scrollToBottom(): void {
    try {
      setTimeout( () => {
        this.chatList.nativeElement.scrollTop = this.chatList.nativeElement.scrollHeight;
      }, 0 );
      
    } catch (err) {
      console.error('Error while scrolling:', err);
    }
  }
}
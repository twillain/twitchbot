import { Component, EventEmitter, Output } from '@angular/core';
import { ChatService } from '../../../services/chat.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-chat-input',
  templateUrl: './chat-input.component.html',
  styleUrls: ['./chat-input.component.scss'],
  imports: [CommonModule, FormsModule]
})
export class ChatInputComponent {
  message: string = '';
  @Output() messageSent = new EventEmitter<string>();

  constructor(private chatService: ChatService) {}

  sendMessage() {
    this.chatService.sendMessage(this.message);
    this.message = ''; // Réinitialiser l'input
  }
}

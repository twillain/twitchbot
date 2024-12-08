import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ChatService {
  private baseUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  getMessages() {
    return this.http.get(`${this.baseUrl}/chat/messages`);
  }

  sendMessage(message: string) {
    return this.http.post(`${this.baseUrl}/chat/send`, { text: message });
  }
}

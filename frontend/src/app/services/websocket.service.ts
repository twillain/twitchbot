import { Injectable } from '@angular/core';
import { Client, Message as StompMessage } from '@stomp/stompjs';  // Importation des types STOMP


@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  private stompClient!: Client;
  private topicCallbacks: { [topic: string]: ((message: any) => void)[] } = {};  // Store des callbacks pour chaque topic

  constructor() {
    this.connect();
  }

  private connect(): void {
    this.stompClient = new Client({
      brokerURL: 'ws://localhost:8080/chat', // URL de votre WebSocket serveur
      connectHeaders: {},
      debug: (str) => console.log(str),  // Pour le debug
      onConnect: () => {
        console.log('WebSocket connection established');
      },
      onStompError: (frame) => {
        console.error('STOMP Error:', frame);
      },
      onWebSocketClose: () => {
        console.log('WebSocket connection closed');
        setTimeout(() => this.connect(), 5000); // Reconnexion après délai
      },
    });

    this.stompClient.activate();  // Active la connexion WebSocket
  }

  // Fonction de souscription à un topic STOMP
  public subscribeToTopic<T>(topic: string, callback: (message: T) => void): void {
    if (!this.topicCallbacks[topic]) {
      this.topicCallbacks[topic] = [];
    }

    this.topicCallbacks[topic].push(callback);

    // Souscription au topic via STOMP après la connexion
    if (this.stompClient.connected) {
      this.stompClient.subscribe(topic, (message: StompMessage) => {
        const payload = JSON.parse(message.body);  // Parse le message reçu
        this.handleMessage(topic, payload);
      });
    } else {
      console.warn('WebSocket is not connected yet, waiting for connection...');
      this.stompClient.onConnect = () => {
        this.stompClient.subscribe(topic, (message: StompMessage) => {
          const payload = JSON.parse(message.body);
          console.log(payload);
          this.handleMessage(topic, payload);
        });
      };
    }
  }

  // Méthode pour gérer la réception de messages
  private handleMessage(topic: string, payload: any): void {
    if (this.topicCallbacks[topic]) {
      this.topicCallbacks[topic].forEach((callback) => callback(payload));  // Appel du callback pour chaque topic
    }
  }
}

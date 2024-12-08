// src/app/models/message.model.ts
export class Message {
    id: number;
    type: string;
    userId: string;
    username: string;
    text: string;
    timestamp: string;
    color: string;

    constructor(id: number, type: string, username: string, userId: string, text: string, timestamp: string, color: string) {
        this.id = id;
        this.type = type;
        this.username = username;
        this.userId = userId;
        this.text = text;
        this.timestamp = timestamp;
        this.color = color;
    }

}

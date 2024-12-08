export class User {
    id: string;
    username: string;
    numberMessagesSent: number;
    followedAt: string;
    totalSubGifted: number;

    constructor(id:string, username:string, numberMessagesSent:number, followedAt:string, totalSubGifted:number){
        this.id = id;
        this.username = username;
        this.numberMessagesSent = numberMessagesSent;
        this.followedAt = followedAt;
        this.totalSubGifted = totalSubGifted;
    }
}
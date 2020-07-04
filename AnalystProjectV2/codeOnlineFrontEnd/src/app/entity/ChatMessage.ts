import { User } from './User';

export class ChatMessage{
    sender:String;
    content:String;
    date:String;
    self:boolean;

    constructor(sender: String, date: String, content: string, self:boolean) {
        this.sender = sender;
        this.content = content;
        this.date = date;
        this.self = self;
    }
}
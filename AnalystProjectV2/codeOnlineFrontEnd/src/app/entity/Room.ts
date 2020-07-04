import { User } from './User';

export interface Room{
    roomId:number,
    roomName:string,
    open:boolean,
    editorName:string,
    ownerName:string
}
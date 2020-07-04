import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {

  private host:string = "api/socket/provider/user/rooms";
  headers:any;
  requestHost:string = "api/user/provider/user/";
  constructor(private http:HttpClient) { 
    this.headers = new HttpHeaders({'Content-Type': 'application/json'});
  }

  getUserInterViews(){
    return this.http.get(
      "api/socket/provider/user/rooms"
    );
  }

  getRoomUsers(roomId:number){
      return this.http.get(
          "api/socket/provider/room/getOnlineAll/"+roomId
      );
  }

  getRoomDetails(roomId:number){
    return this.http.get(
      "api/socket/provider/room/getRoom/"+roomId
  );
  }

  createRoom(candidate:String, interviewers:String[]){
    return this.http.post(
      'api/socket/provider/room/createRoom',
      {
      'user':candidate,
      'roomUsers':interviewers
      },
      this.headers
    );

  }


  

}


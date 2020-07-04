import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/entity/User';
import { ChatMessage } from 'src/app/entity/ChatMessage';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { ActivatedRoute } from '@angular/router';
import {WebSocketService} from '../../service/web-socket.service';

@Component({
  selector: 'app-chat-room',
  templateUrl: './chat-room.component.html',
  styleUrls: ['./chat-room.component.scss']
})
export class ChatRoomComponent implements OnInit {
  userInput:String = "";
  currentUser:User;
  allUser:User[] = [];
  chatHistory:ChatMessage[] = [];
  lineFlag = true;


  public stompClient;
  public serverUrl = "http://localhost:4200/api/socket/websocket/";  // api - front end proxy .   socket - gateway.    websocket - controller
  public room = 11;//频道号
  public sender = "AAA";//发送者

  public types = {
    'CHAT':1,
    'UPDATE_USER':2,
    'UPDATE_CODE':3
  };//消息的类型

  public type = 1;

  public message = "";//消息内容

  constructor(private activatedRoute:ActivatedRoute,
    private roomService:WebSocketService) { 

  }

  ngOnInit(): void {
    this.room = this.activatedRoute.snapshot.queryParams["roomId"];
    this.sender = this.activatedRoute.snapshot.queryParams["userName"];
    if(this.stompClient == undefined)
    {
      this.connect();
    }else{
      console.log("alredy build socket connect");
    }
  }



  // chat core

  connect() {
 
    if(this.sender===undefined) {
      alert("cannot config sender")
      return
    }
 
    if(this.room===undefined) {
      alert("cannot config room")
      return
    }

    const that = this;
 
    const ws = new SockJS(this.serverUrl);
    that.stompClient = Stomp.over(ws);
 
    this.stompClient.connect({
    }, function (frame) {

      that.sendMessageBySystem(that.types.UPDATE_USER,"refresh user list");
      console.log("run once");
 
     //获取聊天室的消息
     that.stompClient.subscribe('/topic/' + that.room, (message) => {
        console.log("get message from chat room");
        if (message.body) {
          const sender = JSON.parse(message.body)['sender'];
          const content = decodeURI(JSON.parse(message.body)['content']);
          const type = JSON.parse(message.body)['type'];

          if(type == that.types.CHAT)
          {

          const item:ChatMessage = new ChatMessage(
             sender,
             "2020-4-20", 
             content,
             that.sender == sender
          );
          that.chatHistory.push(item);
          }else{
            console.log("type:",type);
              if(type ==that.types.UPDATE_USER){
                that.updateUserList();
              }

          }

        }else{
 
          return
        }
          
      });
      
    });
   
  }

  // disconnect
    disconnect() {
      if (this.stompClient !== undefined) {
        this.stompClient.disconnect();
      }else{
        alert("no connection found")
      }
      this.stompClient = undefined;
      alert("Disconnected");
  }


    //send message
   //发送消息(单聊)
   sendMessageByUser() {
    if(this.stompClient===undefined) {
      alert("websocket not configurated")
      return
    };

    if(this.userInput != "" && this.userInput == null){
      alert("Please input");
    }
      this.stompClient.send(
        '/app/chat',
        {},
        JSON.stringify({
          'sender': this.sender,
          'room': this.room,
          'type': this.types.CHAT,
          'content': this.userInput })
      );
      this.userInput = "";

    }

  //send message
   //发送消息(单聊)
   sendMessageBySystem(type,content) {
    if(this.stompClient===undefined) {
      alert("websocket not configurated")
      return
    };
      this.stompClient.send(
        '/app/chat',
        {},
        JSON.stringify({
          'sender': this.sender,
          'room': this.room,
          'type': type,
          'content': content })
      );
    }

    updateUserList(){
      this.roomService.getRoomUsers(this.room).pipe().subscribe(
        data=>{
          if(data["status"] == true){
            
            this.allUser = data["entityValue"];
            console.log("allllllllllllll user");
            console.log(this.allUser);
          }
          else{
            console.log("pull latest user list failed!");
          }
        }
      )
    }


}

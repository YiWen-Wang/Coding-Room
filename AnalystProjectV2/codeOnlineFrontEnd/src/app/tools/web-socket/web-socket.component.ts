import { Component, OnInit } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { Item } from '../../entity/item';

@Component({
  selector: 'app-web-socket',
  templateUrl: './web-socket.component.html',
  styleUrls: ['./web-socket.component.scss']
})
export class WebSocketComponent implements OnInit {
  public stompClient;
 
  public serverUrl = "api/websocket/";
 
  public room;//频道号
 
  public sender;//发送者
 
  public type;//消息的类型
 
  public message;//消息内容
 
  public messageAll;//群发消息的内容
 
  items = [];
 
  atems = [];
 
  constructor() { 
    
  }
 
  ngOnInit() {
      // this.connect();
  }
 
  connect() {
 
    if(this.sender===undefined) {
      alert("发送者不能为空")
      return
    }
 
    if(this.room===undefined) {
      alert("房间号不能为空")
      return
    }
 
 
    const ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
 
    const that = this;
    this.stompClient.connect({}, function (frame) {
      console.log("try connect");
 
     //获取聊天室的消息
      that.stompClient.subscribe('/topic/' + that.room, (message) => {
        console.log("尝试订阅消息!");
        if (message.body) {
 
          const sender = JSON.parse(message.body)['sender'];
          // const language = JSON.parse(message.body)['language'];
          const content = JSON.parse(message.body)['content'];
          const type = JSON.parse(message.body)['type'];
 
          const newitem = new Item(
           type,
           sender,
           content
          );
 
          that.items.push(newitem);
 
        }else{
 
          return
        }
          
      });
 
       //获取群发消息
    that.stompClient.subscribe('/all', (message) =>{
      if (message.body) {
 
        const sender = JSON.parse(message.body)['sender'];
        // const language = JSON.parse(message.body)['language'];
        const content = JSON.parse(message.body)['content'];
        const type = JSON.parse(message.body)['type'];
 
        const newatem = new Item(
         type,
         sender,
         content
        );
 
        that.atems.push(newatem);
 
      }else{
 
        return
      }
    })
      
    });
 
   
  }
 
  //断开连接的方法
  disconnect() {
      if (this.stompClient !== undefined) {
        this.stompClient.disconnect();
      }else{
        alert("当前没有连接websocket")
      }
      this.stompClient = undefined;
      alert("Disconnected");
  }
 
  //发送消息(单聊)
  sendMessage() {
    if(this.stompClient===undefined) {
      alert("websocket还未连接")
      return
    };
 
    if(this.type===undefined) {
      alert("消息类型不能为空")
      return
    };
 
    if(this.message===undefined) {
      alert("消息内容不能为空")
      return
    };
 
      this.stompClient.send(
        '/app/chat',
        {},
        JSON.stringify({
          'sender': this.sender,
          'room': this.room,
          'type': this.type,
          'content': this.message })
      );
    }
 
    //群发消息
    sendMessageToAll() {
      if(this.stompClient===undefined) {
        alert("websocket还未连接")
        return
      };
  
      if(this.messageAll===undefined) {
        alert("群发消息内容不能为空")
        return
      };
  
        this.stompClient.send(
          '/app/chatAll',
          {},
          JSON.stringify({
            'sender': this.sender,
            'room': this.room,
            'type': this.type,
            'content': this.messageAll })
        );
 
      }
    
 
  
}
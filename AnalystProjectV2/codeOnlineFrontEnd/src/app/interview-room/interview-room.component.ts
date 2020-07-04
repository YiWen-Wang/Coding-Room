import { Component, OnInit } from '@angular/core';
import {EditorService} from '../service/editor.service';
import {UserServiceService} from '../service/user-service.service';
import {Router, ActivatedRoute} from '@angular/router';
import {WebSocketService} from '../service/web-socket.service';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

@Component({
  selector: 'app-interview-room',
  templateUrl: './interview-room.component.html',
  styleUrls: ['./interview-room.component.scss']
})
export class InterviewRoomComponent implements OnInit {
  languageList:any;
  selectedLanguage:any;
  logined:boolean;

  // language items
  mysqlOptions:any;
  javaOptions:any;
  pythonOptions:any;

  // language inputs:
  mysqlInput:String;
  javaInput:String;
  pythonInput:String;
  obj:any;

  // output:
  executedResult:String

  //code execution
  codeExecuted:boolean = false;

  // chat config
  room:number;
  user:string;
  public types = {
    'CODE':1,        // share the codes and config;
    'RESULT':2   // share current run result
  };//消息的类型

  //room details
  editor:String;
  owner:String;
  edit_permission = false;



  //websocket config
  public stompClient;
  public serverUrl = "http://localhost:4200/api/codeSharing/websocket/";  // api - front end proxy .   socket - gateway.    websocket - controller


  constructor(private activeRoute:ActivatedRoute,private editorService:EditorService, private userService:UserServiceService,private roomService:WebSocketService, private router:Router) { }

  ngOnInit(): void {
    this.room = this.activeRoute.snapshot.queryParams["roomId"];
    this.user = this.activeRoute.snapshot.queryParams["userName"];

    this.userService.userSessionCheck().pipe().subscribe(data=>{
      console.log(data);
      if(data["status"] == true){
        this.initRoom(this.room);
      }
      else{
        this.router.navigate(['home/login']);
      }
    });
  }

  initPage(){
    
    this.mysqlOptions = {
      theme: 'blackboard',
      mode: 'text/x-mysql',
      extraKeys:{"Ctrl":"autocomplete"},
      lineNumbers: true,
      lineWrapping: true,
      foldGutter: true,
      gutters: ['CodeMirror-linenumbers', 'CodeMirror-foldgutter', 'CodeMirror-lint-markers'],
      autoCloseBrackets: true,
      matchBrackets: true,
      lint: true,
      readOnly: !this.edit_permission
    };


    this.javaOptions = {
      theme: 'blackboard',
      mode: 'text/x-java',
      extraKeys:{"Ctrl":"autocomplete"},
      lineNumbers: true,
      lineWrapping: true,
      foldGutter: true,
      gutters: ['CodeMirror-linenumbers', 'CodeMirror-foldgutter', 'CodeMirror-lint-markers'],
      autoCloseBrackets: true,
      matchBrackets: true,
      lint: true,
      readOnly: !this.edit_permission
    };

    this.pythonOptions = {
      theme: 'blackboard',
      mode: 'text/x-python',
      extraKeys:{"Ctrl":"autocomplete"},
      lineNumbers: true,
      lineWrapping: true,
      foldGutter: true,
      gutters: ['CodeMirror-linenumbers', 'CodeMirror-foldgutter', 'CodeMirror-lint-markers'],
      autoCloseBrackets: true,
      matchBrackets: true,
      lint: true,
      inputStyle:'contenteditable',
      autocorrect:true,
      fullScreen:true,
      readOnly: !this.edit_permission
    };


    this.languageList = [
      // value.name will be used as an identifier for request. please take care.
      {label:'Java', value:{name:"java",option:this.javaOptions,content:this.javaInput,hisContent:""} },
      {label:'Python', value:{name:"python",option:this.pythonOptions,content:this.pythonInput,hisContent:""}},
      {label:'MySQL', value:{name:"mysql",option:this.mysqlOptions,content:this.mysqlInput,hisContent:""}},
  ];



  //init language
  this.selectedLanguage = this.languageList[0].value;
  this.selectedLanguage.content = this.getJavaModel();


  console.log(this.selectedLanguage);
  this.connect(); // share codes config
  }

  setEditorContent(event) {
    // console.log(event, typeof event);
    console.log(this.obj);
  }

  test(){
    console.log(this.selectedLanguage);
  }

  runCodes(){
    if(!this.edit_permission){
      alert("you do not have permission !");
      return;
    }
    this.codeExecuted = true;
     this.editorService.runCodes(
      {type:this.selectedLanguage.name,content:this.selectedLanguage.content}
    ).subscribe(
      (data) =>
      {
        console.log("result:",data);
        if(data['status'] == true){
          this.executedResult = data['entityValue'];
          console.log("result ddd:",this.executedResult);
          this.sendMessageBySystem(this.types.RESULT,this.executedResult);
        } else{
          this.executedResult = data['reason']
        }
      }
    );

  }

  userLogOut(){
    this.userService.userLogOut().pipe().subscribe(
      data=>{
        if(data["status"] == true){
          this.router.navigate(['home/login']);
          this.logined = false;
        }
        else{
          alert(data["reason"]);
        }
      }
    )
  }

  initRoom(roomId){
    this.roomService.getRoomDetails(roomId).pipe().subscribe(
      data=>{
        if(data['status'] == true){
          this.editor = data['entityValue']['editor']['userName'];
          this.owner = data['entityValue']['owner']['userName'];
          if(this.user == this.editor){
            this.edit_permission = true;
          }
          console.log("Editor:",this.editor);
        }
        this.initPage();
      }
    )
  }


  // websocket

  
  connect() {
 
    if(this.user===undefined) {
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
      if(that.editor == that.user)
      {
        that.setCodeSharing(); // share codes
      }
      that.logined = true;
      console.log("come once");
     //获取聊天室的消息
     if(that.editor != that.user)
     {
     that.stompClient.subscribe('/topic/' + that.room, (message) => {
       console.log("get message:",message);
        if (message.body) {
          const msgType = JSON.parse(message.body)['type'];
          const content = JSON.parse(message.body)['content'];

          //share the code and configurations
          if(msgType == that.types.CODE)
          {
          const msgLanguage = JSON.parse(message.body)['language'];
          // if language changed 
          if(msgLanguage != that.selectedLanguage.name){
            for(let item of that.languageList){
              if(item.value.name == msgLanguage){
                that.selectedLanguage = item.value;
                break;
              }
            }
          }
          that.selectedLanguage.content =  content;
          }

          // share the code run result
          else if(msgType == that.types.RESULT){
            that.codeExecuted = true;
            that.executedResult = content;
          }
        }
      });
    }
    
      
    });
   
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
          'language': this.selectedLanguage.name,
          'room': this.room,
          'type': type,
          'content': content })
      );
    }

 
    setCodeSharing(){
      setInterval(() => {//refresh code cotent every second
        // if content changed, then refresh. else do nothing
        if(this.selectedLanguage.content != this.selectedLanguage.hisContent)
        {
        this.selectedLanguage.hisContent = this.selectedLanguage.content;
        this.sendMessageBySystem(1,this.selectedLanguage.content);
        }

        }, 1000)
        }

    getJavaModel():String{
     return `public class ${this.editor} {\n    public static void main(String[] args){\n\n    }\n}`;
    }

    

    }







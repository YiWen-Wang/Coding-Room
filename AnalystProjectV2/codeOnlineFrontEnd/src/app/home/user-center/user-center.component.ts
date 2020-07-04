import { Component, OnInit } from '@angular/core';
import {UserServiceService} from '../../service/user-service.service';
import {WebSocketService} from '../../service/web-socket.service';
import {Router} from '@angular/router';
@Component({
  selector: 'app-user-center',
  templateUrl: './user-center.component.html',
  styleUrls: ['./user-center.component.scss']
})
export class UserCenterComponent implements OnInit {
  private userType:string;
  private logined:boolean = false;
  public rooms:any[];
  private userName:string;

  constructor(private userService:UserServiceService, private roomService:WebSocketService, private router:Router) { 
    console.log("build user center !");

    this.userService.userSessionCheck().pipe().subscribe(
      data=>{
        if(data["status"] == true){
          this.logined = true;
          this.userType = data["entityValue"]["userType"];
          this.userName = data["entityValue"]["userName"];
          this.loadInterviewRooms();
        }else{
          this.router.navigate(["home/login"]);
        }
      }
    )

  }

  ngOnInit(): void {
  }

  isAdmin(){
    return "admin" == this.userType;
  }

  loadInterviewRooms(){
    this.roomService.getUserInterViews().pipe().subscribe(
      data =>{
        if(data["status"] == true){
          this.rooms = data["entityValue"];
          console.log("get values");
          console.log(this.rooms);
        }
      }
    )
  }

  joinInterview(roomId:number){
    console.log("join meeting:",roomId);
    this.router.navigate(["home/interview"], {queryParams:{'roomId':roomId,'userName':this.userName}} )
  }

}

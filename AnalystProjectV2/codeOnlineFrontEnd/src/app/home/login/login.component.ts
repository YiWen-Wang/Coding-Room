import { Component, OnInit } from '@angular/core';
import {UserServiceService} from '../../service/user-service.service';
import {User} from '../../entity/User';
import {Router} from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  user:User;
  userName:string;
  password:string;
  unlogined:boolean = false;

  imgContent:any;

  constructor(private userService:UserServiceService, private router:Router) {
    this.userService.userSessionCheck().pipe().subscribe(
      data=>{
        if(data["status"] == true){
          this.userName = data["entityValue"]["userName"];
          this.router.navigate(["home/userCenter"]);
        }else{
          this.unlogined = true;
        }
      }
    )
   }

  ngOnInit(): void {
    
  }

  login():void{
    this.userService.userLoginAuth(this.userName,this.password).pipe().subscribe(
      data => 
      
      {
        console.log(data);
        if(data["status"]='0000_0'){
          console.log("auth successd");
          this.userService.userLogin(this.userName,this.password).pipe().subscribe(
            data=>{
              console.log(data);
              if(data["status"]==true){
                console.log("come here");
                this.unlogined = false;
                this.router.navigate(["home/userCenter"]);
              }
              else{
                alert(data["reason"]);
                this.userName = "";
                this.password = "";
              }
            }
          )
        }
        else{
          console.log("Auth failed!");
        }

    }
      ) ;
  }

  logOut():void{
    this.userService.userLogOut().pipe().subscribe(  
      data =>  
    {   
      if(data["status"]==true){
       this.unlogined = true;
       this.router.navigate(["home"]);
      }
      else{
        alert(data["reason"]);
        this.router.navigate(["home"]);
      }
      this.userName = "";
      this.password = "";
    }
      
    );
  }
  

}

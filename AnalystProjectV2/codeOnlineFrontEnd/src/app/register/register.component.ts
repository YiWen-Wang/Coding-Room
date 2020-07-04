import { Component, OnInit } from '@angular/core';
import { UserServiceService } from '../service/user-service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  userName:String;
  password:String;
  confirmPassword:String;
  selectedRole:any[];
  role:any;

  constructor(private userService:UserServiceService) { 
    this.selectedRole = [
      {name: 'Admin', typeName:'admin',typeId: 0},
      {name: 'Interviewer',typeName:'editor', typeId: 1},
      {name: 'Candidate',typeName:'reader', typeId: 2}
  ];

  }

  ngOnInit(): void {
  }

  register(){
    if(this.userName != "" && this.userName != undefined ){
      if(this.password != "" && this.password != undefined){
        if(this.role != "" && this.role != undefined){
          if(this.password != this.confirmPassword){
            alert("Please keep same password");
            return;
          }
        }
        else{
          alert("please complete role");
          return;
        }
      }
      else{
        alert("Please complete password");
        return;
      }
    }
    else{
      alert("Please complete userName");
      return;
    }
    console.log(this.role);
    console.log("register successfully");
    this.userService.userRegister(this.userName,this.password,this.role).subscribe(
      data=>{
        console.log('success');
        console.log(data);
        this.userName = "";
        this.confirmPassword = undefined;
        this.password = "";
        alert("succeed");
      }
    );
  }


}

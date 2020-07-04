import { Injectable } from '@angular/core';
import {User} from '../entity/User';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Md5} from 'ts-md5';
@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  headers:any;
  authHeader:any;
  requestHost:string = "api/user/provider/user/";
  constructor(private http:HttpClient) { 
    this.headers = new HttpHeaders({'Content-Type': 'application/json'});
    this.authHeader =  new HttpHeaders({'Content-Type': 'application/x-www-form-urlencoded'});
  }

  userLoginAuth(name:string, password:string){

    let requestBody = 'username='+name+'&password='+Md5.hashStr(password);
    console.log(requestBody);
    
    console.log(Md5.hashStr(password));
    return this.http.post(
      'api/auth/login',
      requestBody,
      {headers:this.authHeader}
    );

  }

  userLogin(name:string, password:string){

    return this.http.post(
      this.requestHost+"login",
      {
        userName:name,
        userPassword:password
      },
      this.headers
    );

  }

  userSessionCheck(){
    return this.http.get(
      this.requestHost+"checkLogin"
    );
  }

  userLogOut(){
    return this.http.get(
      'api/user/provider/user/logout',
    );

  }

  candidateSuggestion(vagueName:String){
    return this.http.get(
      'api/user/provider/user/Candidate/suggestion/'+vagueName,
    )
  }

  interviewerSuggestion(vagueName:String){
    return this.http.get(
      'api/user/provider/user/Interviewer/suggestion/'+vagueName,
    )
  }

  userRegister(name:String, password:String, role:any){
    return this.http.post(
      'api/user/provider/user/register',
      {
      'userName':name,
      'userPassword':password,
      'userType':{
        'typeId': role.typeId,
        'typeName': role.typeName
      }
      },
      this.headers
    );
  }

  testConnect():void{
    this.http.get(
      'api/user/5'
    ).pipe().subscribe(data=>console.log(data));
  }



  handleError():void{
    console.log("connection error");
  }



}

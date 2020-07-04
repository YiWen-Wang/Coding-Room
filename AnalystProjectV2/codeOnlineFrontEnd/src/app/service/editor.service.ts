import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Code } from '../entity/Code';
import {WebMessage} from '../entity/WebMessage';

@Injectable({
  providedIn: 'root'
})
export class EditorService {

  headers:any;
  language:String;
  constructor(private http:HttpClient) { 
    this.headers = new HttpHeaders({'Content-Type': 'application/json'});
  }

  runCodes(code:Code){
    let url = "";
    if(code.type == "python"){
      url = "api/pythonRunner/run"
    }
    else if(code.type == "java"){
      url = "api/javaRunner/java/run"
    }
    console.log(code);
   return this.http.post(url,
   {
     "code":code.content
   }
   ,this.headers).pipe();
  }
}

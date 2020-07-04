import { Component, OnInit } from '@angular/core';
import { UserServiceService } from 'src/app/service/user-service.service';
import { WebSocketService } from 'src/app/service/web-socket.service';

@Component({
  selector: 'app-interview-creator',
  templateUrl: './interview-creator.component.html',
  styleUrls: ['./interview-creator.component.scss']
})
export class InterviewCreatorComponent implements OnInit {

  candidateVagueName:any;
  candidateList:Set<String> = new Set<String>();
  addedCandidateList:Set<String> = new Set<String>() ;

  interviewerVagueName:any;
  interviewerList:Set<String> = new Set<String>();
  addedInterviewerList:Set<String> = new Set<String>() ;

  

  constructor(private userService:UserServiceService, private roomService:WebSocketService) { }

  ngOnInit(): void {
  }

  searchCandidate(data){
    this.userService.candidateSuggestion(data.query).pipe().subscribe(
      data=>{
        console.log(data);
        if(data["status"]==true){
          this.candidateList = data["entityValue"];
        }

      }
    )
  }

  searchInterviewer(data){
    this.userService.interviewerSuggestion(data.query).pipe().subscribe(
      data=>{
        console.log(data);
        if(data["status"]==true){
          this.interviewerList = data["entityValue"];
        }

      }
    )

  }

  addCandidate(){
    if(this.candidateVagueName.userName != undefined && this.candidateVagueName.userName != "" ){
      if(this.addedCandidateList.size > 0){
        alert("Can only have one candidate ! ");
      }
      else{
        this.addedCandidateList.add(this.candidateVagueName.userName);
      }
    }
    else{
      alert("Please select item from the dropdown list");
    }

  }

  removeCandidate(name:String){
    this.addedCandidateList.delete(name);
  }

  addInterviewer(){
    if(this.interviewerVagueName.userName != undefined && this.interviewerVagueName.userName != "" ){
      this.addedInterviewerList.add(this.interviewerVagueName.userName);
    }
    else{
      alert("Please select item from the dropdown list");
    }

  }

  removeInterviewer(name:String){
    this.addedInterviewerList.delete(name);
  }

  createInterviewRoom(){
    let candidate:String;
    let interviewers:String[] = [];
    this.addedCandidateList.forEach(item=>candidate = item);
    this.addedInterviewerList.forEach(item=>{
      interviewers.push(item);
    })
    this.roomService.createRoom(candidate,interviewers).pipe().subscribe(data=>{
      console.log(data);
    });
  }



}

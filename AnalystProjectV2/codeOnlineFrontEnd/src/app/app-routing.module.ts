import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './home/home.component';
import{InterviewRoomComponent} from './interview-room/interview-room.component';
import {UserCenterComponent} from './home/user-center/user-center.component';
import {TestComponentComponent} from './tools/test-component/test-component.component';
import {RegisterComponent} from './register/register.component';


const routes: Routes = [
  {path:"",redirectTo:"home",pathMatch:'full'},

    {
      path:"home",
      component:HomeComponent,
      children:[
        {
          path:'userCenter',
          component: UserCenterComponent
        },

        {
          path:'interview',
          component:InterviewRoomComponent
        },
    
      ]
    },

    {path:'test',component:TestComponentComponent},

    {path:'register', component:RegisterComponent},

    {path:'**',redirectTo:'home',pathMatch:'full'}




];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

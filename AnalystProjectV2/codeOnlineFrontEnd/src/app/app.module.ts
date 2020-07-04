import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import {FormsModule} from '@angular/forms';
import {CodemirrorModule} from '@ctrl/ngx-codemirror';
import { EditorComponent } from './tools/editor/editor.component';
import { LoginComponent } from './home/login/login.component';
import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import { HttpClientModule } from '@angular/common/http';
import { InterviewRoomComponent } from './interview-room/interview-room.component';
import {DropdownModule} from 'primeng/dropdown';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { WebSocketComponent } from './tools/web-socket/web-socket.component';
import {CardModule} from 'primeng/card';
import {BlockUIModule} from 'primeng/blockui';
import { ChatRoomComponent } from './tools/chat-room/chat-room.component';
import {InputTextareaModule} from 'primeng/inputtextarea';
import {ScrollPanelModule} from 'primeng/scrollpanel';
import {Router} from '@angular/router';
import { UserCenterComponent } from './home/user-center/user-center.component';
import {TabViewModule} from 'primeng/tabview';
import { TestComponentComponent } from './tools/test-component/test-component.component';
import { InterviewCreatorComponent } from './tools/interview-creator/interview-creator.component';
import {AutoCompleteModule} from 'primeng/autocomplete';
import { RegisterComponent } from './register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    EditorComponent,
    LoginComponent,
    InterviewRoomComponent,
    WebSocketComponent,
    ChatRoomComponent,
    UserCenterComponent,
    TestComponentComponent,
    InterviewCreatorComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule, 
    AppRoutingModule,
    FormsModule,
    CodemirrorModule,
    InputTextModule,
    ButtonModule,
    HttpClientModule,
    DropdownModule,
    BrowserAnimationsModule,
    CardModule,
    BlockUIModule,
    InputTextareaModule,
    ScrollPanelModule,
    TabViewModule,
    AutoCompleteModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { 
  constructor(router: Router){

  }
}

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';  
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NavigationComponent } from './components/navigation/navigation.component';
import { HomeComponent } from './components/home/home.component';
import { GuildComponent } from './components/guild/guild.component';
import { UserComponent } from './components/user/user.component';
import { UserService } from './services/user.service';
import { GuildService } from './services/guild.service';
import { ContentComponent } from './components/content/content.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { FooterComponent } from './components/footer/footer.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { CommentComponent } from './components/comment/comment.component';
import { AuthService } from './services/auth.service';
import { TopicComponent } from './components/topic/topic.component';
import { LoginRegistrationModalComponent } from './components/login-registration-modal/login-registration-modal.component';
import { RouterModule } from '@angular/router';
import { StatusesComponent } from './components/statuses/statuses.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    HomeComponent,
    GuildComponent,
    UserComponent,
    ContentComponent,
    FooterComponent,
    LoginComponent,
    LogoutComponent,
    NotFoundComponent,
    CommentComponent,
    TopicComponent,
    LoginRegistrationModalComponent,
    StatusesComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgbModule,
    RouterModule
  ],
  providers: [
    AuthService,
    UserService,
    GuildService,
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

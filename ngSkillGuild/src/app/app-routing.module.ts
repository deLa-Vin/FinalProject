import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContentComponent } from './components/content/content.component';
import { GuildComponent } from './components/guild/guild.component';
import { HomeComponent } from './components/home/home.component';
import { UserComponent } from './components/user/user.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent },
  { path: 'user', component: UserComponent },
  { path: 'guild', component: GuildComponent },
  { path: 'content', component: ContentComponent },
  { path: '**', component: HomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

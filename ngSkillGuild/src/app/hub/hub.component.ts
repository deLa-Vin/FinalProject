import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Content } from '../models/content';
import { Guild } from '../models/guild';
import { User } from '../models/user';
import { ContentService } from '../services/content.service';
import { GuildService } from '../services/guild.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-hub',
  templateUrl: './hub.component.html',
  styleUrls: ['./hub.component.css']
})
export class HubComponent implements OnInit {

  guilds: Guild[] = [];
  selectedGuild: Guild | null = null;

  contents: Content[] = [];
  selectedContent: Content | null = null;

  attendees: User[] = [];

  defaultImage: string = 'https://images.unsplash.com/3/doctype-hi-res.jpg';

  user: User = new User();

  constructor(
    private guildSvc: GuildService,
    private contentSvc: ContentService,
    private userSvc: UserService,
    private route: ActivatedRoute,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.getAllGuilds();
  }

  // Guilds
  getAllGuilds() {
    this.guildSvc.index().subscribe(guilds => {
      this.guilds = guilds;
    });
  }

  displayAllGuilds(): void {
    this.selectedGuild = null;
  }

  selectGuild(guild: Guild) {
    this.contents = [];
    this.getGuildContents(guild.id);
    this.selectedGuild = guild;
    this.getUserProfile();
  }

  displayGuild() {
    this.selectedContent = null;
  }

  joinGuild(gid: number, uid: number) {
    if (this.selectedGuild) {
      console.log("User " + uid + " wants to join: " + gid);
      this.guildSvc.join(uid, gid).subscribe({
        next: data => {
          console.log("Joined successfully: " + data);
        },
        error: (err) => {
          console.error('Error joining guild: ', err);
        }
      })
    }
  }

  // Content 
  getGuildContents(gid: number) {
    this.contentSvc.showContentByGuild(gid).subscribe(contents => {
      this.contents = contents;
    });
  }

  getAllContents() {
    this.contentSvc.index().subscribe(contents => {
      this.contents = contents;
    });
  }

  getContentById(id: number) {
    this.contentSvc.show(id).subscribe(
      content => {
        return content;
      },
      err => {
        console.log(err);
      }
    )
  }

  selectContent(cid: number) {
    this.contentSvc.show(cid).subscribe(content => {
      this.selectedContent = content;
    });
  }

  // User
  getUserProfile() {
    this.userSvc.getProfile().subscribe(
      user => {
        this.user = user;
      },
      err => {
        console.log(err);
      }
    )
  }

  // Attendees
  attendContent(cid: number) {
    console.log("User wants to attend: " + cid);
    // this.contentSvc.show(cid).subscribe(content => {
    //   this.selectedContent = content;
    // });
  }

}

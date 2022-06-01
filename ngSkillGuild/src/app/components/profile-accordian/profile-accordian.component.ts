import { Guild } from './../../models/guild';
import { GuildService } from 'src/app/services/guild.service';
import { Component, OnInit } from '@angular/core';
import { NgbCollapse } from '@ng-bootstrap/ng-bootstrap';
import { Content } from 'src/app/models/content';
import { ContentService } from 'src/app/services/content.service';

@Component({
  selector: 'app-profile-accordian',
  templateUrl: './profile-accordian.component.html',
  styleUrls: ['./profile-accordian.component.css'],
})
export class ProfileAccordianComponent implements OnInit {
  myGuilds: Guild[] = [];
  paginationGuilds: Guild[] = [];
  myContents: Content[] = [];
  paginationContents: Content[] = [];
  contents: Content[] = [];


  key: number = 0;
  page: number = 1;
  pageSize: number = 5;

  constructor(
    private guildSvc: GuildService,
    private contentSvc: ContentService
    ) {}

  ngOnInit(): void {
    this.memberOfGuilds();
  }

  memberOfGuilds() {
    this.guildSvc.memberOfGuilds().subscribe((guilds) => {
      this.myGuilds = guilds;
      this.refreshGuilds();
    });
  }

  getUserContent (uid: number) {
    this.contentSvc.showContentByUser(uid).subscribe((contents) => {
      this.contents = contents;
      this.refreshContents();
    });
  }

  refreshGuilds() {
    console.log(this.paginationGuilds);
    this.paginationGuilds = this.myGuilds
      .map((guild, i) => ({ key: i + 1, ...guild }))
      .slice(
        (this.page - 1) * this.pageSize,
        (this.page - 1) * this.pageSize + this.pageSize
      );
  }


refreshContents() {
  console.log(this.paginationContents);
  this.paginationContents = this.myContents
    .map((content, i) => ({ key: i + 1, ...content }))
    .slice(
      (this.page - 1) * this.pageSize,
      (this.page - 1) * this.pageSize + this.pageSize
    );
}
}



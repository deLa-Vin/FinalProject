import { Guild } from './../../models/guild';
import { GuildService } from 'src/app/services/guild.service';
import { Component, OnInit } from '@angular/core';
import { NgbCollapse } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-profile-accordian',
  templateUrl: './profile-accordian.component.html',
  styleUrls: ['./profile-accordian.component.css'],
})
export class ProfileAccordianComponent implements OnInit {
  myGuilds: Guild[] = [];
  paginationGuilds: Guild[] = [];

  key: number = 0;
  page: number = 1;
  pageSize: number = 5;

  constructor(private guildSvc: GuildService) {}

  ngOnInit(): void {
    this.memberOfGuilds();
  }

  memberOfGuilds() {
    this.guildSvc.memberOfGuilds().subscribe((guilds) => {
      this.myGuilds = guilds;
      this.refreshGuilds();
    });
  }

  refreshGuilds() {
    this.paginationGuilds = this.myGuilds
      .map((guild, i) => ({ key: i + 1, ...guild }))
      .slice(
        (this.page - 1) * this.pageSize,
        (this.page - 1) * this.pageSize + this.pageSize
      );
  }
}

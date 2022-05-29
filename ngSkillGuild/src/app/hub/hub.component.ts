import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Content } from '../models/content';
import { Guild } from '../models/guild';
import { ContentService } from '../services/content.service';
import { GuildService } from '../services/guild.service';

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

  constructor(
    private guildSvc: GuildService,
    private contentSvc: ContentService,
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
    this.contents = [];
  }

  selectGuild(guild: Guild) {
    this.getGuildContents(guild.id); // Modify for individual guild
    this.selectedGuild = guild;
  }

  // Content 
  getGuildContents(gid: number) {
    this.contentSvc.showContentByGuild(gid).subscribe(contents => {
      this.contents.push(contents);
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

}

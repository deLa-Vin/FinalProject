import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Guild } from 'src/app/models/guild';
import { GuildService } from 'src/app/services/guild.service';

@Component({
  selector: 'app-guild',
  templateUrl: './guild.component.html',
  styleUrls: ['./guild.component.css']
})
export class GuildComponent implements OnInit {

  guilds: Guild[] = [];

  selected: Guild | null = null;

  constructor(
    private guildSvc: GuildService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getAllGuilds();
  }

  getAllGuilds() {
    this.guildSvc.index().subscribe(guilds => {
      this.guilds = guilds;
    });
  }

  displayAll(): void {
    this.selected = null;
  }

  getGuildById(id: number) {
    this.guildSvc.show(id).subscribe(
      guild => {
        return guild;
      },
      err => {
        console.log(err);
      }
    )
  }

}

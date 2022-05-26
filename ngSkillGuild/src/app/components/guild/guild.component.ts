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

  constructor(
    private guildSvc: GuildService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.guildSvc.index().subscribe(guilds => {
      this.guilds = guilds;
    });
    console.log(this.guilds);
  }

}

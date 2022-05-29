import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Guild } from '../models/guild';
import { GuildService } from '../services/guild.service';

@Component({
  selector: 'app-hub',
  templateUrl: './hub.component.html',
  styleUrls: ['./hub.component.css']
})
export class HubComponent implements OnInit {

  guilds: Guild[] = [];

  selected: Guild | null = null;

  constructor(
    private guildSvc: GuildService,
    private route: ActivatedRoute,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.getAllGuilds();
  }

  getAllGuilds() {
    this.guildSvc.index().subscribe(guilds => {
      this.guilds = guilds;
    });
  }

}

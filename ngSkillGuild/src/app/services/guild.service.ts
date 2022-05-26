import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Guild } from '../models/guild';

@Injectable({
  providedIn: 'root'
})
export class GuildService {

  private url = environment.baseUrl + 'v1/guilds/';

  private guilds: Guild[] = [];

  constructor(
    private http: HttpClient
  ) { }

  index(): Observable<Guild[]> {
    return this.http.get<Guild[]>(this.url);
  }

  show(id: number): Observable<Guild> {
    return this.http.get<Guild>(this.url + id);
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Guild } from '../models/guild';

@Injectable({
  providedIn: 'root'
})
export class GuildService {

  private url = environment.baseUrl + 'v1/guilds/';

  constructor(
    private http: HttpClient
  ) { }

  index(): Observable<Guild[]> {
    return this.http.get<Guild[]>(this.url);
  }

  show(id: number): Observable<Guild> {
    return this.http.get<Guild>(this.url + id);
  }

  create(uid: number, guild: Guild): Observable<Guild> {
    return this.http.post<Guild>(environment.baseUrl + 'v1/users/' + uid + '/guilds', guild).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error(
            'Guild service create() error: ' + err
          )
        );
      })
    );
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Content } from '../models/content';

@Injectable({
  providedIn: 'root'
})
export class ContentService {

  private url = environment.baseUrl + 'v1/contents/';

  private guilds: Content[] = [];

  constructor(
    private http: HttpClient
  ) { }

  index(): Observable<Content[]> {
    return this.http.get<Content[]>(this.url);
  }

  show(id: number): Observable<Content> {
    return this.http.get<Content>(this.url + id);
  }
}

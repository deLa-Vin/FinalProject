import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private url = environment.baseUrl + 'v1/guilds/';

private comments: Comment[] = [];

  constructor(
    private http: HttpClient
  ) { }

  index(): Observable<Comment[]> {
    return this.http.get<Comment[]>(this.url);
  }
}

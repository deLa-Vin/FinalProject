import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Content } from '../models/content';

@Injectable({
  providedIn: 'root'
})
export class ContentService {

  private url = environment.baseUrl + 'v1/contents/';

  constructor(
    private http: HttpClient
  ) { }

  index(): Observable<Content[]> {
    return this.http.get<Content[]>(this.url);
  }

  show(id: number): Observable<Content> {
    return this.http.get<Content>(this.url + id);
  }

  create(uid: number, gid: number, sid: number, content: Content) {
    return this.http.post<Content>(environment.baseUrl + 'v1/users/' + uid + '/guilds/' + gid + '/statuses/' + sid + '/contents', content).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error(
            'Content service create() error: ' + err
          )
        );
      })
    );
  }

  delete(id: number) {
    return this.http.delete<boolean>(this.url + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error(
            'Content service delete() error: ' + err
          )
        );
      })
    );
  }

  update(updateContent: Content) {
    return this.http.put<Content>(this.url + '/' + updateContent.id, updateContent).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error(
            'Content service update() error: ' + err
          )
        );
      })
    );
  }
}

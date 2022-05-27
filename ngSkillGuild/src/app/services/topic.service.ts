import { Topic } from './../models/topic';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TopicService {

  private url = environment.baseUrl + 'v1/topics/';

  constructor(
    private http: HttpClient
  ) { }

  index(): Observable<Topic[]> {
    return this.http.get<Topic[]>(this.url);
  }

  show(id: number): Observable<Topic> {
    return this.http.get<Topic>(this.url + id);
  }

  create(topic: Topic) {
    return this.http.post<Topic>(environment.baseUrl + 'v1/users/' + topic, null).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error(
            'Topic service create() error: ' + err
          )
        );
      })
    );
  }
}

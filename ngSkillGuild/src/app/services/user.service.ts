import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url = environment.baseUrl + 'v1/users/'

  constructor(
    private http: HttpClient
  ) { }

  index(): Observable<User[]> {
    return this.http.get<User[]>(this.url);
  }

  show(id: number): Observable<User> {
    return this.http.get<User>(this.url + id);
  }

  create(user: User) {
    return this.http.post<User>(environment.baseUrl + 'register', user).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error(
            'User service create() error: ' + err
          )
        );
      })
    );
  }

  delete(id: number) {
    return this.http.delete<boolean>(this.url + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(err);
      })
    );
  }
}

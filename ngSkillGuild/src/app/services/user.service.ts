import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url = environment.baseUrl + 'v1/users/'

  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  index(): Observable<User[]> {
    return this.http.get<User[]>(this.url);
  }

  show(id: number): Observable<User> {
    return this.http.get<User>(this.url + id);
  }

  create(user: User) {
    return this.http.post<User>(this.url, user).pipe(
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

  update(user: User) {
    return this.http.put(this.url + `${user.id}`, user).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error(
            'User service update() error: ' + err
          )
        );
      })
    )
  };
}

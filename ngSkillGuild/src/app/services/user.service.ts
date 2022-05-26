import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

private url = environment.baseUrl + 'v1/users/'

private users: User[] = [];

  constructor(
    private http: HttpClient
  ) { }

  index(): Observable<User[]> {
    return this.http.get<User[]>(this.url);
  }

  show(id: number): Observable<User> {
    return this.http.get<User>(this.url + id);
  }
}

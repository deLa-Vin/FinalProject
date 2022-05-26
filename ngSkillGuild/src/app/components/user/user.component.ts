import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  users: User[] = [];

  selected: User | null = null;

  constructor(
    private userSvc: UserService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getAllUsers();

  }
  getAllUsers() {
    this.userSvc.index().subscribe(users => {
      this.users = users;
    });
  }

  displayAll(): void {
    this.selected = null;
  }

  getUserById(id: number) {
    this.userSvc.show(id).subscribe(
      guild => {
        return guild;
      },
      err => {
        console.log(err);
      }
    )
  }

}

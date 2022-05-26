import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  users: User[] = [];

  selected: User | null = null;

  showAllUsers: boolean = true;

  newUser: User = new User();

  createForm: boolean = false;

  createUserForm: any;

  isEditing = false;

  constructor(
    private userSvc: UserService,
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router
  ) { 
    this.createFormInit(fb);
  }

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

  deleteUser(id: number) {
    this.userSvc.delete(id).subscribe(
      (data) => this.getAllUsers(),
      (err) => console.error(err)
    );
  }

  toggleAllUsers = () => {
    this.showAllUsers = !this.showAllUsers;
    this.createForm = !this.createForm;
  }

  showCreateForm = () => {
    this.createForm = !this.createForm;
    this.showAllUsers = !this.showAllUsers;
  }

  createUser(user: User): void {
    this.userSvc.create(user).subscribe({
      next: user => {
        console.log("Created successfully: " + user.id);
        this.users.push(user);
        this.newUser = new User();
        this.toggleAllUsers();
        // this.reload();
      },
      error: (err) => {
        console.error('Error creating content: ', err);
      }
    })
  }

  createFormInit(fb: FormBuilder) {
    this.createUserForm = this.fb.group({
      id: [0],
      username: ['', Validators.required],
      password: ['', Validators.required],
      email: ['2022-06-022 12:30:00'],
      enabled: [true],
      role: ['standard'],
    });
    this.createUserForm.updateValueAndValidity();
    this.isEditing = true;
  }

  sendNewUser() {
    let user: User = {
      id: this.createUserForm.get('id').value,
      username: this.createUserForm.get('username').value,
      password: this.createUserForm.get('password').value,
      email: this.createUserForm.get('email').value,
      enabled: this.createUserForm.get('enabled').value,
      role: this.createUserForm.get('role').value,
    }
    this.isEditing = false;
    this.createUser(user);
  }

}

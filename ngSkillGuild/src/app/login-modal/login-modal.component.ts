import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { User } from '../models/user';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login-modal',
  templateUrl: './login-modal.component.html',
  styleUrls: ['./login-modal.component.css'],
})
export class LoginModalComponent implements OnInit {

  loginUser: User = new User();

  closeResult = '';

  constructor(
    private modalService: NgbModal,
    private auth: AuthService,
    private router: Router
    ) {}

  ngOnInit(): void {}

  open(content: any) {
    this.modalService
      .open(content, {
        ariaLabelledBy: 'modal-basic-title',
        windowClass: 'dark-modal',
      })
      .result.then(
        (result) => {
          this.closeResult = `Closed with: ${result}`;
          this.submitMe(); // submit login code here
          this.login(this.loginUser);
        },
        (reason) => {
          this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        }
      );
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      // this.submitMe();
      return `with: ${reason}`;
    }
  }

  submitMe() {
    console.log('Login Submitted');
  }

  login(user: User) {

    this.auth.login(user.username, user.password).subscribe({
      next: (loggedInUser) => {
        this.router.navigateByUrl('/');
      },
      error: (fail) => {
        console.error('LoginComponent.login(); login failed');
        console.error(fail);
      }
    });

    console.log(user);
  }
}

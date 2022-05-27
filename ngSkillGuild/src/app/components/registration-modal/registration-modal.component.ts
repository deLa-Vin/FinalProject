import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-registration-modal',
  templateUrl: './registration-modal.component.html',
  styleUrls: ['./registration-modal.component.css']
})
export class RegistrationModalComponent implements OnInit {

  newUser = new User();

  closeResult = '';

  constructor(
    private modalService: NgbModal,
    private userSvc: UserService
    ) { }

  ngOnInit(): void {
  }

  registerUser(newUser: User): void {
    console.log(newUser);
    this.userSvc.create(newUser).subscribe({
      next: newUser => {
        console.log("Created successfully: " + newUser.id);
      },
      error: (err) => {
        console.error('Error creating content: ', err);
      }
    })
  }

  open(content: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title', windowClass: 'dark-modal'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
      this.submitMe(); // submit login code here
      this.registerUser(this.newUser);
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
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
    console.log("Login Submitted");
  }

}

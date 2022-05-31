import { Router } from '@angular/router';
import { GuildService } from 'src/app/services/guild.service';
import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Guild } from 'src/app/models/guild';

@Component({
  selector: 'app-create-guild-modal',
  templateUrl: './create-guild-modal.component.html',
  styleUrls: ['./create-guild-modal.component.css'],
})
export class CreateGuildModalComponent implements OnInit {
  guild: Guild = new Guild();

  closeResult = '';

  constructor(
    private modalService: NgbModal,
    private guildSvc: GuildService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  toggleIsPublic() {
    this.guild.isPublic = !this.guild.isPublic;
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  openCreateGuild(content: any) {
    this.modalService
      .open(content, {
        ariaLabelledBy: 'modal-basic-title',
        windowClass: 'dark-modal',
        size: 'xl',
      })
      .result.then(
        (result) => {
          this.closeResult = `Closed with: ${result}`;
          this.createNewGuild(this.guild);
          this.router.navigateByUrl('guild');
          setTimeout(() => {
            window.location.reload();
          }, 100);
        },
        (reason) => {
          this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        }
      );
  }

  createNewGuild = (guild: Guild) => {
    this.guildSvc.createNewGuild(guild).subscribe({
      next: () => {
        console.log('Created Guild successfully: ' + guild.id);
      },
      error: (err: any) => console.error('Error updating user: ', err),
    });
  };
}

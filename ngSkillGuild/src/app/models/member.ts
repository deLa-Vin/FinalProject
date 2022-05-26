export class Member {
  approvedBy: number;
  moderator: boolean;
  createdOn: string;

  constructor(
    approvedBy: number =0,
  moderator: boolean =false,
  createdOn: string=""
  ){
this.approvedBy = approvedBy;
this.moderator = moderator;
this.createdOn = createdOn;
  }
}

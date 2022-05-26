export class Comment {
  id: number;
  inReplyTo: number;
  textContent: string;
  edited: boolean;
  createdOn: string;

  constructor(
    id: number = 0,
    inReplyTo: number = 0,
    textContent: string = "",
    edited: boolean = false,
    createdOn: string = ""
  ) {
    this.id = id;
    this.inReplyTo = inReplyTo;
    this.textContent = textContent;
    this.edited = edited;
    this.createdOn = createdOn;
  }
}

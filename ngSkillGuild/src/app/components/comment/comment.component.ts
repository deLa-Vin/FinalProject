import { Comment } from './../../models/comment';
import { CommentService } from './../../services/comment.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  comments: Comment[] = [];

  editComment: Comment | null = null;

  selected: Comment | null = null;

  newComment: Comment = new Comment();

  constructor(
    private commentSvc: CommentService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  getAllComments() {
    this.commentSvc.index().subscribe(comments => {
      this.comments = comments;
    });
  }

  getCommentById(id: number) {
    this.commentSvc.show(id).subscribe(
      comment => {
        return comment;
      },
      err => {
        console.log(err);
      }
    )
  }

  createComment(uid: number, cid: number, comment: Comment): void {
    this.commentSvc.create(uid, cid, comment).subscribe({
      next: comment => {
        console.log("Created successfully: " + comment.id);
        this.comments.push(comment);
        this.newComment = new Comment();
      },
      error: (err) => {
        console.error('Error creating content: ', err);
      }
    })
  }

  updateComment(comment: Comment) {
    this.commentSvc.update(comment).subscribe(
      (data) => {
        this.getAllComments();
        this.editComment = null;
        if (this.selected) {
          this.selected = Object.assign({}, comment);
        }
      },
      (err) => console.error(err)
    );
  }

  deleteComment(id: number) {
    this.commentSvc.delete(id).subscribe(
      (data) => this.getAllComments(),
      (err) => console.error(err)
    );
  }
}

import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Content } from 'src/app/models/content';
import { Status } from 'src/app/models/status';
import { ContentService } from 'src/app/services/content.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {

  contents: Content[] = [];

  editContent: Content | null = null;

  selected: Content | null = null;

  showAllContents: boolean = true;

  newContent: Content = new Content();

  createForm: boolean = false;

  createContentForm: any;

  isEditing = false;

  constructor(
    private contentSvc: ContentService,
    private fb: FormBuilder,
    public datepipe: DatePipe,
    private route: ActivatedRoute,
    private router: Router
  ) { 
    this.createFormInit(fb);
  }

  ngOnInit(): void {
    this.getAllContents();
  }

  getAllContents() {
    this.contentSvc.index().subscribe(contents => {
      this.contents = contents;
    });
  }

  displayAll(): void {
    this.selected = null;
  }

  getContentById(id: number) {
    this.contentSvc.show(id).subscribe(
      content => {
        return content;
      },
      err => {
        console.log(err);
      }
    )
  }

  setEditContent = () => {
    this.editContent = Object.assign({}, this.selected);
  }

  cancelEdit = () => {
    this.editContent = null;
  }

  toggleAllContents = () => {
    this.showAllContents = !this.showAllContents;
    this.createForm = !this.createForm;
  }

  showCreateForm = () => {
    this.createForm = !this.createForm;
    this.showAllContents = !this.showAllContents;
  }

  createContent(uid: number, gid: number, sid: number, content: Content): void {
    this.contentSvc.create(uid, gid, sid, content).subscribe({
      next: content => {
        console.log("Created successfully: " + content.id);
        this.contents.push(content);
        this.newContent = new Content();
        this.toggleAllContents();
        // this.reload();
      },
      error: (err) => {
        console.error('Error creating content: ', err);
      }
    })
  }

  createFormInit(fb: FormBuilder) {
    this.createContentForm = this.fb.group({
      id: [0],
      title: ['', Validators.required],
      description: ['', Validators.required],
      publishDate: ['2022-06-022 12:30:00'],
      isPublic: [''],
      isLive: [''],
      statusId: [''],
      lastUpdated: [''],
      lengthMinutes: [''],
      presentationDate: ['2022-06-022 12:30:00'],
      guildId: [''],
      createdByUserId: [''],
      resources: [''],
      questions: [''],
      topics: [''],
      comments: [''],
      interactions: [''],
    });
    this.createContentForm.updateValueAndValidity();
    this.isEditing = true;
  }

  sendNewContent() {
    let content: Content = {
      id: this.createContentForm.get('id').value,
      title: this.createContentForm.get('title').value,
      description: this.createContentForm.get('description').value,
      publishDate: new Date(this.createContentForm.get('publishDate').value),
      isPublic: this.createContentForm.get('isPublic').value,
      isLive: this.createContentForm.get('isLive').value,
      statusId: new Status(this.createContentForm.get('statusId').value, 'Draft'),
      lastUpdated: '', // this.createContentForm.get('lastUpdated').value,
      lengthMinutes: 0, // this.createContentForm.get('lengthMinutes').value,
      presentationDate: new Date(this.createContentForm.get('presentationDate').value),
      guildId: this.createContentForm.get('guildId').value,
      createdByUserId: this.createContentForm.get('createdByUserId').value,
      resources: [], // this.createContentForm.get('resources').value,
      questions: [], // this.createContentForm.get('questions').value,
      topics: [], // this.createContentForm.get('topics').value,
      comments: [], // this.createContentForm.get('comments').value
      interactions: [], // this.createContentForm.get('interactions').value
    }
    this.isEditing = false;
    this.createContent(content.createdByUserId, content.guildId, content.statusId.id, content);
  }

  deleteContent(id: number) {
    this.contentSvc.delete(id).subscribe(
      (data) => this.getAllContents(),
      (err) => console.error(err)
    );
  }

  updateContent(content: Content) {
    this.contentSvc.update(content).subscribe(
      (data) => {
        this.getAllContents();
        this.editContent = null;
        if (this.selected) {
          this.selected = Object.assign({}, content);
        }
      },
      (err) => console.error(err)
    );
  }
}

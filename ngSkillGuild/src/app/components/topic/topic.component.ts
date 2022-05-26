import { TopicService } from './../../services/topic.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Topic } from 'src/app/models/topic';

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.css']
})
export class TopicComponent implements OnInit {

topics: Topic[] = [];

newTopic: Topic = new Topic();

  constructor(
    private topicSvc: TopicService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getAllTopics();
  }
  getAllTopics() {
    this.topicSvc.index().subscribe(topics => {
      this.topics = topics;
    });
  }
  createTopic(topic: Topic): void {
    this.topicSvc.create(topic).subscribe({
      next: content => {
        console.log("Created successfully: " + topic.id);
        this.topics.push(topic);
        this.newTopic = new Topic();
        // this.reload();
      },
      error: (err) => {
        console.error('Error creating content: ', err);
      }
    })
  }
}

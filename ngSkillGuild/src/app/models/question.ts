export class Question {
  id: number;
  question: string;
  correctAnswer: string;

  constructor(
    id: number = 0,
    question: string = "",
    correctAnswer: string = ""
  ) {
    this.id = id;
    this.question = question;
    this.correctAnswer = correctAnswer;
  }

}

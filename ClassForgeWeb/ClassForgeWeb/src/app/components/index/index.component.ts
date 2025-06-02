import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {User} from "../../shared/User";
import {Lesson} from "../../shared/Lesson";
import {LessonService} from "../../services/lesson.service";

@Component({
  selector: 'app-index',
  standalone: true,
  imports: [],
  templateUrl: './index.component.html',
  styleUrl: './index.component.css'
})
export class IndexComponent implements OnInit {
  text="123";
  constructor(
    private userService: UserService,
    private lessonService: LessonService
  ) { }

  loadLessons(){
    let lessons: Lesson[] = [];
    this.lessonService.getCurrentTeacherLessons().subscribe((data: Lesson[]) => {
      lessons = data;
      this.text= new Date(lessons[1].timestamp).getDay().toString();
      console.log(lessons);
      console.log(data)
    }
  )
    console.log(lessons);

  }

  ngOnInit() {
    this.loadLessons();

  }

}

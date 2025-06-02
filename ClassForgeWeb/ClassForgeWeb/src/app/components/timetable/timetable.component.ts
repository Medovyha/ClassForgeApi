import { Component, OnInit} from '@angular/core';
import {CardModule} from "primeng/card";
import {ToggleButtonModule} from "primeng/togglebutton";
import {FormsModule} from "@angular/forms";
import {InputSwitchModule} from "primeng/inputswitch";
import {LessonRowComponent} from "./lesson-row.component";
import {DragDropModule} from "primeng/dragdrop";
import {DialogModule} from "primeng/dialog";
import {LessonDialogComponent} from "./lesson-dialog.component";
import {ButtonModule} from "primeng/button";
import {AvatarModule} from "primeng/avatar";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {CommonModule, formatDate} from "@angular/common";
import {HeaderComponent} from "../header/header.component";
import {Lesson} from "../../shared/Lesson";
import {LessonService} from "../../services/lesson.service";
import {DialogService, DynamicDialogModule, DynamicDialogRef} from "primeng/dynamicdialog";
import {weekNumber} from "weeknumber";
import {AddLessonDialogComponent} from "./add-lesson-dialog.component";


@Component({
  selector: 'app-timetable',
  standalone: true,
    imports: [
        CardModule,
        ToggleButtonModule,
        FormsModule,
        InputSwitchModule,
        LessonRowComponent,
        DragDropModule,
        DialogModule,
        LessonDialogComponent,
        ButtonModule,
        AvatarModule,
        CommonModule,
        HeaderComponent,
      DynamicDialogModule
    ],
  providers: [DialogService],
  templateUrl: './timetable.component.html',
  styleUrl: './timetable.component.css'
})
export class TimetableComponent implements OnInit {
  message: string ='';
    ref: DynamicDialogRef | undefined;
    visible = false;
    name: string = 'Jan Kowal';
    text=''
    completed: boolean = false;
    days: Map<string, Lesson[]> = new Map<string, Lesson[]>();
   daysOfWeek: Map<string, Date> = new Map<string, Date>();
   draggedLesson: Lesson | null = null;
   lessons: Lesson[] = [];
   currentWeek: number = weekNumber(new Date());
   weekStartDate: Date = this.getWeekStartDate(this.currentWeek);
   weekEndDate: Date = new Date(this.weekStartDate.getTime() + 6 * 24 * 60 * 60 * 1000);



    constructor(
      private lessonService: LessonService,
      public dialogService: DialogService) {
    }

    ngOnInit() {
      this.initCalendar();
      this.loadLessons();
    }

    initCalendar() {
      this.days = new Map<string, Lesson[]>([
        ["Monday", []],
        ["Tuesday", []],
        ["Wednesday", []],
        ["Thursday", []],
        ["Friday", []],
        ["Saturday", []],
        ["Sunday", []]]
      );
      this.daysOfWeek = new Map<string, Date>();
      for(let day of this.days.keys()){
        this.daysOfWeek.set(day, new Date(this.weekStartDate.getTime() + (this.daysOfWeek.size) * 24 * 60 * 60 * 1000));
      }

    }

  loadLessons(){
    let lessons: Lesson[] = [];
    let now = new Date();
    this.lessonService.getCurrentTeacherWeekLessons(now.getFullYear(), this.currentWeek).subscribe((data: Lesson[]) => {
        lessons = data;
        this.lessons = lessons;
        lessons.forEach(lesson => {
          this.days.get(lesson.timestamp.toLocaleString('en-us', {weekday: 'long'}))?.push(lesson);
        })
        console.log(this.days);
      }
    )
    console.log(lessons);

  }
    dragStart(lesson: Lesson) {
        console.log('drag start'+ lesson.id );
        this.draggedLesson = lesson;
    }

    dragEnd() {
        console.log('drag end');
        this.draggedLesson = null;
    }

    drop(lesson: Lesson) {
      if(this.draggedLesson) {
        let currentWeekday =this.days.get(
          this.draggedLesson
            .timestamp
            .toLocaleString('en-us',
              {weekday: 'long'}))
        if(currentWeekday){
        this.days.get(this.draggedLesson
                              .timestamp
                              .toLocaleString('en-us',
                                        {weekday: 'long'}))
                            ?.splice(
                              currentWeekday.indexOf(this.draggedLesson), 1);}

        this.draggedLesson.timestamp= new Date(lesson.timestamp.getTime()+lesson.length*60000);
        console.log('drop');
        console.log(this.draggedLesson);
        this.days.get(this.draggedLesson.timestamp.toLocaleString('en-us', {weekday: 'long'}))?.push(this.draggedLesson);
        this.lessonService.updateLesson(this.draggedLesson).subscribe((data: Lesson) => {
          this.draggedLesson = data;
          console.log(data);
          // this.initCalendar()
          // this.loadLessons()
        });


        // @ts-ignore
        console.log(this.days.get('Saturday')?.at(0));


        this.draggedLesson= null;
    }
    }

    showDialog(inputLesson: Lesson) {
      console.log("showDialog");

      this.ref = this.dialogService.open(LessonDialogComponent, {
        header: 'Add lesson',
        showHeader: false,
        width: '70%',
        data: {
          lesson: inputLesson,
          lessonId: inputLesson.id
        },
        contentStyle: {"max-height": "1000px", "overflow": "hidden", "min-height": "40rem"},
        baseZIndex: 10000,
      });

      this.ref.onClose.subscribe((data:any) => {
        this.initCalendar();
        this.loadLessons();
      });
    }

  protected readonly formatDate = formatDate;

  onSwitchClick($event: number, lesson: Lesson) {
    lesson.timestamp.getTime()
    console.log("onSwitchClick");
    console.log(lesson.is_done);
    this.lessonService.checkLesson($event).subscribe(
      (data:Lesson) => {
        console.log(data);
        lesson.is_done = data.is_done;
      }
    );

  }

  checkIfNext(currentLesson: Lesson, nextLesson: Lesson | undefined): boolean {
    if (!nextLesson) {
      return false;
    }
    return currentLesson.timestamp.getTime() + currentLesson.length*60000 < nextLesson.timestamp.getTime();
  }

  previousWeek() {
    this.currentWeek--;
    this.initCalendar();
    this.loadLessons();
    this.weekStartDate = this.getWeekStartDate(this.currentWeek);
    this.weekEndDate = new Date(this.weekStartDate.getTime() + 6 * 24 * 60 * 60 * 1000);
  }

  nextWeek() {
    this.currentWeek++;
    this.initCalendar();
    this.loadLessons();
    this.weekStartDate = this.getWeekStartDate(this.currentWeek);
    this.weekEndDate = new Date(this.weekStartDate.getTime() + 6 * 24 * 60 * 60 * 1000);
  }

  currentWeekWeek() {
    this.currentWeek = weekNumber(new Date());
    this.initCalendar();
    this.loadLessons();
    this.weekStartDate = this.getWeekStartDate(this.currentWeek);
    this.weekEndDate = new Date(this.weekStartDate.getTime() + 6 * 24 * 60 * 60 * 1000);
  }

  getWeekStartDate(weekNumber: number) {

    let now = new Date();
    let year = now.getFullYear();
    let januaryFirst = new Date(year, 0, 1);
    let firstWeekDay = januaryFirst.getDay();
    let weekDay = now.getDay();
    let weekStart = new Date(januaryFirst);
    weekStart.setDate(januaryFirst.getDate() + (weekNumber - 1) * 7 - firstWeekDay + 1);
    console.log(weekStart);
    return weekStart;
  }

  protected readonly Date = Date;

  ifToday(param: Date | undefined): boolean {
    let now = new Date();
    console.log(param?.toDateString() == now.toDateString());
    console.log(now.toDateString());
    if(param !== undefined)
    return param.toDateString() === now.toDateString();
    return false;
  }

  addLessonDialog() {
    this.ref = this.dialogService.open(AddLessonDialogComponent, {
      header: 'Add lesson',
      width: '25rem',
      contentStyle: {"max-height": "500px", "overflow": "hidden", "min-height": "25rem"},
      baseZIndex: 10000,
    });

    this.ref.onClose.subscribe((data:any) => {
      this.currentWeek = weekNumber(new Date());
      this.initCalendar();
      this.loadLessons();
      this.weekStartDate = this.getWeekStartDate(this.currentWeek);
      this.weekEndDate = new Date(this.weekStartDate.getTime() + 6 * 24 * 60 * 60 * 1000);

    });
  }


}

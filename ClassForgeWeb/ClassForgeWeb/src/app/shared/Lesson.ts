import {User} from "./User";

export class Lesson{
  id: number;
  name: string;
  theme: string;
  homework: string;
  is_done: boolean;
  is_paid: boolean;
  length: number;
  timestamp!: Date;
  relation_id: string;
  student: User;
  constructor(data: any) {
    console.log(data);
    this.id = data.id || '';
    this.name = data.name || '';
    this.theme = data.theme || '';
    this.homework = data.homework || '';
    console.log(data.is_done);
    this.is_done = data.is_done || '';
    this.is_paid = data.is_paid || '';
    this.length = data.length || '';
    this.timestamp = new Date(data.timestamp);
    this.relation_id = data.relation_id || '';
    this.student = new User(data.student);
  }
}


// private Integer id;
// private String name;
// private String theme;
// private String homework;
// private Boolean is_done;
// private Boolean is_paid;
// private Integer length;
// private java.sql.Timestamp timestamp;
// private Integer relation_id;

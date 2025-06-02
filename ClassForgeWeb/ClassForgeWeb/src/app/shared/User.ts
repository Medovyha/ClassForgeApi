import {ContactInfo} from "./ContactInfo";
import {Info} from "./info";

export class User{
  id: number= 0;
  username: string= '';
  mail: string= '';
  created_at: number= 0;
  is_teacher: boolean= false;
  info_id: number= 0;
  contact_info: ContactInfo[] = [];
  info: Info = new Info({});
  constructor(data: any
              ) {
    this.id = data.id;
    this.username = data.username;
    this.mail = data.mail;
    this.created_at = data.created_at;
    this.is_teacher = data.is_teacher;
    if(data.info){
      this.info = new  Info(data.info);
    }
    if(data.contact_info ){
    for (let i = 0; i < data.contact_info.length; i++) {
      this.contact_info.push(new ContactInfo(data.contact_info[i]));}
    }



  }
}

// @Id
// @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
// @Column(name = "id")
// private Integer id;
//
// @Column(name = "username")
// private String username;
//
// @Column(name = "password")
// private String password;
//
// @Column(name = "mail")
// private String mail;
//
// @Column(name = "created_at", columnDefinition="timestamp")
// private java.sql.Timestamp created_at;
//
// @Column(name = "is_teacher")
// private boolean isTeacher;
//
// @OneToOne(mappedBy = "user")
// private Info info;
//
// @OneToOne(mappedBy = "student", fetch = FetchType.EAGER)
// private TeacherStudent teacher;
//
// @OneToMany(fetch = FetchType.EAGER, mappedBy = "teacher")
// private java.util.List<TeacherStudent> students;
//
// @OneToMany(mappedBy = "user")
// private java.util.List<ContactInfo> contact_info;

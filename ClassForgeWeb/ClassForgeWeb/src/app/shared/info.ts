export class Info{
  public name: string="";
  public surname: string="";
  public photo: string="";
  public description: string="";
  public user_id: number=0;
  public year: number;
  constructor(data: any) {
    this.name = data.name;
    this.surname = data.surname;
    this.photo = data.photo;
    this.description = data.description;
    this.user_id = data.user_id;
    this.year = data.year;
  }
}

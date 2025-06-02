export class ContactInfo{
  id: number;
  type: string;
  value: string;
  user_id: number;
  constructor(data: any  ){
    this.id=data.id;
    this.type=data.type;
    this.value=data.value;
    this.user_id=data.user_id;

  }
}

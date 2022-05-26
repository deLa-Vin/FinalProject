export class Resource {
  id: number;
  title: string;
  description: string;
  resourceUrl: string;

  constructor(
    id: number=0,
    title: string="",
    description: string="",
    resourceUrl: string=""

  ){
this.id=id;
this.title = title;
this.description = description;
this.resourceUrl = resourceUrl;
  }

}

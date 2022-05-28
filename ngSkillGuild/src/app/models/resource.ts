export class Resource {
  id: number;
  title: string;
  description: string;
  resourceUrl: string;
  resourceTypeId: number;

  constructor(
    id: number = 0,
    title: string = "",
    description: string = "",
    resourceUrl: string = "",
    resourceTypeId: number= 0

  ) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.resourceUrl = resourceUrl;
    this.resourceTypeId = resourceTypeId
  }

}

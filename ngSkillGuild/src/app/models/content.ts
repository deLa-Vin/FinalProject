export class Content {
    id: number;
    guildId: number;
    title: string;
    description: string;
    publishDate: string;
    isPublic: boolean;
    isLive: boolean;
    statusId: number;
    lastUpdated: string;
    createdByUserId: number;
    lengthMinutes: number;
    presentationDate: string;

    constructor(
        id: number = 0,
        guildId: number = 0,
        title: string = '',
        description: string = '',
        publishDate: string = '',
        isPublic: boolean = false,
        isLive: boolean = false,
        statusId: number = 0,
        lastUpdated: string = '',
        createdByUserId: number = 0,
        lengthMinutes: number = 0,
        presentationDate: string = ''
    ) {
        this.id = id;
        this.guildId = guildId;
        this.title = title;
        this.description = description;
        this.publishDate = publishDate;
        this.isPublic = isPublic;
        this.isLive = isLive;
        this.statusId = statusId;
        this.lastUpdated = lastUpdated;
        this.createdByUserId = createdByUserId;
        this.lengthMinutes = lengthMinutes;
        this.presentationDate = presentationDate;
    }
}

export class Guild {
    id: number;
    name: string;
    description: string;
    isPublic: boolean;
    coverImg: string | null;
    membershipCriteria: string;
    createdByUserId: number;
    createdOn: string | null;
    lastUpdated: string | null;

    constructor(
        id: number = 0,
        name: string = '',
        description: string = '',
        isPublic: boolean = false,
        coverImg: string | null = null,
        membershipCriteria: string = '',
        createdByUserId: number = 0,
        createdOn: string = '',
        lastUpdated: string | null = null
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isPublic = isPublic;
        this.coverImg = coverImg;
        this.membershipCriteria = membershipCriteria;
        this.createdByUserId = createdByUserId;
        this.createdOn = createdOn;
        this.lastUpdated = lastUpdated;
    }
}

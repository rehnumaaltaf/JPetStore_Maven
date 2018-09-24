export class GlBasicDTO {
    glId: number;
    partyName: PartyName[];
    partyId: number;
    glTypeRefId: number;
   // glCode: string;
   glCode: GLCode[];
    glName: string;
    glType: GlType[];
    // externalSystemRefName: ExternalGlMappingName[];
    glDesc: string
    statusName: string;
    glIsGroup: string;
    parentGl: string;
    parentGlId: number;
}

export class GlType {
     glTypeRefId: number;
     glTypeRefName: string;
}

export class PartyName {
     partyId: number;
     partyName: string;
}


export class GLCode {
     glId: number;
     glCode: string;
}



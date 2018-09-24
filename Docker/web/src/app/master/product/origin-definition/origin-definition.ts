export class OriginDefinition {
   /*
    originCode: string;
    originName: string;
    originFullname: string;
    geoName: string;
    estateName: string;
    originRegionName: string;
    originRegionMeanAboveSeaLevel: string;
    originCupProfileCode: string;
    originCupProfileName: string;*/
    // geoName: string;
    statusName: string;
    originName: string;
    originCode: string;
    originFullName: string;
    originDto: OriginDtoModel;
    originRegionDto: OriginRegionDtoModel[];
    originCupProfileDto: OriginCupProfileDtoModel[];
    externalSystemMapping: ExternalSystemMapping[];
}



export class OriginDtoModel {
    statusName: string;
    originFullName: string;
    originName: string;
    geoName: string;
    originCode: string;
}

export class OriginRegionDtoModel {
   originRegionCode: OriginRegionCode[];
    originRegionName: OriginRegionName[];
}

export class OriginCupProfileDtoModel {
     originCupProfileName: OriginCupProfileName[];
       originCupProfileCode: OriginCupProfileCode[];
}
export class ExternalSystemMapping {
    destinationSystem: DestinationSystem[];
    type: Type[];
    mapping:  Mapping[];
}


export class OriginRegionCode {

    originRegionCodeId: number;
    originRegionCode: string;
}
export class OriginRegionName {

    originRegionNameId: number;
    originRegionName: string;
}
export class OriginRegionMeanAboveSeaLevel {
    originRegionMeanAboveSeaLevelId: number;
    originRegionMeanAboveSeaLevel: string;
}
export class RegionEstate {
    regionEstateId: number;
    regionEstate: string;
}
export class OriginCupProfileName {
    originCupProfileNameId: number;
    originCupProfileName: string;
}
export class OriginCupProfileCode {
    originCupProfileCodeId: number;
    originCupProfileCode: string;
}
export class DestinationSystem {
    destinationSystemId: number;
    destinationSystem: string;
}

export class Type {
    typeId: number;
    type: string;
}
export class Mapping {
    mappingId: number;
    mapping: string;
}


export class UserRoleData {
    id: number;
    value: string;
    rolemappingId: number;
    mappingId: number;
    roleName : string;
    roleId : string;
    roleDesc : string;
    mappingIdtobedelted : FeatureIdDeleted[];
    modules : string;
    roleStatusName: string;
    statusName : string;
    selectedfeaturevalue: string;
    features : Features[];
    featureMapping : Features[];
    module : {id: number , moduleName: string, moduleDesc: string};
    feature : {featureDesc: string, id: number,value: string ,mappingId: number};
    featureValues : featureValues[];
    status : string;
    Message : string;
    
}

export class Features{

    featureId: number;
    featureName: string;
    featureDesc: string;
}
export class FeatureIdDeleted{
    public featureDesc: string;
    public id: number;
    public value: string;
    public mappingId: number;
}


export class featureValues{
    id: number;
    value:number;
}

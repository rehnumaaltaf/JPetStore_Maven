export class ExternalPacking {
    pkSecondaryPackingTypeId: Number;
    secondaryPackingTypeCode: String;
    secondaryPackingTypeName: string;
    secondaryPackingTypeDesc: String;
    secondaryPackingTypeIsBulk: String;
    fkStatusId: Number;
    statusName: String;
    action: String;
    checked: boolean;
    secPackAssocList: ExternalPackingMapping[];
}

export class ExternalPackingMapping {
    pkPackingAssocId: Number;
    fkSecondaryPackingTypeId: Number;
    fkOriginId: Number;
    fkOriginName: String;
    fkProdId: Number;
    fkProdName: String;
    fkPrimaryPackingTypeId: Number;
    fkPrimaryPackingTypeName: String;
    countOfPrimary: Number;
    fkStatusIdSec: Number;
}

export class ProductMapping {
    prodId: Number;
    prodCode: String;
    prodName: String;
}

export class PrimaryPackingList {
    internalPackingTypeId: Number;
    internalPackingTypeCode: String;
    internalPackingTypeName: String;
}

export class OriginList {
    originDto: OriginDto;
}

export class OriginDto {
    pkOriginId: Number;
    originCode: String;
    originName: String;
    originFullName: String;
}

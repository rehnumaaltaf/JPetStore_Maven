export class Grade {
    gradeId: number;
    gradeCode: string;
    gradeFullName: string;
    gradeName: string;
    product: Product;
    originList: OriginList[];
    isBlended: string;
    isBrand: string;
    brand: Brand ;
    isBase: string;
    baseGradeId: number;
    baseGradeCode: string;
    baseGradeName: string;
    processingType: ProcessingType ;
    processingSubType: ProcessingSubType;
    icoIndexId: number;
    icoIndexCode: string;
    icoIndexName: string;
    gradeGroupId: number;
    gradeGroupCode: string;
    gradeGroupName: string;
    gradeIsRaw: string;
    isTenderable: string;
    hedgeRatio: number;
    premiumOrDiscount: number;
    currencyId: number;
    currencyCode: string;
    currencyName: string;
    uomId: number;
    uomCode: string;
    uomName: string;
    intlGradeList: InternationalGrade[];
    externalSystemList: ExternalSystemMapping[];
    statusId: number;
    statusName: string;
    intldeletedmapid: MapId[];
    externaldeletedmapid: MapId[];
    deletedArbitrationMappingIds: MapId[];
    deletedGradeMappingIds:  MapId[];

}
export class Product {
    prodId: number;
    prodCode: string;
    prodName: string;
    attribute1: string;
    attribute2: string;

}
export class ProcessingType {
    pkProductProcessId: number;
    processingCode: string;
    processingName: string;
}
export class Brand {
    brandId: number;
    brandCode: string;
    brandName: string;
}
export class ProcessingSubType {
    processSubTypeRefId: number;
    subTypeCode: string;
    subTypeName: string;
}
export class MapId {
    mapid: number;
}
export class ExternalSystemMapping {
    externalSystemMappingId: number;
    externalSystemId: number;
    externalSystemName: string;
    externalSystemCode: string;
    attribute1: string;
    attribute2: string;
}
export class InternationalGrade {
    intlGradeMappingId:  number;
    intlId:  number;
    intlName:  string;
    intlCode:  string;
    intlDesc:  string;

}
export class OriginList {
    originMappingId: number;
    originId: number;
    originCode: string;
    originName: string;
}


export const active: String = 'Active';
export const inactive: String = 'InActive';
export const draft: String = 'Draft' ;
export const deactive: String = 'Deactive';
export const update: String = 'Update';
export const reactivate: String = 'Reactivate';
export const save: String = 'Save';



export const radioNo: String = 'N';
export const radioYes: String = 'Y';
export const radioYesValue: String = 'Yes';
export const radioNoValue: String = 'No';
export const washedtype: String = 'Washed';
export const draftsuccess: String = '1';
export const activesuccess: String = '2';
export const inactivesuccess: String = '3';
export const kendoHight: String = '600';
export const kendoHightcheckbox: String = '30'
export const MasterName: String = 'Product Grade';



// service urls

export const uomcurrencyurl: String = '/reference/wrapper/uomcurrency';
export const brandproducturl: String = '/product/wrapper/prodorigbrand';
export const originbasedobgradeurl: String = '/product/wrapper/origin/';
export const processingtypeurl: String = '/product/productservice/product/' ;
export const externalsystemurl: String = '/finance/glservice/gl/externalsystemref' ;
export const gradeurl: String = '/product/gradeservice/grade/';
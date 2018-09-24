export class ProductMasterInterface {
    prodId: number;
    parentProductId:string;
    prodCode: string;
    prodName: string;
    productName:string;
    prodFullName: string;
    parentProductName: string;
    processingCode: string;
   gradeMappingId :string;
   codeTypeId:string;
   deletedArbitrationMappingIds = [];
   deletedGradeMappingIds  = [];
    processingName:string;
    public processingSubType = [];
    specialityApplicable: string;
    isBaseProduct: string;  
    processingSubTypeId:string;
    icoCode:string;
    icoName:string;
    statusId:string;
	destinationName:string;
    destinationname:string;
    destinationSystemId :string;
    action: string;
    codeType: string;
    attribute1:string;
    attribute2:string;
     code:string;
   codedescription:string;
    arbitrationShortName: string;
arbitrationFullName:string;
arbitrationAgencyId:string;
mappingId:string;
    statusName: string;
    productArbitrationCollection : ProductArbitrationCollection[];
    gradeCodeMappingCollection :GradeCodeMappingCollection[];
    children:ProductMasterInterface[];
    productsubModel:ProductsubModel[];
}
export class ProductArbitrationCollection {
arbitrationShortName: string;
arbitrationFullName:string;
arbitrationAgencyId:string;
mappingId:string;
}
export class GradeCodeMappingCollection {
codeType: string;
code:string;
codedescription:string;
gradeMappingId:string;
codeTypeId:string;

}
export class ProductsubModel {
        prosubId: Number;
        prosubCode: string;
        prosubName: string;
}
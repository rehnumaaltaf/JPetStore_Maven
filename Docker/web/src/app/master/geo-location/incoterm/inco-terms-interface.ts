export class BaseRefDropdown {
contractTermsBaseRefId: Number;
contractTermsBaseCode: String;
contractTermsBaseName: String;
contractTermsBaseDesc: String;
}

// Basis Ref Dropdown
export class Basis {
contractTermsBasisRefId: Number;
contractTermsBasisCode: String;
contractTermsBasisName: String;
contractTermsBasisDesc: String;
}

// AddReduce Dropdown  in 2nd adn 3rd accordia
export class AddReduce {
contrTrmsAddreduceRefId: Number;
addReduceCode: String;
addReduceName: String;
}


// Incoterms  Main table for this master

export class Incoterm {
contractTermsId: Number;
contractTermsCode: String;
contractTermsName: String;
contractTermsDesc: String;
contractTermsBaseRefId: Number;
prodId: Number;
contractTermsBasisRefId: Number;
contractTermsBaseName: String;
contractTermsBasisName: String;
prodName: String;
contractTermsPurchaseDto: ContractTermsPurchase[];
contractTermsSalesDto: ContractTermsSales[];
statusName: String;
deletedPurchaseIds: Number[];
deletedSalesIds: Number[];
}

// Purchse class for 2nd Accordian
export class ContractTermsPurchase {
contractTermsPurchaseId: Number;
contractTermsId: Number;
costGroupId: Number;
costId: Number;
budgetInd: String;
contrTrmsAddreduceRefId: Number;
costName: String;
costGroupName: String;
addReduceName: String;
}

// Purchse class for 2nd Accordian
export class ContractTermsSales {
contractTermsSalesId: Number;  // primary key
contractTermsId: Number;   // primary key for inco term
costGroupId: Number;  //  group
budgetInd: String;  // radio button
contrTrmsAddreduceRefId: Number;  // add reduce
costId: Number;
costName: String;
costGroupName: String;
addReduceName: String;
}

// Cost Dropdown in 2nd adn 3rd accordian
export class Cost {
pkCostId: Number;
costName: String;
costCode: String;
}

// Cost Group DropDown  in 2nd adn 3rd accordia
export class CostGroup {
costGroupId: Number;
costGroupName: String;
costGroupCode: String;
costGroupDesc: String;
matrixDisplayName: String;
matrixTableName: String;
}



// copiied from add product for product treeview  component

export class ProductMasterInterface {
    prodId: number;
    parentProductId: string;
    prodCode: string;
    prodName: string;
    productName: string;
    prodFullName: string;
    parentProductName: string;
    processingCode: string;
   gradeMappingId: string;
   codeTypeId: string;
   deletedArbitrationMappingIds = [];
   deletedGradeMappingIds  = [];
    processingName: string;
    public processingSubType = [];
    specialityApplicable: string;
    isBaseProduct: string;
    processingSubTypeId: string;
    icoCode: string;
    icoName: string;
    statusId: string;
    destinationName: string;
    destinationname: string;
    destinationSystemId: string;
    action: string;
    codeType: string;
    attribute1: string;
    attribute2: string;
     code: string;
   codedescription: string;
    arbitrationShortName: string;
arbitrationFullName: string;
arbitrationAgencyId: string;
mappingId: string;
    statusName: string;
    productArbitrationCollection: ProductArbitrationCollection[];
    gradeCodeMappingCollection: GradeCodeMappingCollection[];
    children: ProductMasterInterface[];
    productsubModel: ProductsubModel[];
}
export class ProductArbitrationCollection {
arbitrationShortName: string;
arbitrationFullName: string;
arbitrationAgencyId: string;
mappingId: string;
}
export class GradeCodeMappingCollection {
codeType: string;
code: string;
codedescription: string;
gradeMappingId: string;
codeTypeId: string;

}
export class ProductsubModel {
        prosubId: Number;
        prosubCode: string;
        prosubName: string;
}


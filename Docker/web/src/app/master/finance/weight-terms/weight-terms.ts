export class WeightTermModel {
    statusName: string;
    weightTermsId: string;
    weightTermsCode: string;
    weightTermsName: string;
    weightTermsDesc: string;
    weightTermsIsFranchiseApplicable: string;
    weightTermsDefaultFranchiseValue: Number;
    fkFranchiseValueUnitId: Number;
    weightTermsIsToleranceApplicable: string;
    weightTermsDefaultToleranceValue: Number;
    fkToleranceValueUnitId: Number;
    fkStatusId: Number;
    franchiseValueUnitId: Number;
    toleranceValueUnitId: Number;
    franchiseValueUnitCode: String;
    toleranceValueUnitCode: String;
   // action: string;
}

// defining structuere for tolerance
    // origin service http://10.66.194.54:8098/terms/toleranceservice/tolerance
export class Tolerance {

    toleranceValueUnitCode: String
    toleranceValueUnitName: String;
    toleranceValueUnitId: Number;
    toleranceValueUnitDesc: String;
}


// defining structuere for franchise
// http://10.66.194.54:8098/terms/franchiseservice/franchise

export class Franchise {

    franchiseValueUnitCode: String
    franchiseValueUnitName: String;
    franchiseValueUnitId: Number;
    franchiseValueUnitDesc: String;
}




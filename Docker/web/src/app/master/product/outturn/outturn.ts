// Defining structre for outturn ration

export class Outturn {
    outturnRawGradeId: Number;
    deletedIds: Number[] = Array();
    statusName: String;
    outturnRawGradeDto: OutturnRawGradeDto = new OutturnRawGradeDto();
    outturnRatioDto: OutturnRation[];
}
// Variables for the frist accordian
export class OutturnRawGradeDto {
     outturnRawGradeId: Number;
    statusName: String;
    rawGradeCode: String; // input raw grade on html
    rawGradeName: String;
  //  rawGradeFullName: String;
    prodName: string;
  //  outturnRawGradeId: Number;
    prodId: Number; // produc id on html
    originId: Number; // orign id on html
   // rawGradeId: Number;
   // pkOriginId: Number;
   prodCode: String;
    gradeId: Number;
    originName: String;
}
// variable for the 2nd Accord on the Add/ Edit page
export class OutturnRation {
   // quantityPercentage: Number;
    quantityRatio: Number;
    abilityToBearRatio: Number;
    finishedGradeId: Number;
    finishedGradeCode: String;
    gradeCode: String;
    outturnRatioId: Number;
}


// defining structuere for product
    //   service http://ctrmsql:8082/productservice/product

export class Product {
    prodId: Number;
    prodCode: String;
    prodName: String;
    statusName: String;
}

// defining structuere for origin
    // origin service http://ctrmsql:8080/originservice/origin
export class Origin {

    originCode: String
    originName: String;
    pkOriginId: Number;
    originFullName: String;
    statusName: String;
}



// Definning strucutre for Grade

export class Grade {
    gradeId: Number;
    gradeName: String;
    gradeFullName: String;
    gradeCode: String;
    gradeIsRaw: String;
    statusName: String;
  /*  constructor (id: Number,  graCd: String, raw: String) {
        this.gradeId = id;
        this.gradeCode = graCd;
        this.gradeisRaw = raw;

    }; */
}



export class FinancialCalendar {
    fiscalYear: String;
    startYear: String;
    startMonth: String;
    endYear: String;
    endMonth: String;
    pkFinCalId: String;
    statusId: String;
    statusName: String;
    financialId: String;
    checked: Boolean;
    action: String;
    tenorFinancialArray: ForwardTenor[];
}

export class DropdownModel {
    code: String;
    value: String;
}

export class ForwardTenor {
    productId: ProductMapping[];
    monthShortCode: String;
    startDate: Date;
    endDate: Date;
    ctrmStatus: String;
    erpStatus: String;
    pkFinCalMappindId: String;
    startDateStr: String;
    endDateStr: String;
}

export class ProductMapping {
    fkProdId: Number;
   // prodCode: String;
    fkProdName: String;
  //  fkProdId: number;
  //  fkProdName: string;
}

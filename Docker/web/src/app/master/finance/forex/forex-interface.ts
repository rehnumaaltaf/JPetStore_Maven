export class ForexModel {
    baseCurrencyList: DropdownModel[];
    counterCurrencyList: DropdownModel[];
    frequencyList: DropdownModel[];
    tenorList: ForwardTenor[];
    tenorDropDownList: DropdownModel[];
    tenorDurationTypeList: DropdownModel[];
    tenorDayTypeList: DropdownModel[];
    status: string;
    forexCode: string;
    forexName: string;
    forexDesc: string;
    baseCurrency: string;
    counterCurrency: string;
    toValidate: string;
    frequency: string;
    statusId: number;
    forexId: number;
    message: string;
    action: String;
}

export class DropdownModel {
    code: string;
    value: string;
}

export class ForwardTenor {
    ticketerCode: String;
    tenorType: String;
    span: Number;
    tenorDurationType: String;
    tenorDayType: String;
}

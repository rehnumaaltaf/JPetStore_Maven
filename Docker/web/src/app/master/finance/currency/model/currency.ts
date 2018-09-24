export class CurrencyDetails {
    currencyCode: string;
    currencyName: string;
    currencyDesc: string;
    currencySymbol: string;
    pkCurrencyId: String;
    fkStatusId: number;
    statusName: String;
}

export class Currency {
    currencyCode: string;
    currencyName: string;
    currencyDesc: string;
    currencySymbol: string;
    pkCurrencyId: String;
    fkStatusId: number;
    statusName: String;
    isEdit: boolean;
    checked: boolean;
    action: String;
}

export class StatusModel {
    active: string;
    inactive: string;
    draft: string;
}
export const status: StatusModel =  {active: 'Active', inactive: 'InActive', draft: 'Draft'}

export class ConfirmBoxModel {
    message: string;
    header: string;
    isVisible:  Boolean;
    isCommentPresent: Boolean;
    comment: string;
}

export class CurrencyCodeSuggesion {
  currencyCode: string;
  toValidate: string;
}
export class CurrencyNameSuggesion {
  currencyName: string;
  toValidate: string;
}


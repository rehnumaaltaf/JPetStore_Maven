export class ViewBrandModel {
    brandId: number;
    brandCode: string;
    brandName: string;
    brandFullName: string;
    brandIsInternalCode: string;
    brandIsInternal: string;
    status: string;
    checked: Boolean;
}

export class BrandModel {
    action: string;
    brandId: number;
    brandCode: string;
    brandName: string;
    brandFullName: string;
    brandIsInternalCode: string;
    brandIsInternal: string;
    status: string;
    checked: Boolean;
    logoBlob: string;
    toValidate: string;
    isEdit?: Boolean;
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

export class BrandCodeSuggesion {
  brandCode: string;
  toValidate: string;
}
export class BrandNameSuggesion {
  brandCode: string;
  toValidate: string;
}

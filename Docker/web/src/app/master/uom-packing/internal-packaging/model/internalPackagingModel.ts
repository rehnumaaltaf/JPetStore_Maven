export class InternalPackagingModel {
    internalPackingTypeId: number;
    internalPackingTypeCode: string;
    internalPackingTypeName: string;
    status: string;
    internalPackingTypeDesc: string;
    isBulk: string;
    isBulkCode: string;
    internalPackingWeight: number;
    internalPackingUomCode: string;
    action: string;
    toValidate: string;
    selectedUomId: string;
    uombaseOption: DropDownModel[];
    checked: boolean;
}

export class DropDownModel {
    uomName: string;
    uomId: number;
}

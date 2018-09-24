export class PackingModel {
    statusName: string;
    pkPackingMaterialId: string;
    packingMaterialCode: string;
    packingMaterialName: string;
    packingMaterialDesc: string;
    packingMaterialWeight: string;
    packingMaterialIsBulk: string;
    uomValue: string;
    message: string;
    action: string;
    checked: boolean;
    toValidate: string;
    uomName: string;
    uombaseOption: DropDownModel[];
}

export class DropDownModel {
    uomName: string;
    uomId: number;
}





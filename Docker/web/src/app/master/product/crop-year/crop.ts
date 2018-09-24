export class CropYear {
    cropYearId: number;
    cropYearCode: string;
    cropYearName: string;
    cropYearDescription: string;
    action: string;
    productGroup: string;
    statusName: string;
    cropYearProductDto: CropYearProductDto[];

}

export class CropYearProductDto {

       cropYearProductAssoId: Number;
        prodId: Number;
        prodCode: string;
        prodName: string;
}

export class ProdectModel {
        prodId: Number;
        prodCode: string;
        prodName: string;
}

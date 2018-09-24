export class BlendMatrix {
    pkBlendTemplateId: number;
    templateCode: string;
    templateName: string;
    templateDesc: string;
    blendOutputList: BlendOutputArray [];
    blendInputList: BlendInputArray [];
    blendInputCertificationList: BlendCertificationArray [];
    statusName: String;
    checked: boolean;
    fkStatusId: number;
    action: String;
    toValidate: string;
}

export class BlendOutputArray {
    pkBlendOutputId: number;
    fkProdId: number;
    fkOriginId: number;
    fkGradeId: number;
    quantityPercentage: number;
    abilityToBearRatio: number;
    fkProdCertId: number;
    action: string;
    brandName: string;
    certificationPercentage: string;
    fkBrandId: number;
    fkStatusId: number;
    fkGradeName: string;
    fkOriginName: string;
    fkProdName: string;
    fkProdCertName: string;
    statusName: string;
    fkBlendTemplateId: number
}

export class BlendInputArray {
    pkBlendInputId: number;
    fkProdId: number;
    fkOriginId: number;
    fkGradeId: number;
    quantityPercentage: number;
    certificationPercentage: string;
    fkProdCertId: number;
    fkGradeName: string;
    fkOriginName: string;
    fkProdName:  string;
    fkProdCertName: string;
    fkStatusId: number;
    statusName: string

}

export class BlendCertificationArray {
    pkBlendInputCertificationId: number;
    fkProdCertId: number;
    certificationPercentage: number;
    fkProdCertName: string;
}
export class ProductMaster {
    prodId: Number;
    prodName: String;
    prodCode: String;
}

export class OriginMaster {
    pkOriginId: number;
    originName: string;
    originCode: string;
}

export class GradeMaster {
    gradeId: number;
    gradeCode: string;
    gradeName: string;
}

export class CertificationMaster {
    pkProdCertId: number;
    prodCertCode: string;
    prodCertName: string;
}

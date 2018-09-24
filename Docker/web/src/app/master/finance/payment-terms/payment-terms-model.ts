export class PaymentTerm {

    paymentTermId: number;
    payTermCode: string;
    payTermName: string;
    payTermDesc: string;
    paymentTermBaseId: number;
    paymentTermBase: string;
    atSightUsanceFlagId: number;
    atSightUsance: string;
    revocableIrrevocableFlagId: number;
    revocableIrrevocableFlag: string;
    confirmedNonconfirmedFlagId: number;
    confirmedNonconfirmedFlag: string;
    paymentBasisRefId: number;
    paymentBasisRef: string;
    dueDays: number;
    contingencyDays: number;
    printDescription: string;
    statusName: string;
    externalMappingCollection: ExternalSystemMapping[];

}
export class ExternalSystemMapping {
   mappingId: number;
   destinationSystem: string;
   externalSystemRefId: number;
   type: string;
   mapping: string;

  }

  export class PaymentBasecode {
      id: number;
      value: string;
      code: string;
      mappingId: number;
  }

  export class BasisCode {
      paymentBasisRefId: number;
      paymentBasisName: string;
      paymentBasisCode: string;
  }





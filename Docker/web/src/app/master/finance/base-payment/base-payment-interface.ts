export class BasePaymentInterface {
    basePaymentId: number;
    basePaymentTermId: number;
    baseTermCode: string;
    baseTermName: string;
    deletedNego = [];
    baseTermDescription: string;
   // baseUom: Number;
    baseTermCreditRisk: string;
    baseTermLCBased: string;
    action: string;
    statusName: string;
    baseNagotiationTerm : BaseNagotiationTerm[];
}
export class BaseNagotiationTerm {
nagotiationTermId: number;
nagotiationCode: string;
nagotiationName:string;
printDescription:string;

}
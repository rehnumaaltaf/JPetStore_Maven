export class TaxRateInterface {
   taxCodeUID:string;
   taxCode:string;
   taxName:string;
   taxDescription:string;
   taxCountryID:string;
   taxCountryName:string;
   govTaxRef:string;
   taxByLine:string;
   status:string;
   createdBy:string;
   createdDate:string;
   modifiedBy:string;
   modifiedDate:Date;
   taxRateNagotiationTerm : TaxRateNagotiationTerm[];
   taxRateDetails:TaxRateNagotiationTerm[];
    action: string;

    
}
export class TaxRateNagotiationTerm{
    effectiveFrom:Date;
    expirationDate:Date;
    taxRatePercentage:string;
    errorEffectiveFrom:string;
}
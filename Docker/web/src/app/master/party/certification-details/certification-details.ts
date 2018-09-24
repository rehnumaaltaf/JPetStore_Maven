export class CertificationDetails {
   pkProdCertId: number;
   prodCertCode: string;
   prodCertName: string;
   prodCertDescription: string;
   prodCertLogo: string;
   fkCertfBodyPartyId: Number;
   fkCertfBodyPartyName: string;
   registrationNumber: Number;
   validFrom: Date;
   validTo: Date;
   updatedValidFrom: Date;
   updatedValidTo: Date;
   statusName: string;
}

export class PartyMasterInterface {
    //Basic Details//
    addressName: string;
    addressAltAddress1: string;
    addressAltAddress2: string;
    addressAltZipCode: string;
    partyUomId:number;
     productId:number;
    addressAltCountry: string;
    addressLanguage: string;
     internalPartyCodeOrg: string;
    internalPartyNameOrg: string;
    partyProduct: string;
    partyUnit: string;
    limitAlertLevelparty: string;
    partyCurrency: string;
    partyUom: string;
    partyFixedQtyLimit: number;
    partyFixedVAlueLimit: number;
    fixedForwardTenor: number;
    diffQtyLimit: number;
    diffValueLimit: number;
    m2mLimit: number;
    varLimit: number;
    totalQtyLimit: number;
    totalValueLimit: number;
    limitOn: string;
    partyValidTo: string;
    diffForwardTenor: number;
    partyValidFrom: string;
    documentNumber: string;
    documentType: string;
    fkDocumentTypeRefId:number;
    fkDocumentRefId:number;
    documentName: string;
     origin: string;
    code: string;
    description: string;
    gradeMappingId: string;
    uniqueMapping:string;
    exchangeProduct: string;
    exchangeScreenName: string;
    exchangeHubName: string;
    exchangeContractSymbol: string;
    exchangeContractSize: string;
    exchangeMinimmumPriceCurrency: string;
    exchangeMinimmumPriceUom: string;
   // exchangeTradingCalender: string;
    exchangePriceQuotation: string;
    exchangePriceQuotationUom: string
    exchangeMinimmumPrice: string;
    exchangeContractSizeUom: string;
    holidayCalender: string;
    exchangePriceQuotationCurrency: string;
    netLimit: string;
    validFrom: string;
    validTo: string;
    limitType: string;
    partyInternalExternal: string;
    wareLocationName: string;
    wareLocationFullName: string;
    wareLocationTaxRegNo: string;
    wareLocationTaxRegDate: string;
    wareExchangeDate: string;
    wareAddress1: string;
    wareAddress2: string;
    wareZip: string;
    warePhone: string;
    wareEmail: string;
    wareFax: string;
    wareWebsite: string;
    warlocationCode: string;
    countryWareHOuse: string;
    isExchangeDesignated: string;
    isBrandedWareHouse: string;
    partyCode: number;
    partyName: string;
    partyFullName: string;
   
    isGroupCompany: string;
    selectGroup: string;
    geohierarchy: string;
    marketingDesk: string;
    pkPartyId: string;
    gradeId:string;
    creditAllowed: string;
    paymentTermId: string;
    partyType: string;
    partyId: string;
    groupCreditTenor: number;
    firsttransactionDate: string;
    gtprogram: string;
    functionalCurrency: string;
    OperationalCurrency: string;
    reportingUom: string;
    //bank popup
    parbank_default: string;
    parbank_AccountType: string;
    parbank_branch: string;
    parbank_country: string;
    parbank_country1: string;
    parbank_AccountName: string;
    parbank_BicCode: string;
    parbank_swiftCode: string;
    parbank_routing: string;
    parbank_website: string;
    parbank_fax: string;
    parbank_phone: string;
    parbank_zip: string;
    parbank_address1: string;
    parbank_address2: string;
    parbank_bankname: string;
    parbank_email: string;
    parbank_defaultshow: string;
    parbank_AccountTypeshow: string;
    parbank_branchshow: string;
    parbank_countryshow: string;
    parbank_country1show: string
    parbank_AccountNameshow: string;
    parbank_BicCodeshow: string;
    parbank_swiftCodeshow: string;
    parbank_routingshow: string;
    parbank_websiteshow: string;
    parbank_faxshow: string;
    parbank_phoneshow: string;
    parbank_zipshow: string;
    parbank_address1show: string;
    parbank_address2show: string;
    parbank_banknameshow: string;
    parbank_emailshow: string;
    //bank popup end
    //contact popup
    parcontact_department: string;
    parcontact_product: string;
    parcontact_fullname: string;
    parcontact_designation: string;
    parcontact_email: string;
    parcontact_phonework: string;
    parcontact_mobile: string;
    parcontact_fax: string;
    parcontact_default: string;

    parcontact_departmentshow: string;
    parcontact_productshow: string;
    parcontact_fullnameshow: string;
    parcontact_designationshow: string;
    parcontact_emailshow: string;
    parcontact_phoneworkshow: string;
    parcontact_mobileshow: string;
    parcontact_faxshow: string;
    parcontact_defaultshow: string;

    //contact popup end


productName : string;
    partyContactFullname: string;
    partyContactDesignation: string;
    partyContactEmail: string;
    partyContactWorkPhone: string;
    partyContactMobile: string;
    partyContactFax: string;
    partyContactIsDefault: string;
    partyDepartment: string;
    departmentCode: string;

   
    partyRating: string;
    paraddr_name: string;
    paraddr_address1: string;
    paraddr_address2: string;
    paraddr_country: string;
    paraddr_zipcode: string;
    paraddr_email: string;
    paraddr_phone: string;
    paraddr_fax: string;
    paraddr_weburl: string;
    groupCreditLimit: number;
    companyRegistration: string;
    companyRegistrationDate: string;
    taxRegistrationNo: string;
    taxRegistrationDate: string;
    product: string;
    partyGradeName: string;
    partyGradeDesc: string;
    paymentTerm: string;
    partyClassification: string;
    internalGradeName: string;
    plantCode: string;
    plantName: string;
    nameLanguage: string;
    //Address pop up
    defaultaddValueToShow: string;
    addressTypeToShowinModal: string;
    addressNameMOdalToshow: string;
    Address1ModalToShow: string;
    Address2ModalToShow: string;
    CountryModalTOShow: string;
    zipToShowModal: string;
    emailToShowMOdal: string;
    phoneToShowMOdal: string;
    faxToShowModal: string;
    websiteUrlToShowMOdal: string;
    //
    destinationSystem: string;
    externalSystemMappingType: string;
    externalSystemMappingVendor: string;
    externalSystemMappingCustomerCode: string;
    plantFullName: string;
    taxRegistrationNumber: string;
    address1: string;
    address2: string;
    zipCode: string;
    numberOfCopies:string;
    tradingAccountNumber: string;
    commmission: string;
    paraddr_defaddr: string;
    paraddr_addrtype: string;
    paraddr_addrtypeToShowMOdal: string;
    paraddr_defaddrtypeToShowMOdal: string;
    commissionUom: string;
    alternativeName: string;
    alternativeFullName: string;
    countryDefault: string;
    phoneDefault: string;
    emailDefault: string;
    gradeNameId:string;
    faxDefault: string;
    websiteDefault: string;
    internalPartyCode: string;
    internalPartyName: string;
    creditLimitProduct: string;
    unit: string;
    unitName:string;
    limitAlertLevel: string;
    creditCurrency: string;
    Factoring: number;
    FactoringEndDate: string;
    partyBasicDetails: PartyBasicDetails = new PartyBasicDetails();
    partyTranslationsToCreate: PartyTranslationsToCreate = new PartyTranslationsToCreate();
    partyTranslations: PartyTranslations [];
    partyGradeDefinition: PartyGradeDefinition = new PartyGradeDefinition();
    brokerDetails: BrokerDetails = new BrokerDetails();
    paymentTermDetails: PaymentTermDetails = new PaymentTermDetails();
    //partyAddress : PartyAddress[];
    partyAddress: PartyAddress[];
    partyBankAccountDetails: PartyBankAccountDetails [];
    partyContacts: PartyContacts [];
    partyAddressTOCReate: partyAddressTOCReate = new partyAddressTOCReate();
    aartyBankAccountDetailsToCreate: PartyBankAccountDetailsToCreate = new PartyBankAccountDetailsToCreate();
    partyContactsToCreate: PartyContactsToCreate = new PartyContactsToCreate();
    internalGradeMapping: internalGradeMapping[];
    plantDetails: PlantDetails[];
    externalSystemMapping: ExternalSystemMapping[];
    creditLimit: CreditLimit[];
    partyLimit: PartyLimit[];
    translatedAddress: TranslatedAddress[];
    warehouseLocation: WarehouseLocation[];
    exchangeDetails: exchangeDetails[];
    partyDocuments: PartyDocuments[];
    address: Address[];
    contact: Contact[];
    bank: Bank[];
}
export class internalGradeMapping {
    origin: string;
    code: string;
    gradeNameId : string;
    uniqueMapping:string;
    description: string;
    gradeMappingId: string;

}
export class PartyTranslationsToCreate {
    language:string;
    alternativeName:string;
    uniqueMapping:string;
    alternativeFullName:string;
}
export class PartyTranslations {
    language:string;
    alternativeName:string;
    uniqueMapping:string;
    alternativeFullName:string;
}
export class PlantDetails {
    plantCode: string;
    plantName: string;
    plantFullName: string;
    taxRegistrationNumber: string;
    address1: string;
    address2: string;
    uniqueMapping:string;
    zipCode: string;
    countryDefault: string;
    phoneDefault: string;
    emailDefault: string;
    faxDefault: string;
    websiteDefault: string;
}
export class ExternalSystemMapping {
    destinationSystem: string;
    externalSystemMappingType: string;
    externalSystemMappingVendor: string;
    uniqueMapping:string;
    externalSystemMappingCustomerCode: string;
}
export class CreditLimit {
    internalPartyCode: string;
    internalPartyName: string;
    creditLimitProduct: string;
    unit: string;
    unitName:string;
    limitAlertLevel: string;
    creditCurrency: string;
    Factoring: number;
    FactoringEndDate: string;
    netLimit: string;
    uniqueMapping:string;
    validFrom: string;
    validTo: string;
    limitType: string;
}
export class PartyLimit {
    internalPartyCodeOrg: string;
    internalPartyNameOrg: string;
    partyProduct: string;
    partyUnit: string;
    limitAlertLevelparty: string;
    partyCurrency: string;
    partyUom: string;
    partyFixedQtyLimit: number;
    partyFixedVAlueLimit: number;
    uniqueMapping:string;
    fixedForwardTenor: number;
    diffQtyLimit: number;
    diffValueLimit: number;
    m2mLimit: number;
    varLimit: number;
    totalQtyLimit: number;
    totalValueLimit: number;
    limitOn: string;
    partyValidTo: string;
    diffForwardTenor: number;
    partyValidFrom: string;
}
export class TranslatedAddress {
    addressName: string;
    addressAltAddress1: string;
    addressAltAddress2: string;
    addressAltZipCode: string;
    uniqueMapping:string;
    addressAltCountry: string;
    addressLanguage: string;

}
export class WarehouseLocation {
    wareLocationName: string;
    wareLocationFullName: string;
    wareLocationTaxRegNo: string;
    wareLocationTaxRegDate: string;
    wareExchangeDate: string;
    wareAddress1: string;
    wareAddress2: string;
    wareZip: string;
    warePhone: string;
    wareEmail: string;
    wareFax: string;
    uniqueMapping:string;
    wareWebsite: string;
    warlocationCode: string;
    countryWareHOuse: string;
    isExchangeDesignated: string;
    isBrandedWareHouse: string;
}
export class exchangeDetails {
    exchangeProduct: string;
    exchangeScreenName: string;
    exchangeHubName: string;
    exchangeContractSymbol: string;
    exchangeContractSize: string;
    exchangeMinimmumPriceCurrency: string;
    exchangeMinimmumPriceUom: string;
   // exchangeTradingCalender: string;
    exchangePriceQuotation: string;
    exchangePriceQuotationUom: string
    exchangeMinimmumPrice: string;
    uniqueMapping:string;
    exchangeContractSizeUom: string;
    holidayCalender: string;
    exchangePriceQuotationCurrency: string;
    netLimit: string;
    validFrom: string;
    validTo: string;
    limitType: string;
}
export class PartyDocuments {
    numberOfCopies:string;
    documentNumber: string;
    documentType: string;
    fkDocumentTypeRefId:number;
    uniqueMapping:string;
    fkDocumentRefId:number;
    documentName: string;
}

export class Address {
    defaultaddr: boolean;
    addresstype: string;
    name: string;
    address1: string;
    address2: string;
    country: string;
    zipcode: string;
    email: string;
    phone: string;
    fax: string;
    weburl: string;

}
export class Contact {

}
export class Bank {

}
export class PartyBasicDetails {
    partyCode: string;
    partyName: string;
    partyFullName: string;
    parentParty: string;
    geoCountry: string;
	marketingDeskName:string;
    geohierarchy:string;
    partyClassification:string;
    marketingDesk: string;
    partyRatingCode: string;
    partyRatingName: string;
    partyRatingId :string;
    groupCreditLimit: string;
    groupCreditTenorInDays: string;
    partyTypes: string;
    partyTypeIds = [];
    partyClassifications: string;
    partyClassificationIds = [];
    partyTaxRegNo: string;
    partyTaxRegDate: string;
    isGtpApplicableInd: string;
    functionalCurrency: string;
    operationalCurrency: string;
    defaultReportingUOM: string;
    fkStatusId: string;
    partyInternalExternal: string;
    isGroupCompany: string;
    selectGroup: string;
    countryId: string;
    marketingDeskId: string;
    creditAllowed: string;
    partyType: string;
    companyRegistration: string;
    companyRegistrationDate: string;
    firsttransactionDate: string;
    functionalCurrencyId: string;
    operationalCurrencyId: string;
    defaultReportingUOMId: string;
    statusName: string;
};
// export class PartyAddress {
//     accountTypeCode : string;
//     accountTypeName: string;
//     partyBankAddr1: string;
//     partyBankAddr2: string;
//     partyBankZipCode : string;
//     fkBankBranchCountryGeoId : string;
//     country : string;
//     fkStatusId : string;
//     status : string;
//     partyBankPhone : string;
//     partyBankEmail : string;
//     partyBankFax : string;
//     partyBankWebsite : string;
//     parbank_default : string;
//     parbank_AccountName: string;
//     parbank_branch : string;
//     parbank_AccountType: string;
//     parbank_AccountNumber : string;
//     parbank_BicCode : string;
//     parbank_swiftCode : string;
//     parbank_routing : string;
// }
export class PartyAddress {
    addressType: string;
    country: string;
    isDefaultAddress: string;
    addressTypeId: string;
    name: string;
    addressOne: string;
    addressTwo: string;
    zipCode: string;
    countryId: string;
    phone: string;
    email: string;
    fax: string;
    website: string;

}
export class partyAddressTOCReate {
    addressType: string;
    country: string;
    isDefaultAddress: string;
    addressTypeId: string;
    name: string;
    addressOne: string;
    addressTwo: string;
    zipCode: string;
    countryId: string;
    phone: string;
    email: string;
    fax: string;
    website: string;

}
export class PartyBankAccountDetails {
    partyBankBranch: string;
    accountTypeCode: string;
    accountTypeName: string;
    partyBankAccountNo: string;
    partyBankBicCode: string;
    partyBankSwiftCode: string;
    partyBankRouting: string;
    partyBankAddr1: string;
    partyBankAddr2: string;
    partyBankZipCode: string;
    country: string;
    status: string;
    partyBankPhone: string;
    partyBankEmail: string;
    partyBankFax: string;
    partyBankWebsite: string;
    isDefault: string;
    partyAccountName: string;
    partyAccountType: string;
    countryId: string;
    statusId: string;
}
export class PartyBankAccountDetailsToCreate {
    partyBankBranch: string;
    accountTypeCode: string;
    accountTypeName: string;
    partyBankAccountNo: string;
    partyBankBicCode: string;
    partyBankSwiftCode: string;
    partyBankRouting: string;
    partyBankAddr1: string;
    partyBankAddr2: string;
    partyBankZipCode: string;
    country: string;
    status: string;
    partyBankPhone: string;
    partyBankEmail: string;
    partyBankFax: string;
    partyBankWebsite: string;
    isDefault: string;
    partyAccountName: string;
    partyAccountType: string;
    countryId: string;
    statusId: string;
}
// export class PartyBankAccountDetails {
//     accountTypeCode : string;
//     accountTypeName: string;
//     partyBankAddr1 : string;
//     partyBankAddr2 : string;
//     partyBankZipCode : string;
//     fkBankBranchCountryGeoId : string;
//     country : string;
//     fkStatusId : string;
//     status : string;
//     partyBankPhone: string;
//     partyBankEmail : string;
//     partyBankFax : string;
//     partyBankWebsite : string;
//     parbank_default : string;
//     parbank_AccountName : string;
//     parbank_branch : string;
//     parbank_AccountType : string;
//     parbank_AccountNumber : string;
//     parbank_BicCode : string;
//     parbank_swiftCode : string;
//     parbank_routing : string;
// }
// export class partyContacts {
//     deptCode: string;
//     productName: string;
//     parcontact_department: string;
//     parcontact_product: string;
//     parcontact_fullname: string;
//     parcontact_designation: string;
//     parcontact_email: string;
//     parcontact_phonework: string;
//     parcontact_mobile: string;
//     parcontact_fax: string;
//     parcontact_default: string;
// }
export class PartyContacts{
     productName : string;
    partyContactFullname: string;
    partyContactDesignation: string;
    partyContactEmail: string;
    partyContactWorkPhone: string;
    partyContactMobile: string;
    partyContactFax: string;
    partyContactIsDefault: string;
    partyDepartment: string;
    departmentCode: string;
    product: string;
}
export class PartyContactsToCreate{
     productName : string;
    partyContactFullname: string;
    partyContactDesignation: string;
    partyContactEmail: string;
    partyContactWorkPhone: string;
    partyContactMobile: string;
    partyContactFax: string;
    partyContactIsDefault: string;
    partyDepartment: string;
    departmentCode: string;
    product: string;
}
export class PartyGradeDefinition {
    paymentTermId:string;
    prodId: string;
    productName: string;
    paymentTermCode:string;
    partyGradeName: string;
    partyGradeDescription: string;
}
export class PaymentTermDetails {
    paymentTermId: string;
    paymentTerm: string;
    paymentTermDesc: string;
}
export class BrokerDetails {
    tradingAccountNumber: string;
    commission: number;
    fkCommissionUomId: string;
    commissionUOM: string;
    fkStatusId: string;
    status: string;
}

export class Addresseeeee {
    paraddr_name: string;
    // paraddr_address1:string;
    // paraddr_address2:string;
    // paraddr_country:string;
    // paraddr_zipcode:string;
    // paraddr_email:string;
    // paraddr_phone:string;
    // paraddr_fax:string;
    // paraddr_weburl:string;
}
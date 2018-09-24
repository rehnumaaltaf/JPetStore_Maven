import { AccordionModule } from 'ngx-bootstrap';
import { TreeviewItem, TreeviewConfig } from 'ngx-treeview';
import { Component, EventEmitter, OnInit,OnChanges, OnDestroy, Output, ViewChild, IterableDiffers, DoCheck } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { PartyMasterInterface,CreditLimit } from '../party-interface';
import { PartyMasterService } from '../party-master/../service/party-master.service';
import { AddMultiPartyGradeComponent } from './add-internal-grade-multiple';
import { listingpageproduct } from '../../../../shared/interface/router-links';
import { listingpageparty } from '../../../../shared/interface/router-links';
import { AddMultiPantComponent } from './add-plant-multiple';
import { AddMultiCreditSystemComponent } from './add-creditLimit-multiple';
import { AddMultiPartySystemComponent } from './add-partyLimit-multiple';
import {AddMultiExternalSystemComponent} from './add-externalSytem-multiple';
import { AddMultiExchangeComponent } from './add-exchange-multiple';
import { AddMultiWareHouseSystemComponent } from './add-warehouse-multiple';
import { AddMultiAddressSystemComponent } from './add-Address-multiple';
import { AddMultiDocument } from './add-Document-multiple';
import { CanComponentDeactivate } from '../../../../shared/guards/can-deactivate-guard';
import { FormGroup, FormControl, FormArray, FormBuilder, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { DropdownTreeviewSelectComponent } from '../../../../shared/dropdown-treeview-select/dropdown-treeview-select.component';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { Addresseeeee } from '../party-interface';


@Component({
  selector: 'app-add-party-master',
  templateUrl: './add-party-master.component.html',
  styleUrls: ['./add-party-master.component.css']
})
export class AddPartyMasterComponent implements OnInit, OnDestroy,CanComponentDeactivate {
  partyMasterForm: FormGroup;
  party_address: FormGroup;
  isActionPerformed = false;
  addressModalVlaue: PartyMasterInterface;
  bankModalVlaue: PartyMasterInterface;
  //public contactModalVlaue:PartyMasterInterface ;
  party_addr = [];
  party_addrToSave = [];
  party_bank = [];
  party_banktoSave = [];
  party_contact = [];
  party_contactToSave = [];
  public party_addr1 = [];
  store_addr = [];
  count: number;
  paymentDesc: string;
  showModal:boolean;
  companyregdate: Date = new Date(2000, 2, 10);
  taxregdate: Date = new Date(2000, 2, 10);
  factoringEndDate: Date = new Date(2000, 2, 10);
  validFrom: Date = new Date(2000, 2, 10);
  validTo: Date = new Date(2000, 2, 10);
  firsttransactiondate:Date = new Date(2000, 2, 10);
  partyVaidtODate: Date = new Date(2000, 2, 10);
  partyVaidFromDate: Date = new Date(2000, 2, 10);
  gradeMappingdel = [];
  addPartyButtons:boolean= false;
  partySubModel222 = [];
  vendorModel = [];
  public taxRegistrationDate;
  tradingCalenderModel = [];
  externalMappingError:boolean =false;
  partyDetailsObjUpdate: PartyMasterInterface = new PartyMasterInterface();
  partyDetailsObj: PartyMasterInterface = new PartyMasterInterface();
  partyTranslationssssss:PartyMasterInterface = new PartyMasterInterface();
  partyDetailsTranslation: PartyMasterInterface = new PartyMasterInterface();
  partyBasicDetails: PartyMasterInterface = new PartyMasterInterface();
  addressModal: PartyMasterInterface = new PartyMasterInterface();
  bankModalVlaues: PartyMasterInterface = new PartyMasterInterface();
  bankModal: PartyMasterInterface = new PartyMasterInterface();
  contactModal: PartyMasterInterface = new PartyMasterInterface();
  creditLimit = [];
  contactModalVlaues: PartyMasterInterface = new PartyMasterInterface();
  contactModalVlaue: PartyMasterInterface = new PartyMasterInterface();

  /*new code */
  credit_partyname:Boolean=true;
  financialCalendarModelsCredit:PartyMasterInterface[];
  financialCalendarModelsParty:PartyMasterInterface[];
  financialCalendarModelsWare:PartyMasterInterface[];
  financialCalendarModelsEx:PartyMasterInterface[];
  financialCalendarModelsdoc:PartyMasterInterface[];
  financialCalendarModelsPlant:PartyMasterInterface[];
  financialCalendarModelsExternal:PartyMasterInterface[];
  financialCalendarModelsAddress:PartyMasterInterface[];


  /*end */
  // public contactModalVlaue: PartyMasterInterface = new PartyMasterInterface();
  //validation variables declaration.
  ineternal_exeternal: boolean;
  indexValue: string;
  partyName: boolean;
  geohierarchy: boolean;
  marketingDesk: boolean;
  creditAllowedShow: boolean;
  FactoringEndDate: boolean;
  limitType: boolean;
  partyUomId:number;
  productId:number;
  public companyRegistrationDate;
  commissionUom: boolean;
  partyTranslation1233=[];
  reportingUom: boolean;
  OperationalCurrency: boolean ;
  functionalCurrency: boolean;
  // ends
  public pageName;
  subscription: Subscription;
  whentoAdd: string;
  groupCreditLimit: boolean;
  pageTitle;
  selectedItem;
  partyNamedefaultParty: string;
  partySubModel = [];
  copy = {};
  partyNamedefault: string;
  paraddr_defaddr: string;
  parbank_default: string;
  parcontact_default: string;
  filterParty = [];
  public firsttransactionDate;
  addressmulti : PartyMasterInterface =  new PartyMasterInterface;
  partyDataList: PartyMasterInterface = new PartyMasterInterface();
  commisionList: PartyMasterInterface[];
  docItem: PartyMasterInterface[];
  docItemType: PartyMasterInterface[];
  languageList: PartyMasterInterface[];
  limitAlertLevel: PartyMasterInterface[];
  departmentList: PartyMasterInterface[];
  limitOnList: PartyMasterInterface[];
  accountTypeList: PartyMasterInterface[];
  partyRatingList: PartyMasterInterface[];
  partyClassificationList: PartyMasterInterface[];
  addressType: PartyMasterInterface[];
  marketingDeskList: PartyMasterInterface[];
  currencyList: PartyMasterInterface[];

  
 ifedit: boolean = false;
  req_partygradediv: boolean = false;
  internalPartyNameOrg:string;
  req_internalGradeMApping: boolean = false;
  req_defaultPayment: boolean = false;
  req_plant: boolean = false;
  req_partydiv: boolean = false;
  req_credit: boolean = false;
  req_partyLimits: boolean = false;
  req_wareHouse: boolean = false;
  req_exchange: boolean = false;
  req_document: boolean = false;
  req_broker: boolean = false;
  req_partyGrpCredit: boolean = false;
  whenAddrModalSaved: boolean = false;
  whencontactModalSaved: boolean = false;
  whenPartyCodeinLimit:boolean = false;
  partyId:number;
  update_address: boolean = false;
  update_bank: boolean = false;
  emailvalidation: boolean = false;
  update_addr: PartyMasterInterface;
  update_bankdata: PartyMasterInterface;
  update_contact: boolean = false;
  update_contactdata: PartyMasterInterface;
  filterPayment = [];
  req_partyGrpCreditTenor: boolean = false;
  address_index: number;
  partyTypeManadatory: boolean;
  bank_index: number;
  contact_index: number;
  creditAllowed: string;
  isExchangeDesignated: string;
  partyCheck : PartyMasterInterface =  new PartyMasterInterface();
  groupCreditTenor: boolean;
  fisrtDateWithOlam: boolean = false;
  gtpFlag: boolean = false;
  whenToDraft:boolean = false;
  whenL2Approved:boolean= false;
  locationCodeList: string;
  isBrandedWareHouse: string;
  whenbankModalSaved: boolean = false;
  req_functionalCurrency: boolean = false;
  req_OperationalCurrency: boolean = false;
  req_reportingUom: boolean = false;
  creditAllowdflag: boolean = false;
  whenStatusActive:boolean = false;
  addressError: boolean = false;
  isGroupCompany: string;
  whenActive:boolean = false;
  gtprogram: string;
  selectgrpList: PartyMasterInterface[];
  unitList: PartyMasterInterface[];
  partyCodeList: PartyMasterInterface[];
  addressName: PartyMasterInterface[];
  holidayCalenderList: PartyMasterInterface[];
  partyTypeList: PartyMasterInterface[];
  countryList: PartyMasterInterface[];
  productList: PartyMasterInterface[];
  paymentTermLstList: PartyMasterInterface[];
  destinationList: PartyMasterInterface[];
  differ: any;
  req_primaryContact: boolean = true;
  partyaddress: PartyMasterInterface;

  warehouseLocationdata:PartyMasterInterface[];

  @ViewChild('addressmodals') public addressmodals: ModalDirective;
  @ViewChild('bankmodal') public bankmodal: ModalDirective;
  @ViewChild('contactmodals') public contactmodals: ModalDirective;
  @ViewChild('validate') public validate: ModalDirective;
  @ViewChild('duplicatedError') public duplicatedError: ModalDirective;
  financialCalendarModelsGrade: PartyMasterInterface[];
  public datasource = [];
  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  item: FormGroup;
  tradingCalender = [{ 'calenderId': 1, 'calenderMonth': 'January' }, { 'calenderId': 2, 'calenderMonth': 'February' }, {
    'calenderId': 3, 'calenderMonth': 'March'
  }, { 'calenderId': 4, 'calenderMonth': 'April' }, { 'calenderId': 5, 'calenderMonth': 'May' },
    { 'calenderId': 6, 'calenderMonth': 'June' }, { 'calenderId': 7, 'calenderMonth': 'July' }, { 'calenderId': 8, 'calenderMonth': 'August' },
    { 'calenderId': 9, 'calenderMonth': 'September' }, { 'calenderId': 10, 'calenderMonth': 'October' }, { 'calenderId': 11, 'calenderMonth': 'November' },
    { 'calenderId': 12, 'calenderMonth': 'December' }];
  limitTypeDrop = [{ 'limitTypeId': 1, 'limitTypename': 'Permanent' }, { 'limitTypeId': 2, 'limitTypename': 'Temporary' }]
  paraddr = { "defaultaddr": "", "addresstype": "", "name": "", "address1": "", "address2": "", "country": "", "zipcode": "", "email": "", "phone": "", "fax": "", "weburl": "" };
  // productsubModel: ProductsubModel[];
  productsubModel1 = [{ 'prosubId': 1, 'prosubCode': 'P101', 'prosubName': 'Trader' }, { 'prosubId': 3, 'prosubCode': 'P103', 'prosubName': 'Broker' }, { 'prosubId': 4, 'prosubCode': 'PROD4', 'prosubName': 'WareHouse' }, { 'prosubId': 5, 'prosubCode': 'PROD5', 'prosubName': 'Exchange' }, { 'prosubId': 6, 'prosubCode': 'PROD6', 'prosubName': 'ROASTERS' }];
  productsubModel = [{ 'prosubId': 1, 'prosubCode': 'P101', 'prosubName': 'Origin' }, { 'prosubId': 3, 'prosubCode': 'P103', 'prosubName': 'Market' }, { 'prosubId': 4, 'prosubCode': 'PROD4', 'prosubName': 'Origin&Market' }, { 'prosubId': 5, 'prosubCode': 'PROD5', 'prosubName': 'Warehouse' }];
  vanedorsubModel = [{ 'vendorSubId': 1, 'prosubCode': 'P101', 'vendorName': 'Customer' }, { 'vendorSubId': 3, 'prosubCode': 'P103', 'vendorName': 'Vendor' }];
  constructor(private router: Router, public partyMasterService: PartyMasterService, private _routeParams: ActivatedRoute, private fb: FormBuilder, private route: ActivatedRoute, differs: IterableDiffers) {
    this.differ = differs.find([]).create(null);
  }

  addStep() {
    const control = <FormArray>this.partyMasterForm.controls['steps'];
    control.push(new FormControl(this.partyMasterForm.get('stepTextArea').value))
    control.push(new FormControl(this.partyMasterForm.get('xxx').value))
    this.partyMasterForm.get('stepTextArea').reset()
    this.partyMasterForm.get('xxx').reset()
  }

  ngOnInit() {

    //   this.item = this.fb.group({
    //   stepTextArea: [''],
    //   xxx:[''],
    //   steps: this.fb.array([])
    // });
    this.emailvalidation = false;
    this.getDropDownDepartment();
    this.getDropDownForDocumentName();
    this.getDropDownForDocumentType();
    this.getDropDownForCommisionUom();
    this.getDropDownForLanguage();
    this.getDropDownLocationCode();
    this.getDropDownLimitAlert();
    this.getDropDownAccountType();
    this.getDropDownAccountType();
    this.getDropDownPartyRating();
    //this.getDropDownpartyClassification(data);
    this.getDropDownUnit();
    this.getDropPartyCode();
    this.getDropDownAddressName();
    this.getDropDownCurrency();
    this.getDropDownHolidayCalender();
    this.getAssressTypeDropDown();
    this.getDropDownMarketingDesk();
    this.getDropDownPartyType();
    this.getDropDownCountry();
    this.getDropDownProduct();
    this.getDropDownPaymentTerm();
    this.getDropDownDestionSystem();
    //this.getDropDownOrigin();
    // this.getDropDownTradingCalender();
    window.scrollTo(0, 0);
    
      this.partyId = this.route.snapshot.params['pkPartyId'];
    
    this.whentoAdd = 'true';
    this.pageTitle = 'Add Party';
    if (this.partyId != null) {
      this.req_primaryContact = false;
      this.pageTitle = 'Edit Party';
      this.partyMasterForm = this.fb.group({
        firsttransactionDate: '',
      gtprogram: '',
      functionalCurrency: '',
      OperationalCurrency: '',
      reportingUom: '',
      partyInternalExternal: '',
      exchangeContractSizeUom: '',
      paraddr_defaddr: '',
      paraddr_addrtype: '',
      paraddr_name: '',
      paraddr_address1: '',
      paraddr_address2: '',
      paraddr_country: '',
      paraddr_zipcode: '',
      countryWareHOuse: '',
      gradeNameId : '',
      paraddr_email: '',
      addressName: '',
      paraddr_phone: '',
      paraddr_fax: '',
      paraddr_weburl: '',
      // xxx:[''],
      steps: this.fb.array([]),
      groupCreditTenor: '',
      partyCode: '',
      partyName: '',
      partyFullName: '',
      isGroupCompany: '',
      alternativeName: '',
      alternativeFullName: '',
      selectGroup: '',
      geohierarchy: '',
      marketingDesk: '',
      creditAllowed: '',
      nameLanguage: '',
      addressLanguage: '',
      partyRating: '',
      groupCreditLimit: '',
      companyRegistration: '',
      companyRegistrationDate: '',
      taxRegistrationNo: '',
      taxRegistrationDate: '',
      product: '',
      warlocationCode: '',
      partyGradeName: '',
      partyType: '',
      partyGradeDesc: '',
      partyClassification: '',
      origin: '',
      tradingAccountNumber: '',
      commmission: '',
      commissionUom: '',
      internalGradeName: '',
      paymentTerm: '',
      parbank_default: '',
      parbank_AccountType: '',
      parbank_branch: '',
      parbank_country: '',
      parbank_country1: '',
      parbank_AccountName: '',
      parbank_email: '',
      parbank_BicCode: '',
      parbank_swiftCode: '',
      parbank_routing: '',
      parbank_website: '',
      parbank_fax: '',
      parbank_phone: '',
      parbank_zip: '',
      parbank_bankname: '',
      parbank_address1: '',
      parbank_address2: '',
      internalPartyCode: '',
      internalPartyName: '',
      creditLimitProduct: '',
      unit: '',
      //contact popup
      parcontact_department: '',
      parcontact_product: '',
      parcontact_fullname: '',
      parcontact_designation: '',
      parcontact_email: '',
      parcontact_phonework: '',
      parcontact_mobile: '',
      parcontact_fax: '',
      parcontact_default: '',
      holidayCalender: '',

      parcontact_departmentshow: '',
      parcontact_productshow: '',
      parcontact_fullnameshow: '',
      parcontact_designationshow: '',
      parcontact_emailshow: '',
      parcontact_phoneworkshow: '',
      parcontact_mobileshow: '',
      parcontact_faxshow: '',
      parcontact_defaultshow: '',

      //contact popup end internalGradeMapping

      internalGradeMapping: this.fb.array([]),
      plantDetails: this.fb.array([]),
      externalSystemMapping: this.fb.array([]),
      creditLimit: this.fb.array([]),
      partyLimit: this.fb.array([]),
      translatedAddress: this.fb.array([]),
      warehouseLocation: this.fb.array([]),
      exchangeDetails: this.fb.array([]),
      partyDocuments: this.fb.array([]),
       
    });
    
    this.subscription = this.partyMasterService.getselectedparty(Number(this.partyId)).subscribe(editparty => {
     this.partyDataList = editparty.body;
     this.partyTranslationssssss = editparty.body.partyTranslations[0];
     if(this.partyDataList.partyBasicDetails.statusName=="Draft") {
       this.whenToDraft = true;
       this.whenL2Approved = false;
       this.addPartyButtons = false;
      this.whenActive = false;
      this.whenStatusActive = false;
     } 
     if(this.partyDataList.partyBasicDetails.statusName.indexOf('L1 Approved') > -1) { 
       this.whenL2Approved = true;
        this.whenToDraft = false;
       this.addPartyButtons = false;
       this.whenActive = false;
        this.whenStatusActive = false;
     } if (this.partyDataList.partyBasicDetails.statusName.indexOf('L2 Approved') > -1) {
       this.whenActive = true;
        this.whenL2Approved = false;
        this.whenToDraft = false;
       this.addPartyButtons = false;
       this.whenStatusActive = false;
     } if (this.partyDataList.partyBasicDetails.statusName == "Active") {
        this.whenActive = false;
        this.whenL2Approved = false;
        this.whenToDraft = false;
       this.addPartyButtons = false;
       this.whenStatusActive = true;
     }
      this.partyMasterService.partyBasicDetails(editparty.body.partyBasicDetails);
      this.partyMasterService.partyAddress(editparty.body.partyAddress);
      this.partyMasterService.partyBankAccountDetails(editparty.body.partyBankAccountDetails);
      this.partyMasterService.partyContacts(editparty.body.partyContacts);
      this.partyMasterService.editpartyGradeDefinition=editparty.body.partyGradeDefinition;
      this.partyMasterService.internalGradeMapping(editparty.body.internalGradeMapping);
      this.partyMasterService.paymentTermDetails(editparty.body.paymentTermDetails);
      this.partyMasterService.editplantDetails = editparty.body.plantDetails;
      this.partyMasterService.editexternalSystemMapping=editparty.body.externalSystemMapping;
      this.partyMasterService.editcreditLimit= editparty.body.creditLimit;
      this.partyMasterService.editpartyLimit = editparty.body.partyLimit;
      this.partyMasterService.editpartyTranslations = editparty.body.partyTranslations;
      this.partyMasterService.translatedAddress(editparty.body.translatedAddress);
      this.partyMasterService.editwarehouseLocation = editparty.body.warehouseLocation;
      this.partyMasterService.editexchangeDetails = editparty.body.exchangeDetails;
      this.partyMasterService.editbrokerDetails =editparty.body.brokerDetails;
      this.partyMasterService.editpartyDocuments=editparty.body.partyDocuments;
      this.whenAddrModalSaved=true;
      this.party_addr = [];
      this.addressModalVlaue = new PartyMasterInterface();
      for(var i=0;i<editparty.body.partyAddress.length;i++){
        this.addressModalVlaue.addressNameMOdalToshow = editparty.body.partyAddress[i].name;
        this.addressModalVlaue.Address1ModalToShow = editparty.body.partyAddress[i].addressOne;
        this.addressModalVlaue.Address2ModalToShow = editparty.body.partyAddress[i].addressTwo;
        this.addressModalVlaue.CountryModalTOShow = editparty.body.partyAddress[i].country;
        this.addressModalVlaue.zipToShowModal = editparty.body.partyAddress[i].zipCode;
        this.addressModalVlaue.emailToShowMOdal = editparty.body.partyAddress[i].email;
        this.addressModalVlaue.phoneToShowMOdal = editparty.body.partyAddress[i].phone;
        this.addressModalVlaue.paraddr_addrtypeToShowMOdal = editparty.body.partyAddress[i].addressType;
        this.addressModalVlaue.paraddr_defaddrtypeToShowMOdal = editparty.body.partyAddress[i].isDefaultAddress;
        this.addressModalVlaue.faxToShowModal = editparty.body.partyAddress[i].fax;
        this.addressModalVlaue.websiteUrlToShowMOdal = editparty.body.partyAddress[i].website;
        this.party_addr.push(this.addressModalVlaue);
      }
      this.party_bank=[];
      this.whenbankModalSaved=true;
      this.bankModalVlaue = new PartyMasterInterface();
      for(var i=0;i<editparty.body.partyBankAccountDetails.length;i++){
      this.bankModalVlaue.parbank_defaultshow = editparty.body.partyBankAccountDetails[i].isDefault;
      this.bankModalVlaue.parbank_AccountTypeshow = editparty.body.partyBankAccountDetails[i].partyAccountType;
      this.bankModalVlaue.parbank_branchshow = editparty.body.partyBankAccountDetails[i].partyBankBranch;
      //this.bankModalVlaue.parbank_countryshow = editparty.body.partyBankAccountDetails[i].
      //this.bankModalVlaue.parbank_country1show = editparty.body.partyBankAccountDetails[i].
      this.bankModalVlaue.parbank_AccountNameshow = editparty.body.partyBankAccountDetails[i].partyAccountName;
      this.bankModalVlaue.parbank_BicCodeshow = editparty.body.partyBankAccountDetails[i].partyBankBicCode;
      this.bankModalVlaue.parbank_swiftCodeshow = editparty.body.partyBankAccountDetails[i].partyBankSwiftCode;
      this.bankModalVlaue.parbank_routingshow = editparty.body.partyBankAccountDetails[i].partyBankRouting;
      this.bankModalVlaue.parbank_address1show = editparty.body.partyBankAccountDetails[i].partyBankAddr1;
      this.bankModalVlaue.parbank_address2show = editparty.body.partyBankAccountDetails[i].partyBankAddr2;
      //this.bankModalVlaue.parbank_banknameshow = editparty.body.partyBankAccountDetails[i].
      this.bankModalVlaue.parbank_emailshow = editparty.body.partyBankAccountDetails[i].partyBankEmail;
      this.bankModalVlaue.parbank_websiteshow = editparty.body.partyBankAccountDetails[i].partyBankWebsite;
      this.bankModalVlaue.parbank_faxshow = editparty.body.partyBankAccountDetails[i].partyBankFax;
      this.bankModalVlaue.parbank_phoneshow = editparty.body.partyBankAccountDetails[i].partyBankPhone;
      this.bankModalVlaue.parbank_zipshow = editparty.body.partyBankAccountDetails[i].partyBankZipCode;
      this.party_bank.push(this.bankModalVlaue);
      }
      this.party_contact=[];
     this.whencontactModalSaved=true;
      this.contactModalVlaue = new PartyMasterInterface();
      for(var i=0;i<this.partyMasterService.editpartyContacts.length;i++){
      this.contactModalVlaue.parcontact_departmentshow = this.partyMasterService.editpartyContacts[i].partyDepartment;
      this.contactModalVlaue.parcontact_productshow = this.partyMasterService.editpartyContacts[i].productName;
      this.contactModalVlaue.parcontact_fullnameshow = this.partyMasterService.editpartyContacts[i].partyContactFullname;
      this.contactModalVlaue.parcontact_designationshow = this.partyMasterService.editpartyContacts[i].partyContactDesignation;
      this.contactModalVlaue.parcontact_emailshow = this.partyMasterService.editpartyContacts[i].partyContactEmail;
      this.contactModalVlaue.parcontact_phoneworkshow = this.partyMasterService.editpartyContacts[i].partyContactWorkPhone;
      this.contactModalVlaue.parcontact_mobileshow = this.partyMasterService.editpartyContacts[i].partyContactMobile;
      this.contactModalVlaue.parcontact_faxshow = this.partyMasterService.editpartyContacts[i].partyContactFax;
      this.contactModalVlaue.parcontact_defaultshow = this.partyMasterService.editpartyContacts[i].partyContactIsDefault;
      this.party_contact.push(this.contactModalVlaue);
      }

      //this.party_addr.push(editparty.body.partyAddress);
      this.companyRegistrationDate = new Date(this.partyDataList.partyBasicDetails.companyRegistrationDate);
     this.taxRegistrationDate = new Date(this.partyDataList.partyBasicDetails.partyTaxRegDate);
     this.firsttransactionDate = new Date(this.partyDataList.partyBasicDetails.firsttransactionDate);
        this.ifedit = true;
       this.gtprogram = this.partyDataList.partyBasicDetails.isGtpApplicableInd;
       this.creditAllowed = this.partyDataList.partyBasicDetails.creditAllowed;
       this.isGroupCompany = this.partyDataList.partyBasicDetails.isGroupCompany;
       if(this.creditAllowed == 'Y') {
         this.req_partyGrpCredit = true;
        this.req_partyGrpCreditTenor = true;
         } else {
       this.req_partyGrpCredit = false;
       this.req_partyGrpCreditTenor = false;
       }
       this.partyMasterForm.value.taxRegistrationDate = new Date(this.partyDataList.partyBasicDetails.partyTaxRegDate);
       this.partyMasterForm.value.firsttransactionDate = new Date(this.partyDataList.partyBasicDetails.firsttransactionDate);
       this.partyMasterForm.value.companyRegistrationDate = new Date(this.partyDataList.partyBasicDetails.companyRegistrationDate)
       this.getDropDownSelectGrpForEdit(this.partyDataList.partyBasicDetails.partyInternalExternal);
      if (this.partyDataList.partyBasicDetails.partyInternalExternal == 'Y') {
      this.partySubModel = this.productsubModel;
      this.req_partydiv = false;
      this.req_internalGradeMApping = false;
      this.req_defaultPayment = false;
      this.req_functionalCurrency = true;
      this.req_OperationalCurrency = true;
      this.req_reportingUom = true;
      this.creditAllowdflag = false;
      this.fisrtDateWithOlam = false;
      this.gtpFlag = false;
       } else {
      //this.partySubModel = this.partyClassificationList;
      //this.partySubModel = this.productsubModel1;
      this.req_partydiv = true;
      this.req_functionalCurrency = false;
      this.req_OperationalCurrency = false;
      this.req_reportingUom = false;
      this.creditAllowdflag = true;
      this.fisrtDateWithOlam = true;
      this.gtpFlag = true;
      // this.req_internalGradeMApping = true;
      this.req_internalGradeMApping = false;
      this.req_defaultPayment = true;
    }
       this.getDropDownpartyClassification(this.partyDataList.partyBasicDetails.partyInternalExternal);
       this.editListDetails();
     },
      error => console.log(error),
      () => console.log('Finished')
      );
    
    } else {
      this.addPartyButtons = true;
      this.whenToDraft = false;
      this.whenActive = false;
      this.whenStatusActive = false;
      this.whenL2Approved = false;
      this.buildUSERROLEForm();
      this.addGradeMapping();
      this.addDefaultPayment();
      this.addExternalMapping();
      this.addCredit();
      this.addParty();
      this.addaddressAlternativeMulti();
      this.addWarehouse();
      this.addExchange();
      this.addDocument();
      this.isGroupCompany = 'N';
      this.isExchangeDesignated = 'N';
      this.isBrandedWareHouse = 'N';
      this.gtprogram = 'N';
      this.paraddr_defaddr = 'N';
      this.parbank_default = 'N';
      this.parcontact_default = 'N';
      this.creditAllowed = 'N';
      // this.showFields();
    }
  }


  editListDetails() {
     console.log(this.partyMasterService.editexternalSystemMapping);
      this.financialCalendarModelsCredit = this.partyMasterService.editcreditLimit;
      this.financialCalendarModelsParty = this.partyMasterService.editpartyLimit;
      this.financialCalendarModelsWare = this.partyMasterService.editwarehouseLocation;
      this.financialCalendarModelsEx = this.partyMasterService.editexchangeDetails;
      this.financialCalendarModelsdoc = this.partyMasterService.editpartyDocuments;
      this.financialCalendarModelsPlant =  this.partyMasterService.editplantDetails;
      this.financialCalendarModelsExternal = this.partyMasterService.editexternalSystemMapping;
      this.financialCalendarModelsAddress = this.partyMasterService.editpartyTranslations;
      this.financialCalendarModelsGrade = this.partyMasterService.editpartyGradeDefinition;
     if (this.partyDataList.partyBasicDetails.partyInternalExternal == "N") {
       //this.getDropDownpartyClassification(this.partyDataList.partyBasicDetails.partyInternalExternal)
        this.req_defaultPayment = true;
      }
      if(this.partyMasterService.editexternalSystemMapping==null || this.partyMasterService.editexternalSystemMapping.length==null || this.partyMasterService.editexternalSystemMapping.length==0){
        this.addExternalMapping();
      } else {
        for (let i = 0; i < this.partyMasterService.editexternalSystemMapping.length; i++) {
          this.addExternalMapping();
      }
    }
    if(this.partyMasterService.editpartyTranslations==null || this.partyMasterService.editpartyTranslations.length==null || this.partyMasterService.editpartyTranslations.length==0){
        this.addaddressAlternativeMulti();
      } else {
        for (let i = 0; i < this.partyMasterService.editpartyTranslations.length; i++) {
          this.addaddressAlternativeMulti();
      }
    }
    
        
      if(this.partyDataList.partyBasicDetails.partyClassifications!=null || this.partyDataList.partyBasicDetails.partyClassifications!=undefined){
      for (var j = 0; j < this.partyDataList.partyBasicDetails.partyClassifications.length; j++) {
        if ((this.partyDataList.partyBasicDetails.partyInternalExternal == "Y" || this.partyDataList.partyBasicDetails.partyInternalExternal == "N") && this.partyDataList.partyBasicDetails.partyClassifications[j].toLowerCase() == "warehouse") {
            this.req_wareHouse = true;
            
            for (let i = 0; i < this.partyMasterService.editwarehouseLocation.length; i++) {
                this.addWarehouse();
            }
        }

        if (this.partyDataList.partyBasicDetails.partyInternalExternal == "N") {
           if(this.partyDataList.partyBasicDetails.partyClassifications[j] != null) {
          var party_classifications = this.partyDataList.partyBasicDetails.partyClassifications[j].toLowerCase();
          if (party_classifications == "roaster") {
            this.req_plant = true;
            if(this.partyMasterService.editplantDetails==null || this.partyMasterService.editplantDetails.length==null || this.partyMasterService.editplantDetails.length==undefined){
              this.addDefaultPayment();
            } else {
             for (let i = 0; i < this.partyMasterService.editplantDetails.length; i++) {
                this.addDefaultPayment();
            }
            }
          }
          if (party_classifications == "exchange") {
            this.req_exchange = true;
            this.req_primaryContact = true;
            if(this.partyMasterService.editexchangeDetails==null || this.partyMasterService.editexchangeDetails.length==0 || this.partyMasterService.editexchangeDetails.length==null || this.partyMasterService.editexchangeDetails.length==undefined){
              this.addExchange();
            } else {
             for (let i = 0; i < this.partyMasterService.editexchangeDetails.length; i++) {
                this.addExchange();
            }
            }
          }
          if (party_classifications.indexOf('broker') > -1) {
            this.req_broker = true;
          }
           }

        }

      }
    }
 //debugger;
    if(this.partyDataList.partyBasicDetails.partyTypes!=null || this.partyDataList.partyBasicDetails.partyTypes!=undefined){
      for (var j = 0; j < this.partyDataList.partyBasicDetails.partyTypes.length; j++) {
        if (this.partyDataList.partyBasicDetails.partyInternalExternal == "N") {
          if(this.partyDataList.partyBasicDetails.partyTypes[j] != null) {
          var partytype = this.partyDataList.partyBasicDetails.partyTypes[j].toLowerCase();
          if (partytype == "customer") {
            this.req_partygradediv = true;
            this.req_internalGradeMApping = true;
            this.req_credit = true;
            this.req_partyLimits = true;
            this.req_document = true;

          }
             if(this.partyMasterService.editpartyGradeDefinition.length==undefined || this.partyMasterService.editpartyGradeDefinition.length==null){
              this.addGradeMapping();
            } else {
            for (let i = 0; i < this.partyMasterService.editpartyGradeDefinition.length; i++) {
                this.addGradeMapping();
            }
          }
          if(this.partyMasterService.editcreditLimit.length==undefined || this.partyMasterService.editcreditLimit.length==null || this.partyMasterService.editcreditLimit.length==0){
              this.addCredit();
            } else {
            this.credit_partyname=false;
            for (let i = 0; i < this.partyMasterService.editcreditLimit.length; i++) {
                this.addCredit();
            }
          }
          console.log(this.partyMasterService.editpartyLimit.length);
          if(this.partyMasterService.editpartyLimit.length == null || this.partyMasterService.editpartyLimit.length == 0){
              this.addParty();
            } else {
            for (let i = 0; i < this.partyMasterService.editpartyLimit.length; i++) {
                this.addParty();
            }
          }
        //  debugger;
          if(this.partyMasterService.editpartyDocuments.length==0 || this.partyMasterService.editpartyDocuments.length==null || this.partyMasterService.editpartyDocuments.length==0){
              this.addDocument  ();
            } else {
            for (let i = 0; i < this.partyMasterService.editpartyDocuments.length; i++) {
                this.addDocument();
            }
            }
          break;
        }
        }

      }
    }


    /*console.log(this.userRoleDataList)
    if (this.userRoleDataList.statusName == 'Draft') {
      this.whenDataNotTobeEdited = false;
      this.whenDataEdited = true;
      this.whenStatusInactive = false;
      this.whenStatusactive = false;
    } else if (this.userRoleDataList.statusName == 'Active') {
      this.whenStatusactive = true;
      this.whenStatusInactive = false;
      this.whenDataEdited = false;
      this.whenDataNotTobeEdited = false;
    } else if (this.userRoleDataList.statusName == 'InActive') {
      this.whenStatusInactive = true;
      this.whenDataEdited = false;
      this.whenStatusactive = false;
      this.whenDataNotTobeEdited = false;
    }
    this.specialityApplicable = this.userRoleDataList.specialityApplicable;
    this.isBaseProduct = this.userRoleDataList.isBaseProduct;
    // console.log('subtrype' + JSON.stringify(this.userRoleDataList.processingSubType))
    
    if (this.userRoleDataList.processingName == 'washed') {
      this.washed = true;
    } else {
      this.washed = false;
    }
    if (this.userRoleDataList.isBaseProduct == 'Y') {
      this.toShowParentProd = true;
    } else {
      this.toShowParentProd = false;
    }
    //this.selectedItem=this.userRoleDataList.parentProductName;
    console.log(this.productMasterService.editProductdet.length + '  check of length');
    this.financialCalendarModelArbb = this.productMasterService.editProductdet;
    // this.financialCalendarModelsGrade = 
    //alert(JSON.stringify(this.financialCalendarModel));
    // const sizerollemapping = Number(JSON.stringify(this.basePaymentService.selectbasePaymentEdit.length));
    if (this.productMasterService.editProductdet.length >= 1) {
      for (let i = 0; i < this.productMasterService.editProductdet.length; i++) {
        this.addPermissionMapping();

      }
    }
    if (this.productMasterService.editGrade != null) {
      console.log(this.productMasterService.editGrade.length + '  check of length');
      this.financialCalendarModelsGrade = this.productMasterService.editGrade;
      if (this.productMasterService.editGrade.length >= 1) {
        for (let i = 0; i < this.productMasterService.editGrade.length; i++) {
          this.addGradeMapping();

        }
      }
    }*/
  }


  partyaddressForm(): void {
    this.party_address = this.fb.group({
      paraddress_name: '',
      paraddress_address1: "",
      addressdetails: this.fb.array([])
    })

  }

  buildUSERROLEForm(): void {
    this.partyMasterForm = this.fb.group({
      firsttransactionDate: '',
      gtprogram: '',
      functionalCurrency: '',
      OperationalCurrency: '',
      reportingUom: '',
      partyInternalExternal: '',
      exchangeContractSizeUom: '',
      paraddr_defaddr: '',
      paraddr_addrtype: '',
      paraddr_name: '',
      paraddr_address1: '',
      paraddr_address2: '',
      paraddr_country: '',
      paraddr_zipcode: '',
      countryWareHOuse: '',
      gradeNameId : '',
      plantCode: '',
      paraddr_email: '',
      addressName: '',
      paraddr_phone: '',
      paraddr_fax: '',
      paraddr_weburl: '',
      // xxx:[''],
      steps: this.fb.array([]),
      groupCreditTenor: '',
      partyCode: '',
      partyName: '',
      partyFullName: '',
      isGroupCompany: '',
      alternativeName: '',
      alternativeFullName: '',
      selectGroup: '',
      geohierarchy: '',
      marketingDesk: '',
      creditAllowed: '',
      nameLanguage: '',
      addressLanguage: '',
      partyRating: '',
      groupCreditLimit: '',
      companyRegistration: '',
      companyRegistrationDate: '',
      taxRegistrationNo: '',
      taxRegistrationDate: '',
      product: '',
      warlocationCode: '',
      partyGradeName: '',
      partyType: '',
      partyGradeDesc: '',
      partyClassification: '',
      origin: '',
      tradingAccountNumber: '',
      commmission: '',
      commissionUom: '',
      internalGradeName: '',
      paymentTerm: '',
      parbank_default: '',
      parbank_AccountType: '',
      parbank_branch: '',
      parbank_country: '',
      parbank_country1: '',
      parbank_AccountName: '',
      parbank_email: '',
      parbank_BicCode: '',
      parbank_swiftCode: '',
      parbank_routing: '',
      parbank_website: '',
      parbank_fax: '',
      parbank_phone: '',
      parbank_zip: '',
      parbank_bankname: '',
      parbank_address1: '',
      parbank_address2: '',
      internalPartyCode: '',
      internalPartyName: '',
      creditLimitProduct: '',
      unit: '',
      //contact popup
      parcontact_department: '',
      parcontact_product: '',
      parcontact_fullname: '',
      parcontact_designation: '',
      parcontact_email: '',
      parcontact_phonework: '',
      parcontact_mobile: '',
      parcontact_fax: '',
      parcontact_default: '',
      holidayCalender: '',

      parcontact_departmentshow: '',
      parcontact_productshow: '',
      parcontact_fullnameshow: '',
      parcontact_designationshow: '',
      parcontact_emailshow: '',
      parcontact_phoneworkshow: '',
      parcontact_mobileshow: '',
      parcontact_faxshow: '',
      parcontact_defaultshow: '',

      //contact popup end internalGradeMapping

      internalGradeMapping: this.fb.array([]),
      plantDetails: this.fb.array([]),
      externalSystemMapping: this.fb.array([]),
      creditLimit: this.fb.array([]),
      partyLimit: this.fb.array([]),
      translatedAddress: this.fb.array([]),
      warehouseLocation: this.fb.array([]),
      exchangeDetails: this.fb.array([]),
      partyDocuments: this.fb.array([]),
    });

  }
  addGradeMapping() {
    const control = <FormArray>this.partyMasterForm.controls['internalGradeMapping'];
    const addrCtrl = this.fb.group({
      'origin': [],
      'gradeNameId': [],
      'code': [],
      'uniqueMapping':[],
      'description': [],
      'gradeMappingId': []
    });
    control.push(addrCtrl);
  }
  addDocument() {
    const control = <FormArray>this.partyMasterForm.controls['partyDocuments'];
    const addrCtrl = this.fb.group({
      //'documentName': [],
      //'documentType': [],
      'fkDocumentRefId':[],
      'uniqueMapping':[],
      'fkDocumentTypeRefId':[],
      'documentNumber': []
    });
    control.push(addrCtrl);
  }
  addExternalMapping() {
    const control = <FormArray>this.partyMasterForm.controls['externalSystemMapping'];
    const addrCtrl = this.fb.group({
      'destinationSystem': [],
      'externalSystemMappingType': [],
      'externalSystemMappingVendor': [],
      'uniqueMapping':[],
      'externalSystemMappingCustomerCode': []
    });
    control.push(addrCtrl);
  }
  addaddressAlternativeMulti() {
    const control = <FormArray>this.partyMasterForm.controls['translatedAddress'];
    const addrCtrl = this.fb.group({
      'addressName': [],
      'addressAltAddress1': [],
      'addressAltAddress2': [],
      'addressAltZipCode': [],
      'addressAltCountry': [],
      'uniqueMapping':[],
      'addressLanguage': []
    });
    control.push(addrCtrl);
  }
  //         credit(){
  //           debugger;
  //               this.addCredit();
  //                this.credit_limit = new CreditLimit();
  //               // this.credit_limit.internalPartyCode=null;
  //               // this.credit_limit.internalPartyName=null;
  //               // this.credit_limit.creditLimitProduct=null;
  //               // this.credit_limit.unit=null;
  //               // this.credit_limit.limitAlertLevel=null;
  //               // this.credit_limit.creditCurrency=null;
  //               // this.credit_limit.FactoringEndDate=null;
  //               // this.credit_limit.validFrom=null;
  //               // this.credit_limit.validTo=null;
  //               // this.credit_limit.limitType=null;
  //               // this.credit_limit.Factoring=null;
  //               // this.credit_limit.netLimit=null;
                
  // }
  addCredit() {
    const control = <FormArray>this.partyMasterForm.controls['creditLimit'];
    const addrCtrl = this.fb.group({
      'internalPartyCode': '',
      'internalPartyName': '',
      'creditLimitProduct': '',
      'unit': '',
      'limitAlertLevel': [],
      'creditCurrency': [],
      'Factoring': [],
      'FactoringEndDate': [],
      'uniqueMapping':[],
      'netLimit': [],
      'validFrom': [],
      'validTo': [],
      'limitType': []
    });
    control.push(addrCtrl);
  }
  addExchange() {
    const control = <FormArray>this.partyMasterForm.controls['exchangeDetails'];
    const addrCtrl = this.fb.group({
      'exchangeProduct': [],
      'exchangeScreenName': [],
      'exchangeHubName': [],
      'exchangeContractSymbol': [],
      'exchangeContractSize': [],
      //'exchangeTradingCalender': [],
      'exchangePriceQuotation': [],
      'exchangeMinimmumPrice': [],
      'uniqueMapping':[],
      'exchangeContractSizeUom': [],
      'exchangeMinimmumPriceCurrency': [],
      'exchangeMinimmumPriceUom': [],
      'exchangePriceQuotationCurrency': [],
      'exchangePriceQuotationUom': [],
      'holidayCalender': [],
      'netLimit': [],
      'validFrom': [],
      'validTo': [],
      'limitType': []
    });
    control.push(addrCtrl);
  }
  addParty() {
    const control = <FormArray>this.partyMasterForm.controls['partyLimit'];
    const addrCtrl = this.fb.group({
      'internalPartyCodeOrg': [],
      'internalPartyNameOrg': [],
      'partyProduct': [],
      'partyUnit': [],
      'limitAlertLevelparty': [],
      'partyCurrency': [],
      'partyUom': [],
      'partyValidFrom': [],
      'diffForwardTenor': [],
      'partyFixedQtyLimit': [],
      'partyFixedVAlueLimit': [],
      'fixedForwardTenor': [],
      'uniqueMapping':[],
      'diffQtyLimit': [],
      'diffValueLimit': [],
      'm2mLimit': [],
      'varLimit': [],
      'totalQtyLimit': [],
      'totalValueLimit': [],
      'limitOn': [],
      'partyValidTo': []
    });
    control.push(addrCtrl);
  }
  addWarehouse() {
    const control = <FormArray>this.partyMasterForm.controls['warehouseLocation'];
    const addrCtrl = this.fb.group({
      'wareLocationName': [],
      'wareLocationFullName': [],
      'wareLocationTaxRegNo': [],
      'wareLocationTaxRegDate': [],
      'wareExchangeDate': [],
      'wareAddress1': [],
      'wareAddress2': [],
      'wareZip': [],
      'warePhone': [],
      'wareEmail': [],
      'wareFax': [],
      'wareWebsite': [],
      'uniqueMapping':[],
      'countryWareHOuse': [],
      'isExchangeDesignated': [],
      'isBrandedWareHouse': [],
      'warlocationCode': []
    });
    control.push(addrCtrl);
  }
  addDefaultPayment() {
    const control = <FormArray>this.partyMasterForm.controls['plantDetails'];
    const addrCtrl = this.fb.group({
      'plantCode': [],
      'plantName': [],
      'plantFullName': [],
      'taxRegistrationNumber': [],
      'address1': [],
      'address2': [],
      'uniqueMapping':[],
      'taxRegistrationDate': [],
      'zipCode': [],
      'countryDefault': [],
      'phoneDefault': [],
      'emailDefault': [],
      'faxDefault': [],
      'websiteDefault': [],
    });
    control.push(addrCtrl);
  }
  getIntGradeCodeMapping(partyMasterForm) {
    return partyMasterForm.get('internalGradeMapping').controls;
  }
  getIntPartyCodeMapping(partyMasterForm) {
    return partyMasterForm.get('partyLimit').controls;
  }

  getIntExhange(partyMasterForm) {
    return partyMasterForm.get('exchangeDetails').controls;
  }
  getIntWarehouseMapping(partyMasterForm) {
    return partyMasterForm.get('warehouseLocation').controls;
  }
  getIntDocument(partyMasterForm) {
    return partyMasterForm.get('partyDocuments').controls;
  }
  getIntCredit(partyMasterForm) {
    return partyMasterForm.get('creditLimit').controls;
  }
  getIntDefaultMapping(partyMasterForm) {
    return partyMasterForm.get('plantDetails').controls;
  }
  getaddressalternativelanguage(partyMasterForm) {
    return partyMasterForm.get('translatedAddress').controls;
  }
  getExternalMapping(partyMasterForm) {
    return partyMasterForm.get('externalSystemMapping').controls;
  }
  removeIntGradeMapping(i: number) {
    if (i > 0) {
      const control = <FormArray>this.partyMasterForm.controls['internalGradeMapping'];
      if (this.pageName == 6) {
        //  this.gradeMappingdel.push(this.financialCalendarModelsGrade[i].gradeMappingId);
      }
      control.removeAt(i);
    }
  }

  removeExchange(i: number) {
    if (i > 0) {
      const control = <FormArray>this.partyMasterForm.controls['exchangeDetails'];
      if (this.pageName == 6) {
        //  this.gradeMappingdel.push(this.financialCalendarModelsGrade[i].gradeMappingId);
      }
      control.removeAt(i);
    }
  }
  removeDocument(i: number) {
    if (i > 0) {
      const control = <FormArray>this.partyMasterForm.controls['partyDocuments'];
      if (this.pageName == 6) {
        //  this.gradeMappingdel.push(this.financialCalendarModelsGrade[i].gradeMappingId);
      }
      control.removeAt(i);
    }
  }
  removeIntCreditMapping(i: number) {
    if (i > 0) {
      const control = <FormArray>this.partyMasterForm.controls['creditLimit'];
      if (this.pageName == 6) {
        //  this.gradeMappingdel.push(this.financialCalendarModelsGrade[i].gradeMappingId);
      }
      control.removeAt(i);
    }
  }
  removeIntWarehouse(i: number) {
    if (i > 0) {
      const control = <FormArray>this.partyMasterForm.controls['warehouseLocation'];
      if (this.pageName == 6) {
        //  this.gradeMappingdel.push(this.financialCalendarModelsGrade[i].gradeMappingId);
      }
      control.removeAt(i);
    }
  }
  removealernativeAddress(i: number) {
    if (i > 0) {
      const control = <FormArray>this.partyMasterForm.controls['translatedAddress'];
      if (this.pageName == 6) {
        //  this.gradeMappingdel.push(this.financialCalendarModelsGrade[i].gradeMappingId);
      }
      control.removeAt(i);
    }
  }
  removeIntDefaultMapping(i: number) {
    if (i > 0) {
      const control = <FormArray>this.partyMasterForm.controls['plantDetails'];
      if (this.pageName == 6) {
        //  this.gradeMappingdel.push(this.financialCalendarModelsGrade[i].gradeMappingId);
      }
      control.removeAt(i);
    }
  }

  removeIntPartyMapping(i: number) {
    if (i > 0) {
      const control = <FormArray>this.partyMasterForm.controls['partyLimit'];
      if (this.pageName == 6) {
        //  this.gradeMappingdel.push(this.financialCalendarModelsGrade[i].gradeMappingId);
      }
      control.removeAt(i);
    }
  }
  removeIntExternalMapping(i: number) {
    if (i > 0) {
      const control = <FormArray>this.partyMasterForm.controls['externalSystemMapping'];
      if (this.pageName == 6) {
        //  this.gradeMappingdel.push(this.financialCalendarModelsGrade[i].gradeMappingId);
      }
      control.removeAt(i);
    }
  }
  address() {
    this.addressmodals.show();
  }

  bank() {
    this.bankmodal.show();
  }

  contact() {
    this.contactmodals.show();
  }


  edit(data, index) {
    this.addressmodals.show();
    this.addressModalVlaue = new PartyMasterInterface();
    this.addressModal.paraddr_name = data.addressNameMOdalToshow;
    this.addressModal.paraddr_address1 = data.Address1ModalToShow;
    this.addressModal.paraddr_address2 = data.Address2ModalToShow;
    this.addressModal.paraddr_country = data.CountryModalTOShow;
    this.addressModal.paraddr_zipcode = data.zipToShowModal;
    this.addressModal.paraddr_email = data.emailToShowMOdal;
    this.addressModal.paraddr_phone = data.phoneToShowMOdal;
    this.addressModal.paraddr_addrtype = data.paraddr_addrtypeToShowMOdal;
    this.addressModal.paraddr_defaddr = data.paraddr_defaddrtypeToShowMOdal;
    this.addressModal.paraddr_fax = data.faxToShowModal;
    this.addressModal.paraddr_weburl = data.websiteUrlToShowMOdal;
    this.update_address = true;
    this.update_addr = this.addressModal;
    this.address_index = index;
  }

  saveaddress(a, index) {
   // debugger;
    this.count = 0;
    console.log(this.addressModal);
    this.addressModalVlaue = new PartyMasterInterface();
    this.addressModalVlaue.addressNameMOdalToshow = this.addressModal.paraddr_name;
    this.addressModalVlaue.Address1ModalToShow = this.addressModal.paraddr_address1;
    this.addressModalVlaue.Address2ModalToShow = this.addressModal.paraddr_address2;
    this.addressModalVlaue.CountryModalTOShow = this.addressModal.paraddr_country;
    this.addressModalVlaue.zipToShowModal = this.addressModal.paraddr_zipcode;
    this.addressModalVlaue.emailToShowMOdal = this.addressModal.paraddr_email;
    this.addressModalVlaue.phoneToShowMOdal = this.addressModal.paraddr_phone;
    this.addressModalVlaue.paraddr_addrtypeToShowMOdal = this.addressModal.paraddr_addrtype;
    this.addressModalVlaue.paraddr_defaddrtypeToShowMOdal = this.addressModal.paraddr_defaddr;
    this.addressModalVlaue.faxToShowModal = this.addressModal.paraddr_fax;
    this.addressModalVlaue.websiteUrlToShowMOdal = this.addressModal.paraddr_weburl;
    if (this.party_addr.length != 0) {
      for (var i = 0; i < this.party_addr.length; i++) {
       //  this.partyDetailsObj.partyAddressTOCReate.isDefaultAddress = this.party_addr[i].paraddr_defaddrtypeToShowMOdal;
        if (this.addressModal.paraddr_defaddr == 'Y' && this.party_addr[i].paraddr_defaddrtypeToShowMOdal == 'Y') {
          this.party_addr[i].paraddr_defaddrtypeToShowMOdal = 'N';
        }
      }
    }
    this.partyDetailsObj.partyAddressTOCReate.isDefaultAddress = this.addressModal.paraddr_defaddr;
    this.partyDetailsObj.partyAddressTOCReate.addressOne = this.addressModal.paraddr_address1;
    this.partyDetailsObj.partyAddressTOCReate.addressTwo = this.addressModal.paraddr_address2;
    this.partyDetailsObj.partyAddressTOCReate.addressType = '';
    this.partyDetailsObj.partyAddressTOCReate.country = '';
    this.partyDetailsObj.partyAddressTOCReate.countryId = this.addressModal.paraddr_country;
    this.partyDetailsObj.partyAddressTOCReate.addressTypeId = this.addressModal.paraddr_addrtype;
    this.partyDetailsObj.partyAddressTOCReate.name = this.addressModal.paraddr_name;
    this.partyDetailsObj.partyAddressTOCReate.zipCode = this.addressModal.paraddr_zipcode;
    this.partyDetailsObj.partyAddressTOCReate.phone = this.addressModal.paraddr_phone;
    this.partyDetailsObj.partyAddressTOCReate.email = this.addressModal.paraddr_email;
    this.partyDetailsObj.partyAddressTOCReate.fax = this.addressModal.paraddr_fax;
    this.partyDetailsObj.partyAddressTOCReate.website = this.addressModal.paraddr_weburl;
    this.party_addrToSave.push(this.partyDetailsObj.partyAddressTOCReate);
    this.party_addr.push(this.addressModalVlaue);
    console.log(JSON.stringify(this.party_addr));
    this.whenAddrModalSaved = true;
    this.addressmodals.hide();
    this.addressModal.paraddr_address1 = '';
    this.addressModal.paraddr_weburl = '';
    this.addressModal.paraddr_name = '';
    this.addressModal.paraddr_address2 = '';
    this.addressModal.paraddr_country = '';
    this.addressModal.paraddr_zipcode = '';
    this.addressModal.paraddr_email = '';
    this.addressModal.paraddr_phone = '';
    this.addressModal.paraddr_fax = '';

  }

  updateaddress(a, index) {
    this.update_address = false;
    if (this.party_addr.length != 0) {
      for (var i = 0; i < this.party_addr.length; i++) {
        this.party_addr[this.address_index].addressNameMOdalToshow = this.update_addr.paraddr_name;
        this.party_addr[this.address_index].Address1ModalToShow = this.update_addr.paraddr_address1;
        this.party_addr[this.address_index].Address2ModalToShow = this.update_addr.paraddr_address2;
        this.party_addr[this.address_index].CountryModalTOShow = this.update_addr.paraddr_country;
        this.party_addr[this.address_index].zipToShowModal = this.update_addr.paraddr_zipcode;
        this.party_addr[this.address_index].emailToShowMOdal = this.update_addr.paraddr_email;
        this.party_addr[this.address_index].phoneToShowMOdal = this.update_addr.paraddr_phone;
        this.party_addr[this.address_index].paraddr_addrtypeToShowMOdal = this.update_addr.paraddr_addrtype;
        this.party_addr[this.address_index].paraddr_defaddrtypeToShowMOdal = this.update_addr.paraddr_defaddr;
        this.party_addr[this.address_index].faxToShowModal = this.update_addr.paraddr_fax;
        this.party_addr[this.address_index].websiteUrlToShowMOdal = this.update_addr.paraddr_weburl;
        if (this.party_addr.length != 0) {
          if (this.party_addr[this.address_index].paraddr_defaddrtypeToShowMOdal == 'Y') {
            if (this.address_index != i) {
              this.party_addr[i].paraddr_defaddrtypeToShowMOdal = 'N';
            }

          }

        }
      }
      console.log(this.party_addr);
      this.subscription = this.partyMasterService.updateAddressPop(this.party_addr).subscribe(addDoc => {
        this.addressmodals.hide();
        this.whenAddrModalSaved = true;
        this.addressModal.paraddr_address1 = '';
        this.addressModal.paraddr_weburl = '';
        this.addressModal.paraddr_name = '';
        this.addressModal.paraddr_address2 = '';
        this.addressModal.paraddr_country = '';
        this.addressModal.paraddr_zipcode = '';
        this.addressModal.paraddr_email = '';
        this.addressModal.paraddr_phone = '';
        this.addressModal.paraddr_fax = '';
      },
        error => { },
        () => console.log('Finished')
      );

    }
  }

  closeaddress() {
    this.addressmodals.hide();
    this.update_address = false;
    this.addressModal.paraddr_address1 = '';
    this.addressModal.paraddr_weburl = '';
    this.addressModal.paraddr_name = '';
    this.addressModal.paraddr_address2 = '';
    this.addressModal.paraddr_country = '';
    this.addressModal.paraddr_zipcode = '';
    this.addressModal.paraddr_email = '';
    this.addressModal.paraddr_phone = '';
    this.addressModal.paraddr_fax = '';
  }


  editbank(data, index) {
    this.bankmodal.show();
    this.bankModalVlaues = new PartyMasterInterface();
    this.bankModalVlaues.parbank_default = data.parbank_defaultshow;
    this.bankModalVlaues.parbank_AccountType = data.parbank_AccountTypeshow;
    this.bankModalVlaues.parbank_branch = data.parbank_branchshow;
    this.bankModalVlaues.parbank_country = data.parbank_countryshow;
    this.bankModalVlaues.parbank_country1 = data.parbank_country1show;
    this.bankModalVlaues.parbank_AccountName = data.parbank_AccountNameshow;
    this.bankModalVlaues.parbank_BicCode = data.parbank_BicCodeshow;
    this.bankModalVlaues.parbank_swiftCode = data.parbank_swiftCodeshow;
    this.bankModalVlaues.parbank_routing = data.parbank_routingshow;
    this.bankModalVlaues.parbank_website = data.parbank_websiteshow;
    this.bankModalVlaues.parbank_fax = data.parbank_faxshow;
    this.bankModalVlaues.parbank_phone = data.parbank_phoneshow;
    this.bankModalVlaues.parbank_zip = data.parbank_zipshow;
    this.bankModalVlaues.parbank_address1 = data.parbank_address1show;
    this.bankModalVlaues.parbank_address2 = data.parbank_address2show;
    this.bankModalVlaues.parbank_email = data.parbank_emailshow;
    this.bankModalVlaues.parbank_bankname = data.parbank_banknameshow;


    this.update_bank = true;
    this.update_bankdata = this.bankModalVlaues;
    this.bank_index = index;
  }

  saveBank(a, index) {
    this.bankModalVlaue = new PartyMasterInterface();
    this.bankModalVlaue.parbank_defaultshow = this.bankModalVlaues.parbank_default;
    this.bankModalVlaue.parbank_AccountTypeshow = this.bankModalVlaues.parbank_AccountType;
    this.bankModalVlaue.parbank_branchshow = this.bankModalVlaues.parbank_branch;
    this.bankModalVlaue.parbank_countryshow = this.bankModalVlaues.parbank_country;
    this.bankModalVlaue.parbank_country1show = this.bankModalVlaues.parbank_country1;
    this.bankModalVlaue.parbank_AccountNameshow = this.bankModalVlaues.parbank_AccountName;
    this.bankModalVlaue.parbank_BicCodeshow = this.bankModalVlaues.parbank_BicCode;
    this.bankModalVlaue.parbank_swiftCodeshow = this.bankModalVlaues.parbank_swiftCode;
    this.bankModalVlaue.parbank_routingshow = this.bankModalVlaues.parbank_routing;
    this.bankModalVlaue.parbank_address1show = this.bankModalVlaues.parbank_address1;
    this.bankModalVlaue.parbank_address2show = this.bankModalVlaues.parbank_address2;
    this.bankModalVlaue.parbank_banknameshow = this.bankModalVlaues.parbank_bankname;
    this.bankModalVlaue.parbank_emailshow = this.bankModalVlaues.parbank_email;
    this.bankModalVlaue.parbank_websiteshow = this.bankModalVlaues.parbank_website;
    this.bankModalVlaue.parbank_faxshow = this.bankModalVlaues.parbank_fax;
    this.bankModalVlaue.parbank_phoneshow = this.bankModalVlaues.parbank_phone;
    this.bankModalVlaue.parbank_zipshow = this.bankModalVlaues.parbank_zip;

    if (this.party_bank.length != 0) {
      for (var i = 0; i < this.party_bank.length; i++) {
        if (this.bankModalVlaues.parbank_default == 'Y' && this.party_bank[i].parbank_defaultshow == 'Y') {
          this.party_bank[i].parbank_defaultshow = 'N';
        }
        //this.partyDetailsObj.aartyBankAccountDetailsToCreate.isDefault = this.party_bank[i].parbank_defaultshow;
      }
    }
    this.partyDetailsObj.aartyBankAccountDetailsToCreate.isDefault = this.bankModalVlaues.parbank_default;
    this.partyDetailsObj.aartyBankAccountDetailsToCreate.partyBankBranch = this.bankModalVlaues.parbank_branch;
    this.partyDetailsObj.aartyBankAccountDetailsToCreate.accountTypeCode = this.bankModalVlaues.parbank_AccountType;
    this.partyDetailsObj.aartyBankAccountDetailsToCreate.accountTypeName = '';
    this.partyDetailsObj.aartyBankAccountDetailsToCreate.partyBankAccountNo = this.bankModalVlaues.parbank_AccountName;
    this.partyDetailsObj.aartyBankAccountDetailsToCreate.partyBankBicCode = this.bankModalVlaues.parbank_BicCode;
    this.partyDetailsObj.aartyBankAccountDetailsToCreate.partyBankSwiftCode = this.bankModalVlaues.parbank_swiftCode;
    this.partyDetailsObj.aartyBankAccountDetailsToCreate.partyBankRouting = this.bankModalVlaues.parbank_routing;
    this.partyDetailsObj.aartyBankAccountDetailsToCreate.partyBankAddr1 = this.bankModalVlaues.parbank_address1;
    this.partyDetailsObj.aartyBankAccountDetailsToCreate.partyBankAddr2 = this.bankModalVlaues.parbank_address2;
    this.partyDetailsObj.aartyBankAccountDetailsToCreate.partyBankZipCode = this.bankModalVlaues.parbank_zip;
    this.partyDetailsObj.aartyBankAccountDetailsToCreate.country = this.bankModalVlaues.parbank_country;
    this.partyDetailsObj.aartyBankAccountDetailsToCreate.status = '';
    this.partyDetailsObj.aartyBankAccountDetailsToCreate.partyBankPhone = this.bankModalVlaues.parbank_phone;
    this.partyDetailsObj.aartyBankAccountDetailsToCreate.partyBankEmail = this.bankModalVlaues.parbank_email;
    this.partyDetailsObj.aartyBankAccountDetailsToCreate.partyBankFax = this.bankModalVlaues.parbank_fax;
    this.partyDetailsObj.aartyBankAccountDetailsToCreate.partyBankWebsite = this.bankModalVlaues.parbank_website;
    this.partyDetailsObj.aartyBankAccountDetailsToCreate.partyBankZipCode = this.bankModalVlaues.parbank_zip;
    this.partyDetailsObj.aartyBankAccountDetailsToCreate.partyBankZipCode = this.bankModalVlaues.parbank_zip;


    this.party_banktoSave.push(this.partyDetailsObj.aartyBankAccountDetailsToCreate);

    this.party_bank.push(this.bankModalVlaue);
    console.log(JSON.stringify(this.party_bank));

    this.bankmodal.hide();
    this.whenbankModalSaved = true;
    this.whenAddrModalSaved = true;
    this.bankModalVlaues.parbank_default = '';
    this.bankModalVlaues.parbank_AccountType = '';
    this.bankModalVlaues.parbank_branch = '';
    this.bankModalVlaues.parbank_country = '';
    this.bankModalVlaues.parbank_country1 = '';
    this.bankModalVlaues.parbank_AccountName = '';
    this.bankModalVlaues.parbank_BicCode = '';
    this.bankModalVlaues.parbank_swiftCode = '';
    this.bankModalVlaues.parbank_routing = '';
    this.bankModalVlaues.parbank_website = '';
    this.bankModalVlaues.parbank_fax = '';
    this.bankModalVlaues.parbank_fax = '';
    this.bankModalVlaues.parbank_phone = '';
    this.bankModalVlaues.parbank_zip = '';
    this.bankModalVlaues.parbank_address1 = '';
    this.bankModalVlaues.parbank_address2 = '';
    this.bankModalVlaues.parbank_bankname = '';
    this.bankModalVlaues.parbank_email = '';

  }

  updateBank() {
    this.update_bank = false;
    if (this.party_bank.length != 0) {
      for (var i = 0; i < this.party_bank.length; i++) {
        this.party_bank[this.bank_index].parbank_defaultshow = this.update_bankdata.parbank_default;
        this.party_bank[this.bank_index].parbank_AccountTypeshow = this.update_bankdata.parbank_AccountType;
        this.party_bank[this.bank_index].parbank_branchshow = this.update_bankdata.parbank_branch;
        this.party_bank[this.bank_index].parbank_countryshow = this.update_bankdata.parbank_country;
        this.party_bank[this.bank_index].parbank_country1show = this.update_bankdata.parbank_country1;
        this.party_bank[this.bank_index].parbank_AccountNameshow = this.update_bankdata.parbank_AccountName;
        this.party_bank[this.bank_index].parbank_BicCodeshow = this.update_bankdata.parbank_BicCode;
        this.party_bank[this.bank_index].parbank_swiftCodeshow = this.update_bankdata.parbank_swiftCode;
        this.party_bank[this.bank_index].parbank_routingshow = this.update_bankdata.parbank_routing;
        this.party_bank[this.bank_index].parbank_websiteshow = this.update_bankdata.parbank_website;
        this.party_bank[this.bank_index].parbank_faxshow = this.update_bankdata.parbank_fax;
        this.party_bank[this.bank_index].parbank_phoneshow = this.update_bankdata.parbank_phone;
        this.party_bank[this.bank_index].parbank_zipshow = this.update_bankdata.parbank_zip;
        this.party_bank[this.bank_index].parbank_address1show = this.update_bankdata.parbank_address1;
        this.party_bank[this.bank_index].parbank_address2show = this.update_bankdata.parbank_address2;
        this.party_bank[this.bank_index].parbank_banknameshow = this.update_bankdata.parbank_bankname;
        this.party_bank[this.bank_index].parbank_emailshow = this.update_bankdata.parbank_email;
        if (this.party_bank.length != 0) {
          if (this.party_bank[this.bank_index].parbank_defaultshow == 'Y') {
            if (this.bank_index != i) {
              this.party_bank[i].parbank_defaultshow = 'N';
            }

          }

        }

      }
      this.bankmodal.hide();
      this.update_bank = false;
      this.bankModalVlaues.parbank_default = '';
      this.bankModalVlaues.parbank_AccountType = '';
      this.bankModalVlaues.parbank_branch = '';
      this.bankModalVlaues.parbank_country = '';
      this.bankModalVlaues.parbank_country1 = '';
      this.bankModalVlaues.parbank_AccountName = '';
      this.bankModalVlaues.parbank_BicCode = '';
      this.bankModalVlaues.parbank_swiftCode = '';
      this.bankModalVlaues.parbank_routing = '';
      this.bankModalVlaues.parbank_website = '';
      this.bankModalVlaues.parbank_fax = '';
      this.bankModalVlaues.parbank_phone = '';
      this.bankModalVlaues.parbank_zip = '';
      this.bankModalVlaues.parbank_address1 = '';
      this.bankModalVlaues.parbank_address2 = '';
      this.bankModalVlaues.parbank_bankname = '';
      this.bankModalVlaues.parbank_email = '';
    }
  }

  closebank() {
    this.bankmodal.hide();
    this.update_bank = false;
    this.bankModalVlaues.parbank_default = '';
    this.bankModalVlaues.parbank_AccountType = '';
    this.bankModalVlaues.parbank_branch = '';
    this.bankModalVlaues.parbank_country = '';
    this.bankModalVlaues.parbank_country1 = '';
    this.bankModalVlaues.parbank_AccountName = '';
    this.bankModalVlaues.parbank_BicCode = '';
    this.bankModalVlaues.parbank_swiftCode = '';
    this.bankModalVlaues.parbank_routing = '';
    this.bankModalVlaues.parbank_website = '';
    this.bankModalVlaues.parbank_fax = '';
    this.bankModalVlaues.parbank_phone = '';
    this.bankModalVlaues.parbank_zip = '';
    this.bankModalVlaues.parbank_address1 = '';
    this.bankModalVlaues.parbank_address2 = '';
    this.bankModalVlaues.parbank_bankname = '';
    this.bankModalVlaues.parbank_email = '';
  }


  saveContact(a, index) {
    this.contactModalVlaue = new PartyMasterInterface();
    this.contactModalVlaue.parcontact_departmentshow = this.contactModalVlaues.parcontact_department;
    this.contactModalVlaue.parcontact_productshow = this.contactModalVlaues.parcontact_product;
    this.contactModalVlaue.parcontact_fullnameshow = this.contactModalVlaues.parcontact_fullname;
    this.contactModalVlaue.parcontact_designationshow = this.contactModalVlaues.parcontact_designation;
    this.contactModalVlaue.parcontact_emailshow = this.contactModalVlaues.parcontact_email;
    this.contactModalVlaue.parcontact_phoneworkshow = this.contactModalVlaues.parcontact_phonework;
    this.contactModalVlaue.parcontact_mobileshow = this.contactModalVlaues.parcontact_mobile;
    this.contactModalVlaue.parcontact_faxshow = this.contactModalVlaues.parcontact_fax;
    this.contactModalVlaue.parcontact_defaultshow = this.contactModalVlaues.parcontact_default;
    if (this.party_contact.length != 0) {
      for (var i = 0; i < this.party_contact.length; i++) {
        if (this.contactModalVlaues.parcontact_default == 'Y' && this.party_contact[i].parcontact_defaultshow == 'Y') {
          this.party_contact[i].parcontact_defaultshow = 'N';
        }
       // this.partyDetailsObj.partyContactsToCreate.partyContactIsDefault = this.party_contact[i].parcontact_defaultshow;
      }
    }
    this.partyDetailsObj.partyContactsToCreate.partyContactIsDefault = this.contactModalVlaues.parcontact_default;
    this.partyDetailsObj.partyContactsToCreate.productName = this.contactModalVlaues.parcontact_product;
    this.partyDetailsObj.partyContactsToCreate.partyContactFullname = this.contactModalVlaues.parcontact_fullname;

    this.partyDetailsObj.partyContactsToCreate.partyContactDesignation = this.contactModalVlaues.parcontact_designation;
    this.partyDetailsObj.partyContactsToCreate.partyContactEmail = this.contactModalVlaues.parcontact_email;
    this.partyDetailsObj.partyContactsToCreate.partyContactWorkPhone = this.contactModalVlaues.parcontact_phonework;
    this.partyDetailsObj.partyContactsToCreate.partyContactMobile = this.contactModalVlaues.parcontact_mobile;
    this.partyDetailsObj.partyContactsToCreate.partyContactFax = this.contactModalVlaues.parcontact_fax;
    this.partyDetailsObj.partyContactsToCreate.partyDepartment = this.contactModalVlaues.parcontact_department;
    this.partyDetailsObj.partyContactsToCreate.departmentCode = this.contactModalVlaues.parcontact_department;
    this.partyDetailsObj.partyContactsToCreate.product = this.contactModalVlaues.parcontact_product;

    this.party_contactToSave.push(this.partyDetailsObj.partyContactsToCreate);

    this.party_contact.push(this.contactModalVlaue);
    console.log(JSON.stringify(this.party_contact));
    this.contactmodals.hide();
    this.whencontactModalSaved = true;
    this.contactModalVlaues.parcontact_department = '';
    this.contactModalVlaues.parcontact_product = '';
    this.contactModalVlaues.parcontact_fullname = '';
    this.contactModalVlaues.parcontact_designation = '';
    this.contactModalVlaues.parcontact_email = '';
    this.contactModalVlaues.parcontact_phonework = '';
    this.contactModalVlaues.parcontact_mobile = '';
    this.contactModalVlaues.parcontact_fax = '';
    this.contactModalVlaues.parcontact_default = '';

  }



  editcontact(data, index) {
    this.contactmodals.show();
    this.contactModalVlaues = new PartyMasterInterface();
    this.contactModalVlaues.parcontact_department = data.parcontact_departmentshow;
    this.contactModalVlaues.parcontact_product = data.parcontact_productshow;
    this.contactModalVlaues.parcontact_fullname = data.parcontact_fullnameshow;
    this.contactModalVlaues.parcontact_designation = data.parcontact_designationshow;
    this.contactModalVlaues.parcontact_email = data.parcontact_emailshow;
    this.contactModalVlaues.parcontact_phonework = data.parcontact_phoneworkshow;
    this.contactModalVlaues.parcontact_mobile = data.parcontact_mobileshow;
    this.contactModalVlaues.parcontact_fax = data.parcontact_faxshow;
    this.contactModalVlaues.parcontact_default = data.parcontact_defaultshow;
    this.update_contact = true;
    this.update_contactdata = this.contactModalVlaues;
    this.contact_index = index;
  }

  updateContact() {
    this.update_contact = false;
    if (this.party_contact.length != 0) {
      for (var i = 0; i < this.party_contact.length; i++) {
        this.party_contact[this.contact_index].parcontact_departmentshow = this.update_contactdata.parcontact_department;
        this.party_contact[this.contact_index].parcontact_productshow = this.update_contactdata.parcontact_product;
        this.party_contact[this.contact_index].parcontact_fullnameshow = this.update_contactdata.parcontact_fullname;
        this.party_contact[this.contact_index].parcontact_designationshow = this.update_contactdata.parcontact_designation;
        this.party_contact[this.contact_index].parcontact_emailshow = this.update_contactdata.parcontact_email;
        this.party_contact[this.contact_index].parcontact_phoneworkshow = this.update_contactdata.parcontact_phonework;
        this.party_contact[this.contact_index].parcontact_mobileshow = this.update_contactdata.parcontact_mobile;
        this.party_contact[this.contact_index].parcontact_faxshow = this.update_contactdata.parcontact_fax;
        this.party_contact[this.contact_index].parcontact_defaultshow = this.update_contactdata.parcontact_default;
        if (this.party_contact.length != 0) {
          if (this.party_contact[this.contact_index].parcontact_defaultshow == 'Y') {
            if (this.contact_index != i) {
              this.party_contact[i].parcontact_defaultshow = 'N';
            }

          }

        }


      }
      this.contactmodals.hide();
      this.update_contact = false;
      this.contactModalVlaues.parcontact_department = '';
      this.contactModalVlaues.parcontact_product = '';
      this.contactModalVlaues.parcontact_fullname = '';
      this.contactModalVlaues.parcontact_designation = '';
      this.contactModalVlaues.parcontact_email = '';
      this.contactModalVlaues.parcontact_phonework = '';
      this.contactModalVlaues.parcontact_mobile = '';
      this.contactModalVlaues.parcontact_fax = '';
      this.contactModalVlaues.parcontact_default = '';
    }
  }

  closecontact() {
    this.contactmodals.hide();
    this.update_contact = false;
    this.contactModalVlaues.parcontact_department = '';
    this.contactModalVlaues.parcontact_product = '';
    this.contactModalVlaues.parcontact_fullname = '';
    this.contactModalVlaues.parcontact_designation = '';
    this.contactModalVlaues.parcontact_email = '';
    this.contactModalVlaues.parcontact_phonework = '';
    this.contactModalVlaues.parcontact_mobile = '';
    this.contactModalVlaues.parcontact_fax = '';
    this.contactModalVlaues.parcontact_default = '';

  }


  remove(index) {
   // debugger;
    this.party_addr.splice(index, 1);
    if (this.party_addr.length != 0) {
      for (var i = 0; i < this.party_addr.length; i++) {
        this.party_addr[0].paraddr_defaddrtypeToShowMOdal = 'Y';
        
      }
     
    }
       if(this.party_addrToSave != null || this.party_addrToSave.length != null || this.party_addrToSave.length !=0) {
         this.party_addrToSave.splice(i,1);
      }
  }
  removebank(index) {
    this.party_bank.splice(index, 1);
    if (this.party_bank.length != 0) {
      for (var i = 0; i < this.party_bank.length; i++) {
        this.party_bank[0].parbank_defaultshow = 'Y';
      }
      
    }
        if(this.party_banktoSave!= null || this.party_banktoSave.length != null || this.party_banktoSave.length !=0) {
          this.party_banktoSave.splice(i,1);
        }
  }
  removecontact(index) {
    this.party_contact.splice(index, 1);
    if (this.party_contact.length != 0) {
      for (var i = 0; i < this.party_contact.length; i++) {
        this.party_contact[0].parbank_defaultshow = 'Y';
          }
    }
        if(this.party_contactToSave != null || this.party_contactToSave.length != null || this.party_contactToSave.length !=0) {
          this.party_contactToSave.splice(i,1);
        }
    
  }
  savebank() {

  }
  getdestinationlist() {
    /* this.subscription = this.partyMasterService.getdestinationFromJson().subscribe(destination => {
       this.productsubModel = destination.body.processSubTypeValues;
     },
       error => { throw error },
       () => console.log('Finished')
     );*/

    this.vanedorsubModel = this.vanedorsubModel;
  }
  // getDropDownTradingCalender() {
  //   this.tadingCalenderList =  this.tradingCalender;
  // }

  ngOnDestroy() {
    // Called once, before the instance is destroyed.
    //  this.subscription.unsubscribe();
  }
  cancel() {
    this.router.navigate([listingpageparty]);
  }

  canDeactivate(): boolean {
    return this.isActionPerformed;
  }
  showFields(value) {
    this.partySubModel222 = [];
    this.vendorModel = [];
    this.tradingCalenderModel = [];
    this.req_partygradediv = false;
    this.req_internalGradeMApping = false;
    this.req_defaultPayment = false;
    this.req_plant = false;
    this.req_credit = false;
    this.req_partyLimits = false;
    this.req_wareHouse = false;
    this.req_exchange = false;
    this.req_document = false;
    this.req_broker = false;
    this.req_primaryContact = false;
    // console.log(this.partyMasterForm.value.partyInternalExternal);
    // debugger;
    if (this.partyMasterForm.value.partyInternalExternal == 'Y') {
      this.partySubModel = this.productsubModel;
      this.req_partydiv = false;
      this.req_internalGradeMApping = false;
      this.req_defaultPayment = false;
      this.req_functionalCurrency = true;
      this.req_OperationalCurrency = true;
      this.req_reportingUom = true;
      this.creditAllowdflag = false;
      this.fisrtDateWithOlam = false;
      this.gtpFlag = false;
    } else {
      this.partySubModel = this.productsubModel1;
      this.req_partydiv = true;
      this.req_functionalCurrency = false;
      this.req_OperationalCurrency = false;
      this.req_reportingUom = false;
      this.creditAllowdflag = true;
      this.fisrtDateWithOlam = true;
      this.gtpFlag = true;
      // this.req_internalGradeMApping = true;
      this.req_internalGradeMApping = false;
      this.req_defaultPayment = true;
    }
  }
  // grpCreditShow(event: Event) {
  //   if (this.partyMasterForm.value.creditAllowed == "Y") {
  //     this.req_partyGrpCredit = true;
  //     this.req_partyGrpCreditTenor = true;
  //   } else {
  //     this.req_partyGrpCredit = false;
  //     this.req_partyGrpCreditTenor = false;
  //   }
  // }
  radioChangeFalse() {
    this.req_partyGrpCredit = true;
    this.req_partyGrpCreditTenor = true;
  }
  radioChangeTrue() {
    this.req_partyGrpCredit = false;
    this.req_partyGrpCreditTenor = false;
  }
  valueChangeForParty(value: any): void {
   // debugger;
    console.log(value);
    if (value.length != 0) {
      for (var i = 0; i < value.length; i++) {
        var vendorSub = value[i].partyTypeName.toLowerCase();
        if (vendorSub == "customer") {
          this.credit_partyname=true;
          this.req_partygradediv = true;
          this.req_internalGradeMApping = true;
          this.req_credit = true;
          this.req_partyLimits = true;
          this.req_document = true;
         // this.addDocument();
         // this.addParty();
           //this.addCredit();
           //this.addGradeMapping();
          break;
        }
        else {
          this.credit_partyname=false;
          this.req_partygradediv = false;
          this.req_internalGradeMApping = false;
          this.req_defaultPayment = false;
          this.req_credit = false;
          this.req_partyLimits = false;
          this.req_document = false;
          this.req_primaryContact = false;
        }
      }
    } else {
      this.req_partygradediv = false;
      this.req_internalGradeMApping = false;
      this.req_defaultPayment = false;
      this.req_credit = false;
      this.req_partyLimits = false;
      this.req_document = false;
      this.req_primaryContact = false;
    }
  }
  valueChange(value: any): void {
    console.log(value);
    //  public req_partygradediv : boolean = false;
    // public req_internalGradeMApping : boolean = false;
    // public req_defaultPayment : boolean = false;
    //  public req_plant : boolean = false;

    //  public req_credit : boolean = false;

    //  public req_partyLimits : boolean = false;
    //  public req_wareHouse : boolean = false;
    //  public req_exchange : boolean = false;
    //  public req_document : boolean = false;
    //  public req_broker : boolean = false;
    // public req_primaryContact : boolean = true;
    this.datasource = value;
    const changes = this.differ.diff(value);
    if (changes._collection.length == 0) {
      this.req_wareHouse = false;
      this.req_plant = false;
      this.req_broker = false;
      this.req_exchange = false;
    }
    if (changes._removalsHead != null || changes._removalsHead != undefined) {
      if (changes._removalsHead.item.partyClassName != undefined || changes._removalsHead.item.partyClassName != null) {
        if (changes._removalsHead.item.partyClassName.toLowerCase() == "warehouse") {
          this.req_wareHouse = false;
        }
        if (changes._removalsHead.item.partyClassName.toLowerCase() == "roasters") {
          this.req_plant = false;
        }
        if (changes._removalsHead.item.partyClassName.toLowerCase() == "broker") {
          this.req_broker = false;
        }
        if (changes._removalsHead.item.partyClassName.toLowerCase() == "exchange") {
          this.req_exchange = false;
          this.req_primaryContact = true;
        }
      }
    }
    else if (value.length != 0) {
      for (var i = 0; i < value.length; i++) {
        var prosub = value[i].partyClassName.toLowerCase();
        if ((prosub.indexOf('broker') > -1) && this.partyMasterForm.value.partyInternalExternal == 'N') {
          this.req_broker = true;
        }
        else if ((prosub == "roaster") && this.partyMasterForm.value.partyInternalExternal == 'N') {
          this.req_plant = true;
        }
        else if (prosub == "warehouse") {
          this.req_wareHouse = true;
        } else if ((prosub == "exchange") && this.partyMasterForm.value.partyInternalExternal == 'N') {
          this.req_exchange = true;
          this.req_primaryContact = false;
        }
        else if ((prosub == "trader") && this.partyMasterForm.value.partyInternalExternal == 'N') {

        }
        else {
          this.req_partygradediv = false;
          this.req_internalGradeMApping = false;
          this.req_defaultPayment = false;
          this.req_plant = false;
          this.req_credit = false;
          this.req_partyLimits = false;
          this.req_wareHouse = false;
          this.req_exchange = false;
          this.req_document = false;
          this.req_broker = false;
          this.req_primaryContact = false;
        }
      }
    }
  }

  isValidForm() { 
     this.addressError = false;
      this.externalMappingError = false;
    this.count = 0;
    if (this.partyMasterForm.value.partyInternalExternal === '' || this.partyMasterForm.value.partyInternalExternal === undefined) {
      this.ineternal_exeternal = true;
       this.count++;
    }
    else {
      this.ineternal_exeternal = false;
    }
    if (this.partyMasterForm.value.partyName === '' || this.partyMasterForm.value.partyName === undefined) {
      this.partyName = true;
       this.count++;
    }
    else {
      this.partyName = false;
    }
    if (this.partyMasterForm.value.geohierarchy === '' || this.partyMasterForm.value.geohierarchy === undefined) {
      this.geohierarchy = true;
       this.count++;
    }
    else {
      this.geohierarchy = false;
    }


    if (this.partyMasterForm.value.marketingDesk === '' || this.partyMasterForm.value.marketingDesk === undefined) {
      this.marketingDesk = true;
       this.count++;
    }
    else {
      this.marketingDesk = false;
    }

    if ((this.partyMasterForm.value.creditAllowed === '' || this.partyMasterForm.value.creditAllowed === undefined)
      && this.partyMasterForm.value.partyInternalExternal == 'N') {
      this.creditAllowedShow = true;
       this.count++;
    }
    else {
      this.creditAllowedShow = false;
    }
    if ((this.partyMasterForm.value.partyType.length == 0 || this.partyMasterForm.value.partyType == null)
      && this.partyMasterForm.value.partyInternalExternal == 'N') {
      this.partyTypeManadatory = true;
       this.count++;
    }
    else {
      this.partyTypeManadatory = false;
    }
    if ((this.partyMasterForm.value.functionalCurrency === '' || this.partyMasterForm.value.functionalCurrency === undefined)
      && this.partyMasterForm.value.partyInternalExternal == 'Y') {
      this.functionalCurrency = true;
       this.count++;
    }
    else {
      this.functionalCurrency = false;
    }
    if ((this.partyMasterForm.value.OperationalCurrency === '' || this.partyMasterForm.value.OperationalCurrency === undefined)
      && this.partyMasterForm.value.partyInternalExternal == 'Y') {
      this.OperationalCurrency = true;
       this.count++;
    }
    else {
      this.OperationalCurrency = false;
    }
    if ((this.partyMasterForm.value.reportingUom === '' || this.partyMasterForm.value.reportingUom === undefined)
      && this.partyMasterForm.value.partyInternalExternal == 'Y') {
      this.reportingUom = true;
       this.count++;
    }
    else {
      this.reportingUom = false;
    }
    if ((this.partyMasterForm.value.groupCreditLimit === '' || this.partyMasterForm.value.groupCreditLimit === undefined)
      && this.partyMasterForm.value.isGroupCompany == 'Y' && this.partyMasterForm.value.partyInternalExternal == 'N') {
      this.groupCreditLimit = true;
       this.count++;
    }
    else {
      this.groupCreditLimit = false;
    }
    if ((this.partyMasterForm.value.groupCreditTenor === '' || this.partyMasterForm.value.groupCreditTenor === undefined)
      && this.partyMasterForm.value.isGroupCompany == 'Y') {
      this.groupCreditTenor = true;
       this.count++;
    }
    else {
      this.groupCreditTenor = false;
    }
    if (this.partyMasterForm.value.creditLimit != null) {
       if (this.partyMasterForm.value.creditLimit.length != 0) {
      this.partyMasterService.creditLimitList = this.partyMasterForm.value.creditLimit;
      for (var i = 0; i < this.partyMasterForm.value.creditLimit.length; i++) {
        if ((this.partyMasterForm.value.creditLimit[i].FactoringEndDate === '' || (this.partyMasterForm.value.creditLimit[i].FactoringEndDate == null))
          && (this.partyMasterForm.value.partyInternalExternal == 'N')) {
          if (this.partyMasterForm.value.partyType != null) {
            for (var i = 0; i < this.partyMasterForm.value.partyType.length; i++) {
              if (this.partyMasterForm.value.partyType[i].partyTypeName == "Customer") {
                this.FactoringEndDate = true;
                 this.count++;
              } else {
                 this.FactoringEndDate = false;
              }
            } 
          } else {
             this.FactoringEndDate = false;
          }
        }
        else {
          this.FactoringEndDate = false;
        }
      }
    }  else {
          this.FactoringEndDate = false;
        }
  }  else {
          this.FactoringEndDate = false;
   }
    // if ((this.partyMasterForm.value.limitType === '' || this.partyMasterForm.value.limitType === undefined)
    //   && (this.partyMasterForm.value.partyInternalExternal == 'N')) {
    //   if (this.partyMasterForm.value.partyType != null) {
    //     for (var i = 0; i < this.partyMasterForm.value.partyType.length; i++) {
    //       if (this.partyMasterForm.value.partyType[i].partyTypeName == "Customer") {
    //         this.limitType = true;
    //       }
    //     }
    //   }
    // }
    // else {
    //   this.limitType = false;
    // }
    if (this.partyMasterForm.value.creditLimit != null) {
      if(this.partyMasterForm.value.creditLimit.length != 0) {
      for (var i = 0; i < this.partyMasterForm.value.creditLimit.length; i++) {
        if ((this.partyMasterForm.value.creditLimit[i].limitType === '' || (this.partyMasterForm.value.creditLimit[i].limitType == null))
          && (this.partyMasterForm.value.partyInternalExternal == 'N')) {
          if (this.partyMasterForm.value.partyType != null) {
            for (var i = 0; i < this.partyMasterForm.value.partyType.length; i++) {
              if (this.partyMasterForm.value.partyType[i].partyTypeName == "Customer") {
                this.limitType = true;
                 this.count++;

              } else {
                  this.limitType = false;
              }
            }
          } else {
              this.limitType = false;
          }
        }
        else {
          this.limitType = false;
        }
      }
    } else {
          this.limitType = false;
    }
    } else {
          this.limitType = false;
  }
    if(this.partyMasterForm.value.partyClassification != undefined || this.partyMasterForm.value.partyClassification != null) {
    if (this.partyMasterForm.value.partyClassification.length != 0) {
      for (var i = 0; i < this.partyMasterForm.value.partyClassification.length; i++) {
        var classification = this.partyMasterForm.value.partyClassification[i].partyClassName.toLowerCase();
        if ((this.partyMasterForm.value.commissionUom === '' || this.partyMasterForm.value.commissionUom === undefined || (this.partyMasterForm.value.commissionUom == null))
          && (this.partyMasterForm.value.partyInternalExternal == 'N' && (classification.indexOf('broker') > -1))) {
          this.commissionUom = true;
           this.count++;
        }
        else {
          this.commissionUom = false;
        }
      }
    } else {
      this.commissionUom = false;
    }
    } else {
      this.commissionUom = false;
    }
     this.partyCheck = this.partyMasterForm.value;
   if(this.partyCheck.translatedAddress != null) {
    for (let i = 0; i <  this.partyCheck.translatedAddress.length ; i++) {
                 for (let j = i + 1 ; j <  this.partyCheck.translatedAddress.length ; j++) {
                    console.log('// check duplicate nego terms mapping' );
                    if (this.partyCheck.translatedAddress[i].addressName ===
                            this.partyCheck.translatedAddress[j].addressName  &&
                             this.partyCheck.translatedAddress[i].addressLanguage ===
                             this.partyCheck.translatedAddress[j].addressLanguage ) {
                                console.log('// duplicate occured in nego terms')
                                this.partyMasterService.errorMessage = ' Address Name and Language must be Unique ' ;
                                this.duplicatedError.show();
                                this.addressError = true;
                                this.count++;

                    } else {
                       this.addressError = false;
                    }
              }
             
            }
   }
    if(this.partyCheck.externalSystemMapping != null) {
    for (let i = 0; i < this.partyCheck.externalSystemMapping.length ; i++) {
                 for (let j = i + 1 ; j <  this.partyCheck.externalSystemMapping.length ; j++) {
                    console.log('// check duplicate role mapping' );
                    if (this.partyCheck.externalSystemMapping[i].destinationSystem ===
                    this.partyCheck.externalSystemMapping[j].destinationSystem  &&
                        this.partyCheck.externalSystemMapping[i].externalSystemMappingType ===
                        this.partyCheck.externalSystemMapping[j].externalSystemMappingType  ) {
                    console.log('// duplicate occured in role mapping')
                  this.partyMasterService.errorMessage = ' External System Mapping must be Unique ' ;
                                this.duplicatedError.show();
                                this.externalMappingError = true;
                                this.count++;
                                
            
                    } else {
                       this.externalMappingError = false;
                    }

              }


            }
    }
    if (this.ineternal_exeternal === false && this.partyName === false && this.geohierarchy === false && this.marketingDesk === false &&
      this.creditAllowedShow === false && this.partyTypeManadatory === false && this.groupCreditLimit === false &&
      this.groupCreditTenor === false && this.FactoringEndDate === false && this.limitType === false && this.commissionUom === false
      && this.reportingUom === false && this.addressError===false && this.externalMappingError===false && this.OperationalCurrency === false && this.functionalCurrency === false) {
      //alert('save');
      return true;
    }
    else {
      this.validate.show();
      setTimeout(() => { this.validate.hide(); }, 3000);
      return false;
    }
  }

  saveParty(status) {
    this.partyDetailsObj.partyBasicDetails.partyClassificationIds = [];
    this.partyDetailsObj.partyBasicDetails.partyTypeIds = [];
    this.partyTranslation1233 = [];
    this.partyDetailsTranslation.partyTranslationsToCreate.alternativeFullName = this.partyMasterForm.value.alternativeFullName;
    this.partyDetailsTranslation.partyTranslationsToCreate.alternativeName = this.partyMasterForm.value.alternativeName;
    this.partyDetailsTranslation.partyTranslationsToCreate.language = this.partyMasterForm.value.nameLanguage;
    this.partyTranslation1233.push(this.partyDetailsTranslation.partyTranslationsToCreate);
    console.log(JSON.stringify(this.partyMasterForm.value));
    if (this.isValidForm()) {
    this.partyDetailsObj.pkPartyId = "";
    this.partyDetailsObj.partyBasicDetails.partyCode = "";
    this.partyDetailsObj.partyBasicDetails.partyName = this.partyMasterForm.value.partyName;
    this.partyDetailsObj.partyBasicDetails.partyFullName = this.partyMasterForm.value.partyFullName;
    this.partyDetailsObj.partyBasicDetails.parentParty = '';
    this.partyDetailsObj.partyBasicDetails.geoCountry = this.partyMasterForm.value.geohierarchy;
    this.partyDetailsObj.partyBasicDetails.partyTaxRegNo = this.partyMasterForm.value.taxRegistrationNo;
    this.partyDetailsObj.partyBasicDetails.marketingDesk = this.partyMasterForm.value.marketingDesk;
    this.partyDetailsObj.partyBasicDetails.partyRatingCode = this.partyMasterForm.value.partyRatingCode;
    this.partyDetailsObj.partyBasicDetails.partyRatingId = this.partyMasterForm.value.partyRating;
    this.partyDetailsObj.partyBasicDetails.groupCreditLimit = this.partyMasterForm.value.groupCreditLimit;
    this.partyDetailsObj.partyBasicDetails.groupCreditTenorInDays = this.partyMasterForm.value.groupCreditTenor;
    this.partyDetailsObj.partyBasicDetails.parentParty = '';
    if(this.partyMasterForm.value.partyClassification != undefined){
    if (this.partyMasterForm.value.partyClassification.length != 0 || this.partyMasterForm.value.partyClassification != null ) {
      for (var i = 0; i < this.partyMasterForm.value.partyClassification.length; i++) {
        var ids = this.partyMasterForm.value.partyClassification[i].pkPartyClassId;
        this.partyDetailsObj.partyBasicDetails.partyClassificationIds.push(ids);
      }
    } else {
      this.partyDetailsObj.partyBasicDetails.partyClassificationIds = [];
    }
    } else {
      this.partyDetailsObj.partyBasicDetails.partyClassificationIds = [];
    }
   // this.partyDetailsObj.partyBasicDetails.partyTaxRegNo = this.partyMasterForm.value.taxRegistrationNumber;
    this.partyDetailsObj.partyBasicDetails.partyTaxRegDate = this.partyMasterForm.value.taxRegistrationDate;
    this.partyDetailsObj.partyBasicDetails.isGtpApplicableInd = this.partyMasterForm.value.gtprogram;
    this.partyDetailsObj.partyBasicDetails.functionalCurrency = this.partyMasterForm.value.functionalCurrency;
    this.partyDetailsObj.partyBasicDetails.operationalCurrency = this.partyMasterForm.value.operationalCurrency;
    this.partyDetailsObj.partyBasicDetails.defaultReportingUOM = this.partyMasterForm.value.reportingUom;
    this.partyDetailsObj.partyBasicDetails.fkStatusId = '';
    this.partyDetailsObj.partyGradeDefinition.partyGradeDescription = this.partyMasterForm.value.partyGradeDesc;
    this.partyDetailsObj.partyBasicDetails.partyInternalExternal = this.partyMasterForm.value.partyInternalExternal;
    this.partyDetailsObj.partyBasicDetails.isGroupCompany = this.partyMasterForm.value.isGroupCompany;
    this.partyDetailsObj.partyBasicDetails.selectGroup = this.partyMasterForm.value.selectGroup;
    this.partyDetailsObj.partyBasicDetails.countryId = '';
    this.partyDetailsObj.partyBasicDetails.marketingDeskId = '';
    this.partyDetailsObj.partyBasicDetails.creditAllowed = this.partyMasterForm.value.creditAllowed;
    if(this.partyMasterForm.value.partyType.length!= undefined) {
    if (this.partyMasterForm.value.partyType.length != 0 || this.partyMasterForm.value.partyType != null ) {
      for (var i = 0; i < this.partyMasterForm.value.partyType.length; i++) {
        var ids = this.partyMasterForm.value.partyType[i].pkPartyTypeId;
        this.partyDetailsObj.partyBasicDetails.partyTypeIds.push(ids);
      }
    } else {
      this.partyDetailsObj.partyBasicDetails.partyTypeIds = [];
    }
    } else {
      this.partyDetailsObj.partyBasicDetails.partyTypeIds = [];
    }
    // this.partyDetailsObj.partyBasicDetails.partyType = this.partyMasterForm.value.partyType;
    this.partyDetailsObj.partyBasicDetails.companyRegistration = this.partyMasterForm.value.companyRegistration;
    this.partyDetailsObj.partyBasicDetails.companyRegistrationDate = this.partyMasterForm.value.companyRegistrationDate;
    this.partyDetailsObj.partyBasicDetails.firsttransactionDate = this.partyMasterForm.value.firsttransactionDate;
    this.partyDetailsObj.partyBasicDetails.functionalCurrencyId = '';
    this.partyDetailsObj.partyBasicDetails.operationalCurrencyId = '';
    this.partyDetailsObj.partyBasicDetails.defaultReportingUOMId = '';
    this.partyDetailsObj.partyBasicDetails.statusName = status;
    this.partyDetailsObj.partyContacts = this.party_contactToSave;
    this.partyDetailsObj.partyAddress = this.party_addrToSave;
   // debugger;
    this.partyDetailsObj.partyBankAccountDetails = this.party_banktoSave;
    this.partyDetailsObj.partyGradeDefinition.prodId = this.partyMasterForm.value.product;
    this.partyDetailsObj.partyGradeDefinition.productName = "";
    this.partyDetailsObj.partyGradeDefinition.partyGradeName = this.partyMasterForm.value.partyGradeName;
    this.partyDetailsObj.partyGradeDefinition.partyGradeDescription = this.partyMasterForm.value.partyGradeDesc;
    this.partyDetailsObj.internalGradeMapping = this.partyMasterForm.value.internalGradeMapping;
    this.partyDetailsObj.paymentTermDetails.paymentTermId = this.partyMasterForm.value.paymentTerm
    this.partyDetailsObj.paymentTermDetails.paymentTerm = '';
    this.partyDetailsObj.paymentTermDetails.paymentTermDesc = '';
    this.partyDetailsObj.plantDetails = this.partyMasterForm.value.plantDetails;
    this.partyDetailsObj.externalSystemMapping = this.partyMasterForm.value.externalSystemMapping;
    this.partyDetailsObj.creditLimit = this.partyMasterForm.value.creditLimit;
    this.partyDetailsObj.partyLimit = this.partyMasterForm.value.partyLimit;
    this.partyDetailsObj.partyTranslations = this.partyTranslation1233 ;
    this.partyDetailsObj.translatedAddress = this.partyMasterForm.value.translatedAddress;
    this.partyDetailsObj.warehouseLocation = this.partyMasterForm.value.warehouseLocation;
    this.partyDetailsObj.exchangeDetails = this.partyMasterForm.value.exchangeDetails;
    this.partyDetailsObj.brokerDetails.tradingAccountNumber = this.partyMasterForm.value.tradingAccountNumber;
    this.partyDetailsObj.brokerDetails.commission = this.partyMasterForm.value.commmission;
    this.partyDetailsObj.brokerDetails.fkCommissionUomId = '';
    this.partyDetailsObj.brokerDetails.commissionUOM = this.partyMasterForm.value.commissionUom;
    this.partyDetailsObj.brokerDetails.fkStatusId = '';
    this.partyDetailsObj.brokerDetails.status = '';
    this.partyDetailsObj.exchangeDetails = this.partyMasterForm.value.exchangeDetails;
    this.partyDetailsObj.partyDocuments = this.partyMasterForm.value.partyDocuments;
    console.log(JSON.stringify(this.partyDetailsObj));
    this.subscription = this.partyMasterService.savePartyDetails(this.partyDetailsObj).subscribe(addUomDetail => {
      //this.basePaymentService.uomDetails.push(addUomDetail);
      this.router.navigate([listingpageparty], { queryParams: { 'success': 1 } });
    },
      error => {
        //alert(error);
        //  this.ispagebackPopupModal = true;
        this.duplicatedError.show();

      }, // Alerts need to be replaced by error
      () => console.log('Finished')
    );
    }
  }
   updatePartywithoutStatus() {
    this.partyDetailsObjUpdate = this.partyDataList;
    this.partyDetailsObjUpdate.partyBasicDetails.partyClassificationIds = [];
    this.partyDetailsObjUpdate.partyBasicDetails.partyTypeIds = [];
    this.partyTranslation1233 = [];
    this.partyDetailsTranslation.partyTranslationsToCreate.alternativeFullName = this.partyMasterForm.value.alternativeFullName;
    this.partyDetailsTranslation.partyTranslationsToCreate.alternativeName = this.partyMasterForm.value.alternativeName;
    this.partyDetailsTranslation.partyTranslationsToCreate.language = this.partyMasterForm.value.nameLanguage;
    this.partyTranslation1233.push(this.partyDetailsTranslation.partyTranslationsToCreate);
    console.log(JSON.stringify(this.partyMasterForm.value));
    if (this.isValidForm()) {
    this.partyDetailsObjUpdate.partyBasicDetails.partyCode = "";
    this.partyDetailsObjUpdate.partyBasicDetails.partyName = this.partyMasterForm.value.partyName;
    this.partyDetailsObjUpdate.partyBasicDetails.partyFullName = this.partyMasterForm.value.partyFullName;
    this.partyDetailsObjUpdate.partyBasicDetails.parentParty = '';
    this.partyDetailsObjUpdate.partyBasicDetails.geoCountry = this.partyMasterForm.value.geohierarchy;
    this.partyDetailsObjUpdate.partyBasicDetails.partyTaxRegNo = this.partyMasterForm.value.taxRegistrationNo;
    this.partyDetailsObjUpdate.partyBasicDetails.marketingDesk = this.partyMasterForm.value.marketingDesk;
    this.partyDetailsObjUpdate.partyBasicDetails.partyRatingCode = this.partyMasterForm.value.partyRatingCode;
    this.partyDetailsObjUpdate.partyBasicDetails.partyRatingId = this.partyMasterForm.value.partyRating;
    this.partyDetailsObjUpdate.partyBasicDetails.groupCreditLimit = this.partyMasterForm.value.groupCreditLimit;
    this.partyDetailsObjUpdate.partyBasicDetails.groupCreditTenorInDays = this.partyMasterForm.value.groupCreditTenor;
    this.partyDetailsObjUpdate.partyBasicDetails.parentParty = '';
    if(this.partyMasterForm.value.partyClassification != undefined){
    if (this.partyMasterForm.value.partyClassification.length != 0 || this.partyMasterForm.value.partyClassification != null ) {
      for (var i = 0; i < this.partyMasterForm.value.partyClassification.length; i++) {
        var ids = this.partyMasterForm.value.partyClassification[i].pkPartyClassId;
        this.partyDetailsObjUpdate.partyBasicDetails.partyClassificationIds.push(ids);
      }
    } else {
      this.partyDetailsObjUpdate.partyBasicDetails.partyClassificationIds = [];
    }
    } else {
      this.partyDetailsObjUpdate.partyBasicDetails.partyClassificationIds = [];
    }
   // this.partyDetailsObj.partyBasicDetails.partyTaxRegNo = this.partyMasterForm.value.taxRegistrationNumber;
    this.partyDetailsObjUpdate.partyBasicDetails.partyTaxRegDate = this.partyMasterForm.value.taxRegistrationDate;
    this.partyDetailsObjUpdate.partyBasicDetails.isGtpApplicableInd = this.partyMasterForm.value.gtprogram;
    this.partyDetailsObjUpdate.partyBasicDetails.functionalCurrency = this.partyMasterForm.value.functionalCurrency;
    this.partyDetailsObjUpdate.partyBasicDetails.operationalCurrency = this.partyMasterForm.value.operationalCurrency;
    this.partyDetailsObjUpdate.partyBasicDetails.defaultReportingUOM = this.partyMasterForm.value.reportingUom;
    this.partyDetailsObjUpdate.partyBasicDetails.fkStatusId = '';
    this.partyDetailsObjUpdate.partyGradeDefinition.partyGradeDescription = this.partyMasterForm.value.partyGradeDesc;
    this.partyDetailsObjUpdate.partyBasicDetails.partyInternalExternal = this.partyMasterForm.value.partyInternalExternal;
    this.partyDetailsObjUpdate.partyBasicDetails.isGroupCompany = this.partyMasterForm.value.isGroupCompany;
    this.partyDetailsObjUpdate.partyBasicDetails.selectGroup = this.partyMasterForm.value.selectGroup;
    this.partyDetailsObjUpdate.partyBasicDetails.countryId = '';
    this.partyDetailsObjUpdate.partyBasicDetails.marketingDeskId = '';
    this.partyDetailsObjUpdate.partyBasicDetails.creditAllowed = this.partyMasterForm.value.creditAllowed;
    if(this.partyMasterForm.value.partyType.length!= undefined) {
    if (this.partyMasterForm.value.partyType.length != 0 || this.partyMasterForm.value.partyType != null ) {
      for (var i = 0; i < this.partyMasterForm.value.partyType.length; i++) {
        var ids = this.partyMasterForm.value.partyType[i].pkPartyTypeId;
        this.partyDetailsObjUpdate.partyBasicDetails.partyTypeIds.push(ids);
      }
    } else {
      this.partyDetailsObjUpdate.partyBasicDetails.partyTypeIds = [];
    }
    } else {
      this.partyDetailsObjUpdate.partyBasicDetails.partyTypeIds = [];
    }
    // this.partyDetailsObj.partyBasicDetails.partyType = this.partyMasterForm.value.partyType;
    this.partyDetailsObjUpdate.partyBasicDetails.companyRegistration = this.partyMasterForm.value.companyRegistration;
    this.partyDetailsObjUpdate.partyBasicDetails.companyRegistrationDate = this.partyMasterForm.value.companyRegistrationDate;
    this.partyDetailsObjUpdate.partyBasicDetails.firsttransactionDate = this.partyMasterForm.value.firsttransactionDate;
    this.partyDetailsObjUpdate.partyBasicDetails.functionalCurrencyId = '';
    this.partyDetailsObjUpdate.partyBasicDetails.operationalCurrencyId = '';
    this.partyDetailsObjUpdate.partyBasicDetails.defaultReportingUOMId = '';
    //this.partyDetailsObjUpdate.partyBasicDetails.statusName = status;
    this.partyDetailsObjUpdate.partyContacts = this.party_contactToSave;
    this.partyDetailsObjUpdate.partyAddress = this.party_addrToSave;
    this.partyDetailsObjUpdate.partyBankAccountDetails = this.party_banktoSave;
    this.partyDetailsObjUpdate.partyGradeDefinition.prodId = this.partyMasterForm.value.product;
    this.partyDetailsObjUpdate.partyGradeDefinition.productName = "";
    this.partyDetailsObjUpdate.partyGradeDefinition.partyGradeName = this.partyMasterForm.value.partyGradeName;
    this.partyDetailsObjUpdate.partyGradeDefinition.partyGradeDescription = this.partyMasterForm.value.partyGradeDesc;
    this.partyDetailsObjUpdate.internalGradeMapping = this.partyMasterForm.value.internalGradeMapping;
    this.partyDetailsObjUpdate.paymentTermDetails.paymentTermId = this.partyMasterForm.value.paymentTerm
    this.partyDetailsObjUpdate.paymentTermDetails.paymentTerm = '';
    this.partyDetailsObjUpdate.paymentTermDetails.paymentTermDesc = '';
    this.partyDetailsObjUpdate.plantDetails = this.partyMasterForm.value.plantDetails;
    this.partyDetailsObjUpdate.externalSystemMapping = this.partyMasterForm.value.externalSystemMapping;
    this.partyDetailsObjUpdate.creditLimit = this.partyMasterForm.value.creditLimit;
    this.partyDetailsObjUpdate.partyLimit = this.partyMasterForm.value.partyLimit;
    this.partyDetailsObjUpdate.partyTranslations = this.partyTranslation1233 ;
    this.partyDetailsObjUpdate.translatedAddress = this.partyMasterForm.value.translatedAddress;
    this.partyDetailsObjUpdate.warehouseLocation = this.partyMasterForm.value.warehouseLocation;
    this.partyDetailsObjUpdate.exchangeDetails = this.partyMasterForm.value.exchangeDetails;
    this.partyDetailsObjUpdate.brokerDetails.tradingAccountNumber = this.partyMasterForm.value.tradingAccountNumber;
    this.partyDetailsObjUpdate.brokerDetails.commission = this.partyMasterForm.value.commmission;
    this.partyDetailsObjUpdate.brokerDetails.fkCommissionUomId = '';
    this.partyDetailsObjUpdate.brokerDetails.commissionUOM = this.partyMasterForm.value.commissionUom;
    this.partyDetailsObjUpdate.brokerDetails.fkStatusId = '';
    this.partyDetailsObjUpdate.brokerDetails.status = '';
    this.partyDetailsObjUpdate.exchangeDetails = this.partyMasterForm.value.exchangeDetails;
    this.partyDetailsObjUpdate.partyDocuments = this.partyMasterForm.value.partyDocuments;
    console.log(JSON.stringify(this.partyDetailsObjUpdate));
    this.subscription = this.partyMasterService.updtaePartyDetails(this.partyDetailsObjUpdate).subscribe(addUomDetail => {
      //this.basePaymentService.uomDetails.push(addUomDetail);
      this.router.navigate([listingpageparty], { queryParams: { 'success': 2 } });
    },
      error => {
        //alert(error);
        //  this.ispagebackPopupModal = true;
        this.duplicatedError.show();

      }, // Alerts need to be replaced by error
      () => console.log('Finished')
    );
    }
  }
  updateParty(status) {
    this.partyDetailsObjUpdate = this.partyDataList;
    this.partyDetailsObjUpdate.partyBasicDetails.partyClassificationIds = [];
    this.partyDetailsObjUpdate.partyBasicDetails.partyTypeIds = [];
    this.partyTranslation1233 = [];
    this.partyDetailsTranslation.partyTranslationsToCreate.alternativeFullName = this.partyMasterForm.value.alternativeFullName;
    this.partyDetailsTranslation.partyTranslationsToCreate.alternativeName = this.partyMasterForm.value.alternativeName;
    this.partyDetailsTranslation.partyTranslationsToCreate.language = this.partyMasterForm.value.nameLanguage;
    this.partyTranslation1233.push(this.partyDetailsTranslation.partyTranslationsToCreate);
    console.log(JSON.stringify(this.partyMasterForm.value));
    if (this.isValidForm()) {
    this.partyDetailsObjUpdate.partyBasicDetails.partyCode = "";
    this.partyDetailsObjUpdate.partyBasicDetails.partyName = this.partyMasterForm.value.partyName;
    this.partyDetailsObjUpdate.partyBasicDetails.partyFullName = this.partyMasterForm.value.partyFullName;
    this.partyDetailsObjUpdate.partyBasicDetails.parentParty = '';
    this.partyDetailsObjUpdate.partyBasicDetails.geoCountry = this.partyMasterForm.value.geohierarchy;
    this.partyDetailsObjUpdate.partyBasicDetails.partyTaxRegNo = this.partyMasterForm.value.taxRegistrationNo;
    this.partyDetailsObjUpdate.partyBasicDetails.marketingDesk = this.partyMasterForm.value.marketingDesk;
    this.partyDetailsObjUpdate.partyBasicDetails.partyRatingCode = this.partyMasterForm.value.partyRatingCode;
    this.partyDetailsObjUpdate.partyBasicDetails.partyRatingId = this.partyMasterForm.value.partyRating;
    this.partyDetailsObjUpdate.partyBasicDetails.groupCreditLimit = this.partyMasterForm.value.groupCreditLimit;
    this.partyDetailsObjUpdate.partyBasicDetails.groupCreditTenorInDays = this.partyMasterForm.value.groupCreditTenor;
    this.partyDetailsObjUpdate.partyBasicDetails.parentParty = '';
    if(this.partyMasterForm.value.partyClassification != undefined){
    if (this.partyMasterForm.value.partyClassification.length != 0 || this.partyMasterForm.value.partyClassification != null ) {
      for (var i = 0; i < this.partyMasterForm.value.partyClassification.length; i++) {
        var ids = this.partyMasterForm.value.partyClassification[i].pkPartyClassId;
        this.partyDetailsObjUpdate.partyBasicDetails.partyClassificationIds.push(ids);
      }
    } else {
      this.partyDetailsObjUpdate.partyBasicDetails.partyClassificationIds = [];
    }
    } else {
      this.partyDetailsObjUpdate.partyBasicDetails.partyClassificationIds = [];
    }
   // this.partyDetailsObj.partyBasicDetails.partyTaxRegNo = this.partyMasterForm.value.taxRegistrationNumber;
    this.partyDetailsObjUpdate.partyBasicDetails.partyTaxRegDate = this.partyMasterForm.value.taxRegistrationDate;
    this.partyDetailsObjUpdate.partyBasicDetails.isGtpApplicableInd = this.partyMasterForm.value.gtprogram;
    this.partyDetailsObjUpdate.partyBasicDetails.functionalCurrency = this.partyMasterForm.value.functionalCurrency;
    this.partyDetailsObjUpdate.partyBasicDetails.operationalCurrency = this.partyMasterForm.value.operationalCurrency;
    this.partyDetailsObjUpdate.partyBasicDetails.defaultReportingUOM = this.partyMasterForm.value.reportingUom;
    this.partyDetailsObjUpdate.partyBasicDetails.fkStatusId = '';
    this.partyDetailsObjUpdate.partyGradeDefinition.partyGradeDescription = this.partyMasterForm.value.partyGradeDesc;
    this.partyDetailsObjUpdate.partyBasicDetails.partyInternalExternal = this.partyMasterForm.value.partyInternalExternal;
    this.partyDetailsObjUpdate.partyBasicDetails.isGroupCompany = this.partyMasterForm.value.isGroupCompany;
    this.partyDetailsObjUpdate.partyBasicDetails.selectGroup = this.partyMasterForm.value.selectGroup;
    this.partyDetailsObjUpdate.partyBasicDetails.countryId = '';
    this.partyDetailsObjUpdate.partyBasicDetails.marketingDeskId = '';
    this.partyDetailsObjUpdate.partyBasicDetails.creditAllowed = this.partyMasterForm.value.creditAllowed;
    if(this.partyMasterForm.value.partyType.length!= undefined) {
    if (this.partyMasterForm.value.partyType.length != 0 || this.partyMasterForm.value.partyType != null ) {
      for (var i = 0; i < this.partyMasterForm.value.partyType.length; i++) {
        var ids = this.partyMasterForm.value.partyType[i].pkPartyTypeId;
        this.partyDetailsObjUpdate.partyBasicDetails.partyTypeIds.push(ids);
      }
    } else {
      this.partyDetailsObjUpdate.partyBasicDetails.partyTypeIds = [];
    }
    } else {
      this.partyDetailsObjUpdate.partyBasicDetails.partyTypeIds = [];
    }
    // this.partyDetailsObj.partyBasicDetails.partyType = this.partyMasterForm.value.partyType;
    this.partyDetailsObjUpdate.partyBasicDetails.companyRegistration = this.partyMasterForm.value.companyRegistration;
    this.partyDetailsObjUpdate.partyBasicDetails.companyRegistrationDate = this.partyMasterForm.value.companyRegistrationDate;
    this.partyDetailsObjUpdate.partyBasicDetails.firsttransactionDate = this.partyMasterForm.value.firsttransactionDate;
    this.partyDetailsObjUpdate.partyBasicDetails.functionalCurrencyId = '';
    this.partyDetailsObjUpdate.partyBasicDetails.operationalCurrencyId = '';
    this.partyDetailsObjUpdate.partyBasicDetails.defaultReportingUOMId = '';
    this.partyDetailsObjUpdate.partyBasicDetails.statusName = status;
    this.partyDetailsObjUpdate.partyContacts = this.party_contactToSave;
    this.partyDetailsObjUpdate.partyAddress = this.party_addrToSave;
    this.partyDetailsObjUpdate.partyBankAccountDetails = this.party_banktoSave;
    this.partyDetailsObjUpdate.partyGradeDefinition.prodId = this.partyMasterForm.value.product;
    this.partyDetailsObjUpdate.partyGradeDefinition.productName = "";
    this.partyDetailsObjUpdate.partyGradeDefinition.partyGradeName = this.partyMasterForm.value.partyGradeName;
    this.partyDetailsObjUpdate.partyGradeDefinition.partyGradeDescription = this.partyMasterForm.value.partyGradeDesc;
    this.partyDetailsObjUpdate.internalGradeMapping = this.partyMasterForm.value.internalGradeMapping;
    this.partyDetailsObjUpdate.paymentTermDetails.paymentTermId = this.partyMasterForm.value.paymentTerm
    this.partyDetailsObjUpdate.paymentTermDetails.paymentTerm = '';
    this.partyDetailsObjUpdate.paymentTermDetails.paymentTermDesc = '';
    this.partyDetailsObjUpdate.plantDetails = this.partyMasterForm.value.plantDetails;
    this.partyDetailsObjUpdate.externalSystemMapping = this.partyMasterForm.value.externalSystemMapping;
    this.partyDetailsObjUpdate.creditLimit = this.partyMasterForm.value.creditLimit;
    this.partyDetailsObjUpdate.partyLimit = this.partyMasterForm.value.partyLimit;
    this.partyDetailsObjUpdate.partyTranslations = this.partyTranslation1233 ;
    this.partyDetailsObjUpdate.translatedAddress = this.partyMasterForm.value.translatedAddress;
    this.partyDetailsObjUpdate.warehouseLocation = this.partyMasterForm.value.warehouseLocation;
    this.partyDetailsObjUpdate.exchangeDetails = this.partyMasterForm.value.exchangeDetails;
    this.partyDetailsObjUpdate.brokerDetails.tradingAccountNumber = this.partyMasterForm.value.tradingAccountNumber;
    this.partyDetailsObjUpdate.brokerDetails.commission = this.partyMasterForm.value.commmission;
    this.partyDetailsObjUpdate.brokerDetails.fkCommissionUomId = '';
    this.partyDetailsObjUpdate.brokerDetails.commissionUOM = this.partyMasterForm.value.commissionUom;
    this.partyDetailsObjUpdate.brokerDetails.fkStatusId = '';
    this.partyDetailsObjUpdate.brokerDetails.status = '';
    this.partyDetailsObjUpdate.exchangeDetails = this.partyMasterForm.value.exchangeDetails;
    this.partyDetailsObjUpdate.partyDocuments = this.partyMasterForm.value.partyDocuments;
    console.log(JSON.stringify(this.partyDetailsObjUpdate));
    this.subscription = this.partyMasterService.updtaePartyDetails(this.partyDetailsObjUpdate).subscribe(addUomDetail => {
      //this.basePaymentService.uomDetails.push(addUomDetail);
      this.router.navigate([listingpageparty], { queryParams: { 'success': 1 } });
    },
      error => {
        //alert(error);
        //  this.ispagebackPopupModal = true;
        this.duplicatedError.show();

      }, // Alerts need to be replaced by error
      () => console.log('Finished')
    );
    }
  }
  numberonly(evt) {
    // if ( Number(this.gradeForm.value.hedgeRatio) < 0 || Number(this.gradeForm.value.hedgeRatio) > 100 ) {
    // this.req_hedgeratiolimit = true;
    // }
    const charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode !== 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
      return false;
    }
    return true;
  }
  checkEmail(inputvalue) {
    this.emailvalidation = false;
    var pattern = /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
    if (pattern.test(inputvalue)) {
      this.emailvalidation = false;
    } else {
      this.emailvalidation = true;
    }
  }
  ngDoCheck() {
  }
  reset() {
    this.ngOnInit();
  }
  getDropDownForDocumentName() {
    this.subscription = this.partyMasterService.getDocumentName().subscribe(addDoc => {
      this.docItem = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
  getDropDownPartyType() {
    this.subscription = this.partyMasterService.getDropDownPartyType().subscribe(addDoc => {
      this.partyTypeList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
  getDropDownForDocumentType() {
    this.subscription = this.partyMasterService.getDocumentType().subscribe(addDoc => {
      this.docItemType = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }

  getDropDownForCommisionUom() {
    this.subscription = this.partyMasterService.getCommisionUom().subscribe(addDoc => {
      this.commisionList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
  getDropDownForLanguage() {
    this.subscription = this.partyMasterService.getLanguageList().subscribe(addDoc => {
      this.languageList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
  getDropDownPaymentTerm() {
    this.subscription = this.partyMasterService.getPaymenttermDropDown().subscribe(addDoc => {
      this.paymentTermLstList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
  getDropDownDestionSystem() {
    this.subscription = this.partyMasterService.getDestinationDropDown().subscribe(addDoc => {
      this.destinationList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
  getAssressTypeDropDown() {
    this.subscription = this.partyMasterService.getAddressTypeDropDown().subscribe(addDoc => {
      this.addressType = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
  getDropDownLimitAlert() {
    this.subscription = this.partyMasterService.getLimitLevelAlert().subscribe(addDoc => {
      this.limitAlertLevel = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
  getDropDownLocationCode() {
    this.subscription = this.partyMasterService.getDropDownLocationCode().subscribe(addDoc => {
      this.locationCodeList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
  getDropDownCountry() {
    this.subscription = this.partyMasterService.getCountryDropDown().subscribe(addDoc => {
      this.countryList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
  getDropDownHolidayCalender() {
    this.subscription = this.partyMasterService.getHolidayCalenderList().subscribe(addDoc => {
      this.holidayCalenderList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
  getDropDownMarketingDesk() {
    this.subscription = this.partyMasterService.getMarketingDeskDropDown().subscribe(addDoc => {
      this.marketingDeskList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }

  //  getDropDownSelectGrp(){
  //    this.subscription = this.partyMasterService.getDropDownSelectGrp(this.partyMasterForm.value.partyInternalExternal).subscribe(addDoc => {
  //       this.locationCodeList = addDoc.body;
  //       // this.arbshortname = destination.body.externalSystemRefValues;
  //       // this.arbagency = destination.body.arbitrationAgencyValues;
  //     },
  //       error => {  },
  //       () => console.log('Finished')
  //     );
  //  }
  getDropDownDepartment() {
    this.subscription = this.partyMasterService.getDropDownDepartment().subscribe(addDoc => {
      this.departmentList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
  getDropPartyCode() {
    this.subscription = this.partyMasterService.getDropDownPartyCode().subscribe(addDoc => {
      this.partyCodeList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
  getDropDownAccountType() {
    this.subscription = this.partyMasterService.getDropDownAccountType().subscribe(addDoc => {
      this.accountTypeList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
  getDropDownPartyRating() {
    this.subscription = this.partyMasterService.getDropDownPartyRating().subscribe(addDoc => {
      this.partyRatingList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }


  getDropDownpartyClassification(data) {
    //  this.partyClassificationList = this.productsubModel1;
    this.subscription = this.partyMasterService.getDropDownPartyClassification().subscribe(addDoc => {
      if(data=="N"){
        this.partyClassificationList = addDoc.body;
      }
      else{
        this.partyClassificationList = [];
      }
      // this.partyClassificationList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
  getDropDownUnit() {
    this.subscription = this.partyMasterService.getDropDownUnit().subscribe(addDoc => {
      this.unitList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
  getDropDownProduct() {
    this.subscription = this.partyMasterService.getProductDropDown().subscribe(addDoc => {
      this.productList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
  getDropDownSelectGrp(event) {
    this.subscription = this.partyMasterService.getDropDownSelectGrp(this.partyMasterForm.value.partyInternalExternal).subscribe(addDoc => {
      this.selectgrpList = addDoc.body;
      this.getDropDownpartyClassification(event);
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
   getDropDownSelectGrpForEdit(external) {
     var flag = external;
    this.subscription = this.partyMasterService.getDropDownSelectGrp(flag).subscribe(addDoc => {
      this.selectgrpList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
  getDropDownAddressName() {
    this.subscription = this.partyMasterService.getDropDownAddressName().subscribe(addDoc => {
      this.addressName = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
  getDropDownCurrency() {
    this.subscription = this.partyMasterService.getCurrencyList().subscribe(addDoc => {
      this.currencyList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
  getDropDownLimitOn() {
    this.subscription = this.partyMasterService.getLimitLevelOn().subscribe(addDoc => {
      this.limitOnList = addDoc.body;
      // this.arbshortname = destination.body.externalSystemRefValues;
      // this.arbagency = destination.body.arbitrationAgencyValues;
    },
      error => { },
      () => console.log('Finished')
    );
  }
  onchangePaymentTerm() {
    this.filterPayment = [];
    var payId = this.partyMasterForm.value.paymentTerm;
    for (var i = 0; i < this.paymentTermLstList.length; i++) {
      if (this.paymentTermLstList[i].paymentTermId == payId) {
        var payObj = this.paymentTermLstList[i];
        this.filterPayment.push(payObj);
        console.log(this.filterPayment[0]);
      }
    }
    this.paymentDesc = this.filterPayment[0].payTermDesc;
    console.log(this.paymentDesc);
  }
  
  onchangePartyCode(event) {
    this.filterParty = [];
    var partyId = this.partyMasterForm.value.creditLimit[0].internalPartyCode;
    for (var i = 0; i < this.partyCodeList.length; i++) {
      if (this.partyCodeList[i].partyId == partyId) {
        var partyObj = this.partyCodeList[i];
        this.filterParty.push(partyObj);
        console.log(this.filterParty[0]);
      }
    }
    this.partyNamedefault = this.filterParty[0].partyName;
    this.indexValue = event;
    console.log(this.partyNamedefault);
    this.partyMasterForm.value.creditLimit[0].internalPartyName = this.partyNamedefault;
  }
  onchangePartyCodeForParty(event) {
    
    this.filterParty = [];  
    var partyId = this.partyMasterForm.value.partyLimit[event].internalPartyCodeOrg;
    for (var i = 0; i < this.partyCodeList.length; i++) {
      if (this.partyCodeList[i].partyId == partyId) {
        var partyObj = this.partyCodeList[i];
        this.filterParty.push(partyObj);
        console.log(this.filterParty[0]);
      }
    }
    this.internalPartyNameOrg = this.filterParty[0].partyName;
    this.whenPartyCodeinLimit = true;
    this.indexValue = event;
    console.log(this.partyNamedefault);
  }
  close(){
    this.duplicatedError.hide();
  }
}

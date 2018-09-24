import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../../shared/interface/responseData';
import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs/Rx';
import { Link } from '../../../../shared/interface/link';
import { PartyMasterInterface,CreditLimit } from '../party-interface';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { GridComponent, GridDataResult, DataStateChangeEvent} from '@progress/kendo-angular-grid';
import { environment } from '../../../../../environments/environment';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';
import { TreeviewItem, TreeviewConfig } from 'ngx-treeview';

@Injectable()
export class PartyMasterService extends AbstractLinkService {
partyDetails : PartyMasterInterface[];
errorMessage:string;
errorStatusCode:string;
partyName:string;
arrStringJSON:string;
statusName:string;
editpartybasic:PartyMasterInterface[];
editpartyaddress:PartyMasterInterface[];
creditLimitList:PartyMasterInterface[];
editpartyBankAccountDetails:PartyMasterInterface[];
editpartyContacts:PartyMasterInterface[];
editpartyGradeDefinition:PartyMasterInterface[];
editinternalGradeMapping:PartyMasterInterface[];
editpaymentTermDetails:PartyMasterInterface[];
editplantDetails:PartyMasterInterface[];
editexternalSystemMapping:PartyMasterInterface[];
editcreditLimit:PartyMasterInterface[];
editpartyLimit:PartyMasterInterface[];
editpartyTranslations:PartyMasterInterface[];
edittranslatedAddress:PartyMasterInterface[];
editwarehouseLocation:PartyMasterInterface[];
editexchangeDetails:PartyMasterInterface[];
editbrokerDetails:PartyMasterInterface[];
editpartyDocuments:PartyMasterInterface[];
editParty: PartyMasterInterface;
selectedPartyBodyToedit: any;
constructor(private http: Http) { 
 super();
  }
 getdestinationFromJson(){
  // return this.http.get('/product/productservice/product/getDropBox').map((response: Response) => <ResponseData>response.json());
  return true;
}
 getListingPageFromJson() {
    const url: string = '/party/partymasterservice/party';
    return this.http.get(url).map((response: Response) =>response.json());
   // return this.http.get(url).map((response: Response) => <ResponseData>response.json());
  }
getDocumentName(){
   return this.http.get('/reference/DocumentNameService/docName').map((response: Response) => <ResponseData>response.json());
}
getDocumentType(){
   return this.http.get('/reference/DocumentTypeService/docType').map((response: Response) => <ResponseData>response.json());
}
getCommisionUom(){
   return this.http.get('/reference/uomservice/uom').map((response: Response) => <ResponseData>response.json());
}
getLanguageList(){
   return this.http.get('/reference/MasterLanguageService/language').map((response: Response) => <ResponseData>response.json());
}
getselectedProduct(){
   return this.http.get('/bins/d6rk5').map((response: Response) =>response.json());//.map((response: Response) => <ResponseData>response.json());
}
getLimitLevelAlert(){
   return this.http.get('/limit/MasterLimitAlertLevelService/masterLimitAlertLevel').map((response: Response) => <ResponseData>response.json());
}
getLimitLevelOn(){
   return this.http.get('/limit/MasterCounterPartyLimitTypeService/counterPartyLimitType').map((response: Response) => <ResponseData>response.json());
}
getDropDownLocationCode(){
   return this.http.get('/location/warehouseLocation').map((response: Response) => <ResponseData>response.json());
}
getDropDownDepartment(){
   return this.http.get('/party/department').map((response: Response) => <ResponseData>response.json());
}
getDropDownAccountType(){
   return this.http.get('/party/accountType').map((response: Response) => <ResponseData>response.json());
}
getDropDownPartyRating(){
   return this.http.get('/party/rating').map((response: Response) => <ResponseData>response.json());
}
getDropDownPartyClassification(){
   return this.http.get('/party/classification').map((response: Response) => <ResponseData>response.json());
}
getDropDownSelectGrp(isParty){
  console.log(isParty);
   return this.http.get('/party/partyType/' + isParty).map((response: Response) => <ResponseData>response.json());
}
getDropDownPartyType() {
   return this.http.get('/party/partytype/').map((response: Response) => <ResponseData>response.json());
}
getDropDownUnit(){
   return this.http.get('/party/unitservice/unit').map((response: Response) => <ResponseData>response.json());
}
getDropDownPartyCode(){
   return this.http.get('/party/partyCode').map((response: Response) => <ResponseData>response.json());
}
getDropDownAddressName(){
   return this.http.get('/party/address').map((response: Response) => <ResponseData>response.json());
}
getCurrencyList(){
      return this.http.get('/reference/currencyservice/currency').map((response: Response) => <ResponseData>response.json());
}
getHolidayCalenderList(){
     return this.http.get('/reference/holidaycalendarservice/holidaycalendar').map((response: Response) => <ResponseData>response.json());  
}
getOriginDropDown(){
     return this.http.get('/product/originservice/origin').map((response: Response) => <ResponseData>response.json());  
}
getGradeDropDown(){
     return this.http.get('/product/gradeservice/grade').map((response: Response) => <ResponseData>response.json());  
}
getAddressTypeDropDown(){
     return this.http.get('/location/addressType').map((response: Response) => <ResponseData>response.json());  
}
getMarketingDeskDropDown(){
     return this.http.get('/reference/marketDesk').map((response: Response) => <ResponseData>response.json());  
}
getCountryDropDown(){
     return this.http.get('/location/geoservice/geo').map((response: Response) => <ResponseData>response.json());  
}
getProductDropDown(){
     return this.http.get('/product/productservice/product/').map((response: Response) => <ResponseData>response.json());  
}
getPaymenttermDropDown(){
     return this.http.get('/terms/paymenttermservice/paymentterm').map((response: Response) => <ResponseData>response.json());  
}
getDestinationDropDown(){
     return this.http.get('/finance/glservice/gl/externalsystemref').map((response: Response) => <ResponseData>response.json());  
}
saveAddressPopUp(baseDet: PartyMasterInterface[]) {
    //    const url = '/uomservice/uom';
    const url = '/product/productservice/product/';
    return this.http.post( url, baseDet) 
    .map((res: Response ) => {
                  if (res) {
                    if (res.status === 201) {
                        return [{ status: res.status, json: res }]
                    } else if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
              }
            return res.json();
      }).catch((error: any) => {
                        return this.errorHandling(error);
                  });
   }
   updateAddressPop(baseDet: PartyMasterInterface[]) {
    //    const url = '/uomservice/uom';
    const url = '/product/productservice/product/';
    return this.http.put( url, baseDet)
    .map((res: Response ) => {
                  if (res) {
                    if (res.status === 201) {
                        return [{ status: res.status, json: res }]
                    } else if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
              }
            return res.json();
      }).catch((error: any) => {
                        return this.errorHandling(error);
                  });
   }
   saveContact(baseDet: PartyMasterInterface[]){
    const url = '/product/productservice/product/';
    return this.http.post( url, baseDet)
    .map((res: Response ) => {
                  if (res) {
                    if (res.status === 201) {
                        return [{ status: res.status, json: res }]
                    } else if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
              }
            return res.json();
      }).catch((error: any) => {
                        return this.errorHandling(error);
                  });
   }
  saveBank(baseDet: PartyMasterInterface[]){
    const url = '/product/productservice/product/';
    return this.http.post( url, baseDet)
    .map((res: Response ) => {
                  if (res) {
                    if (res.status === 201) {
                        return [{ status: res.status, json: res }]
                    } else if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
              }
            return res.json();
      }).catch((error: any) => {
                        return this.errorHandling(error);
                  });
   }
savePartyDetails(baseDet: PartyMasterInterface) {
  this.partyName = baseDet.partyBasicDetails.partyName;
  console.log('service' + baseDet.partyBasicDetails.partyName);
  this.statusName =  baseDet.partyBasicDetails.statusName;
    const headers = new Headers({ 'Content-Type': 'application/json' });
    //    const url = '/uomservice/uom';
    const url = '/party/partymasterservice/party';
    return this.http.post( url, baseDet) 
    .map((res: Response ) => {
                  if (res) {
                    if (res.status === 201) {
                        return [{ status: res.status, json: res }]
                    } else if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
              }
            return res.json();
      }).catch((error: any) => {
                        return this.errorHandling(error);
     });
   }
   updtaePartyDetails(baseDet: PartyMasterInterface) {
  this.partyName = baseDet.partyBasicDetails.partyName;
  console.log('service' + baseDet.partyBasicDetails.partyName);
  this.statusName =  baseDet.partyBasicDetails.statusName;
    const headers = new Headers({ 'Content-Type': 'application/json' });
    //    const url = '/uomservice/uom';
    const url = '/party/partymasterservice/party';
    return this.http.put( url, baseDet) 
    .map((res: Response ) => {
                  if (res) {
                    if (res.status === 201) {
                        return [{ status: res.status, json: res }]
                    } else if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
              }
            return res.json();
      }).catch((error: any) => {
                        return this.errorHandling(error);
                  });
   }
    getselectedparty(partyId){
      //getselectedparty(){
  //return this.http.get('/bins/10ogup').map((response: Response) =>response.json());//.map((response: Response) => <ResponseData>response.json());
   const url: string = '/party/partymasterservice/party/' + partyId;
         //const url: string = '/bins/wgp1h';
         return this.http.get(url).map((response: Response) => response.json());
}
changestatus(id){
    const url = '/reference/uomservice/uom';
    this.arrStringJSON = ' [ ' + JSON.stringify(id) + ' ] ';
    return this.http.delete(url , new RequestOptions({
                      body:  this.arrStringJSON
              })) .map((res: Response ) => {
             if (res) {
                    if (res.status === 201) {
                        return [{ status: res.status, json: res }]
                    } else if (res.status === 200) {
                         return [{ status: res.status, json: res }]
                    }
              }
        return res.json();
      });
}


partyBasicDetails(partybasic : any) {
          this.editpartybasic = partybasic;
        }
partyAddress(partyaddress: any){
      this.editpartyaddress = partyaddress;
}
partyBankAccountDetails(partyBankAccountDetails: any){
      this.editpartyBankAccountDetails = partyBankAccountDetails;
}
partyContacts(partyContacts:any){
      this.editpartyContacts=partyContacts;
}
partyGradeDefinition(partyGradeDefinition:any){
      this.editpartyGradeDefinition=partyGradeDefinition;
}
internalGradeMapping(internalGradeMapping:any){
      this.editinternalGradeMapping=internalGradeMapping;
}
paymentTermDetails(paymentTermDetails:any){
      this.editpaymentTermDetails=paymentTermDetails;
}
plantDetails(plantDetails:any){
      this.editplantDetails=plantDetails;
}
externalSystemMapping(externalSystemMapping:any){
      this.editexternalSystemMapping=externalSystemMapping;
}
creditLimit(creditLimit:any){
      this.editcreditLimit=creditLimit;
}
partyLimit(partyLimit:any){
      this.editpartyLimit=partyLimit;
}
partyTranslations(partyTranslations:any){
      this.editpartyTranslations=partyTranslations;
}
translatedAddress(translatedAddress:any){
      this.edittranslatedAddress=translatedAddress;
}
warehouseLocation(warehouseLocation:any){
      this.editwarehouseLocation=warehouseLocation;
}
exchangeDetails(exchangeDetails:any){
      this.editexchangeDetails=exchangeDetails;
}
brokerDetails(brokerDetails:any){
      this.editbrokerDetails=brokerDetails;
}
partyDocuments(partyDocuments:any){
      this.editpartyDocuments=partyDocuments;
}
selectedPartyBody(fetchedBody:PartyMasterInterface){
      this.editParty=fetchedBody;
}
}

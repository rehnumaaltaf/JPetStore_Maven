import { Injectable } from '@angular/core';
import { TaxRateInterface } from '../tax-rate-interface';
import { Http, Response, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../../shared/interface/responseData';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { AbstractLinkService } from '../../../../shared/service/abstract-link.service';
import { MessageModel } from '../../../../shared/message/message.model';
@Injectable()
export class TaxRateService extends AbstractLinkService{
    taxCodeForPayment:string;
    errorStatusCode: string;
    errorMessage: string;
    showUnique:boolean;
    originCodeParam: string;
    taxRateCodeForPayment:string;
    editFeatures:TaxRateInterface;
    taxRatePaymentdetails:TaxRateInterface[];
    selectTaxRateEdit:TaxRateInterface[];
    taxRatedetails: TaxRateInterface[];
	taxCode: string;
    paramPassingId: string;
    editTaxRateinAdd: TaxRateInterface = new TaxRateInterface();
    viewtaxRatedetails: TaxRateInterface = new TaxRateInterface();
    viewbyIdParam: any;
    editPayment: TaxRateInterface;
    editFeaturesMultiple: TaxRateInterface;
    public messages: MessageModel;
  constructor(private http: Http) {
      super();
   }

    getListingPageFromJson(): Observable<ResponseData> {
	 return this.http.get('/finance/taxrate/').map((response: Response) =><ResponseData>response.json());
   }
      loactionTaxCountryname(): Observable<ResponseData> {
       return this.http.get('/location/geoservice/geo/').map((response: Response) =><ResponseData>response.json());
	  // return this.http.get('/location12/geoservice12/geo123/').map((response: Response) =><ResponseData>response.json());
      }

   getSelectedTaxRateByID():  Observable<ResponseData> {
            return this.http.get('/finance/taxrate/' + this.paramPassingId)
            .map((response:  Response) => <ResponseData>response.json())
            }

      deletetaxRateById(param){			 
					const url = '/finance/taxrate/'+param;  
						return this.http.delete(url).map((res: Response) => {
							if (res) {
								if (res.status === 200) {
									return [{ status: res.status, json: res }]
								}
							}
							return res.json();
						}).catch((error: any) => {
							this.errorStatusCode = error.status;
							if (error.status === 422) {
								return Observable.throw(new Error(this.errorMessage = 'Cannot Delete..Tax Code is+'
									+ ' already associated as a parent to some other Tax Code'));
							} else if (error.status === 400) {
								return Observable.throw(new Error(error.status));
							} else if (error.status === 409 || error.status === 404) {

								return Observable.throw(new Error(this.errorMessage = 'TaxCode Already exists.Please Choose any Other..'));
							} else if (error.status === 406) {
								return Observable.throw(new Error(error.status));
							}
						});
				
			}
                  
      updateTaxRates(param): Observable<ResponseData> {
				const url = '/finance/taxrate/'+param;
				 const params = { 'taxCodeUID': param };
				   const arrStringJSON = '[' + JSON.stringify(params) + ']';
				return this.http.put(url, arrStringJSON)
					.map((res: Response) => {
						if (res) {
							if (res.status === 200) {
								return [{ status: res.status, json: res }]
							}
						}
						return res.json();
					}).catch((error: any) => {
						this.errorMessage = error.json().message;
						if (error.status === 409) {
							return Observable.throw(new Error(error.status));
						}
					});
			}

       // for save ast
 saveTaxRateDetails(taxRateDet: TaxRateInterface) : Observable<ResponseData> {
   const url = '/finance/taxrate/';
    return this.http.post(url, taxRateDet)   
          .map((res: Response ) => {
                  if (res) {
                    if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
              }
            return res.json();
      }).catch((error: any ) => {
            this.errorStatusCode = error.status;
             if (error.status === 500) {
                    // TO DO: It should not be 500
                   // this needs to be removed once backend is handled for viewbyId response code for Duplicate UOM code
                   return Observable.throw( this.errorMessage) ;
              } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
              } else if (error.status === 409) {
                   return Observable.throw(this.errorMessage = taxRateDet.taxCode+" exists. Please reenter valid unique data");
              }
            else if (error.status === 412) {
                   return Observable.throw(this.errorMessage = taxRateDet.taxName+" exists. Please reenter valid unique data");
              }
                  else if (error.status === 416) {
                   return Observable.throw(this.errorMessage = "Expiration Date should be greater than or equal to Effective Date");
              }
                  else if (error.status === 417) {
                   return Observable.throw(this.errorMessage = "Validity period of the Tax Rate overlaps with an existing Tax Rate");
              }
              else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
              }
                  
      });
}



      selectedFeatureToedit(taxRatePercentageEditDetails: any) {
            this. selectTaxRateEdit = taxRatePercentageEditDetails;
      }
      editUserRoleFeatures(userRoleData2:TaxRateInterface) {
            this.editFeatures = userRoleData2;
      }
      

// for edit all ast
updateTaxRateDetails(updateTaxRateDetails: TaxRateInterface){ 
    this.taxCodeForPayment = updateTaxRateDetails.taxCode;
    const url = '/finance/taxrate/';

    return this.http.put(url, updateTaxRateDetails)  
          .map((res: Response ) => {
                  if (res) {
                    if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
              }
            return res.json();
      }).catch((error: any ) => {
            this.errorStatusCode = error.status;
             if (error.status === 500) {
                    // TO DO: It should not be 500
                   // this needs to be removed once backend is handled for viewbyId response code for Duplicate UOM code
                   return Observable.throw( this.errorMessage) ;
              } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
              } else if (error.status === 409) {
                   return Observable.throw(this.errorMessage = updateTaxRateDetails.taxCode+" exists. Please reenter valid unique data");
              }
            else if (error.status === 412) {
                   return Observable.throw(this.errorMessage = updateTaxRateDetails.taxName+" exists. Please reenter valid unique data");
              }
                  else if (error.status === 416) {
                   return Observable.throw(this.errorMessage = "Expiration Date should be greater than or equal to Effective Date");
              }
                  else if (error.status === 417) {
                   return Observable.throw(this.errorMessage = "Validity period of the Tax Rate overlaps with an existing Tax Rate");
              }
              else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
              }
                  
      });
}
}



import { Injectable } from '@angular/core';
import { CalendarSetupInterface } from '../calendar-setup-interface';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseData } from '../../../../shared/interface/responseData';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class CalendarSetupService {
  calendarCodeForPayment:string;
  errorStatusCode: any;
  errorMessage: string;
  originCodeParam: any;
  CalendarSetupdetails: CalendarSetupInterface[];
  calendarCode: string;
  paramPassingId:any;
  viewbyIdParam: any;
  arrStringJSON: any;
  constructor(private http: Http) { }
  
  //shivas code
//  getCalendarList()
//   {
//   return this.http.get('/marketdata/calendarsetup/').map((response: Response) =>response.json());
//   }

// getcalendartypeJson(){
//     return this.http.get('/marketdata/calendarsetupcalendartype/').map((response: Response) =>response.json());
// }

getSelectedcalendarSetupByID():  Observable<ResponseData> {
	       // console.log(this.paramPassingId);
            return this.http.get('/reference/calendarservice/'+ this.paramPassingId)
            .map((response:  Response) => <ResponseData>response.json())
      //  http://10.66.194.48:8090/reference/calendarservice/2 
}

updateCalendarSetupDetails(updateCalendarSetupDetails: CalendarSetupInterface): Observable<ResponseData>{
    //this.taxCodeForPayment = updateCalendarSetupDetails.taxCode; 
   // const headers = new Headers({ 'Content-Type': 'application/json' });
  //  http://10.66.196.50:8090/reference/calendarservice
    const url = '/reference/calendarservice/';
    return this.http.put(url, updateCalendarSetupDetails)  
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
                   return Observable.throw(this.errorMessage = updateCalendarSetupDetails.calendarCode+" exists. Please reenter valid unique data");
              }
                 else if (error.status === 412) {                  
                   return Observable.throw(this.errorMessage = updateCalendarSetupDetails.calendarName+" exists. Please reenter valid unique data");
              }
              else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
              }                 
      });
      }

  //mahi's code
getListingPageFromJson() {
      return this.http.get('/reference/calendarservice/').map((response: Response) =>response.json());

	 //return this.http.get('/masterdata/calendarSetup').map((response: Response) =>response.json());
}
   
deletecalendarSetupById(param){			 
					const url = '/reference/calendarservice/'+param;  
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
								// return Observable.throw(new Error(error.status));
								return Observable.throw(new Error(this.errorMessage = 'Cannot Delete..Weight Terms is+'
									+ ' already associated as a parent to some other Weight Terms'));
								// this.errorMessage="UOM Code Already exists.Please Choose any Other..")
							} else if (error.status === 400) {
								return Observable.throw(new Error(error.status));
							} else if (error.status === 409 || error.status === 404) {
								return Observable.throw(new Error(this.errorMessage = 'weight Terms Code Already exists.Please Choose any Other..'));
							} else if (error.status === 406) {
								return Observable.throw(new Error(error.status));
							}
						});
				
			}
			
			
			updateCalendarSetup(param): Observable<ResponseData> {	
					//alert(param);
				const url = '/reference/calendarservice/'+param;
				 const params = { 'calendarSetupId': param };
				   this.arrStringJSON = '[' + JSON.stringify(params) + ']';
				   //alert(this.arrStringJSON);
				//console.log('passing JSON in db' + this.arrStringJSON);
				return this.http.put(url,this.arrStringJSON)
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

			//add service

			  addCalendarSetupDetails(calendarSetupDet: CalendarSetupInterface) 
 {
   // const headers = new Headers({ 'Content-Type': 'application/json' });
   const url = '/reference/calendarservice/';
    return this.http.post(url, calendarSetupDet)
          .map((res: Response ) => {
                // debugger;
                  if (res) {
                   if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
              }
            return res.json();
      }).catch((error: any ) => {
            // debugger;
            this.errorStatusCode = error.status;
             if (error.status === 500) {
                    // TO DO: It should not be 500
                   // this needs to be removed once backend is handled for viewbyId response code for Duplicate UOM code
                   return Observable.throw( this.errorMessage) ;
              } else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
              } else if (error.status === 409) {
                   // console.log("Error message in service"+""+this.errorMessage)tax code
                   return Observable.throw(this.errorMessage = calendarSetupDet.calendarCode+" exists. Please reenter valid unique data");
              }
            else if (error.status === 412) {
                   // console.log("Error message in service"+""+this.errorMessage)tax name
                   return Observable.throw(this.errorMessage = calendarSetupDet.calendarName+" exists. Please reenter valid unique data");
              }
                  else if (error.status === 416) {
                   // console.log("Error message in service"+""+this.errorMessage)start date and end date
                   return Observable.throw(this.errorMessage = "Expiration Date should be greater than or equal to Effective Date");
              }
                  else if (error.status === 417) {
                   // console.log("Error message in service"+""+this.errorMessage)date overlap
                   return Observable.throw(this.errorMessage = "Validity period of the Tax Rate overlaps with an existing Tax Rate");
              }
              else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
              }
      });


}
}

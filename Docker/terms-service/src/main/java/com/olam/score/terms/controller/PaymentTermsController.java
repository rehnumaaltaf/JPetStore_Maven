package com.olam.score.terms.controller;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olam.score.common.domain.UserBean;
import com.olam.score.common.dto.Link;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.util.CommonUtil;
import com.olam.score.common.util.Mapping;
import com.olam.score.terms.dto.PaymentTermsDto;
import com.olam.score.terms.service.PaymentTermsService;

import io.swagger.annotations.Api;

@CrossOrigin // TODO: need to remove cross origin in future
@RestController
@Mapping(featureName = "PaymentTerm", featureId = "24")
@RequestMapping("/terms/paymenttermservice/paymentterm")
@Api(value = "PaymentTerms", description = "Operations pertaining to Payment Terms Master in OLAM CTRM")
public class PaymentTermsController {
	
	@Autowired
	PaymentTermsService service;

	@RequestMapping(value= "/{paymentTermId}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<PaymentTermsDto>> getPaymentTerms
						(@PathVariable("paymentTermId") Integer paymentTermId) {
		ResponseData<PaymentTermsDto> response = new ResponseData<>();
		PaymentTermsDto paymentTermsList = service.readById(paymentTermId);
		response.setBody(paymentTermsList);
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@RequestMapping(value= "", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<List<PaymentTermsDto>>> getPaymentTerms(
			@RequestParam(value = "termCode", required=false) String termCode,
			@RequestParam(value = "termName", required=false) String termName,
			@RequestParam(value = "status", required=false) String status,Model model) {
		if(termCode!=null && termCode.length()>0){
			model.addAttribute("payTermCode", termCode);
		}
		if(termName!=null && termName.length()>0){
			model.addAttribute("payTermName", termName);
		}
		if(status!=null && status.length()>0){
			model.addAttribute("status", status);
		}
		ResponseData<List<PaymentTermsDto>> response = new ResponseData<>();
		List<PaymentTermsDto> paymentTermsList = service.readAll(model);
		response.setBody(paymentTermsList);
		response.setLinks(null);
		response.setView(service.getViewDetails(this.getClass()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
    public ResponseEntity<ResponseData<PaymentTermsDto>> addPaymentTerm(@QueryParam("action") String action,@Valid @RequestBody PaymentTermsDto body){
    	ResponseData<PaymentTermsDto> response = new ResponseData<>();
    	if (action.equalsIgnoreCase("Draft")) {
			body.setStatusName("Draft");
		} else if (action.equalsIgnoreCase("Save")) {
			body.setStatusName("Active");
		}
    		response.setBody(service.create(body));
    		response.setLinks(null);
    	return new ResponseEntity<>(response, HttpStatus.OK);
    } 
	
	@RequestMapping(value = "/getDropBox", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<PaymentTermsDto>> getDropBoxForCreate(){
		
		ResponseData<PaymentTermsDto> response = new ResponseData<>();
		try {
    		UserBean userBean = new UserBean();
    		List<Link<?>> links = CommonUtil.setLinks(this.getClass(), "VIEW" , userBean); 
    		response.setBody(service.getDataForCreate());
    		response.setLinks(links);//TOOD: Need to get links for the entity
    	} catch (Exception e) {
    		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/{paymentTermId}", method = RequestMethod.DELETE, produces = { "application/json" })
    public ResponseEntity<ResponseData<PaymentTermsDto>> deletePaymentTerm(@QueryParam("action") String action,@PathVariable("paymentTermId") Integer paymentTermId) {

		ResponseData<PaymentTermsDto> response = new ResponseData<>();
		try {
			UserBean userBean = new UserBean();
			List<Link<?>> links = CommonUtil.setLinks(this.getClass(), "VIEW", userBean);
			response.setBody(service.delete(paymentTermId,action));
			response.setLinks(links);// TOOD: Need to get links for the entity
		} catch (Exception e) {
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

    } 
	
	@RequestMapping(value = "", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.PUT)
    public ResponseEntity<ResponseData<PaymentTermsDto>> updateRole(@QueryParam("action") String action,@Valid @RequestBody PaymentTermsDto body){
    	ResponseData<PaymentTermsDto> response = new ResponseData<>();
    		response.setBody(service.update(body));
    		response.setLinks(null);//TOOD: Need to get links for the entity
      	return new ResponseEntity<>(response, HttpStatus.OK);
    } 	
	

}

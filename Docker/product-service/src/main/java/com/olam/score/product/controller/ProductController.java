package com.olam.score.product.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.olam.score.product.dto.ProductDto;
import com.olam.score.product.service.ProductService;

/**
 * @author Mohit_Agrawal08
 *
 */
@CrossOrigin // TODO: need to remove cross origin in future
@RestController
@Mapping(featureName = "PRODUCT", featureId = "14")
@RequestMapping("/product/productservice/product")
public class ProductController {

	@Autowired
	ProductService service;

	@RequestMapping(value = "/{productId}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<ProductDto>> getProducts(@PathVariable("productId") Integer productId) {

		ResponseData<ProductDto> response = new ResponseData<>();
		// TODO: User Authorization needs to happen
		response.setBody(service.readById(productId));
		response.setLinks(null);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<List<ProductDto>>> getProducts() {
		ResponseData<List<ProductDto>> response = new ResponseData<>();
		// TODO: User Authorization needs to happen
		response.setBody(service.readAll());
		response.setLinks(null);// TOOD: Need to get links for the entity
		response.setView(service.getViewDetails(this.getClass()));
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseData<ProductDto>> addPaymentTerm(@Valid @RequestBody ProductDto body) {
		ResponseData<ProductDto> response = new ResponseData<>();
		response.setBody(service.create(body));
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/getDropBox", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<ProductDto>> getDropBoxForCreate() {

		ResponseData<ProductDto> response = new ResponseData<>();
		UserBean userBean = new UserBean();
		List<Link<?>> links = CommonUtil.setLinks(this.getClass(), "VIEW", userBean);
		response.setBody(service.getDataForCreate());
		response.setLinks(links);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/treeview", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<List<ProductDto>>> getProductDropDown() {

		ResponseData<List<ProductDto>> response = new ResponseData<>();
		response.setBody(service.readProductForDropDown());
		response.setLinks(null);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE, produces = { "application/json" }, consumes = {
			"application/json" })
	public HttpStatus deleteProduct(@RequestBody List<Map<String, Integer>> body) {

		service.deleteProduct(body);
		return HttpStatus.OK;

	}

	@RequestMapping(value = "", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<ProductDto>> updateProduct(@Valid @RequestBody ProductDto body) {
		ResponseData<ProductDto> response = new ResponseData<>();
		response.setBody(service.update(body));
		response.setLinks(null);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/basicdetails", produces = { "application/json" })
	public ResponseEntity<ResponseData<List<ProductDto>>> getProductsBasicDetails(
			@RequestParam(value = "prodCode", required = false) String prodCode,
			@RequestParam(value = "prodName", required = false) String prodName,
			@RequestParam(value = "status", required = false) String status, Model model) {
		if (prodCode != null && prodCode.length() > 0) {
			model.addAttribute("prodCode", prodCode);
		}
		if (prodName != null && prodName.length() > 0) {
			model.addAttribute("prodName", prodName);
		}
		if (status != null && status.length() > 0) {
			model.addAttribute("status", status);
		}
		ResponseData<List<ProductDto>> response = new ResponseData<>();
		// TODO: User Authorization needs to happen
		response.setBody(service.readBasicDetails(model));
		response.setLinks(null);// TOOD: Need to get links for the entity
		response.setView(service.getViewDetails(this.getClass()));
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}

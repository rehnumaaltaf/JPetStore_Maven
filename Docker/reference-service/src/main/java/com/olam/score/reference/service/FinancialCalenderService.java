package com.olam.score.reference.service;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.olam.score.common.constants.Constants;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.dto.StatusDto;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.reference.domain.MasterFinancialCalender;
import com.olam.score.reference.domain.MasterFinancialCalenderMapping;
import com.olam.score.reference.domain.MasterFincalProduct;
import com.olam.score.reference.dto.FinancialCalenderDTO;
import com.olam.score.reference.dto.MasterProductDto;
import com.olam.score.reference.dto.ProductDto;
import com.olam.score.reference.dto.TenorFinancialArray;
import com.olam.score.reference.repository.FinancialCalenderMappingRepository;
import com.olam.score.reference.repository.FinancialCalenderRepository;
import com.olam.score.reference.repository.MasterFincalProductRepository;


/**
 *
 * This service class having the methods to connect to dataBase using hibernate.
 *
 *
 * @author prabhakar
 * @version 1.00, 10 july 2017
 */

@Service
public class FinancialCalenderService {
	
	@Autowired
	private WebServiceCallUtil webServiceCall;

	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private FinancialCalenderRepository financialCalenderRepository;
	@Autowired
	private FinancialCalenderMappingRepository financialCalenderMappingRepository;
	@Autowired
	private MasterFincalProductRepository masterFincalProductRepository;
	@Value("${reference.name}")
	private String referenceName;
	@Value("${product.name}")
	private String productName;
	@Autowired
	private WebServiceCallUtil serviceCall;
	List<Map<Object, Object>> listProd;
	@Transactional
	public FinancialCalenderDTO createFinCal(FinancialCalenderDTO financialCalenderDTO) throws ParseException {

		MasterFinancialCalender masterFinancialCalender = new MasterFinancialCalender();
		masterFinancialCalender.setFinCalFiscalYear(financialCalenderDTO.getFiscalYear());
		masterFinancialCalender.setFinCalStartYear(financialCalenderDTO.getStartYear());
		masterFinancialCalender.setFinCalStartMonth(financialCalenderDTO.getStartMonth());
		masterFinancialCalender.setFinCalEndYear(financialCalenderDTO.getEndYear());
		masterFinancialCalender.setFinCalEndMonth(financialCalenderDTO.getEndMonth());
		masterFinancialCalender.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		if (financialCalenderDTO.getAction().equalsIgnoreCase(Constants.SAVE)) {
			masterFinancialCalender.setFkStatusId(Constants.SAVE_DRAFT);
		} else {
			masterFinancialCalender.setFkStatusId(Constants.SUBMIT_ACTIVE);
		}

		List<TenorFinancialArray> tenorFinancialArraysList = financialCalenderDTO.getTenorFinancialArray();
		masterFinancialCalender = financialCalenderRepository.save(masterFinancialCalender);
		if (tenorFinancialArraysList == null || tenorFinancialArraysList.isEmpty()) {
			// responce = "list is empty";
		} else {
			for (TenorFinancialArray tenorFinancial : tenorFinancialArraysList) {
				MasterFinancialCalenderMapping masterFinancialCalenderMapping = new MasterFinancialCalenderMapping();
				if (tenorFinancial.getMonthShortCode() != null) {
					masterFinancialCalenderMapping.setFinCalMappingMonthName(tenorFinancial.getMonthShortCode());
				}

				masterFinancialCalenderMapping.setFkFinCalId(masterFinancialCalender.getPkFinCalId());
				SimpleDateFormat ft = new SimpleDateFormat(Constants.DATE_FORMAT);
				masterFinancialCalenderMapping.setFinCalMappingStartDate(tenorFinancial.getStartDate());
				masterFinancialCalenderMapping.setFinCalMappingEndDate(tenorFinancial.getEndDate());

				if (Constants.STATUS_OPEN.equalsIgnoreCase(tenorFinancial.getCtrmStatus())) {
					masterFinancialCalenderMapping.setFkStatusId(Constants.OPEN);

				} else {
					masterFinancialCalenderMapping.setFkStatusId(Constants.CLOSED);
				}

				if (Constants.STATUS_OPEN.equalsIgnoreCase(tenorFinancial.getErpStatus())) {
					masterFinancialCalenderMapping.setFkErpStatusId(Constants.ERP_OPEN);

				} else if (Constants.STATUS_CLOSED.equalsIgnoreCase(tenorFinancial.getErpStatus())) {
					masterFinancialCalenderMapping.setFkErpStatusId(Constants.ERP_CLOSED);
				}
				financialCalenderMappingRepository.save(masterFinancialCalenderMapping);
				
				if(!tenorFinancial.getProductId().isEmpty()) {
					for(MasterProductDto masterProductDto: tenorFinancial.getProductId()) {
						MasterFincalProduct fincalProduct = new MasterFincalProduct();
						fincalProduct.setFkProdId(masterProductDto.getFkProdId());
						fincalProduct.setFkFinCalMappingtId(masterFinancialCalenderMapping);
						masterFincalProductRepository.save(fincalProduct);
					}
				}
			}
			
		}
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		financialCalenderDTO = masterFinancialCalender.convertToFinancialCalendarDto(statusMap);
		return financialCalenderDTO;

	}

	@Transactional
	public List<FinancialCalenderDTO> readFinCal() {

		List<MasterFinancialCalender> masterFinancialCalenderList = financialCalenderRepository.getAllFinCal();

		List<FinancialCalenderDTO> financialCalenderList = new ArrayList<>();
		for (MasterFinancialCalender masterfc : masterFinancialCalenderList) {
			FinancialCalenderDTO financialCalenderDTO = new FinancialCalenderDTO();
			financialCalenderDTO.setPkFinCalId(String.valueOf(masterfc.getPkFinCalId()));
			financialCalenderDTO.setFiscalYear(masterfc.getFinCalFiscalYear());
			financialCalenderDTO.setStartYear(masterfc.getFinCalStartYear());
			financialCalenderDTO.setStartMonth(masterfc.getFinCalStartMonth());
			financialCalenderDTO.setEndYear(masterfc.getFinCalEndYear());
			financialCalenderDTO.setEndMonth(masterfc.getFinCalEndMonth());
			financialCalenderDTO.setStatusName(returnStatusNameById(masterfc.getFkStatusId()));
			financialCalenderList.add(financialCalenderDTO);

		}

		return financialCalenderList;

	}

	
	@Transactional
	public String updateFinCal(FinancialCalenderDTO financialCalenderDTO) throws ParseException {

		String responce = Constants.UPDATE_SUCCESS;
		int pkFinCalId=Integer.parseInt(financialCalenderDTO.getPkFinCalId());
		MasterFinancialCalender masterFinancialCalender =financialCalenderRepository.findOne(pkFinCalId);
		
		masterFinancialCalender.setFinCalFiscalYear(financialCalenderDTO.getFiscalYear());
		masterFinancialCalender.setFinCalStartYear(financialCalenderDTO.getStartYear());
		masterFinancialCalender.setFinCalStartMonth(financialCalenderDTO.getStartMonth());
		masterFinancialCalender.setFinCalEndYear(financialCalenderDTO.getEndYear());
		masterFinancialCalender.setFinCalEndMonth(financialCalenderDTO.getEndMonth());
		masterFinancialCalender.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		if (financialCalenderDTO.getAction().equalsIgnoreCase(Constants.SAVE)) {
			masterFinancialCalender.setFkStatusId(Constants.SAVE_DRAFT);
			responce = Constants.SAVE_FINCAL_SUCCESS_DRAFT;

		} else if (financialCalenderDTO.getAction().equalsIgnoreCase(Constants.SUBMIT)){
			masterFinancialCalender.setFkStatusId(Constants.SUBMIT_ACTIVE);
			responce = Constants.SAVE_FINCAL_SUCCESS_ACTIVE;
		}
		else if (financialCalenderDTO.getAction().equalsIgnoreCase(Constants.ACTIVE)){
			masterFinancialCalender.setFkStatusId(Constants.ACIVATEID);
			responce = Constants.SAVE_FINCAL_SUCCESS_ACTIVE;
		}
		else if (financialCalenderDTO.getAction().equalsIgnoreCase(Constants.INACTIVE)){
			masterFinancialCalender.setFkStatusId(Constants.INACIVATEID);
			responce = Constants.SAVE_FINCAL_SUCCESS_INACTIVE;
		}
		else if(financialCalenderDTO.getAction().equalsIgnoreCase(Constants.UPDATE))
		{
			
			responce = Constants.SAVE_FINCAL_SUCCESS_ACTIVE;
		}

		financialCalenderRepository.save(masterFinancialCalender);
		deleteFinCalMapping(masterFinancialCalender.getPkFinCalId());
		for (TenorFinancialArray tenorFinancial : financialCalenderDTO.getTenorFinancialArray()) {
			MasterFinancialCalenderMapping masterFinancialCalenderMapping = new MasterFinancialCalenderMapping();
			if (tenorFinancial.getMonthShortCode() != null) {
				masterFinancialCalenderMapping.setFinCalMappingMonthName(tenorFinancial.getMonthShortCode());
			}

			masterFinancialCalenderMapping.setFkFinCalId(masterFinancialCalender.getPkFinCalId());
			masterFinancialCalenderMapping.setFinCalMappingStartDate(tenorFinancial.getStartDate());
			masterFinancialCalenderMapping.setFinCalMappingEndDate(tenorFinancial.getEndDate());

			if (Constants.STATUS_OPEN.equalsIgnoreCase(tenorFinancial.getCtrmStatus())) {
				masterFinancialCalenderMapping.setFkStatusId(Constants.OPEN);

			} else {
				masterFinancialCalenderMapping.setFkStatusId(Constants.CLOSED);
			}

			if (Constants.STATUS_OPEN.equalsIgnoreCase(tenorFinancial.getErpStatus())) {
				masterFinancialCalenderMapping.setFkErpStatusId(Constants.ERP_OPEN);

			} else if (Constants.STATUS_CLOSED.equalsIgnoreCase(tenorFinancial.getErpStatus())) {
				masterFinancialCalenderMapping.setFkErpStatusId(Constants.ERP_CLOSED);
			}
			financialCalenderMappingRepository.save(masterFinancialCalenderMapping);
			
			if(!tenorFinancial.getProductId().isEmpty()) {
				for(MasterProductDto masterProductDto: tenorFinancial.getProductId()) {
					MasterFincalProduct fincalProduct = new MasterFincalProduct();
					fincalProduct.setFkProdId(masterProductDto.getFkProdId());
					fincalProduct.setFkFinCalMappingtId(masterFinancialCalenderMapping);
					masterFincalProductRepository.save(fincalProduct);
				}
			}
		}
		return responce;

	}

	public String deleteFinCal(Integer finCalId) {

		String responce;
		String status = null;
		MasterFinancialCalender masterFinancialCalender = financialCalenderRepository.getValuesById(finCalId);
		
		status = returnStatusNameById(masterFinancialCalender.getFkStatusId());

		if ("Draft".equalsIgnoreCase(status)) {
			responce = Constants.DELETE_SUCCESS;

			List<MasterFinancialCalenderMapping> masterFinancialCalenderMappingList = financialCalenderMappingRepository
					.getFisCalFkId(finCalId);

			for (MasterFinancialCalenderMapping masterFinancialCalenderMappingvar : masterFinancialCalenderMappingList) {
				financialCalenderMappingRepository.delete(masterFinancialCalenderMappingvar.getPkFinCalMappingId());
			}
			financialCalenderRepository.delete(finCalId);
		} else if ("Active".equalsIgnoreCase(status)) {
			masterFinancialCalender.setFkStatusId(Constants.INACIVATEID);

			financialCalenderRepository.save(masterFinancialCalender);
			responce = Constants.UPDATE_SUCCESS;

		} else if ("InActive".equalsIgnoreCase(status)) {
			masterFinancialCalender.setFkStatusId(Constants.ACIVATEID);

			financialCalenderRepository.save(masterFinancialCalender);
			responce = Constants.UPDATE_SUCCESS;
		} else {
			responce = Constants.NODATA_AVILABLE;
		}
		return responce;

	}

	@Transactional
	public String validate(String fiscalYear) {
		String response;
		MasterFinancialCalender masterFinancialCalender;

		masterFinancialCalender = financialCalenderRepository.getFisCalYear(fiscalYear);
		if (masterFinancialCalender != null) {
			response = Constants.DUPLICATE_FINCAL;
		} else {
			response = Constants.FINCAL;
		}
		return response;
	}

	public List<String> getFisicalYear(String fiscalYear) {

		return financialCalenderRepository.getFiscalYearSuggitions(fiscalYear);

	}

	public String validateMonth(String fiscalYear, String month) {

		String response;
		String monthName = financialCalenderMappingRepository
				.getFisCalMonth(financialCalenderRepository.getPkFinCalId(fiscalYear));

		if (monthName.equalsIgnoreCase(month)) {
			response = Constants.DUPLICATE_FINCAL;
		} else {
			response = Constants.FINCAL;
		}
		return response;

	}

	public List<FinancialCalenderDTO> readFinCalById(Integer finCalId) {
		loadProductList();
		List<MasterFinancialCalender> masterFinancialCalenderList = financialCalenderRepository.getValuesByIdList(finCalId);
		List<MasterFinancialCalenderMapping> masterFinancialCalenderMappingList=financialCalenderMappingRepository.getFisCalFkId(finCalId);
		
		
		List<FinancialCalenderDTO> financialCalenderList = new ArrayList<>();
		List<TenorFinancialArray> tenorFinancialArraysList=new ArrayList<>();
		for (MasterFinancialCalender masterfc : masterFinancialCalenderList) {
			FinancialCalenderDTO financialCalenderDTO = new FinancialCalenderDTO();
			financialCalenderDTO.setPkFinCalId(String.valueOf(masterfc.getPkFinCalId()));
			financialCalenderDTO.setFiscalYear(masterfc.getFinCalFiscalYear());
			financialCalenderDTO.setStartYear(masterfc.getFinCalStartYear());
			financialCalenderDTO.setStartMonth(masterfc.getFinCalStartMonth());
			financialCalenderDTO.setEndYear(masterfc.getFinCalEndYear());
			financialCalenderDTO.setEndMonth(masterfc.getFinCalEndMonth());
			financialCalenderDTO.setStatusName(returnStatusNameById(masterfc.getFkStatusId()));
			for (MasterFinancialCalenderMapping masterfincal : masterFinancialCalenderMappingList) {
				TenorFinancialArray tenorFinancialArray=new TenorFinancialArray();
				
				tenorFinancialArray.setMonthShortCode(masterfincal.getFinCalMappingMonthName());
				SimpleDateFormat ft = new SimpleDateFormat(Constants.DATE_FORMAT);
				tenorFinancialArray.setStartDateStr(ft.format(masterfincal.getFinCalMappingStartDate()));
				tenorFinancialArray.setEndDateStr(ft.format(masterfincal.getFinCalMappingEndDate()));
				tenorFinancialArray.setStartDate(masterfincal.getFinCalMappingStartDate());
				tenorFinancialArray.setEndDate(masterfincal.getFinCalMappingEndDate());
				tenorFinancialArray.setPkFinCalMappindId(String.valueOf(masterfincal.getPkFinCalMappingId()));
				tenorFinancialArray.setCtrmStatus(returnStatusNameById(masterfincal.getFkStatusId()));
				tenorFinancialArray.setErpStatus(returnStatusNameById(masterfincal.getFkErpStatusId()));
				List<MasterFincalProduct> masterFincalProductList = masterFincalProductRepository.getFinCalProdList(masterfincal.getPkFinCalMappingId());
				
				List<MasterProductDto> masterProductDtoList = new ArrayList<>();
				for(MasterFincalProduct masterFincalProduct: masterFincalProductList) {
					MasterProductDto masterProductDto = new MasterProductDto();
					masterProductDto.setFkProdId(masterFincalProduct.getFkProdId());
					masterProductDto.setFkProdName(getProductNameById(masterFincalProduct.getFkProdId()));
					if(masterFincalProduct.getFkStatusId() != null) {
						masterProductDto.setFkStatusId(masterFincalProduct.getFkStatusId());
						masterProductDto.setFkStatusName(returnStatusNameById(masterFincalProduct.getFkStatusId()));
					}
					masterProductDtoList.add(masterProductDto);
				}
				tenorFinancialArray.setProductId(masterProductDtoList);
				tenorFinancialArraysList.add(tenorFinancialArray);
				
			}
			financialCalenderDTO.setTenorFinancialArray(tenorFinancialArraysList);
			financialCalenderList.add(financialCalenderDTO);

		}
	
		
	

		return financialCalenderList;
	}

	

	public String chnageStatus(Integer fcId) {
		String responce = null;
		String status = null;
		MasterFinancialCalender masterFinancialCalender = financialCalenderRepository.getValuesById(fcId);
		
		ResponseEntity<ResponseData> responseEntity = webServiceCall.internalServiceCall(referenceName, Constants.FINCAL_STATUS_SERVICE_CALL_URL);
		ResponseData<List<StatusDto>> body = responseEntity.getBody();
		List<Map<Object, Object>> list = (List<Map<Object, Object>>) body.getBody();

		Integer statusId;
		for (int x=0; x<list.size(); x++){
			statusId=(Integer) list.get(x).get(Constants.STATUS_PKID);
			if(statusId!=null && masterFinancialCalender.getFkStatusId()==statusId){
				 status=(String) list.get(x).get(Constants.STATUS_NAME);
				
			}
		}

		if ("Draft".equalsIgnoreCase(status)) {
			
			
			masterFinancialCalender.setFkStatusId((Constants.ACIVATEID));
			financialCalenderRepository.saveAndFlush(masterFinancialCalender);
			responce = Constants.UPDATE_SUCCESS;
		}
		return responce;
	


	}
	
	public String returnStatusNameById(int id)
	{
		Map<Integer, String> statusMap = serviceCall.getAllStatus();
		return statusMap.get(id);
	}
	
	private void deleteMasterFinCalProduct(Integer finCalMapId) {
		masterFincalProductRepository.deleteByFinCalMapId(finCalMapId);
	}
	
	private void deleteFinCalMapping(Integer pkFinCalId) {
		List<MasterFinancialCalenderMapping> financialCalenderMappingList = financialCalenderMappingRepository.getFisCalFkId(pkFinCalId);
		for(MasterFinancialCalenderMapping financialCalenderMapping: financialCalenderMappingList) {
			financialCalenderMappingRepository.delete(financialCalenderMapping.getPkFinCalMappingId());
		}
	}
	
	private String getProductNameById(Integer id) {
		String prodName = null;
		if(!listProd.isEmpty()) {
			for (int x = 0; x < listProd.size(); x++) {
				Integer prodId = (Integer) listProd.get(x).get("prodId");
				if (prodId != null && id != null && id.equals(prodId)) {
					prodName = (String) listProd.get(x).get("prodName");
					break;
				}
			}
		}
		return prodName;
	}
	
	@SuppressWarnings("unchecked")
	private void loadProductList() {
		ResponseEntity<ResponseData> responseEntityProd = webServiceCall.internalServiceCall(productName,"/product/productservice/product/basicdetails");
		listProd = (List<Map<Object, Object>>) responseEntityProd.getBody().getBody();
	}
		
}

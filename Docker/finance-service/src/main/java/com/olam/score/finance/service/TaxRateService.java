package com.olam.score.finance.service;


import java.util.ArrayList;
import java.util.Date;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.olam.score.common.constants.Constants;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.util.ScoreBaseException;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.finance.domain.MasterTaxRate;
import com.olam.score.finance.domain.TaxRateDetails;
import com.olam.score.finance.dto.TaxRateDetailsDto;
import com.olam.score.finance.dto.TaxRateDto;
import com.olam.score.finance.repository.TaxRateDetailsRepository;
import com.olam.score.finance.repository.TaxRateRepository;

import javax.transaction.Transactional;

@Service
public class TaxRateService {//

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	TaxRateRepository taxRateRepository;

	@Autowired
	private WebServiceCallUtil webServiceCall;

	@Autowired
	TaxRateDetailsRepository taxRateDetailsRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Value("${location.name}")
	public String locationName;

	Map<Integer, String> statusMap;
	private int chkValue = 0;

	@Transactional
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<TaxRateDto> getAllTaxRates() {

		List<TaxRateDto> taxRateDtoList = new ArrayList<>();
		List<MasterTaxRate> masterTaxRateList = new ArrayList<>();

		masterTaxRateList = taxRateRepository.findAll();

		for (int i = 0; i < masterTaxRateList.size(); i++) {

			TaxRateDto taxRateDto = new TaxRateDto();
			MasterTaxRate masterTaxRate = new MasterTaxRate();
			masterTaxRate = masterTaxRateList.get(i);
			int taxCodeUID = masterTaxRate.getTaxCodeUID();
			List<TaxRateDetails> taxRateDetails = taxRateDetailsRepository.rerurnAllByTaxRateUID(taxCodeUID);
			taxRateDto = generateTaxRateDto(masterTaxRate, taxRateDetails);
			taxRateDtoList.add(taxRateDto);
		}

		return taxRateDtoList;
	}

	// @SuppressWarnings({"unchecked","rawtypes" })
	private TaxRateDto generateTaxRateDto(MasterTaxRate masterTaxRate, List<TaxRateDetails> taxRateDetailsList) {

		List<Map<Object, Object>> listLctn = getLoactaion();

		TaxRateDto taxRateDto = new TaxRateDto();
		List<TaxRateDetailsDto> taxRateDetailsListDto = new ArrayList<>();
		for (int i = 0; i < taxRateDetailsList.size(); i++) {
			TaxRateDetailsDto dtoData = new TaxRateDetailsDto();
			dtoData.setTaxCodeUID(taxRateDetailsList.get(i).getTaxCodeUID());
			dtoData.setTaxRateDetailId(taxRateDetailsList.get(i).getTaxRateDetailId());
			dtoData.setEffectiveFrom(taxRateDetailsList.get(i).getEffectiveFrom());
			dtoData.setExpirationDate(taxRateDetailsList.get(i).getExpirationDate());
			dtoData.setTaxRatePercentage(taxRateDetailsList.get(i).getTaxRatePercentage());
			taxRateDetailsListDto.add(dtoData);
		}
		taxRateDto.setTaxRateDetails(taxRateDetailsListDto);
		taxRateDto.setTaxCodeUID(masterTaxRate.getTaxCodeUID());
		taxRateDto.setTaxCode(masterTaxRate.getTaxCode());
		taxRateDto.setTaxName(masterTaxRate.getTaxName());
		taxRateDto.setTaxDescription(masterTaxRate.getTaxDescription());
		taxRateDto.setTaxCountryID(masterTaxRate.getTaxCountryID());

		for (int i = 0; i < listLctn.size(); i++) {
			Integer geoLoctnID = (Integer) listLctn.get(i).get("pkGeoId");

			if (geoLoctnID != null && masterTaxRate.getTaxCountryID() == geoLoctnID) {
				String geoName = (String) listLctn.get(i).get("geoName");
				taxRateDto.setTaxCountryName(geoName);
				break;
			}

		}

		taxRateDto.setGovTaxRef(masterTaxRate.getGovTaxRef());
		taxRateDto.setTaxByLine(masterTaxRate.getTaxByLine());
		if (masterTaxRate.getStatus() == 1) {

			taxRateDto.setStatus(Constants.ACTIVE);
		} else if (masterTaxRate.getStatus() == 2) {

			taxRateDto.setStatus(Constants.INACTIVE);
		} else if (masterTaxRate.getStatus() == 3) {

			taxRateDto.setStatus(Constants.DRAFT);
		}
		return taxRateDto;

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map<Object, Object>> getLoactaion() {
		List<Map<Object, Object>> listLctn = null;
		// ResponseEntity<ResponseData> responseEntityLocatn =
		// CommonUtil.getServiceCall(url + "/location/geoservice/geo");
		ResponseEntity<ResponseData> responseEntityLocatn = webServiceCall.internalServiceCall(locationName,
				Constants.LOCATION_URL);
		ResponseData<List<Object>> bodyLctn = responseEntityLocatn.getBody();
		listLctn = (List<Map<Object, Object>>) bodyLctn.getBody();

		return listLctn;
	}

	private MasterTaxRate generateMasterTaxRate(TaxRateDto taxRateDto) {

		MasterTaxRate masterTaxRate = new MasterTaxRate();
		masterTaxRate.setCreatedBy("test");
		masterTaxRate.setCreatedDate(new Date());
		masterTaxRate.setGovTaxRef(taxRateDto.getGovTaxRef().trim());
		masterTaxRate.setModifiedBy("test");
		masterTaxRate.setModifiedDate(new Date());
		if ((Constants.ACTIVE.equalsIgnoreCase(taxRateDto.getStatus()))
				|| (Constants.DRAFT.equalsIgnoreCase(taxRateDto.getStatus()))
				|| (Constants.INACTIVE.equalsIgnoreCase(taxRateDto.getStatus()))) {
			if (Constants.ACTIVE.equalsIgnoreCase(taxRateDto.getStatus().trim())) {

				masterTaxRate.setStatus(1);
			} else if (Constants.DRAFT.equalsIgnoreCase(taxRateDto.getStatus().trim())) {
				masterTaxRate.setStatus(3);
			} else if (Constants.INACTIVE.equalsIgnoreCase(taxRateDto.getStatus().trim())) {
				masterTaxRate.setStatus(2);
			}
		} else {
			throw new ScoreBaseException(0, "status_is_mandatory_Accepted_Values_Active_InActive_Draft",
					HttpStatus.CONFLICT);
		}

		masterTaxRate.setTaxByLine(taxRateDto.getTaxByLine().trim());
		masterTaxRate.setTaxCode(taxRateDto.getTaxCode().trim());
		masterTaxRate.setTaxCodeUID(taxRateDto.getTaxCodeUID());
		masterTaxRate.setTaxCountryID(taxRateDto.getTaxCountryID());
		masterTaxRate.setTaxDescription(taxRateDto.getTaxDescription().trim());
		masterTaxRate.setTaxName(taxRateDto.getTaxName().trim());

		List<TaxRateDetailsDto> taxRateDetailsList = taxRateDto.getTaxRateDetails();
		List<TaxRateDetails> taxRateDetailList = new ArrayList<>();

		for (TaxRateDetailsDto taxDetail : taxRateDetailsList) {
			TaxRateDetails taxRateDetails = new TaxRateDetails();
			// validate
			if (taxDetail.getEffectiveFrom() != null) {
				taxRateDetails.setEffectiveFrom(taxDetail.getEffectiveFrom());
			} else {
				throw new ScoreBaseException(0, "EffectiveFromDate_is_mandatory", HttpStatus.CONFLICT);
			}
			taxRateDetails.setExpirationDate(taxDetail.getExpirationDate());

			if (taxRateDetails.getExpirationDate() != null) {
				Date startDate = taxRateDetails.getEffectiveFrom();
				Date endDate = taxRateDetails.getExpirationDate();
				if (startDate.compareTo(endDate) > 0) {
					throw new ScoreBaseException(0, "EndDateshouldbeGreaterThanStartDate",
							HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
				}

			}

			taxRateDetails.setTaxRateDetailId(taxDetail.getTaxRateDetailId());

			// validate
			if (taxDetail.getTaxRatePercentage() != null) {
				taxRateDetails.setTaxRatePercentage(taxDetail.getTaxRatePercentage());
			} else {
				throw new ScoreBaseException(0, "TaxRatePercentage_is_mandatory", HttpStatus.CONFLICT);
			}
			taxRateDetailList.add(taxRateDetails);

		}

		return masterTaxRate;

	}

	@Transactional
	public TaxRateDto readByTaxRateId(Integer taxCodeUID) {
		TaxRateDto dto;
		if (taxCodeUID == null)
			throw new ScoreBaseException(0, "taxCodeUID : " + taxCodeUID, HttpStatus.NOT_FOUND);

		MasterTaxRate masterTaxRate = taxRateRepository.findOne(taxCodeUID);

		List<TaxRateDetails> taxRateDetailsList = taxRateDetailsRepository.rerurnAllByTaxRateUID(taxCodeUID);

		if (masterTaxRate == null)
			throw new ScoreBaseException(0, "taxCodeUID : " + taxCodeUID + " not found in database",
					HttpStatus.CONFLICT);

		dto = generateTaxRateDto(masterTaxRate, taxRateDetailsList);
		return dto;
	}

	@Transactional
	public TaxRateDto addNewTaxRate(TaxRateDto taxrateDto) {

		MasterTaxRate mastrTaxRate = new MasterTaxRate();
		mastrTaxRate = generateMasterTaxRate(taxrateDto);
		List<TaxRateDetailsDto> taxRateDetails = taxrateDto.getTaxRateDetails();
		validationForAddingNewTaxRate(mastrTaxRate);
		taxRateRepository.saveAndFlush(mastrTaxRate);		
		validationForDateOverlap(taxRateDetails);
		for (TaxRateDetailsDto details : taxRateDetails) {
			details.setTaxCodeUID(mastrTaxRate.getTaxCodeUID());
			TaxRateDetails details1 = modelMapper.map(details, TaxRateDetails.class);
			taxRateDetailsRepository.save(details1);
		}
		return taxrateDto;

	}

	@Autowired(required = false)
	private void validationForDateOverlap(List<TaxRateDetailsDto> taxRateDetails) {

		String toDateFlag = "true";
		for (TaxRateDetailsDto details : taxRateDetails) {
			if (details.getExpirationDate() != null) {
				toDateFlag = "false";

			} else {
				toDateFlag = "true";
				break;
			}
		}
		if (toDateFlag.equals("false")) {
			// when todate is filled
			for (int i = 0; i < taxRateDetails.size(); i++) {
				Date fromDate1 = taxRateDetails.get(i).getEffectiveFrom();
				Date toDate1 = taxRateDetails.get(i).getExpirationDate();
				for (int j = i + 1; j < taxRateDetails.size(); j++) {
					Date fromDate2 = taxRateDetails.get(j).getEffectiveFrom();
					Date toDate2 = taxRateDetails.get(j).getExpirationDate();

					if ((fromDate2.after(fromDate1) && fromDate2.before(toDate1))
							|| (toDate1.after(fromDate1) && fromDate2.before(toDate1)) || (fromDate2.equals(fromDate1))
							|| (fromDate2.equals(toDate1)) || (toDate2.equals(fromDate1))
							|| (toDate2.equals(toDate1))) {
						throw new ScoreBaseException(0,
								"Validity period of the Tax Rate overlaps with an existing Tax Rate’",
								HttpStatus.EXPECTATION_FAILED);
					}
				}
			}
		} else if (toDateFlag.equals("true")) {
			// when atleast one todate is empty
			for (int i = 0; i < taxRateDetails.size(); i++) {
				Date fromDate1 = taxRateDetails.get(i).getEffectiveFrom();
				for (int j = i + 1; j < taxRateDetails.size(); j++) {
					Date fromDate2 = taxRateDetails.get(j).getEffectiveFrom();

					if ((fromDate2.before(fromDate1)) || (fromDate2.equals(fromDate1))) {
						throw new ScoreBaseException(0,
								"Validity period of the Tax Rate overlaps with an existing Tax Rate’",
								HttpStatus.EXPECTATION_FAILED);
					}
				}
			}

		}

	}

	public String updateTaxRate(TaxRateDto taxRateDto) {
		String addStatus = "DATA Succcessfully Updated";
		MasterTaxRate masterTaxRate = generateMasterTaxRate(taxRateDto);

		validationForUpdatingTaxRate(masterTaxRate);
		List<TaxRateDetailsDto> dateValidationtaxRateDetailsList = taxRateDto.getTaxRateDetails();
		validationForDateOverlap(dateValidationtaxRateDetailsList);
		taxRateRepository.save(masterTaxRate);
		List<TaxRateDetailsDto> taxRateDetails = taxRateDto.getTaxRateDetails();
		taxRateDetailsRepository.deleteAllchildTaxRateId(masterTaxRate.getTaxCodeUID());
		for (TaxRateDetailsDto details : taxRateDetails) {
			details.setTaxCodeUID(masterTaxRate.getTaxCodeUID());
			TaxRateDetails taxDetails = modelMapper.map(details, TaxRateDetails.class);
			taxRateDetailsRepository.save(taxDetails);
		}

		return addStatus;
	}

	private void validationForAddingNewTaxRate(MasterTaxRate masterTaxRate) {

		if (taxRateRepository.uniqueTaxCode(masterTaxRate.getTaxCode()) > chkValue) {
			throw new ScoreBaseException(masterTaxRate.getTaxCountryID(), "Tax Code already exists",
					HttpStatus.CONFLICT);
		}

		if (taxRateRepository.uniqueTaxName(masterTaxRate.getTaxName()) > chkValue) {
			throw new ScoreBaseException(masterTaxRate.getTaxCountryID(), "Tax Name already exists",
					HttpStatus.PRECONDITION_FAILED);
		}

	}

	private void validationForUpdatingTaxRate(MasterTaxRate masterTaxRate) {

		if (taxRateRepository.uniqueTaxCodeForUpdate(masterTaxRate.getTaxCode(),
				masterTaxRate.getTaxCodeUID()) > chkValue) {
			throw new ScoreBaseException(0, "Tax Code already exists", HttpStatus.CONFLICT);
		}

		if (taxRateRepository.uniqueTaxNameForUpdate(masterTaxRate.getTaxName(),
				masterTaxRate.getTaxCodeUID()) > chkValue) {
			throw new ScoreBaseException(1, "Tax Name already exists", HttpStatus.PRECONDITION_FAILED);
		}

	}

	public String updateStatus(Integer taxCodeUID) {

		if (taxCodeUID <= 0)
			return Constants.TAXRATE_DELETE_FAILURE;
		else {
			int statusId = 0;
			int masterStatusId = 0;
			String response = "";
			MasterTaxRate taxRate = taxRateRepository.findOne(taxCodeUID);
			masterStatusId = taxRate.getStatus();
			if (Constants.TAXRATE_ACTIVE_STATUS_ID == masterStatusId) {
				// changing status to DEACTIVATE }
				statusId = Constants.TAXRATE_DEACTIVE_STATUS_ID;
				response = Constants.TAXRATE_DEACTIVATE_SUCCESS;
			} else if (Constants.TAXRATE_DEACTIVE_STATUS_ID == masterStatusId) {
				// changing status to DEACTIVATE }
				statusId = Constants.TAXRATE_ACTIVE_STATUS_ID;
				response = Constants.TAXRATE_REACTIVATE_SUCCESS;
			}

			taxRateRepository.deactivateOrReactivateTaxRate(statusId, taxCodeUID);

			response = response.replace(Constants.TAXRATE_NAME_MESSAGE, taxRate.getTaxName());
			return response;

		}
	}

	
	public String deleteDeactivateOrReactivateTaxRare(Integer taxCodeUID) {

		if (taxCodeUID <= 0)
			return Constants.TAXRATE_DELETE_FAILURE;
		else {
			int statusId = 0;
			MasterTaxRate taxRate = taxRateRepository.findOne(taxCodeUID);
			statusId = taxRate.getStatus();
			// for draft:-> hard delete
			if (Constants.TAXRATE_DRAFT_STATUS_ID == statusId) {
				taxRateDetailsRepository.deleteAllchildTaxRateId(taxCodeUID);

				taxRateRepository.delete(taxCodeUID);
				String response = Constants.TAXRATE_DELETE_SUCCESS;
				response = response.replace(Constants.TAXRATE_NAME_MESSAGE, taxRate.getTaxName());
				return response;
			}
			// for Active: -> Soft delete - Change Status : DEACTIVATING
			else if (Constants.TAXRATE_ACTIVE_STATUS_ID == statusId) {
				// changing status to DEACTIVATE }
				int status = Constants.TAXRATE_DEACTIVE_STATUS_ID;

				taxRateRepository.deactivateOrReactivateTaxRate(status, taxCodeUID);
				String response = Constants.TAXRATE_DEACTIVATE_SUCCESS;
				response = response.replace(Constants.TAXRATE_NAME_MESSAGE, taxRate.getTaxName());
				return response;
				// for Deactivating: -> Soft delete - Change Status : Activating
			} else if (Constants.TAXRATE_DEACTIVE_STATUS_ID == statusId) {

				int status = Constants.TAXRATE_ACTIVE_STATUS_ID;
				taxRateRepository.deactivateOrReactivateTaxRate(status, taxCodeUID);
				String response = Constants.TAXRATE_REACTIVATE_SUCCESS;
				response = response.replace(Constants.TAXRATE_NAME_MESSAGE, taxRate.getTaxName());

				return response;
			} else {
				String response = Constants.TAXRATE_BLANK_STATUS_ID;

				return response;
			}
		}
	}

}

package com.olam.score.product.service;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.olam.score.common.constants.Constants;
import com.olam.score.common.dto.PageDto;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.dto.ViewDto;
import com.olam.score.common.util.ErrorMessageUtil;
import com.olam.score.common.util.ListViewUtil;
import com.olam.score.common.util.Mapping;
import com.olam.score.common.util.ScoreBaseException;
import com.olam.score.common.util.ScoreBaseExceptionResponse;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.product.domain.MasterProductCertification;
import com.olam.score.product.dto.CertificationDto;
import com.olam.score.product.repository.CertificationRepository;
import com.olam.score.product.util.CertificationSpecificationsBuilder;

@Service
public class CertificationService {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	public CertificationRepository certificationRepository;
	@Autowired
	private WebServiceCallUtil webServiceCall;

	@Autowired
	EntityManager entityManager;

	@Value("${reference.name}")
	public String referenceName;

	@Value("${product.name}")
	public String productName;

	@Value("${party.name}")
	public String partyName;

	@Autowired
	private ListViewUtil listview;

	@Transactional
	public List<CertificationDto> readAll(ViewDto view) {
		List<String> filterList = view.getFiltersArray();
		CertificationSpecificationsBuilder builder = new CertificationSpecificationsBuilder();
		for (int filterSize = 0; filterSize < filterList.size(); filterSize++) {
			builder.with(filterList.get(filterSize).substring(0, filterList.get(filterSize).indexOf('=')),
					filterList.get(filterSize).substring(filterList.get(filterSize).indexOf('=') + 1));
		}
		Specification<MasterProductCertification> spec = builder.build();
		Pageable pageRequest = ListViewUtil.createPageRequest(view);
		log.info("===Pageable object created from view object===");
		List<MasterProductCertification> certifications = certificationRepository.findAll(spec, pageRequest)
				.getContent();
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		ResponseEntity<ResponseData> responseEntity = webServiceCall.internalServiceCall(partyName,
				Constants.PARTY_URL);
		List<Map<Object, Object>> partys = (List<Map<Object, Object>>) responseEntity.getBody().getBody();
		List<CertificationDto> certificationDtoList = new ArrayList<>();

		for (MasterProductCertification certification : certifications) {
			CertificationDto certificationDto = certificationModelToDto(statusMap, partys, certification);
			certificationDtoList.add(certificationDto);
		}

		return certificationDtoList;
	}

	private CertificationDto certificationModelToDto(Map<Integer, String> statusMap, List<Map<Object, Object>> partys,
			MasterProductCertification certification) {
		CertificationDto certificationDto = new CertificationDto();
		certificationDto.setPkProdCertId(certification.getPkProdCertId());
		certificationDto.setProdCertCode(certification.getProdCertCode());
		certificationDto.setProdCertName(certification.getProdCertName());
		certificationDto.setProdCertDescription(certification.getProdCertDescription());
		certificationDto.setRegistrationNumber(certification.getRegistrationNumber());
		if (!partys.isEmpty()) {
			for (Map<Object, Object> party : partys) {
				if (party.get("partyId").equals(certification.getFkCertfBodyPartyId())) {
					certificationDto.setFkCertfBodyPartyId(certification.getFkCertfBodyPartyId());
					certificationDto.setFkCertfBodyPartyName((String) party.get("partyName"));
					break;
				}

			}
		}

		certificationDto.setProdCertLogo(certification.getLogoBlob());
		certificationDto.setValidFrom(certification.getValidFrom());
		certificationDto.setValidTo(certification.getValidTo());
		certificationDto.setFkStatusId(certification.getFkStatusId());
		certificationDto.setStatusName(statusMap.get(certification.getFkStatusId()));
		certificationDto.setCreatedBy(certification.getCreatedBy());
		certificationDto.setCreatedDate(certification.getCreatedDate());
		certificationDto.setModifiedBy(certification.getModifiedBy());
		certificationDto.setModifiedDate(certification.getModifiedDate());
		return certificationDto;
	}

	@Transactional
	public CertificationDto create(CertificationDto certificationDto) {
		MasterProductCertification certificationName = null;
		MasterProductCertification certificationCode = null;
		MasterProductCertification certificationRegistrationNumber = null;
		MasterProductCertification certification = null;
		CertificationDto dto = null;
		log.info("In CertificationService class create(). Find Cropyear with the name supplied by user");
		certificationName = certificationRepository
				.findByCertificationName(certificationDto.getProdCertName().toUpperCase());
		log.info("In CertificationService class create(). Find Cropyear with the code supplied by user");
		certificationCode = certificationRepository
				.findByCertificationCode(certificationDto.getProdCertCode().toUpperCase());
		log.info("In CertificationService class create(). Find Cropyear with the Registration Number supplied by user");
		certificationRegistrationNumber = certificationRepository
				.findByCertificationRegistrationNumber(certificationDto.getRegistrationNumber().toUpperCase());

		List<ScoreBaseExceptionResponse> errorBeans = new ArrayList<>();
		if (certificationCode != null || certificationName != null || certificationRegistrationNumber != null) {
			if (certificationCode != null) {
				log.error("===Duplicate Certification Code found in Database===");
				ErrorMessageUtil.errorHandler(new Object[] { certificationDto.getProdCertCode() }, errorBeans,
						Constants.ERROR_DUPLICATE_CERTF_CODE, Constants.ERROR_TYPE_DUPLICATE_CODE);
			}
			if (certificationName != null) {
				log.error("===Duplicate Certification Name found in Database===");
				ErrorMessageUtil.errorHandler(new Object[] { certificationDto.getProdCertName() }, errorBeans,
						Constants.ERROR_DUPLICATE_CERTF_NAME, Constants.ERROR_TYPE_DUPLICATE_NAME);
			}
			if (certificationRegistrationNumber != null) {
				log.error("===Duplicate Certification registration number found in Database===");
				ErrorMessageUtil.errorHandler(new Object[] { certificationDto.getRegistrationNumber() }, errorBeans,
						Constants.ERROR_DUPLICATE_CERTF_REGNO, Constants.ERROR_TYPE_DUPLICATE_CERTF_REGNO);
			}
			throw new ScoreBaseException(errorBeans, HttpStatus.CONFLICT);
		}
		log.info("Status Name : ");
		log.info(certificationDto.getStatusName());
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		ResponseEntity<ResponseData> responseEntity = webServiceCall.internalServiceCall(partyName,
				Constants.PARTY_URL);
		List<Map<Object, Object>> partys = (List<Map<Object, Object>>) responseEntity.getBody().getBody();
		Integer statusId = null;
		Optional<Integer> statusId1 = statusMap.entrySet().stream()
				.filter(e -> e.getValue().equals(certificationDto.getStatusName())).map(Map.Entry::getKey).findFirst();
		if (statusId1.isPresent()) {
			statusId = statusId1.get();
		}
		certification = certificationRepository
				.saveAndFlush(cropYearDtoToModel(certificationDto, statusId, Constants.CREATE));
		dto = certificationModelToDto(statusMap, partys, certification);
		return dto;
	}

	public MasterProductCertification cropYearDtoToModel(CertificationDto certificationDto, Integer statusId,
			String action) {

		MasterProductCertification productCertification = new MasterProductCertification();
		productCertification.setProdCertCode(certificationDto.getProdCertCode());
		productCertification.setProdCertName(certificationDto.getProdCertName());
		productCertification.setProdCertDescription(certificationDto.getProdCertDescription());
		productCertification.setRegistrationNumber(certificationDto.getRegistrationNumber());
		productCertification.setLogoBlob(certificationDto.getProdCertLogo());
		productCertification.setValidFrom(certificationDto.getValidFrom());
		productCertification.setValidTo(certificationDto.getValidTo());
		productCertification.setFkStatusId(certificationDto.getFkStatusId());
		productCertification.setFkCertfBodyPartyId(certificationDto.getFkCertfBodyPartyId());
		productCertification.setFkStatusId(statusId);
		productCertification.setCreatedBy("Test");// TODO: Hard Coding needs
		// changes
		productCertification.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		productCertification.setModifiedBy("Test");// TODO: Hard Coding needs
		// changes
		productCertification.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		if (Constants.UPDATE.equalsIgnoreCase(action)) {
			productCertification.setPkProdCertId(certificationDto.getPkProdCertId());
		}
		return productCertification;
	}

	public CertificationDto readById(Integer certificationId) {

		MasterProductCertification cropYear = certificationRepository.findOne(certificationId);
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		ResponseEntity<ResponseData> responseEntity = webServiceCall.internalServiceCall(partyName,
				Constants.PARTY_URL);
		List<Map<Object, Object>> partys = (List<Map<Object, Object>>) responseEntity.getBody().getBody();
		return certificationModelToDto(statusMap, partys, cropYear);

	}

	public ViewDto getViewDetails(Class<? extends Object> currentfeature) {
		String featureId = currentfeature.getAnnotation(Mapping.class).featureId();
		ViewDto viewdto = listview.getView(featureId);
		log.info("===viewdto from common has been generated for JSON===");
		PageDto pageDto = new PageDto();
		pageDto.setPageNumber(0);
		pageDto.setPageSize(Constants.PAGESIZE);
		pageDto.setTotalCount(certificationRepository.count());
		pageDto.setOperation("Client");// To be set from Database
		viewdto.setPage(pageDto);
		Class<CertificationDto> uomDto = CertificationDto.class;
		Field[] fields = uomDto.getDeclaredFields();
		Map<String, String> dataType = new HashMap<>();
		for (int i = 0; i < fields.length; i++) {
			String type = fields[i].getType().toString();
			type = type.substring(type.lastIndexOf('.') + 1, type.length());
			dataType.put(fields[i].getName(), type);
		}
		viewdto.setDataType(dataType);
		log.info("===dataType is set in view according to entity===");

		return viewdto;
	}

	public void deleteCertification(List<Map<String, Integer>> body) {
		log.info("In CertificationService class deleteCropyear().");
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();

		Optional<Integer> statusId1 = statusMap.entrySet().stream().filter(e -> e.getValue().equals(Constants.INACTIVE))
				.map(Map.Entry::getKey).findFirst();
		Integer inactiveStatusId = null;
		if (statusId1.isPresent()) {
			inactiveStatusId = statusId1.get();
		}

		Optional<Integer> statusId2 = statusMap.entrySet().stream().filter(e -> e.getValue().equals(Constants.ACTIVE))
				.map(Map.Entry::getKey).findFirst();
		Integer activeStatusId = null;
		if (statusId2.isPresent()) {
			activeStatusId = statusId2.get();
		}

		for (Map<String, Integer> map : body) {

			MasterProductCertification masterCropYearProduct;
			masterCropYearProduct = certificationRepository.findOne(map.get("pkProdCertId"));

			if (masterCropYearProduct.getFkStatusId() != null) {
				String currentStatusName = null;

				currentStatusName = statusMap.get(masterCropYearProduct.getFkStatusId());
				log.info("Curerent Status Name : ");
				log.info(currentStatusName);
				if (currentStatusName.equals(Constants.ACTIVE)) {
					masterCropYearProduct.setFkStatusId(inactiveStatusId);
					certificationRepository.saveAndFlush(masterCropYearProduct);

				} else if (currentStatusName.equals(Constants.INACTIVE)) {

					masterCropYearProduct.setFkStatusId(activeStatusId);
					certificationRepository.saveAndFlush(masterCropYearProduct);
				} else if (currentStatusName.equals(Constants.DRAFT)) {
					certificationRepository.delete(masterCropYearProduct);
				}

			}

		}

	}

	public CertificationDto update(CertificationDto certificationDto) {

		MasterProductCertification certificationName = null;
		MasterProductCertification certificationCode = null;
		MasterProductCertification certificationRegistrationNumber = null;
		MasterProductCertification certification = null;
		CertificationDto dto;
		log.info("In CertificationService class update(). Find Cropyear with the name supplied by user");
		certificationName = certificationRepository
				.findByCertificationName(certificationDto.getProdCertName().toUpperCase());
		log.info("In CertificationService class update(). Find Cropyear with the code supplied by user");
		certificationCode = certificationRepository
				.findByCertificationCode(certificationDto.getProdCertCode().toUpperCase());
		log.info("In CertificationService class update(). Find Cropyear with the Registration Number supplied by user");
		certificationRegistrationNumber = certificationRepository
				.findByCertificationRegistrationNumber(certificationDto.getRegistrationNumber().toUpperCase());

		List<ScoreBaseExceptionResponse> errorBeans = new ArrayList<>();
		// Check for duplicates and throw exception
		if ((certificationCode != null
				&& !certificationCode.getPkProdCertId().equals(certificationDto.getPkProdCertId()))
				|| (certificationName != null
						&& !certificationName.getPkProdCertId().equals(certificationDto.getPkProdCertId()))
				|| (certificationRegistrationNumber != null && !certificationRegistrationNumber.getPkProdCertId()
						.equals(certificationDto.getPkProdCertId()))) {
			if (certificationCode != null
					&& !certificationCode.getPkProdCertId().equals(certificationDto.getPkProdCertId())) {
				log.error("===Duplicate Certification Code found in Database===");
				ErrorMessageUtil.errorHandler(new Object[] { certificationDto.getProdCertCode() }, errorBeans,
						Constants.ERROR_DUPLICATE_CERTF_CODE, Constants.ERROR_TYPE_DUPLICATE_CODE);
			}
			if (certificationName != null
					&& !certificationName.getPkProdCertId().equals(certificationDto.getPkProdCertId())) {
				log.error("===Duplicate Certification Name found in Database===");
				ErrorMessageUtil.errorHandler(new Object[] { certificationDto.getProdCertName() }, errorBeans,
						Constants.ERROR_DUPLICATE_CERTF_NAME, Constants.ERROR_TYPE_DUPLICATE_NAME);
			}
			if (certificationRegistrationNumber != null
					&& !certificationRegistrationNumber.getPkProdCertId().equals(certificationDto.getPkProdCertId())) {
				log.error("===Duplicate Certification Registration number found in Database===");
				ErrorMessageUtil.errorHandler(new Object[] { certificationDto.getRegistrationNumber() }, errorBeans,
						Constants.ERROR_DUPLICATE_CERTF_REGNO, Constants.ERROR_TYPE_DUPLICATE_CERTF_REGNO);
			}
			throw new ScoreBaseException(errorBeans, HttpStatus.CONFLICT);
		}

		log.info("Status Name : ");
		log.info(certificationDto.getStatusName());
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		ResponseEntity<ResponseData> responseEntity = webServiceCall.internalServiceCall(partyName,
				Constants.PARTY_URL);
		List<Map<Object, Object>> partys = (List<Map<Object, Object>>) responseEntity.getBody().getBody();
		Integer statusId = null;
		Optional<Integer> statusId1 = statusMap.entrySet().stream()
				.filter(e -> e.getValue().equalsIgnoreCase(certificationDto.getStatusName())).map(Map.Entry::getKey)
				.findFirst();
		if (statusId1.isPresent()) {
			statusId = statusId1.get();
		}
		certification = certificationRepository
				.saveAndFlush(cropYearDtoToModel(certificationDto, statusId, Constants.UPDATE));
		dto = certificationModelToDto(statusMap, partys, certification);
		return dto;
	}
}

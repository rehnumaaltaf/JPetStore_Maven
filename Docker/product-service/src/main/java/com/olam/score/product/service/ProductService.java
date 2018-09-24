package com.olam.score.product.service;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.olam.score.product.controller.ProductController;
import com.olam.score.product.domain.MasterIntlCodeTypeRef;
import com.olam.score.product.domain.MasterProcessSubTypeRef;
import com.olam.score.product.domain.MasterProdExternalMapping;
import com.olam.score.product.domain.MasterProduct;
import com.olam.score.product.domain.MasterProductArbitrationMapping;
import com.olam.score.product.domain.MasterProductIcoIndex;
import com.olam.score.product.domain.MasterProductIntlCode;
import com.olam.score.product.domain.MasterProductProcess;
import com.olam.score.product.domain.MasterProductProcessSub;
import com.olam.score.product.dto.ExternalSystemRefDto;
import com.olam.score.product.dto.InternationalGradeCodeMappingDto;
import com.olam.score.product.dto.IntlCodeTypeRefDto;
import com.olam.score.product.dto.PartyDto;
import com.olam.score.product.dto.ProcessSubTypeRefDto;
import com.olam.score.product.dto.ProcessingSubTypeDto;
import com.olam.score.product.dto.ProductArbitrationDto;
import com.olam.score.product.dto.ProductDto;
import com.olam.score.product.repository.InternationGradeProductRepository;
import com.olam.score.product.repository.IntlCodeTypeRefRepository;
import com.olam.score.product.repository.ProcessSubTypeRefRepository;
import com.olam.score.product.repository.ProductArbitrationRepository;
import com.olam.score.product.repository.ProductRepository;
import com.olam.score.product.util.ProductSpecificationsBuilder;

@Service
public class ProductService {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ProductRepository repository;

	@Autowired
	private ProcessSubTypeRefRepository processSubTypeRefRepository;
	@Autowired
	private IntlCodeTypeRefRepository codeTypeRefRepository;
	@Autowired
	private ProductArbitrationRepository productArbitrationRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ListViewUtil listview;
	@Autowired
	private WebServiceCallUtil webServiceCall;

	@Value("${finance.name}")
	private String financeName;

	@Value("${party.name}")
	private String partyName;

	private InternationGradeProductRepository productIntlRepository;

	private final class MapProductDto extends PropertyMap<ProductDto, MasterProduct> {
		@Override
		protected void configure() {
			map().setParentProdId(source.getParentProductId());
			map().setFkStatusId(source.getStatusId());
			map().setPkProdId(source.getProdId());
			map().setProdCode(source.getProdCode());
			map().setProdFullname(source.getProdFullName());
			map().setProdName(source.getProdName());
			map().setProdSpecialityApplicable(source.getSpecialityApplicable());
			map().setProdIsTradedProd(source.getIsBaseProduct());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public ProductDto readById(Integer productId) {

		MasterProduct product = repository.findOne(productId);
		List<MasterProcessSubTypeRef> subTypeRef = processSubTypeRefRepository.findAll();
		List<MasterIntlCodeTypeRef> codeTypeRefList = codeTypeRefRepository.findAll();
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		ResponseEntity<ResponseData> responseEntity = webServiceCall.internalServiceCall(partyName,
				"party/partyservice/party?partyTypeName=AGENCY");
		List<Map<Object, Object>> partylist = (List<Map<Object, Object>>) responseEntity.getBody().getBody();
		ResponseEntity<ResponseData> responseEntityFinance = webServiceCall.internalServiceCall(financeName,
				"finance/glservice/gl/externalsystemref");
		List<Map<Object, Object>> financelist = (List<Map<Object, Object>>) responseEntityFinance.getBody().getBody();
		return product.convertEntityToDto(statusMap, partylist, financelist, codeTypeRefList, subTypeRef);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public List<ProductDto> readAll() {
		ViewDto view = getViewDetails(ProductController.class);
		List<ProductDto> newList = new ArrayList<>();
		List<String> filterList = view.getFiltersArray();
		ProductSpecificationsBuilder builder = new ProductSpecificationsBuilder();
		for (int filterSize = 0; filterSize < filterList.size(); filterSize++) {
			builder.with(filterList.get(filterSize).substring(0, filterList.get(filterSize).indexOf("=")),
					filterList.get(filterSize).substring(filterList.get(filterSize).indexOf("=") + 1));
		}
		Specification<MasterProduct> spec = builder.build();
		Pageable pageRequest = ListViewUtil.createPageRequest(view);
		log.info("===Pageable object created from view object===");
		List<MasterProduct> allProducts = repository.findAll(spec, pageRequest).getContent();
		List<MasterIntlCodeTypeRef> codeTypeRefList = codeTypeRefRepository.findAll();
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		ResponseEntity<ResponseData> responseEntity = webServiceCall.internalServiceCall(partyName,
				"party/partyservice/party");
		List<Map<Object, Object>> partylist = (List<Map<Object, Object>>) responseEntity.getBody().getBody();
		ResponseEntity<ResponseData> responseEntityFinance = webServiceCall.internalServiceCall(financeName,
				"finance/glservice/gl/externalsystemref");
		List<MasterProcessSubTypeRef> subTypeRef = processSubTypeRefRepository.findAll();
		List<Map<Object, Object>> financelist = (List<Map<Object, Object>>) responseEntityFinance.getBody().getBody();
		for (MasterProduct product : allProducts) {
			ProductDto productDto = product.convertEntityToDto(statusMap, partylist, financelist, codeTypeRefList,
					subTypeRef);
			newList.add(productDto);
		}
		return newList;
	}

	@Transactional
	public ProductDto update(ProductDto updatedProductDetails) {
		MasterProduct productCode = repository.findByCode(updatedProductDetails.getProdCode());
		MasterProduct productName = repository.findByName(updatedProductDetails.getProdName());
		List<ScoreBaseExceptionResponse> errorBeans = new ArrayList<>();
		if ((productCode != null && !productCode.getPkProdId().equals(updatedProductDetails.getProdId()))
				|| (productName != null && !productName.getPkProdId().equals(updatedProductDetails.getProdId()))) {
			if (productCode != null && !productCode.getPkProdId().equals(updatedProductDetails.getProdId())) {
				log.error("===Duplicate Product Code found in Database===");
				ErrorMessageUtil.errorHandler(new Object[] { updatedProductDetails.getProdCode() }, errorBeans,
						Constants.ERROR_DUPLICATE_PRODUCT_CODE, Constants.ERROR_TYPE_DUPLICATE_CODE);
			}
			if (productName != null && !productName.getPkProdId().equals(updatedProductDetails.getProdId())) {
				log.error("===Duplicate Product Name found in Database===");
				ErrorMessageUtil.errorHandler(new Object[] { updatedProductDetails.getProdName() }, errorBeans,
						Constants.ERROR_DUPLICATE_PRODUCT_NAME, Constants.ERROR_TYPE_DUPLICATE_NAME);
			}
			throw new ScoreBaseException(errorBeans, HttpStatus.CONFLICT);

		}
		TypeMap<ProductDto, MasterProduct> typeMap = modelMapper.getTypeMap(ProductDto.class, MasterProduct.class);
		if (typeMap == null) {
			modelMapper.addMappings(new MapProductDto());
		}
		MasterProduct updatedProductRow = modelMapper.map(updatedProductDetails, MasterProduct.class);
		updatedProductRow.setFkParentProdId(new MasterProduct(updatedProductDetails.getParentProductId()));
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		Optional<Integer> firstKey = statusMap.entrySet().stream()
				.filter(entry -> Objects.equals(entry.getValue(), updatedProductDetails.getStatusName()))
				.map(Map.Entry::getKey).findFirst();
		if (firstKey.isPresent()) {
			updatedProductRow.setFkStatusId(firstKey.get());
		}

		// Update arbitration mapping(multiple)
		List<MasterProductArbitrationMapping> updatedArbitrationMappingList = new ArrayList<>();

		for (ProductArbitrationDto obj : updatedProductDetails.getProductArbitrationCollection()) {
			MasterProductArbitrationMapping arbitrationMapping = new MasterProductArbitrationMapping(
					obj.getTableMappingId());
			arbitrationMapping.setFkArbitrationAgencyId(obj.getArbitrationAgencyId());
			arbitrationMapping.setFkProdId(updatedProductRow);
			updatedArbitrationMappingList.add(arbitrationMapping);
		}
		updatedProductRow.setMasterProductArbitrationMappingCollection(updatedArbitrationMappingList);
		// Delete Rows
		for (Integer deletedId : updatedProductDetails.getDeletedArbitrationMappingIds()) {
			MasterProductArbitrationMapping findOne = productArbitrationRepository.findOne(deletedId);
			if (findOne != null) {
				productArbitrationRepository.delete(deletedId);
			}
		}

		// International Product Grade

		List<MasterProductIntlCode> productGradeList = new ArrayList<>();

		for (InternationalGradeCodeMappingDto obj : updatedProductDetails.getGradeCodeMappingCollection()) {
			MasterProductIntlCode productGrade = new MasterProductIntlCode(obj.getTableMappingId());
			productGrade.setFkIntlCodeTypeRefId(obj.getCodeTypeId());
			productGrade.setIntlCode(obj.getCode());
			productGrade.setDesc(obj.getDescription());
			productGrade.setFkProdId(updatedProductRow);
			productGradeList.add(productGrade);
		}
		updatedProductRow.setMasterProductIntlCodeCollection(productGradeList);
		// Delete Rows
		for (Integer deletedId : updatedProductDetails.getDeletedGradeMappingIds()) {
			MasterProductIntlCode findOne = productIntlRepository.findOne(deletedId);
			if (findOne != null) {
				productArbitrationRepository.delete(deletedId);
			}
		}

		// Add masterproductprocess
		List<MasterProductProcess> updatedProcessList = new ArrayList<>();
		MasterProductProcess process = new MasterProductProcess(updatedProductDetails.getProcessMappingId());
		process.setFkProdId(updatedProductRow);
		process.setProcessingCode(updatedProductDetails.getProcessingCode());
		process.setProcessingName(updatedProductDetails.getProcessingName());
		List<MasterProductProcessSub> processSubList = new ArrayList<>();
		if (updatedProductDetails.getProcessingSubType() != null) {
			for (ProcessingSubTypeDto proSubType : updatedProductDetails.getProcessingSubType()) {
				MasterProductProcessSub obj = new MasterProductProcessSub();
				obj.setFkProcessSubTypeRefId(proSubType.getProcessSubId());
				obj.setFkProductProcessId(process);
				obj.setFkStatusId(1);// Remove this hard coding
				processSubList.add(obj);
			}
			process.setMasterProductProcessSubCollection(processSubList);
		}
		updatedProcessList.add(process);
		updatedProductRow.setMasterProductProcessCollection(updatedProcessList);

		// Add ICO index
		List<MasterProductIcoIndex> icoIndexList = new ArrayList<>();
		MasterProductIcoIndex updatedProductIndex = new MasterProductIcoIndex(updatedProductDetails.getIcoMappingId());
		updatedProductIndex.setIcoName(updatedProductDetails.getIcoName());
		updatedProductIndex.setIcoCode(updatedProductDetails.getIcoCode());
		updatedProductIndex.setFkProdId(updatedProductRow);
		icoIndexList.add(updatedProductIndex);
		updatedProductRow.setMasterProductIcoIndexCollection(icoIndexList);

		// Add External Mapping
		List<MasterProdExternalMapping> listExternalMapping = new ArrayList<>();
		MasterProdExternalMapping map = new MasterProdExternalMapping(
				updatedProductDetails.getExternalSystemMappingId());
		map.setCustomAttribute1(updatedProductDetails.getAttribute1());
		map.setCustomAttribute2(updatedProductDetails.getAttribute2());
		map.setFkProdId(updatedProductRow);
		map.setFkExternalSystemRefId(updatedProductDetails.getDestinationSystemId());
		listExternalMapping.add(map);
		updatedProductRow.setMasterProdExternalMappingCollection(listExternalMapping);

		updatedProductRow.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		try {
			repository.saveAndFlush(updatedProductRow);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updatedProductDetails;
	}

	@Transactional
	public ProductDto create(ProductDto productDetails) {
		MasterProduct productRow;
		MasterProduct productCode = repository.findByCode(productDetails.getProdCode());
		MasterProduct productName = repository.findByName(productDetails.getProdName());
		List<ScoreBaseExceptionResponse> errorBeans = new ArrayList<>();
		if (productCode != null || productName != null) {
			if (productCode != null) {
				log.error("===Duplicate Product Code found in Database===");
				ErrorMessageUtil.errorHandler(new Object[] { productDetails.getProdCode() }, errorBeans,
						Constants.ERROR_DUPLICATE_PRODUCT_CODE, Constants.ERROR_TYPE_DUPLICATE_CODE);
			}
			if (productName != null) {
				log.error("===Duplicate Product Name found in Database===");
				ErrorMessageUtil.errorHandler(new Object[] { productDetails.getProdName() }, errorBeans,
						Constants.ERROR_DUPLICATE_PRODUCT_NAME, Constants.ERROR_TYPE_DUPLICATE_NAME);
			}
			throw new ScoreBaseException(errorBeans, HttpStatus.CONFLICT);
		} else {
			Map<Integer, String> statusMap = webServiceCall.getAllStatus();
			Optional<Integer> firstKey = statusMap.entrySet().stream()
					.filter(entry -> Objects.equals(entry.getValue(), productDetails.getStatusName()))
					.map(Map.Entry::getKey).findFirst();
			if (firstKey.isPresent()) {
				productDetails.setStatusId(firstKey.get());
			}

			TypeMap<ProductDto, MasterProduct> typeMap = modelMapper.getTypeMap(ProductDto.class, MasterProduct.class);
			if (typeMap == null) {
				modelMapper.addMappings(new MapProductDto());
			}

			productRow = modelMapper.map(productDetails, MasterProduct.class);
			if (productRow.getParentProdId() != null) {
				productRow.setFkParentProdId(new MasterProduct(productRow.getParentProdId()));
			}
			// Add ICO index
			List<MasterProductIcoIndex> icoIndexList = new ArrayList<>();
			MasterProductIcoIndex productIcoIndex = new MasterProductIcoIndex();
			productIcoIndex.setIcoName(productDetails.getIcoName());
			productIcoIndex.setIcoCode(productDetails.getIcoCode());
			productIcoIndex.setFkProdId(productRow);
			// productIcoIndex.setFkStatusId(fkStatusId)
			icoIndexList.add(productIcoIndex);
			productRow.setMasterProductIcoIndexCollection(icoIndexList);

			// Add arbitration mapping
			List<MasterProductArbitrationMapping> arbitrationMappingList = new ArrayList<>();

			for (ProductArbitrationDto obj : productDetails.getProductArbitrationCollection()) {
				MasterProductArbitrationMapping arbitrationMapping = new MasterProductArbitrationMapping();
				arbitrationMapping.setFkArbitrationAgencyId(obj.getArbitrationAgencyId());
				arbitrationMapping.setFkProdId(productRow);
				arbitrationMappingList.add(arbitrationMapping);
			}

			productRow.setMasterProductArbitrationMappingCollection(arbitrationMappingList);

			// Add External Mapping
			List<MasterProdExternalMapping> listExternalMapping = new ArrayList<>();
			MasterProdExternalMapping map = new MasterProdExternalMapping();
			map.setCustomAttribute1(productDetails.getAttribute1());
			map.setCustomAttribute2(productDetails.getAttribute2());
			map.setFkProdId(productRow);
			map.setFkExternalSystemRefId(productDetails.getDestinationSystemId());
			listExternalMapping.add(map);
			productRow.setMasterProdExternalMappingCollection(listExternalMapping);

			// Add masterproductprocess
			List<MasterProductProcess> processList = new ArrayList<>();
			MasterProductProcess process = new MasterProductProcess();
			process.setFkProdId(productRow);
			process.setProcessingCode(productDetails.getProcessingCode());
			process.setProcessingName(productDetails.getProcessingName());
			List<MasterProductProcessSub> processSubList = new ArrayList<>();
			if (productDetails.getProcessingSubType() != null) {
				for (ProcessingSubTypeDto proSubType : productDetails.getProcessingSubType()) {
					MasterProductProcessSub obj = new MasterProductProcessSub();
					obj.setFkProcessSubTypeRefId(proSubType.getProcessSubId());
					obj.setFkProductProcessId(process);
					obj.setFkStatusId(1);// Remove this hard coding
					processSubList.add(obj);
				}
				process.setMasterProductProcessSubCollection(processSubList);
			}
			processList.add(process);
			productRow.setMasterProductProcessCollection(processList);

			// International Product Grade
			List<MasterProductIntlCode> productGradeList = new ArrayList<>();

			for (InternationalGradeCodeMappingDto obj : productDetails.getGradeCodeMappingCollection()) {
				MasterProductIntlCode productGrade = new MasterProductIntlCode();
				productGrade.setFkIntlCodeTypeRefId(obj.getCodeTypeId());
				productGrade.setIntlCode(obj.getCode());
				productGrade.setDesc(obj.getDescription());
				productGrade.setFkProdId(productRow);
				productGradeList.add(productGrade);
			}
			productRow.setMasterProductIntlCodeCollection(productGradeList);
			productRow.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			productRow.setModifiedDate(new Timestamp(System.currentTimeMillis()));
			repository.saveAndFlush(productRow);

		}

		return productDetails;
	}

	public ProductDto getDataForCreate() {
		ProductDto productDto = new ProductDto();
		getProcessingSubtype(productDto);
		getProductArbitration(productDto);
		getDestinationSystem(productDto);
		getInternationalGradeCode(productDto);
		return productDto;
	}

	private void getInternationalGradeCode(ProductDto productDto) {

		List<MasterIntlCodeTypeRef> listCodeTypeRefList = codeTypeRefRepository.findAll();
		List<IntlCodeTypeRefDto> processReference = new ArrayList<>();
		for (MasterIntlCodeTypeRef listCodeTypeRef : listCodeTypeRefList) {
			IntlCodeTypeRefDto map = new IntlCodeTypeRefDto();

			map.setTypeRefId(listCodeTypeRef.getPkIntlCodeTypeRefId());
			map.setIntlCodeTypeCode(listCodeTypeRef.getIntlCodeTypeCode());
			map.setIntlCodeTypeName(listCodeTypeRef.getIntlCodeTypeName());
			processReference.add(map);
		}
		productDto.setIntlCodeTypeRefValues(processReference);

	}

	private void getProcessingSubtype(ProductDto productDto) {

		List<MasterProcessSubTypeRef> listSubTypeRef = processSubTypeRefRepository.findAll();
		List<ProcessSubTypeRefDto> processReference = new ArrayList<>();
		for (MasterProcessSubTypeRef processSubTypeRef : listSubTypeRef) {
			ProcessSubTypeRefDto map = new ProcessSubTypeRefDto();
			map.setProcessSubTypeRefId(processSubTypeRef.getPkProcessSubTypeRefId());
			map.setSubTypeCode(processSubTypeRef.getProcessSubTypeCode());
			map.setSubTypeName(processSubTypeRef.getProcessSubTypeName());
			processReference.add(map);
		}
		productDto.setProcessSubTypeValues(processReference);

	}

	private void getProductArbitration(ProductDto productDto) {

		ObjectMapper map = new ObjectMapper();
		ResponseEntity<ResponseData> serviceCall = webServiceCall.internalServiceCall(partyName,
				"/party/partyservice/party?partyTypeName=AGENCY");
		List<PartyDto> agencyPartyList = map.convertValue(serviceCall.getBody().getBody(),
				new TypeReference<List<PartyDto>>() {
				});
		productDto.setArbitrationAgencyValues(agencyPartyList);

	}

	private void getDestinationSystem(ProductDto productDto) {
		ObjectMapper map = new ObjectMapper();
		ResponseEntity<ResponseData> serviceCall = webServiceCall.internalServiceCall(financeName,
				"/finance/glservice/gl/externalsystemref");
		List<ExternalSystemRefDto> externalSystemRefList = map.convertValue(serviceCall.getBody().getBody(),
				new TypeReference<List<ExternalSystemRefDto>>() {
				});
		productDto.setExternalSystemRefValues(externalSystemRefList);
	}

	public ViewDto getViewDetails(Class<? extends Object> currentfeature) {
		String featureId = currentfeature.getAnnotation(Mapping.class).featureId();
		ViewDto viewdto = listview.getView(featureId);
		log.info("===viewdto from common has been generated for JSON===");
		PageDto pageDto = new PageDto();
		pageDto.setPageNumber(0);
		pageDto.setPageSize(Constants.PAGESIZE);
		pageDto.setTotalCount(repository.count());
		pageDto.setOperation("Client");// To be set from Database
		viewdto.setPage(pageDto);
		Class<ProductDto> uomDto = ProductDto.class;
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

	@Transactional
	public List<ProductDto> readProductForDropDown() {

		List<String> sortColumns = new ArrayList<>();
		sortColumns.add("fkParentProdId");
		Sort sort = new Sort(Direction.ASC, sortColumns);
		List<MasterProduct> oldList = repository.findAll(sort);

		List<ProductDto> rootObj = new ArrayList<>();

		for (MasterProduct product : oldList) {
			ProductDto dto = new ProductDto();
			dto.setProdId(product.getPkProdId());
			dto.setProdCode(product.getProdCode());
			dto.setProdName(product.getProdName());
			if (null != product.getFkParentProdId()) {
				dto.setParentProductId(product.getFkParentProdId().getPkProdId());
				dto.setParentProductName(product.getFkParentProdId().getProdName());
			}
			if (product.getFkParentProdId() == null) {
				rootObj.add(dto);
			} else {
				addChildToParentProduct(dto, product.getFkParentProdId(), rootObj);
			}
		}
		return rootObj;
	}

	private void addChildToParentProduct(ProductDto dto, MasterProduct parentProduct, List<ProductDto> rootObj) {
		for (ProductDto productDto : rootObj) {
			if (productDto.getProdId() == dto.getParentProductId()) {
				productDto.addChildren(dto);
				break;
			}
		}

		if (parentProduct != null && parentProduct.getFkParentProdId() != null) {
			for (int i = 0; i < rootObj.size() - 1; i++) {
				if (rootObj.get(i).getChildren() != null) {
					addChildToParentProduct(dto, parentProduct, rootObj.get(i).getChildren());
				}
			}
		}
	}

	public void deleteProduct(List<Map<String, Integer>> body) {
		log.info("In ProductService class deleteProduct().");
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

			MasterProduct product;
			product = repository.findOne(map.get("prodId"));

			if (product.getFkStatusId() != null) {
				String currentStatusName = null;

				currentStatusName = statusMap.get(product.getFkStatusId());
				log.info("Curerent Status Name : ");
				log.info(currentStatusName);
				if (currentStatusName.equals(Constants.ACTIVE)) {
					product.setFkStatusId(inactiveStatusId);
					repository.saveAndFlush(product);

				} else if (currentStatusName.equals(Constants.INACTIVE)) {

					product.setFkStatusId(activeStatusId);
					repository.saveAndFlush(product);
				} else if (currentStatusName.equals(Constants.DRAFT)) {
					repository.delete(product);
				}

			}

		}

	}

	public List<ProductDto> readBasicDetails(Model model) {

		ViewDto view = getViewDetails(ProductController.class);
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		List<ProductDto> newList = new ArrayList<>();
		List<String> filterList = view.getFiltersArray();
		ProductSpecificationsBuilder builder = new ProductSpecificationsBuilder();
		for (int filterSize = 0; filterSize < filterList.size(); filterSize++) {
			builder.with(filterList.get(filterSize).substring(0, filterList.get(filterSize).indexOf("=")),
					filterList.get(filterSize).substring(filterList.get(filterSize).indexOf("=") + 1));
		}
		if (model != null) {
			Map<String, Object> params = model.asMap();
			if (params.get(Constants.STATUS) != null) {
				Optional<Integer> statusId = statusMap.entrySet().stream()
						.filter(e -> e.getValue().equalsIgnoreCase((String) params.get("status")))
						.map(Map.Entry::getKey).findFirst();
				if (statusId.isPresent()) {
					params.put("fkStatusId", statusId.get());
					params.remove(Constants.STATUS);
				}

			}
			params.forEach((attribute, value) -> {
				builder.with(attribute, value);
			});
		}

		Specification<MasterProduct> spec = builder.build();
		Pageable pageRequest = ListViewUtil.createPageRequest(view);
		log.info("===Pageable object created from view object===");
		List<MasterProduct> allProducts = repository.findAll(spec, pageRequest).getContent();
		for (MasterProduct product : allProducts) {
			ProductDto productDto = product.convertBasicEntityToDto();
			newList.add(productDto);
		}

		return newList;
	}

}

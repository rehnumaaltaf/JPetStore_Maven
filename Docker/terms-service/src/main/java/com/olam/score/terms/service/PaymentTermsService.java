package com.olam.score.terms.service;

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
import com.olam.score.terms.controller.PaymentTermsController;
import com.olam.score.terms.domain.MasterPaymentBasisRef;
import com.olam.score.terms.domain.MasterPaymentExternalMapping;
import com.olam.score.terms.domain.MasterPaymentTerm;
import com.olam.score.terms.domain.MasterPaymentTermBase;
import com.olam.score.terms.dto.BasePaymentDto;
import com.olam.score.terms.dto.DisplayValue;
import com.olam.score.terms.dto.ExternalMappingDto;
import com.olam.score.terms.dto.ExternalSystemRefDto;
import com.olam.score.terms.dto.PaymentBasisRefDto;
import com.olam.score.terms.dto.PaymentTermsDto;
import com.olam.score.terms.repository.BasePaymentRepository;
import com.olam.score.terms.repository.PaymentBasisRefRepository;
import com.olam.score.terms.repository.PaymentExternalMappingRefRepository;
import com.olam.score.terms.repository.PaymentTermsRepository;
import com.olam.score.terms.util.PaymentSpecificationsBuilder;

@Service
public class PaymentTermsService {
	
	private final class MapPaymentTerm extends PropertyMap<MasterPaymentTerm, PaymentTermsDto> {
		@Override
		protected void configure() {
		    map().setAtSightUsanceFlagId(source.getAtSightUsanceFlagId());
		    map().setConfirmedNonconfirmedFlagId(source.getConfirmedNonConfirmedFlagId());
		    map().setPaymentTermBaseId(source.getFkPaymentTermBaseId());
		    map().setRevocableIrrevocableFlagId(source.getRevocableIrrevocableFlagId());
		    map().setPaymentBasisRefId(source.getFkPaymentBasisRefId());
		    map().setAtSightUsance(null);
		    map().setConfirmedNonconfirmedFlag(null);
		    map().setPaymentTermBase(null);
		    map().setRevocableIrrevocableFlag(null);
		    map().setPaymentBasisRef(null);
		    map().setPrintDescription(source.getPrintDesc());
		}
	}
	
	private final class MapMasterPaymentTable extends PropertyMap<PaymentTermsDto,MasterPaymentTerm> {
		@Override
		protected void configure() {
			map().setPkPaymentTermId(source.getPaymentTermId());
			map().setAtSightUsanceFlagId(source.getAtSightUsanceFlagId());
			map().setConfirmedNonConfirmedFlagId(source.getConfirmedNonconfirmedFlagId());
			map().setFkPaymentTermBaseId(source.getPaymentTermBaseId());
			map().setRevocableIrrevocableFlagId(source.getRevocableIrrevocableFlagId());
			map().setFkPaymentBasisRefId(source.getPaymentBasisRefId());
			map().setFkStatusId(source.getStatusId());
			map().setPayTermDueDays(source.getDueDays());
			map().setPayTermContingencyDays(source.getContingencyDays());
			map().setPrintDesc(source.getPrintDescription());
		}
	}
	
	
	private final class MapMasterPaymentExternalTable extends PropertyMap<ExternalMappingDto,MasterPaymentExternalMapping> {
		@Override
		protected void configure() {
			map().setPkPaymentExternalMappingId(source.getTableMappingId());
			map().setFkExternalSystemRefId(source.getExternalSystemRefId());
			//map().setCustomAttribute1(source.getAttribute1());
			map().setCustomAttribute2(source.getAttribute2());
			map().setCustomAttribute3(source.getAttribute3());
		}
	}
	
	private final class MapMasterPaymentExternalRefTable extends PropertyMap<MasterPaymentExternalMapping,ExternalMappingDto> {
		@Override
		protected void configure() {
			map().setTableMappingId(source.getPkPaymentExternalMappingId());
			map().setPaymentTermId(source.getFkPaymentTermId().getPkPaymentTermId());
			map().setAttribute1(source.getCustomAttribute1());
			map().setAttribute2(source.getCustomAttribute2());
			map().setAttribute3(source.getCustomAttribute3());
			map().setExternalSystemRefId(source.getFkExternalSystemRefId());
			map().setPaymentTerm(null);
		}
	}

	@Autowired 
	private PaymentTermsRepository repository;
	
	@Autowired 
	private BasePaymentRepository basePaymentRepository;
	
	@Autowired 
	private PaymentBasisRefRepository paymentBasisRefRepository;
	
	@Autowired 
	private PaymentExternalMappingRefRepository externalMappingBasisRefRepository;
	
	@Value("${terms.name}")
	private String termsName;
	
	@Value("${finance.name}")
	private String financeName;
	
	@Autowired
	private WebServiceCallUtil webServiceCall;
	
	@Autowired
	private PaymentBasisRefService refService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ListViewUtil listview;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Transactional
	public List<PaymentTermsDto> readAll(Model model) {
		ViewDto view = getViewDetails(PaymentTermsController.class);
		List<PaymentTermsDto> paymentTermsList = new ArrayList<>();
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		List<String> filterList = view.getFiltersArray();
		PaymentSpecificationsBuilder builder = new PaymentSpecificationsBuilder();
		for (int filterSize = 0; filterSize < filterList.size(); filterSize++) {
			builder.with(filterList.get(filterSize).substring(0, filterList.get(filterSize).indexOf(Constants.EQUAL)),
					filterList.get(filterSize).substring(filterList.get(filterSize).indexOf(Constants.EQUAL) + 1));
		}
		if(model!=null){
			Map<String,Object> params=model.asMap();
			if (params.get(Constants.STATUS) != null) {
				Optional<Integer> statusId = statusMap.entrySet().stream().filter(e -> e.getValue().equalsIgnoreCase((String) params.get("status")))
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
		Specification<MasterPaymentTerm> spec = builder.build();
		Pageable pageRequest = ListViewUtil.createPageRequest(view);
		log.info("===Pageable object created from view object===");
		List<MasterPaymentTerm> findAll = repository.findAll(spec,pageRequest).getContent();
		for (MasterPaymentTerm obj : findAll) {
			PaymentTermsDto paymentTerm = new PaymentTermsDto();
			if(obj.getFkPaymentTermBaseId()!=null){
				MasterPaymentTermBase basePaymentTerm = basePaymentRepository.findOne(obj.getFkPaymentTermBaseId());
				paymentTerm.setBasePaymentTermCode(basePaymentTerm.getTermBaseCode());
			}
			paymentTerm.setPaymentTermId(obj.getPkPaymentTermId());
			paymentTerm.setPayTermCode(obj.getPayTermCode());
			paymentTerm.setPayTermName(obj.getPayTermName());
			paymentTerm.setStatusName(statusMap.get(obj.getFkStatusId()));
			if(obj.getFkPaymentBasisRefId()!=null){
				MasterPaymentBasisRef paymentBasisRef = paymentBasisRefRepository.findOne(obj.getFkPaymentBasisRefId());
				paymentTerm.setPaymentBasisRef(paymentBasisRef.getPaymentBasisName());
			}
			paymentTerm.setDueDays(obj.getPayTermDueDays());
			paymentTerm.setContingencyDays(obj.getPayTermContingencyDays());
			paymentTerm.setStatusId(obj.getFkStatusId());
			paymentTermsList.add(paymentTerm);
		}
		return paymentTermsList;

	}
	
	@Transactional
	public PaymentTermsDto readById(Integer id) {
		
		TypeMap<MasterPaymentTerm, PaymentTermsDto> typeMap = modelMapper.getTypeMap(MasterPaymentTerm.class, PaymentTermsDto.class);
		if (typeMap == null) {
			modelMapper.addMappings(new MapPaymentTerm());
		}
		MasterPaymentTerm paymentTermRow = repository.findOne(id);
		PaymentTermsDto paymentTerm = null;
		TypeMap<MasterPaymentExternalMapping,ExternalMappingDto> typeMapExternal = modelMapper.getTypeMap(MasterPaymentExternalMapping.class, ExternalMappingDto.class);
		if (typeMapExternal == null) {
			modelMapper.addMappings(new MapMasterPaymentExternalRefTable());
		}
		if(paymentTermRow!=null){
			paymentTerm = modelMapper.map(paymentTermRow, PaymentTermsDto.class);
			//statusName
			Map<Integer, String> statusMap = webServiceCall.getAllStatus();
			paymentTerm.setStatusName(statusMap.get(paymentTerm.getStatusId()));
			//paymentBasisRef
			MasterPaymentBasisRef paymentBasisRef = paymentBasisRefRepository.findOne(paymentTermRow.getFkPaymentBasisRefId());
			if(paymentBasisRef!=null){
				paymentTerm.setPaymentBasisRef(paymentBasisRef.getPaymentBasisName());
			}
			//paymentTermBase
			if(paymentTermRow.getFkPaymentTermBaseId()!=null){
				MasterPaymentTermBase basePaymentTerm = basePaymentRepository.findOne(paymentTermRow.getFkPaymentTermBaseId());
				if(basePaymentTerm!=null){
					paymentTerm.setPaymentTermBase(basePaymentTerm.getTermBaseName());
					paymentTerm.setBasePaymentTermCode(basePaymentTerm.getTermBaseCode());
				}
			}
			
			//atSightUsance
			
			if(paymentTerm.getAtSightUsanceFlagId()!=null){
				String atSightUsance = paymentTerm.getAtSightUsanceFlagId()==1?"At Sight":"Usance";
				paymentTerm.setAtSightUsance(atSightUsance);
			}
			
			//revocableIrrevocableFlag
			
			if(paymentTerm.getRevocableIrrevocableFlagId()!=null){
				String revocableIrrevocable = paymentTerm.getRevocableIrrevocableFlagId()==1?"Revocable":"Irrevocable";
				paymentTerm.setRevocableIrrevocableFlag(revocableIrrevocable);
			}
			
			//confirmedNonconfirmedFlag
			
			if(paymentTerm.getConfirmedNonconfirmedFlagId()!=null){
				String confirmedNonconfirmed = paymentTerm.getConfirmedNonconfirmedFlagId()==1?"Confirmed":"Nonconfirmed";
				paymentTerm.setConfirmedNonconfirmedFlag(confirmedNonconfirmed);
			}
			for(ExternalMappingDto dto:paymentTerm.getExternalMappingCollection()){
				dto.setExternalSystemName(getExternalSystemName(dto.getExternalSystemRefId()));
			}
			
		}
		return paymentTerm;
	}
	
	private String getExternalSystemName(Integer externalSystemRefId) {
		
		String externalSystemName = null;
		ObjectMapper map = new ObjectMapper();
		ResponseEntity<ResponseData> serviceCall = webServiceCall.internalServiceCall(financeName, "/finance/glservice/gl/externalsystemref");
		List<ExternalSystemRefDto> externalSystemRefList = map.convertValue(serviceCall.getBody().getBody(), new TypeReference<List<ExternalSystemRefDto>>(){});
		for(ExternalSystemRefDto externalRef:externalSystemRefList){
			if(externalRef.getExternalSystemRefId() == externalSystemRefId){
				externalSystemName =  externalRef.getExternalSystemRefName();
			}
		}
		return externalSystemName;
	}

	@Transactional
	public PaymentTermsDto create(PaymentTermsDto paymentTerm) {
		MasterPaymentTerm paymentTermRow;
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		Optional<Integer> firstKey = statusMap.entrySet().stream()
                .filter(entry -> Objects.equals(entry.getValue(), paymentTerm.getStatusName()))
                .map(Map.Entry::getKey).findFirst();
		if (firstKey.isPresent()) {
            paymentTerm.setStatusId(firstKey.get());
        }
		MasterPaymentTerm paymentTermCode = repository.findByName(paymentTerm.getPayTermCode());
		MasterPaymentTerm paymentTermName = repository.findByName(paymentTerm.getPayTermName());
		
		List<ScoreBaseExceptionResponse> errorBeans = new ArrayList<>();
		
		if (paymentTermCode != null || paymentTermName != null) {
			if (paymentTermCode != null) {
				log.error("===Duplicate Payment term Code found in Database===");
				ErrorMessageUtil.errorHandler(new Object[] { paymentTerm.getPayTermCode() }, errorBeans,
						Constants.ERROR_DUPLICATE_PAYTERM_CODE, Constants.ERROR_TYPE_DUPLICATE_CODE);
			}
			if (paymentTermName != null) {
				log.error("===Duplicate Payment term Name found in Database===");
				ErrorMessageUtil.errorHandler(new Object[] { paymentTerm.getPayTermName() }, errorBeans,
						Constants.ERROR_DUPLICATE_PAYTERM_NAME, Constants.ERROR_TYPE_DUPLICATE_NAME);
			}
			throw new ScoreBaseException(errorBeans, HttpStatus.CONFLICT);
		}else{
			
			TypeMap<PaymentTermsDto,MasterPaymentTerm> typeMap = modelMapper.getTypeMap(PaymentTermsDto.class, MasterPaymentTerm.class);
			if (typeMap == null) {
				modelMapper.addMappings(new MapMasterPaymentTable());
			}
			paymentTermRow = modelMapper.map(paymentTerm, MasterPaymentTerm.class);
			List<MasterPaymentExternalMapping> list = new ArrayList<>();
			TypeMap<ExternalMappingDto,MasterPaymentExternalMapping> typeMapEx = modelMapper.getTypeMap(ExternalMappingDto.class, MasterPaymentExternalMapping.class);
			if (typeMapEx == null) {
				modelMapper.addMappings(new MapMasterPaymentExternalTable());
			}
			for(ExternalMappingDto obj:paymentTerm.getExternalMappingCollection()){
				MasterPaymentExternalMapping map = modelMapper.map(obj, MasterPaymentExternalMapping.class);
				map.setFkPaymentTermId(paymentTermRow);
				map.setFkExternalSystemRefId(obj.getExternalSystemRefId());
				map.setPkPaymentExternalMappingId(null);
				list.add(map);
			}
			paymentTermRow.setMasterPaymentExternalMappingCollection(list);
			paymentTermRow.setModifiedDate(new Timestamp(System.currentTimeMillis()));
			MasterPaymentTerm savedPaymentTerm = repository.saveAndFlush(paymentTermRow);
			paymentTerm.setPaymentTermId(savedPaymentTerm.getPkPaymentTermId());
		}
		
		return paymentTerm;
	}

	public PaymentTermsDto getDataForCreate() {
		PaymentTermsDto paymentTermsDto = new PaymentTermsDto();
		getBasePaymentTerms(paymentTermsDto);
		getAtSightUsance(paymentTermsDto);
		getRevocableIrrevocable(paymentTermsDto);
		getConfirmedNonConfirmed(paymentTermsDto);
		getBasisRef(paymentTermsDto);
		getDestinationSystem(paymentTermsDto);
		return paymentTermsDto;
	}

	private void getBasePaymentTerms(PaymentTermsDto paymentTermsDto) {
		
		ObjectMapper map = new ObjectMapper();
		
		ResponseEntity<ResponseData> serviceCall = webServiceCall.internalServiceCall(termsName, "/terms/basepaymentservice/basepayment");
		List<BasePaymentDto> basePaymentTermList = map.convertValue(serviceCall.getBody().getBody(), new TypeReference<List<BasePaymentDto>>(){});
		List<DisplayValue> basePaymentTermMap = new ArrayList<>();
		for(BasePaymentDto basePaymentDto:basePaymentTermList){
			if(Constants.ACTIVE.equalsIgnoreCase(basePaymentDto.getStatusName())) {
				basePaymentTermMap.add(new DisplayValue(basePaymentDto.getBaseTermId(),basePaymentDto.getBaseTermCode(),basePaymentDto.getBaseTermLCBased()));
			}
		}
		paymentTermsDto.setBasePaymentTermValues(basePaymentTermMap);
	}
	
	private void getDestinationSystem(PaymentTermsDto paymentTermsDto) {
		
		ObjectMapper map = new ObjectMapper();
		
		ResponseEntity<ResponseData> serviceCall = webServiceCall.internalServiceCall(financeName, "/finance/glservice/gl/externalsystemref");
		List<ExternalSystemRefDto> externalSystemRefList = map.convertValue(serviceCall.getBody().getBody(), new TypeReference<List<ExternalSystemRefDto>>(){});
		List<DisplayValue> externalSystemRef = new ArrayList<>();
		for(ExternalSystemRefDto externalSystemrefDto:externalSystemRefList){
			externalSystemRef.add(new DisplayValue(externalSystemrefDto.getExternalSystemRefId()
					,externalSystemrefDto.getExternalSystemRefName()));
		}
		paymentTermsDto.setExternalSystemRefValues(externalSystemRef);
	}

	private void getBasisRef(PaymentTermsDto paymentTermsDto) {
		
		List<PaymentBasisRefDto> paymentBasisRefDto = refService.readAll();
		paymentTermsDto.setPaymentBasisRefValues(paymentBasisRefDto);
		
	}

	private void getConfirmedNonConfirmed(PaymentTermsDto paymentTermsDto) {
		List<DisplayValue> confirmedNonConfirmed = new ArrayList<>();
		confirmedNonConfirmed.add(new DisplayValue(1,"Confirmed"));
		confirmedNonConfirmed.add(new DisplayValue(2,"NonConfirmed"));
		paymentTermsDto.setConfirmedNonconfirmedValues(confirmedNonConfirmed);
	}

	private void getRevocableIrrevocable(PaymentTermsDto paymentTermsDto) {
		List<DisplayValue> revocableIrrevocable = new ArrayList<>();
		revocableIrrevocable.add(new DisplayValue(1,"Revocable"));
		revocableIrrevocable.add(new DisplayValue(2,"Irrevocable"));
		paymentTermsDto.setRevocableIrrevocableValues(revocableIrrevocable);
	}

	private void getAtSightUsance(PaymentTermsDto paymentTermsDto) {
		List<DisplayValue> atSightUsance = new ArrayList<>();
		atSightUsance.add(new DisplayValue(1,"At Sight"));
		atSightUsance.add(new DisplayValue(2,"Usance"));
		paymentTermsDto.setAtSightUsanceValues(atSightUsance);
	}
	
	
	public PaymentTermsDto delete(Integer paymentTermId, String action) {
		MasterPaymentTerm paymentTerm = repository.findOne(paymentTermId);
		PaymentTermsDto paymentTermDto = new PaymentTermsDto();
		paymentTermDto.setPaymentTermId(paymentTerm.getPkPaymentTermId());
		try {
			if (action.equals(Constants.ACTIVE)) {
				// Change the status to Inactive
				Map<Integer, String> statusMap = webServiceCall.getAllStatus();
				Optional<Integer> firstKey = statusMap.entrySet().stream()
		                .filter(entry -> Objects.equals(entry.getValue(), Constants.INACTIVE))
		                .map(Map.Entry::getKey).findFirst();
				if (firstKey.isPresent()) {
		            paymentTerm.setFkStatusId(firstKey.get());
		        }
				paymentTermDto.setStatusId(paymentTerm.getFkStatusId());
				repository.saveAndFlush(paymentTerm);
			} else if (action.equalsIgnoreCase("Draft")) {
				repository.delete(paymentTerm);
			}
		} catch (Exception e) {
			log.error("Could not delete the role since there are users assigned to this group");
		}
		return paymentTermDto;
	}
	
	@Transactional
	public PaymentTermsDto update(PaymentTermsDto paymentTermDto) {
		try{
		TypeMap<PaymentTermsDto,MasterPaymentTerm> typeMap = modelMapper.getTypeMap(PaymentTermsDto.class, MasterPaymentTerm.class);
		if (typeMap == null) {
			modelMapper.addMappings(new MapMasterPaymentTable());
		}	
		MasterPaymentTerm paymentTermCode = repository.findByName(paymentTermDto.getPayTermCode());
		MasterPaymentTerm paymentTermName = repository.findByName(paymentTermDto.getPayTermName());
		
		List<ScoreBaseExceptionResponse> errorBeans = new ArrayList<>();
		
		if ((paymentTermCode != null && !paymentTermCode.getPkPaymentTermId().equals(paymentTermDto.getPaymentTermId())) || (paymentTermName != null && !paymentTermName.getPkPaymentTermId().equals(paymentTermDto.getPaymentTermId()))) {
			if (paymentTermCode != null && !paymentTermCode.getPkPaymentTermId().equals(paymentTermDto.getPaymentTermId())) {
				log.error("===Duplicate Payment term Code found in Database===");
				ErrorMessageUtil.errorHandler(new Object[] { paymentTermDto.getPayTermCode() }, errorBeans,
						Constants.ERROR_DUPLICATE_PAYTERM_CODE, Constants.ERROR_TYPE_DUPLICATE_CODE);
			}
			if (paymentTermName != null&& !paymentTermName.getPkPaymentTermId().equals(paymentTermDto.getPaymentTermId())) {
				log.error("===Duplicate Payment term Name found in Database===");
				ErrorMessageUtil.errorHandler(new Object[] { paymentTermDto.getPayTermName() }, errorBeans,
						Constants.ERROR_DUPLICATE_PAYTERM_NAME, Constants.ERROR_TYPE_DUPLICATE_NAME);
			}
			throw new ScoreBaseException(errorBeans, HttpStatus.CONFLICT);
		}
		

		MasterPaymentTerm updatedPaymentTerm = modelMapper.map(paymentTermDto, MasterPaymentTerm.class);
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		Optional<Integer> firstKey = statusMap.entrySet().stream()
                .filter(entry -> Objects.equals(entry.getValue(), paymentTermDto.getStatusName()))
                .map(Map.Entry::getKey).findFirst();
		if (firstKey.isPresent()) {
			updatedPaymentTerm.setFkStatusId(firstKey.get());
        }
		List<MasterPaymentExternalMapping> updatedList = new ArrayList<>();
		TypeMap<ExternalMappingDto,MasterPaymentExternalMapping> typeMapEx = modelMapper.getTypeMap(ExternalMappingDto.class, MasterPaymentExternalMapping.class);
		if (typeMapEx == null) {
			modelMapper.addMappings(new MapMasterPaymentExternalTable());
		}
		for(ExternalMappingDto updatedMapping :paymentTermDto.getExternalMappingCollection()){
			MasterPaymentExternalMapping updatedExternalMapping = modelMapper.map(updatedMapping, 
					MasterPaymentExternalMapping.class);
			updatedExternalMapping.setFkPaymentTermId(updatedPaymentTerm);
			updatedList.add(updatedExternalMapping);
			//externalMappingBasisRefRepository.saveAndFlush(updatedExternalMapping);
		}
		updatedPaymentTerm.setMasterPaymentExternalMappingCollection(updatedList);
		updatedPaymentTerm.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		repository.saveAndFlush(updatedPaymentTerm);
		for(Integer deletedId:paymentTermDto.getDeletedExternalMappingId()){
			MasterPaymentExternalMapping findOne = externalMappingBasisRefRepository.findOne(deletedId);
			if(findOne!=null){
				externalMappingBasisRefRepository.delete(deletedId);
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return paymentTermDto;
	}
	
	public ViewDto getViewDetails(Class<? extends Object> currentfeature) {
		String featureId = currentfeature.getAnnotation(Mapping.class).featureId();
		ViewDto viewdto = listview.getView(featureId);
		log.info("===viewdto from common has been generated for JSON===");
		PageDto pageDto = new PageDto();
		pageDto.setPageNumber(0);
		pageDto.setPageSize(Constants.PAGESIZE);
		pageDto.setTotalCount(repository.count());
		pageDto.setOperation("Client");//To be set from Database
		viewdto.setPage(pageDto);
		Class<PaymentTermsDto> uomDto = PaymentTermsDto.class;
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

}

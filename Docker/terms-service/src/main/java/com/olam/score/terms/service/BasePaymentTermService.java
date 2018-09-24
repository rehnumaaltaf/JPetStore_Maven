package com.olam.score.terms.service;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.olam.score.common.constants.Constants;
import com.olam.score.common.dto.PageDto;
import com.olam.score.common.dto.ViewDto;
import com.olam.score.common.util.EnumStatus;
import com.olam.score.common.util.ErrorMessageUtil;
import com.olam.score.common.util.ListViewUtil;
import com.olam.score.common.util.Mapping;
import com.olam.score.common.util.ScoreBaseException;
import com.olam.score.common.util.ScoreBaseExceptionResponse;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.terms.controller.BasePaymentTermController;
import com.olam.score.terms.domain.MasterPaymentNegotiation;
import com.olam.score.terms.domain.MasterPaymentTermBase;
import com.olam.score.terms.domain.MasterPaymentTermBaseNego;
import com.olam.score.terms.dto.BasePaymentDto;
import com.olam.score.terms.dto.NegotiationDto;
import com.olam.score.terms.repository.BasePaymentNegoRepository;
import com.olam.score.terms.repository.BasePaymentRepository;
import com.olam.score.terms.repository.NegotiationRepository;
import com.olam.score.terms.util.BasePaymentSpecificationsBuilder;

@Service
public class BasePaymentTermService {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private WebServiceCallUtil webServiceCall;
	@Autowired
	private ListViewUtil listview;
	@Autowired
	private BasePaymentRepository basePayementRep;
	@Autowired
	private NegotiationRepository negotiationRepository;
	@Autowired
	private BasePaymentNegoRepository basePayementNegoRep;
	@Transactional(readOnly=true)
	public List<BasePaymentDto> readAll(Model model) {
		ViewDto view = getViewDetails(BasePaymentTermController.class);
		List<BasePaymentDto> newList = new ArrayList<>();
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		List<String> filterList = view.getFiltersArray();
		BasePaymentSpecificationsBuilder builder = new BasePaymentSpecificationsBuilder();
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
		Specification<MasterPaymentTermBase> spec = builder.build();
		Pageable pageRequest = ListViewUtil.createPageRequest(view);
		log.info("===Pageable object created from view object===");
		List<MasterPaymentTermBase> basePaymentList = basePayementRep.findAll(spec,pageRequest).getContent();
		List<MasterPaymentTermBaseNego> paymentNegoList=basePayementNegoRep.findAll();
		for (MasterPaymentTermBase basePayment : basePaymentList) {
			List<NegotiationDto> negoList=new ArrayList<>();
			for (MasterPaymentTermBaseNego paymentBaseNego : paymentNegoList) {
				if (basePayment.getPkPaymentTermBaseId() == paymentBaseNego.getFkPaymentTermBaseId().getPkPaymentTermBaseId()) {
					NegotiationDto negotiationDto=new NegotiationDto();
					negotiationDto.setNagotiationCode(paymentBaseNego.getFkPaymentNegotiationId().getPayNegoCode());
					negotiationDto.setNagotiationName(paymentBaseNego.getFkPaymentNegotiationId().getPayNegoName());
					negotiationDto.setNagotiationTermId(paymentBaseNego.getFkPaymentNegotiationId().getPkPaymentNegotiationId());
					negotiationDto.setPrintDescription(paymentBaseNego.getFkPaymentNegotiationId().getPayNegoDesc());
					negoList.add(negotiationDto);
				}
			}
			BasePaymentDto basePaymentDto=basePayment.convertEntityToDto(statusMap,negoList);
			newList.add(basePaymentDto);
		}
		return newList;
	}
	@Transactional(readOnly=true)
	public BasePaymentDto readById(Integer id) {
		List<NegotiationDto> negoList=new ArrayList<>();
		MasterPaymentTermBase basePayment = basePayementRep.findOne(id);
		List<MasterPaymentTermBaseNego> paymentNegoList=basePayementNegoRep.findByFkPaymentTermBaseId(basePayment);
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		for (MasterPaymentTermBaseNego paymentBaseNego : paymentNegoList) {
			if (basePayment.getPkPaymentTermBaseId() == paymentBaseNego.getFkPaymentTermBaseId().getPkPaymentTermBaseId()) {
				NegotiationDto negotiationDto=new NegotiationDto();
				negotiationDto.setNagotiationCode(paymentBaseNego.getFkPaymentNegotiationId().getPayNegoCode());
				negotiationDto.setNagotiationName(paymentBaseNego.getFkPaymentNegotiationId().getPayNegoName());
				negotiationDto.setNagotiationTermId(paymentBaseNego.getFkPaymentNegotiationId().getPkPaymentNegotiationId());
				negotiationDto.setPrintDescription(paymentBaseNego.getFkPaymentNegotiationId().getPayNegoDesc());
				negoList.add(negotiationDto);
			}
		}
		return basePayment.convertEntityToDto(statusMap,negoList);
	}
	public ViewDto getViewDetails(Class<? extends Object> currentfeature) {
		String featureId = currentfeature.getAnnotation(Mapping.class).featureId();
		ViewDto viewdto = listview.getView(featureId);
		log.info("===viewdto from common has been generated for JSON===");
		PageDto pageDto = new PageDto();
		pageDto.setPageNumber(0);
		pageDto.setPageSize(Constants.PAGESIZE);
		pageDto.setTotalCount(basePayementRep.count());
		pageDto.setOperation("Client");//To be set from Database
		viewdto.setPage(pageDto);
		Class<BasePaymentDto> uomDto = BasePaymentDto.class;
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
	public BasePaymentDto create(BasePaymentDto basePaymentDto) {
		MasterPaymentTermBase basePayment;
		List<NegotiationDto> negoList=basePaymentDto.getBaseNagotiationTerm();
		List<NegotiationDto> reponseNegoList=new ArrayList<>();
		String action =basePaymentDto.getAction();
		if (action.equalsIgnoreCase(Constants.DRAFT)) {
			log.info("===draft action to perform for create ===");
			basePaymentDto.setStatusName(Constants.DRAFT);
		}else if (action.equalsIgnoreCase(Constants.SAVE)) {
			log.info("===save(active) action to perform for create ===");
			basePaymentDto.setStatusName(Constants.ACTIVE);
		}
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		
		MasterPaymentTermBase basePaymentCode = basePayementRep.findByTermBaseCode(basePaymentDto.getBaseTermCode());
		MasterPaymentTermBase basePaymentName = basePayementRep.findByTermBaseName(basePaymentDto.getBaseTermName());
		
		List<ScoreBaseExceptionResponse> errorBeans = new ArrayList<>();
		if(basePaymentCode != null || basePaymentName != null){
			if (basePaymentCode != null) {
				log.error("===Duplicate Base Payment Code found in Database===");
				ErrorMessageUtil.errorHandler(new Object[] { basePaymentDto.getBaseTermCode() }, errorBeans,
						Constants.ERROR_DUPLICATE_BASE_PAYTERM_CODE, Constants.ERROR_TYPE_DUPLICATE_CODE);
			}
			if (basePaymentName != null) {
				log.error("===Duplicate Base Payment Name found in Database===");
				ErrorMessageUtil.errorHandler(new Object[] { basePaymentDto.getBaseTermName() }, errorBeans,
						Constants.ERROR_DUPLICATE_BASE_PAYTERM_NAME, Constants.ERROR_TYPE_DUPLICATE_NAME);
			}
			throw new ScoreBaseException(errorBeans, HttpStatus.CONFLICT);
		} else {
			basePayment=basePayementRep.saveAndFlush(generateBasePayment(basePaymentDto,Constants.CREATE,statusMap));
			for(NegotiationDto negotiationDto :negoList){
				MasterPaymentNegotiation paymentNego;
				paymentNego=negotiationRepository.saveAndFlush(generateNegotiation(negotiationDto,Constants.CREATE));
				if(paymentNego!=null){
				basePayementNegoRep.saveAndFlush(generateNegotiationPaymentAsso(basePayment,paymentNego));
				reponseNegoList.add(paymentNego.convertEntityToDto());
				}
			}
			return basePayment.convertEntityToDto(statusMap,reponseNegoList);

		}
		
	}
	@Transactional
	public List<BasePaymentDto> update(List<BasePaymentDto> body) {
		List<BasePaymentDto> paymentDtoList=new ArrayList<>();
		MasterPaymentTermBase basePayment;
		List<NegotiationDto> negoList;
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		for(BasePaymentDto basePaymentDto:body){
			MasterPaymentTermBase basePaymentCode = basePayementRep.findByTermBaseCode(basePaymentDto.getBaseTermCode());
			MasterPaymentTermBase basePaymentName = basePayementRep.findByTermBaseName(basePaymentDto.getBaseTermName());
			List<ScoreBaseExceptionResponse> errorBeans = new ArrayList<>();
			if((basePaymentCode != null && !basePaymentCode.getPkPaymentTermBaseId().equals(basePaymentDto.getBaseTermId())) || (basePaymentName != null && !basePaymentName.getPkPaymentTermBaseId().equals(basePaymentDto.getBaseTermId()))){
				if (basePaymentCode != null && !basePaymentCode.getPkPaymentTermBaseId().equals(basePaymentDto.getBaseTermId())) {
					log.error("===Duplicate Base Payment Code found in Database===");
					ErrorMessageUtil.errorHandler(new Object[] { basePaymentDto.getBaseTermCode() }, errorBeans,
							Constants.ERROR_DUPLICATE_BASE_PAYTERM_CODE, Constants.ERROR_TYPE_DUPLICATE_CODE);
				}
				if (basePaymentName != null && !basePaymentName.getPkPaymentTermBaseId().equals(basePaymentDto.getBaseTermId())) {
					log.error("===Duplicate Base Payment Name found in Database===");
					ErrorMessageUtil.errorHandler(new Object[] { basePaymentDto.getBaseTermName() }, errorBeans,
							Constants.ERROR_DUPLICATE_BASE_PAYTERM_NAME, Constants.ERROR_TYPE_DUPLICATE_NAME);
				}
				throw new ScoreBaseException(errorBeans, HttpStatus.CONFLICT);
			} 
			basePayment=basePayementRep.saveAndFlush(generateBasePayment(basePaymentDto,Constants.UPDATE,statusMap));
			negoList=basePaymentDto.getBaseNagotiationTerm();
			if(basePaymentDto.getDeletedNego()!=null && basePaymentDto.getDeletedNego().length>0){
				basePayementNegoRep.deleteByBasePaymentId(Arrays.asList(basePaymentDto.getDeletedNego()));
				negotiationRepository.deleteNegotiation(Arrays.asList(basePaymentDto.getDeletedNego()));
			}
			BasePaymentDto dto=basePayment.convertEntityToDto(statusMap,generateresponseNegoList(negoList,basePayment));
			paymentDtoList.add(dto);
			
		}
		return paymentDtoList;

	}
	private List<NegotiationDto> generateresponseNegoList(List<NegotiationDto> negoList, MasterPaymentTermBase basePayment) {
		List<NegotiationDto> reponseNegoList=new ArrayList<>();
		for(NegotiationDto negotiationDto :negoList){
			MasterPaymentNegotiation paymentNego;
			if(negotiationDto.getNagotiationTermId()!=null && negotiationDto.getNagotiationTermId()!=0 ){
			paymentNego=negotiationRepository.saveAndFlush(generateNegotiation(negotiationDto,Constants.UPDATE));
			basePayementNegoRep.saveAndFlush(generateNegotiationPaymentAsso(basePayment,paymentNego));
			}else{
			paymentNego=negotiationRepository.saveAndFlush(generateNegotiation(negotiationDto,Constants.CREATE));	
			}
			reponseNegoList.add(paymentNego.convertEntityToDto());
		}

	
		return reponseNegoList;		 
	}
	private MasterPaymentTermBaseNego generateNegotiationPaymentAsso(MasterPaymentTermBase basePayment,
			MasterPaymentNegotiation paymentNego) {
		MasterPaymentTermBaseNego masterPaymentTermBaseNego=new MasterPaymentTermBaseNego();
		masterPaymentTermBaseNego.setFkStatusId(1);
		masterPaymentTermBaseNego.setCreatedBy("Test");
		masterPaymentTermBaseNego.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		masterPaymentTermBaseNego.setModifiedBy("Test");
		masterPaymentTermBaseNego.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		masterPaymentTermBaseNego.setFkPaymentNegotiationId(paymentNego);
		masterPaymentTermBaseNego.setFkPaymentTermBaseId(basePayment);
		return masterPaymentTermBaseNego;
	}
	private MasterPaymentNegotiation generateNegotiation(NegotiationDto negotiationDto,String action) {
		MasterPaymentNegotiation masterPaymentNegotiation=new MasterPaymentNegotiation();
		masterPaymentNegotiation.setFkStatusId(1);
		masterPaymentNegotiation.setPayNegoCode(negotiationDto.getNagotiationCode());
		masterPaymentNegotiation.setPayNegoDesc(negotiationDto.getPrintDescription());
		masterPaymentNegotiation.setPayNegoName(negotiationDto.getNagotiationName());
		masterPaymentNegotiation.setModifiedBy("Test");
		masterPaymentNegotiation.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		if(Constants.CREATE.equalsIgnoreCase(action)){
		masterPaymentNegotiation.setCreatedBy("Test");
		masterPaymentNegotiation.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		}else if(Constants.UPDATE.equalsIgnoreCase(action)){
		masterPaymentNegotiation.setPkPaymentNegotiationId(negotiationDto.getNagotiationTermId());
		}
		return masterPaymentNegotiation;
	}
	private MasterPaymentTermBase generateBasePayment(BasePaymentDto basePaymentDto, String action, Map<Integer, String> statusMap) {
		MasterPaymentTermBase masterPaymentTermBase=new MasterPaymentTermBase();
		Optional<Integer> statusId = statusMap.entrySet().stream()
				.filter(e -> e.getValue().equals(basePaymentDto.getStatusName())).map(Map.Entry::getKey).findFirst();
		if (statusId.isPresent()) {
			log.info("===Got Status Id in Status map ===");
			masterPaymentTermBase.setFkStatusId(statusId.get());
		}
		masterPaymentTermBase.setIsCreditRisk(basePaymentDto.getBaseTermCreditRisk());
		masterPaymentTermBase.setIsLcBased(basePaymentDto.getBaseTermLCBased());
		masterPaymentTermBase.setTermBaseCode(basePaymentDto.getBaseTermCode());
		masterPaymentTermBase.setTermBaseName(basePaymentDto.getBaseTermName());
		masterPaymentTermBase.setTermBaseDesc(basePaymentDto.getBaseTermDescription());
		if (Constants.CREATE.equalsIgnoreCase(action)) {
			masterPaymentTermBase.setCreatedBy("Test");
			masterPaymentTermBase.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			masterPaymentTermBase.setModifiedBy("Test");
			masterPaymentTermBase.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		}else if (Constants.UPDATE.equalsIgnoreCase(action)) {
			masterPaymentTermBase.setCreatedBy(basePaymentDto.getCreatedBy());
			masterPaymentTermBase.setCreatedDate(basePaymentDto.getCreatedDate());
			masterPaymentTermBase.setModifiedBy("Test");
			masterPaymentTermBase.setModifiedDate(new Timestamp(System.currentTimeMillis()));
			masterPaymentTermBase.setPkPaymentTermBaseId(basePaymentDto.getBaseTermId());
		}
		return masterPaymentTermBase;
	}
	public void deleteBasePaymentTerm(List<Map<String, Integer>> body) {
		for (Map<String, Integer> i : body) {
			i.forEach((attribute,basePaymentTermId) -> {
				MasterPaymentTermBase masterPaymentTermBase=new MasterPaymentTermBase();
				masterPaymentTermBase=basePayementRep.findOne(basePaymentTermId);
				if(masterPaymentTermBase.getPkPaymentTermBaseId()!=null && EnumStatus.ACTIVE.getValue() == masterPaymentTermBase.getFkStatusId()){
					log.info(basePaymentTermId.toString());
					log.info("===Changing Active status to Inactive===");
					masterPaymentTermBase.setFkStatusId(EnumStatus.INACTIVE.getValue());
					basePayementRep.saveAndFlush(masterPaymentTermBase);
				}else if(masterPaymentTermBase.getPkPaymentTermBaseId()!=null && EnumStatus.DRAFT.getValue() == masterPaymentTermBase.getFkStatusId()){
					log.info(basePaymentTermId.toString());
					log.info("===Deleting the draft base payment Term===");
					basePayementNegoRep.deleteAllByBasePaymentId(masterPaymentTermBase.getPkPaymentTermBaseId());
					basePayementRep.delete(masterPaymentTermBase);
				}else if(masterPaymentTermBase.getPkPaymentTermBaseId()!=null && EnumStatus.INACTIVE.getValue() == masterPaymentTermBase.getFkStatusId()){
					log.info(basePaymentTermId.toString());
					log.info("===Changing InActive status to Active===");
					masterPaymentTermBase.setFkStatusId(EnumStatus.ACTIVE.getValue());
					basePayementRep.saveAndFlush(masterPaymentTermBase);
				}
			});
		}
		
	}

}

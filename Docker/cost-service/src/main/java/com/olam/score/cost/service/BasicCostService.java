package com.olam.score.cost.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.olam.score.common.constants.Constants;
import com.olam.score.common.constants.ResponseConstants;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.util.ScoreBaseException;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.cost.domain.MasterCost;
import com.olam.score.cost.domain.MasterCostExternalMapping;
import com.olam.score.cost.domain.MasterCostGlMapping;
import com.olam.score.cost.domain.MasterCostPartyAssoc;
import com.olam.score.cost.dto.MasterCostDto;
import com.olam.score.cost.dto.MasterCostExternalMappingDto;
import com.olam.score.cost.dto.MasterCostPartyAssocDto;
import com.olam.score.cost.repository.MasterCnfCostRepository;
import com.olam.score.cost.repository.MasterCostDefaultValueTypeRefRepository;
import com.olam.score.cost.repository.MasterCostExternalMappingRepository;
import com.olam.score.cost.repository.MasterCostGroupRepository;
import com.olam.score.cost.repository.MasterCostRepository;
import com.olam.score.cost.repository.MasterFreightCostMatrixRepository;
import com.olam.score.cost.repository.MasterWhCostRepositoy;

@Service
public class BasicCostService {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private WebServiceCallUtil serviceCall;

	@Autowired
	private MasterCostRepository masterCostRepository;

	@Autowired
	private MasterCostExternalMappingRepository masterCostExternalMappingRepository;
	@Autowired
	private MasterCostDefaultValueTypeRefRepository masterCostDefaultValueTypeRefRepository;
	@Value("${reference.name}")
	private String url;
	
	@Value("${party.name}")
	private String partyURL;
	
	@Value("${packing.name}")
	private String packingURL;
	@Value("${product.name}")
	private String productURL;
	

	@Autowired
	private MasterCostGroupRepository masterCostGroupRepository;
	
	@Autowired
	private MasterCnfCostRepository masterCnfCostRepository;
	
	@Autowired
	private MasterWhCostRepositoy masterWhCostRepository;
	
	@Autowired
	private MasterFreightCostMatrixRepository masterFreightCostMatrixRepository;

	@Transactional
	public String create(MasterCostDto inputData) throws ParseException {
		String addStatus = ResponseConstants.MASTER_COST_ADD_SUCESS;
		MasterCost masterCost = new MasterCost();
		String action = inputData.getAction();
		if (action.equalsIgnoreCase(Constants.SAVE)) {
			log.info("===draft action to perform for create ===");
			inputData.setFkStatusId(returnStatusIdByName(Constants.DRAFT));
		} else if (action.equalsIgnoreCase(Constants.SUBMIT)) {
			log.info("===save(active) action to perform for create ===");
			inputData.setFkStatusId(returnStatusIdByName(Constants.ACTIVE));
		}

		masterCost = getMappingCost(inputData);
		validate(masterCost, 0);
		masterCostRepository.saveAndFlush(masterCost);
		return addStatus;
	}

	@Transactional
	public String update(MasterCostDto inputData) {
		String addStatus = ResponseConstants.MASTER_COST_UPDATED_SUCESS;

		String action = inputData.getAction();
		if (action.equalsIgnoreCase(Constants.SAVE)) {
			log.info("===draft action to perform for update ===");
			inputData.setFkStatusId(returnStatusIdByName(Constants.DRAFT));
		} else if (action.equalsIgnoreCase(Constants.SUBMIT)) {
			log.info("===save(active) action to perform for updtae ===");
			inputData.setFkStatusId(returnStatusIdByName(Constants.ACTIVE));
		} else if (action.equalsIgnoreCase(Constants.DEACTIVE)) {
			log.info("===save(Deactive) action to perform for update ===");
			inputData.setFkStatusId(returnStatusIdByName(Constants.INACTIVE));
		}
		MasterCost masterCost = getMappingCost(inputData);
		validateEdit(masterCost, inputData.getPkCostId());
		masterCostRepository.saveAndFlush(masterCost);

		return addStatus;
	}
	
	@Transactional
	public String delete(int id) throws ParseException {
		String addStatus = ResponseConstants.MASTER_COST_ADD_SUCESS;
		MasterCost cost=masterCostRepository.findOne(id);
		if(returnStatusIdByName(Constants.DRAFT)==cost.getFkStatusId()) {
			masterCostRepository.delete(id);
		}
		else if(returnStatusIdByName(Constants.ACTIVE)==cost.getFkStatusId()) {
			int status=returnStatusIdByName(Constants.INACTIVE);
			masterCostRepository.deactivateOrReactivateMasterCost(status, cost.getPkCostId());
			masterCostExternalMappingRepository.deactivateOrReactivateCostExternalMapping(status, cost);
		}else if(returnStatusIdByName(Constants.INACTIVE)==cost.getFkStatusId()) {
			int status=returnStatusIdByName(Constants.ACTIVE);
			masterCostRepository.deactivateOrReactivateMasterCost(status, cost.getPkCostId());
			masterCostExternalMappingRepository.deactivateOrReactivateCostExternalMapping(status, cost);
		}
		return addStatus;
	}

	private MasterCost getMappingCost(MasterCostDto inputData) {

		MasterCost masterCost = new MasterCost();
		masterCost.setCostFullname(inputData.getCostFullName());
		if (inputData.getPkCostId() != null) {
			masterCost.setPkCostId(inputData.getPkCostId());
			MasterCost cost = masterCostRepository.findOne(masterCost.getPkCostId());
			masterCost.setCreatedBy(cost.getCreatedBy());
			masterCost.setCreatedDate(cost.getCreatedDate());
			masterCost.setModifiedBy("OLAM");
			masterCost.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		} else {
			masterCost.setCreatedBy("OLAM");
			masterCost.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		}
		masterCost.setCostName(inputData.getCostName());
		if (inputData.getFkCostGroupId() != null && inputData.getFkCostGroupId() > 0)
			masterCost.setFkCostGroupId(masterCostGroupRepository.findOne(inputData.getFkCostGroupId()));
		masterCost.setFkStatusId(inputData.getFkStatusId());
		masterCost.setCostCode(inputData.getCostCode());
		masterCost.setFkIndexUomId(inputData.getFkIndexUomId());
		masterCost.setFkCurrencyId(inputData.getFkCurrencyId());
		masterCost.setModifiedBy(inputData.getModifiedBy());
		masterCost.setModifiedDate(inputData.getModifiedDate());
		masterCost.setFkRevenueGlId(inputData.getFkRevenueGlId());
		masterCost.setRealizedInd(inputData.getRealizedInd());
		masterCost.setFkExpenseGlId(inputData.getFkExpenseGlId());
		if (inputData.getFkcostDefValueTypeId() != null && inputData.getFkcostDefValueTypeId() > 0)
			masterCost.setFkCostDefValueTypeId(
					masterCostDefaultValueTypeRefRepository.findOne(inputData.getFkcostDefValueTypeId()));
		masterCost.setFkMatrixEntityId(inputData.getFkMatrixEntityId());
		masterCost.setCostDefaultValue(inputData.getCostDefaultValue());
		masterCost.setCostTypeIsPrimaryInd(inputData.getCostTypeIsPrimaryInd());
		masterCost.setMatrixApplicableInd(inputData.getMatrixApplicableInd());
		masterCost.setFkDefaultValueBasisRefId(inputData.getFkDefaultValueBasisRefId());
		masterCost.setCapitalizeCostInd(inputData.getCapitalizeCostInd());
		masterCost.setFkDefValSecondaryPackingId(inputData.getFkDefValSecondaryPackingId());
		masterCost.setFkDefaultValueUomId(inputData.getFkDefaultValueUomId());
		masterCost.setFkDefValPrimaryPackingId(inputData.getFkDefValPrimaryPackingId());
		masterCost.setNettedForMtmInd(inputData.getNettedForMtmInd());

		// Mapping the external mapping
		List<MasterCostExternalMapping> masterCostExternalMappingList = new ArrayList<>();
		List<Integer> updatedExMappingList = new ArrayList<>();
		if (inputData.getExterPackAssoc() != null) {
			for (MasterCostExternalMappingDto masterCostExternalMappingDto : inputData.getExterPackAssoc()) {
				MasterCostExternalMapping masterCostExternalMapping = new MasterCostExternalMapping();
				masterCostExternalMapping.setMappingType(masterCostExternalMappingDto.getMappingType());
				masterCostExternalMapping.setCostExterMapAssoc(masterCost);
				masterCostExternalMapping.setCreatedBy(masterCostExternalMappingDto.getCreatedBy());
				masterCostExternalMapping.setCreatedDate(masterCostExternalMappingDto.getCreatedDate());
				masterCostExternalMapping.setModifiedDate(masterCostExternalMappingDto.getModifiedDate());
				masterCostExternalMapping.setFkStatusId(masterCostExternalMappingDto.getFkStatusId());
				masterCostExternalMapping.setExternalCode(masterCostExternalMappingDto.getExternalCode());
				masterCostExternalMapping.setModifiedBy(masterCostExternalMappingDto.getModifiedBy());
				masterCostExternalMapping
						.setFkExternalSystemRefId(masterCostExternalMappingDto.getFkExternalSystemRefId());
				if (masterCostExternalMappingDto.getPkCostExternalMappingId() != null) {
					masterCostExternalMapping
							.setPkCostExternalMappingId(masterCostExternalMappingDto.getPkCostExternalMappingId());
					updatedExMappingList.add(masterCostExternalMappingDto.getPkCostExternalMappingId());
				}
				masterCostExternalMappingList.add(masterCostExternalMapping);

			}
			if (updatedExMappingList.size() > 0) {
				masterCostExternalMappingRepository.deleteByCostExterMapAssocAndPkCostExternalMappingIdNotIn(masterCost,
						updatedExMappingList);
			}
		}

		masterCost.setMasterPackingAssocList(masterCostExternalMappingList);
		return masterCost;
	}

	public int returnStatusIdByName(String name) {
		Map<Integer, String> statusMap = serviceCall.getAllStatus();
		Optional<Integer> statusId = statusMap.entrySet().stream().filter(e -> e.getValue().equals(name))
				.map(Map.Entry::getKey).findFirst();
		if (statusId.isPresent()) {
			log.info("===Got Status Id in Status map ===");
			return statusId.get();
		}
		return 0;
	}

	public String returnStatusNameById(int id) {
		String toReturn = null;
		Map<Integer, String> statusMap = serviceCall.getAllStatus();
		toReturn = statusMap.get(id);
		return toReturn;
	}

	private void validate(MasterCost masterCost, Integer id) {
		if (masterCostRepository.getByCostName(masterCost.getCostName()).size() > 0
				&& masterCostRepository.getByCostCode(masterCost.getCostCode()).size() > 0) {
			throw new ScoreBaseException(id, "Cost name and code already exists in database", HttpStatus.CONFLICT);
		}
		if (masterCostRepository.getByCostName(masterCost.getCostName()).size() > 0) {
			throw new ScoreBaseException(id, "Cost name already exists in database", HttpStatus.CONFLICT);
		}
		if (masterCostRepository.getByCostCode(masterCost.getCostCode()).size() > 0) {
			throw new ScoreBaseException(id, "Cost code already exists in database", HttpStatus.CONFLICT);
		}

	}

	private void validateEdit(MasterCost masterCost, Integer id) {
		if (masterCostRepository.getByCostNameAndPkCostIdNotIn(masterCost.getCostName(), masterCost.getPkCostId())
				.size() > 0
				&& masterCostRepository
						.getByCostCodeAndPkCostIdNotIn(masterCost.getCostCode(), id).size() > 0) {
			throw new ScoreBaseException(id, "Cost name and code already exists in database", HttpStatus.CONFLICT);
		}
		if (masterCostRepository.getByCostNameAndPkCostIdNotIn(masterCost.getCostName(), id)
				.size() > 0) {
			throw new ScoreBaseException(id, "Cost name already exists in database", HttpStatus.CONFLICT);
		}
		if (masterCostRepository.getByCostCodeAndPkCostIdNotIn(masterCost.getCostCode(), id)
				.size() > 0) {
			throw new ScoreBaseException(id, "Cost code already exists in database", HttpStatus.CONFLICT);
		}

	}

	public List<MasterCostDto> readAll() {

		List<MasterCostExternalMappingDto> masterCostExternalMappingDtoList = new ArrayList<>();
		List<MasterCost> masterCostList = masterCostRepository.findAllByOrderByCreatedDateDesc();
		List<MasterCostDto> masterCostDtoList = new ArrayList<>();
		for (MasterCost masterCost : masterCostList) {

			MasterCostDto masterCostDto = new MasterCostDto();
			if (masterCost.getMasterCostGlMappingCollection() != null) {
				Collection<MasterCostGlMapping> costGlMappingList = masterCost.getMasterCostGlMappingCollection();
				for (MasterCostGlMapping masterCostGlMapping : costGlMappingList) {
					MasterCostExternalMappingDto masterCostExternalMappingDto = new MasterCostExternalMappingDto();
					masterCostExternalMappingDto.setFkCostId((masterCostGlMapping.getFkCostId().getPkCostId()));
					masterCostExternalMappingDto.setPkCostExternalMappingId(masterCostGlMapping.getPkCostGlMappingId());
					masterCostExternalMappingDto.setFkExternalSystemRefId(masterCostGlMapping.getFkGlId());
					masterCostExternalMappingDtoList.add(masterCostExternalMappingDto);
				}
			}
			List<MasterCostExternalMapping> masterCostExternalMappingList = new ArrayList<>();

			if (masterCost.getMasterPackingAssocList() != null) {

				for (MasterCostExternalMapping masterCostExternalMapping : masterCostExternalMappingList) {
					MasterCostExternalMappingDto masterCostExternalMappingDto = new MasterCostExternalMappingDto();
					masterCostExternalMappingDto.setExternalCode(masterCostExternalMapping.getExternalCode());
					masterCostExternalMappingDto
							.setFkExternalSystemRefId(masterCostExternalMapping.getFkExternalSystemRefId());
					masterCostExternalMappingDto
							.setFkCostId(masterCostExternalMapping.getCostExterMapAssoc().getPkCostId());
					masterCostExternalMappingDto
							.setPkCostExternalMappingId(masterCostExternalMapping.getPkCostExternalMappingId());
					masterCostExternalMappingDto.setMappingType(masterCostExternalMapping.getMappingType());
					masterCostExternalMappingDtoList.add(masterCostExternalMappingDto);
				}

			}

			masterCostDto.setExterPackAssoc(masterCostExternalMappingDtoList);
			masterCostDto.setCostCode(masterCost.getCostCode());
			masterCostDto.setCostName(masterCost.getCostName());
			masterCostDto.setCostFullName(masterCost.getCostFullname());
			masterCostDto.setPkCostId(masterCost.getPkCostId());
			masterCostDto.setNettedForMtmInd(masterCost.getNettedForMtmInd());
			masterCostDto.setCostTypeIsPrimaryInd(masterCost.getCostTypeIsPrimaryInd());
			masterCostDto.setMatrixApplicableInd(masterCost.getMatrixApplicableInd());
			masterCostDto.setFkRevenueGlId(masterCost.getFkRevenueGlId());
			masterCostDto.setFkExpenseGlId(masterCost.getFkExpenseGlId());
			masterCostDto.setCostDefaultValue(masterCost.getCostDefaultValue());
			masterCostDto.setFkMatrixEntityName(
					masterCostGroupRepository.findOne(masterCost.getFkMatrixEntityId()).getCostGroupName());
			masterCostDto.setFkCostGroupName(
					masterCostGroupRepository.findOne(masterCost.getFkMatrixEntityId()).getCostGroupName());
			if (masterCost.getFkStatusId() != null) {
				masterCostDto.setFkStatusId(masterCost.getFkStatusId());
				masterCostDto.setFkStatusName(returnStatusNameById(masterCost.getFkStatusId()));
			}

			masterCostDtoList.add(masterCostDto);
		}
		return masterCostDtoList;

	}
	
	public MasterCostDto returnBasicDetailsOfCost(int costId) {
		MasterCostDto costDto=new MasterCostDto();
		MasterCost cost=masterCostRepository.findOne(costId);
		costDto.setPkCostId(cost.getPkCostId());
		costDto.setCostName(cost.getCostName());
		costDto.setCostCode(cost.getCostCode());
		return costDto;
	}

	@SuppressWarnings("rawtypes")
	public List<MasterCostDto> readById(Integer costId) {
		MasterCost masterCost = masterCostRepository.findOne(costId);
		
		HashMap<Integer, String> rateBasisMap = new HashMap<>();

		rateBasisMap.put(1, "List UOM");
		rateBasisMap.put(2, "Primary Packing");
		rateBasisMap.put(3, "Secondary Packing");
		List<MasterCostDto> masterCostDtoList = new ArrayList<>();
		List<MasterCostPartyAssocDto> masterCostPartyAssocDtoList = new ArrayList<>();

		MasterCostDto masterCostDto = new MasterCostDto();
		
		masterCostDto.setPkCostId(masterCost.getPkCostId());

		masterCostDto.setFkIndexUomId(masterCost.getFkIndexUomId());

		masterCostDto.setFkCurrencyId(masterCost.getFkCurrencyId());
		try {
			if (masterCost.getFkCurrencyId() != null) {
				masterCostDto
						.setFkCurrencyName(getCurrencyName(masterCost.getFkCurrencyId()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		masterCostDto.setCostCode(masterCost.getCostCode());
		masterCostDto.setCostName(masterCost.getCostName());
		masterCostDto.setCostFullName(masterCost.getCostFullname());
		if (masterCost.getFkCostGroupId() != null) {

			masterCostDto.setFkCostGroupName(masterCostGroupRepository
					.findOne(masterCost.getFkCostGroupId().getPkCostGroupId()).getCostGroupName());
			masterCostDto.setFkCostGroupId(masterCost.getFkCostGroupId().getPkCostGroupId());
		}
		masterCostDto.setCostTypeIsPrimaryInd(masterCost.getCostTypeIsPrimaryInd());
		masterCostDto.setMatrixApplicableInd(masterCost.getMatrixApplicableInd());
		masterCostDto.setCostDefaultValue(masterCost.getCostDefaultValue());
		if (masterCost.getFkCostDefValueTypeId() != null) {
			masterCostDto.setFkcostDefValueTypeId(masterCost.getFkCostDefValueTypeId().getPkCostDefValueTypeId());
			HashMap<Integer, String> costTypeMap = new HashMap<>();

			costTypeMap.put(1, "Percentage");
			costTypeMap.put(2, "Lump Sum");
			costTypeMap.put(3, "Index");
			costTypeMap.put(4, "Unit");
			Set set = (Set) costTypeMap.entrySet();
			masterCostDto.setFkcostDefValueTypeName(
					returnValueFromSet(set, masterCost.getFkCostDefValueTypeId().getPkCostDefValueTypeId()));
		}
		masterCostDto.setFkRevenueGlId(masterCost.getFkRevenueGlId());
		if (masterCost.getFkRevenueGlId() != null)
			masterCostDto.setFkRevenueGlName("00-REVENUE-WarehouseRent");
		masterCostDto.setFkExpenseGlId(masterCost.getFkExpenseGlId());
		if (masterCost.getFkExpenseGlId() != null)
			masterCostDto.setFkExpenseGlName("00-GL-WarehouseRent");
		masterCostDto.setNettedForMtmInd(masterCost.getNettedForMtmInd());
		masterCostDto.setRealizedInd(masterCost.getRealizedInd());
		masterCostDto.setCapitalizeCostInd(masterCost.getCapitalizeCostInd());
		if (masterCost.getFkCostDefValueTypeId()!=null && masterCost.getFkCostDefValueTypeId().getPkCostDefValueTypeId()>0) {
			masterCostDto.setFkDefaultValueBasisRefId(masterCost.getFkCostDefValueTypeId().getPkCostDefValueTypeId());
			Set set = (Set) rateBasisMap.entrySet();
			masterCostDto.setFkDefaultValueBasisRefName(
					returnValueFromSet(set, masterCost.getFkCostDefValueTypeId().getPkCostDefValueTypeId()));

		}

		if (masterCost.getFkDefaultValueUomId() != null && masterCost.getFkDefaultValueUomId()>0) {
			masterCostDto.setFkDefaultValueUomId(masterCost.getFkDefaultValueUomId());
			masterCostDto.setFkDefaultValueUomName(
					getUomName(masterCost.getFkDefaultValueUomId()));
		}
		masterCostDto.setFkDefValPrimaryPackingId(masterCost.getFkDefValPrimaryPackingId());
		if (masterCost.getFkDefValPrimaryPackingId() != null && masterCost.getFkDefValPrimaryPackingId()>0)
			masterCostDto.setFkDefValPrimaryPackingName(
					getPrimaryPackingName(masterCost.getFkDefValPrimaryPackingId()));

		if (masterCost.getFkDefValSecondaryPackingId() != null && masterCost.getFkDefValSecondaryPackingId()>0) {
			masterCostDto.setFkDefValSecondaryPackingId(masterCost.getFkDefValSecondaryPackingId());
			masterCostDto.setFkDefValSecondaryPackingName(
					getSecondaryPackingName(masterCost.getFkDefValSecondaryPackingId()));
		}
		
		masterCostDto.setFkMatrixEntityId(masterCost.getFkMatrixEntityId());
		if (masterCost.getFkMatrixEntityId() != null) {
			masterCostDto.setFkMatrixEntityName(
					masterCostGroupRepository.findOne(masterCost.getFkMatrixEntityId()).getCostGroupName());
		}
		if (masterCost.getFkStatusId() != null) {
			masterCostDto.setFkStatusId(masterCost.getFkStatusId());
			masterCostDto.setFkStatusName(returnStatusNameById(masterCost.getFkStatusId()));
		}
		if (masterCost.getCostPartyAssoc() != null) {
			List<MasterCostPartyAssoc> costPartyAssocList = masterCost.getCostPartyAssoc();
			for (MasterCostPartyAssoc masterCostPartyAssoc : costPartyAssocList) {
				MasterCostPartyAssocDto masterCostPartyAssocDto = new MasterCostPartyAssocDto();
				masterCostPartyAssocDto.setFkPartyId(masterCostPartyAssoc.getFkPartyId());
				masterCostPartyAssocDto
						.setFkPartyName(getPartyName(masterCostPartyAssoc.getFkPartyId()));
				masterCostPartyAssocDto.setPkCostPartyAssocId(masterCostPartyAssoc.getPkCostPartyAssocId());
				masterCostPartyAssocDtoList.add(masterCostPartyAssocDto);
			}
		}
		masterCostDto.setFkPartyId(masterCostPartyAssocDtoList);
		List<MasterCostExternalMappingDto> masterCostExternalMappingDtoList = new ArrayList<>();
		if (masterCost.getMasterPackingAssocList() != null) {
			List<MasterCostExternalMapping> masterCostExternalMappingList = masterCost.getMasterPackingAssocList();
			for (MasterCostExternalMapping masterCostExternalMapping : masterCostExternalMappingList) {
				MasterCostExternalMappingDto masterCostExternalMappingDto = new MasterCostExternalMappingDto();
				masterCostExternalMappingDto.setExternalCode(masterCostExternalMapping.getExternalCode());
				masterCostExternalMappingDto
						.setFkExternalSystemRefId(masterCostExternalMapping.getFkExternalSystemRefId());
				if (masterCostExternalMapping.getFkExternalSystemRefId() != null) {
					if (masterCostExternalMapping.getFkExternalSystemRefId() == 1)
						masterCostExternalMappingDto.setFkExternalSystemRefName("SAP");
					else
						masterCostExternalMappingDto.setFkExternalSystemRefName("Oracle");
				}
				if (masterCostExternalMapping.getCostExterMapAssoc() != null) {
					masterCostExternalMappingDto.setFkCostName("wareHouseCost");
					masterCostExternalMappingDto
							.setFkCostId(masterCostExternalMapping.getCostExterMapAssoc().getPkCostId());
				}
				if (masterCostExternalMapping.getCostExterMapAssoc().getPkCostId() != null)

					masterCostExternalMappingDto
							.setPkCostExternalMappingId(masterCostExternalMapping.getPkCostExternalMappingId());
				masterCostExternalMappingDto.setMappingType(masterCostExternalMapping.getMappingType());
				masterCostExternalMappingDtoList.add(masterCostExternalMappingDto);
			}

		}

		masterCostDto.setExterPackAssoc(masterCostExternalMappingDtoList);

		masterCostDtoList.add(masterCostDto);

		return masterCostDtoList;
	}

	@SuppressWarnings("rawtypes")
	public String returnValueFromSet(Set set, int value) {

		Iterator iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry mapEntry = (Map.Entry) iterator.next();
			if (value != 0) {
				int keyValue = (int) mapEntry.getKey();
				if (value == keyValue) {
					String toReturn = (String) mapEntry.getValue();
					System.out.println("Key : " + keyValue + "= Value : " + value);
					return toReturn;
				}
			}
		}
		return null;

	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String getPartyName(Integer partyId) {
		String partyName = null;
		if (partyId != null) {
			ResponseEntity<ResponseData> responseEntityParty = serviceCall.internalServiceCall(partyURL,
					"/party/partyservice/party/");
			List<Map<Object, Object>> partys = (List<Map<Object, Object>>) responseEntityParty.getBody().getBody();
			if (!partys.isEmpty()) {
				for (Map<Object, Object> party : partys) {
					if (party.get("partyId").equals(partyId)) {
						partyName = (String) party.get("partyName");
						break;
					}

				}
			}
			return partyName;
		} else {
			return null;
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String getCurrencyName(Integer currencyId) {
		String currencyName = null;
		if (currencyId != null) {
			ResponseEntity<ResponseData> responseEntityCurrency = serviceCall.internalServiceCall(url,
					"/reference/currencyservice/currency/"+currencyId.toString());
			Map<Object, Object> currencys = (Map<Object, Object>) responseEntityCurrency.getBody()
					.getBody();
			if (!currencys.isEmpty()) {
				currencyName = (String) currencys.get("currencyName");
					

				}
			
			return currencyName;
		} else {
			return null;
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String getUomName(Integer uomId) {
		String uomName = null;
		if (uomId != null) {
			ResponseEntity<ResponseData> responseEntityUom = serviceCall.internalServiceCall(url,
					"/reference/uomservice/uom/"+uomId.toString());
			Map<Object, Object> uoms = (Map<Object, Object>) responseEntityUom.getBody().getBody();
			if (!uoms.isEmpty()) {
				uomName = (String) uoms.get("uomName");
					
			}
			return uomName;
		} else {
			return null;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String getPrimaryPackingName(Integer ppId) {
		String primaryPackingName = null;
		if (ppId != null) {
			ResponseEntity<ResponseData> responseEntityPrimaryPacking = serviceCall.internalServiceCall(packingURL,
					"/packing/PrimaryPackingType/viewPrimaryPackingType/"+ppId.toString());
			Map<Object, Object> pps = (Map<Object, Object>) responseEntityPrimaryPacking.getBody()
					.getBody();
			if (!pps.isEmpty()) {
				primaryPackingName = (String) pps.get("internalPackingTypeName");
				
			}
			return primaryPackingName;
		} else {
			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String getSecondaryPackingName(Integer spId) {
		String secondaryPackingName = null;
		if (spId != null) {
			ResponseEntity<ResponseData> responseEntitySecondaryPacking = serviceCall.internalServiceCall(packingURL,
					"/packing/secPackTypeService/secPackType/"+spId.toString());
			Map<Object, Object> sps = (Map<Object, Object>) responseEntitySecondaryPacking.getBody()
					.getBody();
			if (!sps.isEmpty()) {
				secondaryPackingName = (String) sps.get("secondaryPackingTypeName");
					
			}
			return secondaryPackingName;
		} else {
			return null;
		}
	}

	
	
	
}

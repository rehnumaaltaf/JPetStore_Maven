package com.olam.score.cost.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olam.score.common.constants.Constants;
import com.olam.score.common.constants.ResponseConstants;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.cost.domain.MasterCost;
import com.olam.score.cost.domain.MasterCostGroup;
import com.olam.score.cost.domain.MasterWhCost;
import com.olam.score.cost.domain.MasterWhCostDetail;
import com.olam.score.cost.domain.MasterWhCostPartyAssoc;
import com.olam.score.cost.domain.MasterWhCostStockLocAssoc;
import com.olam.score.cost.dto.CostMatrixDto;
import com.olam.score.cost.dto.MasterCostDto;
import com.olam.score.cost.dto.MasterCostWhPartyAssocDto;
import com.olam.score.cost.dto.MasterCostWhStockLocationAssocDto;
import com.olam.score.cost.dto.WareHouseCostDTO;
import com.olam.score.cost.dto.WhCostDetailArray;
import com.olam.score.cost.repository.MasterCostDefaultValueTypeRefRepository;
import com.olam.score.cost.repository.MasterCostGroupRepository;
import com.olam.score.cost.repository.MasterCostRepository;
import com.olam.score.cost.repository.MasterWhCostDetailRepository;
import com.olam.score.cost.repository.MasterWhCostPartyAssocRepository;
import com.olam.score.cost.repository.MasterWhCostRepositoy;
import com.olam.score.cost.repository.MasterWhCostStockLocRepository;
import com.olam.score.cost.repository.MasterWhCostTypeRefRepository;
import com.olam.score.cost.repository.MasterWhTimeBasisRefRepository;

@Service
public class CostMatrixService {
	public final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	public WebServiceCallUtil serviceCall;
	@Value("${reference.name}")
	public String url;

	@Value("${party.name}")
	public String partyURL;

	@Value("${packing.name}")
	public String packingURL;
	@Value("${product.name}")
	public String productURL;
	@Autowired
	public MasterCostRepository masterCostRepository;
	@Autowired
	public MasterWhCostDetailRepository masterWhCostDetailRepository;
	@Autowired
	public MasterWhCostPartyAssocRepository masterWhCostPartyAssocRepository;
	@Autowired
	public MasterWhCostStockLocRepository masterWhCostStockLocAssocRepository;
	@Autowired
	public MasterWhCostRepositoy masterWhCostRepository;
	@Autowired
	public MasterCostGroupRepository masterCostGroupRepository;
	@Autowired
	public MasterCostDefaultValueTypeRefRepository masterCostDefaultValueTypeRefRepository;
	@Autowired
	public MasterWhTimeBasisRefRepository masterWhTimeBasisRefRepository;
	@Autowired
	public MasterWhCostTypeRefRepository masterWhCostTypeRefRepository;

	

	@Autowired
	public EntityManager em;

	@Transactional
	public String create(CostMatrixDto inputData) throws ParseException {
		String addStatus = ResponseConstants.LOCATION_ADD_SUCESS;
		String action = inputData.getAction();
		if (action.equalsIgnoreCase(Constants.SAVE)) {
			log.info("===draft action to perform for create ===");
			inputData.setFkStatusId(returnStatusIdByName(Constants.DRAFT));
		} else if (action.equalsIgnoreCase(Constants.SUBMIT)) {
			log.info("===save(active) action to perform for create ===");
			inputData.setFkStatusId(returnStatusIdByName(Constants.ACTIVE));
		} else if (action.equalsIgnoreCase(Constants.DEACTIVE)) {
			log.info("===save(Deactive) action to perform for create ===");
			inputData.setFkStatusId(returnStatusIdByName(Constants.INACTIVE));
		}
		if (inputData.getMatrixTypeId() > 0 && Constants.WAREHOUSE.equalsIgnoreCase(masterCostGroupRepository.findOne(inputData.getMatrixTypeId()).getCostGroupName())) {
			
				List<MasterWhCost> whCostList = createAndReturnWhCost(inputData);
				for (MasterWhCost whCost : whCostList)
					masterWhCostRepository.saveAndFlush(whCost);
			
		}
		return addStatus;
	}
	
	@Transactional
	public String update(CostMatrixDto inputData) throws ParseException {
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
		if (inputData.getMatrixTypeId() > 0 && Constants.WAREHOUSE.equalsIgnoreCase(masterCostGroupRepository.findOne(inputData.getMatrixTypeId()).getCostGroupName())) {
			
			List<MasterWhCost> whCostList = createAndReturnWhCost(inputData);
			for (MasterWhCost whCost : whCostList)
				masterWhCostRepository.saveAndFlush(whCost);
		
	}

		return addStatus;
	}
	@Transactional
	public String deleteCostMatrix(int id,int matrixType) throws ParseException {
		String addStatus = ResponseConstants.MASTER_COST_UPDATED_SUCESS;

		
		if (matrixType > 0 && Constants.WAREHOUSE.equalsIgnoreCase(masterCostGroupRepository.findOne(matrixType).getCostGroupName())) {
		deleteOrActivateOrReactivateWareHouseCost(id);
		
	}

		return addStatus;
	}
	public CostMatrixDto returnMatrixDetailsById(int pkCostMatrixId,String matrixTypeName) {
		CostMatrixDto dto=new CostMatrixDto();
		if("Warehouse".equalsIgnoreCase(matrixTypeName)) {
			dto=returnWhCostDto(pkCostMatrixId);
		}
		return dto;
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
		Map<Integer, String> statusMap = serviceCall.getAllStatus();
		return statusMap.get(id);
	}

	public List<MasterWhCost> createAndReturnWhCost(CostMatrixDto costMatrixDto) throws ParseException {
		List<MasterWhCost> whCostList = new ArrayList<>();
		MasterCost masterCost = masterCostRepository.findOne(costMatrixDto.getFkCostId());
		List<WareHouseCostDTO> dtoList = costMatrixDto.getWareHouseCostDto();
		for (WareHouseCostDTO dto : dtoList) {

			MasterWhCost masterWhCost = new MasterWhCost();
			if (dto.getPkWhCostId() != null) {
				masterWhCost.setPkWhCostId(dto.getPkWhCostId());
				MasterWhCost whCostById = masterWhCostRepository.findOne(dto.getPkWhCostId());
				masterWhCost.setCreatedBy(whCostById.getCreatedBy());
				masterWhCost.setCreatedDate(whCostById.getCreatedDate());
				masterWhCost.setModifiedBy("OLAM");
				masterWhCost.setModifiedDate(new Timestamp(System.currentTimeMillis()));
			} else {
				masterWhCost.setCreatedBy("OLAM");
				masterWhCost.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			}
			masterWhCost.setFkStatusId(costMatrixDto.getFkStatusId());
			masterWhCost.setIsApplicableToAllPartyInd(Integer.parseInt(dto.getIsApplicableToAllPartyInd()));
			if (masterWhCost.getIsApplicableToAllPartyInd() == 0)
				masterWhCost.setMasterWhCostPartyAssocList(
						createAndReturnWhCostPartyAssoc(dto.getLegalEntity(), masterWhCost));
			masterWhCost.setIsApplicableToAllStockLocInd(dto.getIsApplicableToAllStockLocInd());
			if (masterWhCost.getIsApplicableToAllStockLocInd() == 0)
				masterWhCost.setMasterWhCostStockLocAssocList(
						createAndReturnWhCostStockLocAssoc(dto.getWareHouseLocation(), masterWhCost));
			masterWhCost.setFkProdId(dto.getFkProdId());
			if ("Y".equalsIgnoreCase(dto.getCostIsDetailedInd()))
				masterWhCost.setCostIsDetailedInd(1);
			else
				masterWhCost.setCostIsDetailedInd(0);
			masterWhCost.setFkWarehousePartyId(dto.getWareHouse());
			masterWhCost.setWhCostContrRefNo(dto.getWhCostContrRefNo());
			masterWhCost.setWhCostValidFrom(dto.getWhCostValidFrom());
			masterWhCost.setWhCostValidTo(dto.getWhCostValidTo());
			masterWhCost.setWhCostRemarks(dto.getWhCostRemarks());
			masterWhCost.setWhCostFreePeriodType(dto.getWhCostFreePeriodType());
			masterWhCost.setWhCostNoOfFreeDays(Integer.parseInt(dto.getWhCostNoOfFreeDays()));
			masterWhCost.setMasterWhCostDetailList(
					createAndReturnWareHostCostDetial(masterWhCost, dto.getWhCostDetailArray()));
			masterWhCost.setFkCostId(masterCost);
			masterWhCost.setWhCostCode(costMatrixDto.getMatrixCode());
			masterWhCost.setWhCostName(costMatrixDto.getMatrixName());
			masterWhCost.setWhCostDesc(costMatrixDto.getMatrixDesc());
			whCostList.add(masterWhCost);
			
		}
		return whCostList;
	}

	public List<MasterWhCostPartyAssoc> createAndReturnWhCostPartyAssoc(List<MasterCostWhPartyAssocDto> value,
			MasterWhCost masterWhCost) {
		List<MasterWhCostPartyAssoc> partAssocList = new ArrayList<>();
		List<Integer> updatedList = new ArrayList<>();
		for (MasterCostWhPartyAssocDto model : value) {
			MasterWhCostPartyAssoc partyAssoc = new MasterWhCostPartyAssoc();
			if (model.getPkWhCostPartyAssocId() != null) {
				partyAssoc.setPkWhCostPartyAssocId(model.getPkWhCostPartyAssocId());
				updatedList.add(model.getPkWhCostPartyAssocId());
			}
			partyAssoc.setCreatedBy("OLAM");
			partyAssoc.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			partyAssoc.setFkWhCostId(masterWhCost);
			partyAssoc.setFkPartyId(model.getFkPartyId());
			partyAssoc.setFkStatusId(masterWhCost.getFkStatusId());
			partAssocList.add(partyAssoc);
		}
		if (!updatedList.isEmpty()) {
			masterWhCostPartyAssocRepository.deleteByFkWhCostIdAndPkWhCostPartyAssocIdNotIn(masterWhCost, updatedList);

		}
		return partAssocList;
	}

	public List<MasterWhCostStockLocAssoc> createAndReturnWhCostStockLocAssoc(
			List<MasterCostWhStockLocationAssocDto> value, MasterWhCost masterWhCost) {
		List<MasterWhCostStockLocAssoc> whCostStockAssocList = new ArrayList<>();
		List<Integer> updatedList = new ArrayList<>();
		if (!value.isEmpty()) {
			for (MasterCostWhStockLocationAssocDto model : value) {
				MasterWhCostStockLocAssoc partyAssoc = new MasterWhCostStockLocAssoc();
				if (model.getPkWhCostStockLocAssocId() != null) {
					partyAssoc.setPkWhCostStockAssocId(model.getPkWhCostStockLocAssocId());
					updatedList.add(model.getPkWhCostStockLocAssocId());
				}
				partyAssoc.setCreatedBy("OLAM");
				partyAssoc.setCreatedDate(new Timestamp(System.currentTimeMillis()));
				partyAssoc.setFkWhCostId(masterWhCost);
				partyAssoc.setFkPartyWhStockLocationId(model.getFkPartyWhStockLocId());
				partyAssoc.setFkStatusId(masterWhCost.getFkStatusId());
				whCostStockAssocList.add(partyAssoc);
			}
		}
		if (!updatedList.isEmpty()) {
			masterWhCostStockLocAssocRepository.deleteByFkWhCostIdAndPkWhCostStockAssocIdNotIn(masterWhCost,
					updatedList);
		}
		return whCostStockAssocList;
	}

	public List<MasterWhCostDetail> createAndReturnWareHostCostDetial(MasterWhCost masterWhCost,
			List<WhCostDetailArray> detailsArray) {
		List<MasterWhCostDetail> whCostDetail = new ArrayList<>();
		List<Integer> updatedList = new ArrayList<>();
		for (WhCostDetailArray masterWhCostDetailList : detailsArray) {
			MasterWhCostDetail detail = new MasterWhCostDetail();
			if (masterWhCostDetailList.getPkWhDetailId() != null && masterWhCostDetailList.getPkWhDetailId() > 0) {
				detail.setPkWhCostDetailId(masterWhCostDetailList.getPkWhDetailId());
				updatedList.add(masterWhCostDetailList.getPkWhDetailId());
			}
			detail.setFkWhCostId(masterWhCost);
			detail.setFkStatusId(masterWhCost.getFkStatusId());
			detail.setFkRateBasisRefId(masterWhCostDetailList.getRateBasis());
			if (masterWhCostDetailList.getWhfkDefaultValueUomId() != null)
				detail.setFkUomId(masterWhCostDetailList.getWhfkDefaultValueUomId());
			if (masterWhCostDetailList.getWhfkDefValPrimaryPackingId() != null)
				detail.setFkRatePrimaryPackingId(masterWhCostDetailList.getWhfkDefValPrimaryPackingId());
			if (masterWhCostDetailList.getWhfkDefValSecondaryPackingId() != null)
				detail.setFkRateSecondaryPacking(masterWhCostDetailList.getWhfkDefValSecondaryPackingId());
			if (masterWhCostDetailList.getRateCurrency() != null)
				detail.setFkCurrencyId(masterWhCostDetailList.getRateCurrency());
			detail.setRate(masterWhCostDetailList.getRate());
			if (masterWhCostDetailList.getTimeBasis() > 0)
				detail.setFkWhRentztimeBasisRefId(
						masterWhTimeBasisRefRepository.findOne(masterWhCostDetailList.getTimeBasis()));
			if (masterWhCostDetailList.getCostName() > 0)
				detail.setFkWhCostTypeRefId(
						masterWhCostTypeRefRepository.findOne(masterWhCostDetailList.getCostName()));
			whCostDetail.add(detail);
		}
		if (!updatedList.isEmpty()) {
			masterWhCostDetailRepository.deleteByFkWhCostIdAndPkWhCostDetailIdNotIn(masterWhCost, updatedList);
		}
		return whCostDetail;
	}

	public void deleteOrActivateOrReactivateWareHouseCost(int id) {
		MasterWhCost whCost = masterWhCostRepository.findOne(id);
		if (whCost.getFkStatusId() == returnStatusIdByName(Constants.DRAFT)) {
			masterWhCostRepository.delete(id);
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<CostMatrixDto> test() {
		List<CostMatrixDto> dtoList = new ArrayList<>();
		
			Query query = em.createNativeQuery(
					"select w.pk_wh_cost_id as id,w.wh_Cost_Name as name,w.wh_cost_code as code,w.wh_cost_desc as descp,'Warehouse' as costType,w.fk_warehouse_party_id as serviceProvider,w.fk_status_id as status,w.fk_cost_id as costId,w.created_Date as date1 from Master_Wh_Cost w union "
							+ "select f.PK_FREIGHT_COST_MATRIX_ID as id,f.FREIGHT_COST_NAME as name,f.FREIGHT_COST_CODE as code,f.FREIGHT_COST_DESC as descp,'Freight' as costType,f.FK_SERVICE_PROVIDER_PARTY_ID as serviceProvider,f.fk_status_id as status,f.fk_cost_id as costId,f.created_Date as date1 from MASTER_FREIGHT_COST_MATRIX f union "
							+ "select c.PK_CROP_SEASON_COST_ID as id,c.CROP_SEASON_COST_NAME as name,c.CROP_SEASON_COST_CODE as code,c.CROP_SEASON_COST_DESC as descp,'Crop Season' as costType,0 as serviceProvider,c.fk_status_id as status,c.fk_cost_id as costId,c.created_Date as date1 from MASTER_CROP_SEASON_COST c union "
							+ "select cnf.PK_CNF_COST_ID as id,cnf.CNF_COST_NAME as name,cnf.CNF_COST_CODE as code,cnf.CNF_COST_DESC as descp,'Clearing and Forwarding' as costType,cnf.FK_WAREHOUSE_PARTY_ID as serviceProvider,cnf.fk_status_id as status,cnf.fk_cost_id as costId,cnf.created_Date as date1 from MASTER_CNF_COST cnf "
							+ "order by date1 desc;");

			List<Object[]> rows = query.getResultList();
			for (Object[] object : rows) {
				CostMatrixDto dto = new CostMatrixDto();
				try {
					dto.setPkCostMatrixId(Integer.parseInt(object[0].toString()));
					dto.setMatrixName(object[1].toString());
					dto.setMatrixCode(object[2].toString());
					dto.setMatrixDesc(object[3].toString());

					MasterCostGroup costGroup = masterCostGroupRepository.getByCostGroupName(object[4].toString());
					dto.setMatrixTypeId(costGroup.getPkCostGroupId());
					dto.setMatrixTypeName(costGroup.getCostGroupName());
					if (Integer.parseInt(object[5].toString()) > 0) {
						dto.setServiceProvider(getPartyName(Integer.parseInt(object[5].toString())));
					} else {
						dto.setServiceProvider("No Service Provider");
					}
					dto.setFkStatusId(Integer.parseInt(object[6].toString()));
					dto.setFkStatusName(returnStatusNameById(Integer.parseInt(object[6].toString())));
					MasterCost cost = masterCostRepository.findOne(Integer.parseInt(object[7].toString()));
					dto.setFkCostId(cost.getPkCostId());
					dto.setFkCostName(cost.getCostName());
				} catch (Exception ex) {
					log.info("Inside View List of Cost Matrix", ex);
				}
				dtoList.add(dto);

			}
		
		return dtoList;
	}

	@SuppressWarnings("rawtypes")
	public CostMatrixDto returnWhCostDto(int id) {
		List<WareHouseCostDTO> dtoList = new ArrayList<>();
		MasterWhCost masterWhCost = masterWhCostRepository.findOne(id);
		HashMap<Integer, String> rateBasisMap = new HashMap<>();

		rateBasisMap.put(1, "List UOM");
		rateBasisMap.put(2, "Primary Packing");
		rateBasisMap.put(3, "Secondary Packing");
		HashMap<Integer, String> wareHouseLocationMap = new HashMap<>();

		wareHouseLocationMap.put(1, "Helsinki");
		wareHouseLocationMap.put(2, "Singapore");
		wareHouseLocationMap.put(3, "India");
		wareHouseLocationMap.put(4, "USA");
		WareHouseCostDTO wareHouseCostDTO = new WareHouseCostDTO();
		if (masterWhCost.getCostIsDetailedInd() == 0)
			wareHouseCostDTO.setCostIsDetailedInd("N");
		else
			wareHouseCostDTO.setCostIsDetailedInd("Y");
		if (masterWhCost.getFkProdId()!=null && masterWhCost.getFkProdId() > 0) {
			wareHouseCostDTO.setFkProdId(masterWhCost.getFkProdId());
			wareHouseCostDTO.setFkProdValue(getProductName(masterWhCost.getFkProdId()));
		}
		wareHouseCostDTO.setPkWhCostId(masterWhCost.getPkWhCostId());
		wareHouseCostDTO.setWhCostContrRefNo(masterWhCost.getWhCostContrRefNo());
		wareHouseCostDTO.setWhCostFreePeriodType(masterWhCost.getWhCostFreePeriodType());
		wareHouseCostDTO.setWhCostNoOfFreeDays(String.valueOf(masterWhCost.getWhCostNoOfFreeDays()));
		wareHouseCostDTO.setWhCostRemarks(masterWhCost.getWhCostRemarks());
		SimpleDateFormat ft = new SimpleDateFormat(Constants.DATE_FORMAT);
		wareHouseCostDTO.setWhCostValidFrom(masterWhCost.getWhCostValidFrom());
		wareHouseCostDTO.setWhCostValidTo(masterWhCost.getWhCostValidTo());

		HashMap<Integer, String> wareHouseMap = new HashMap<>();

		wareHouseMap.put(1, "Trader");
		wareHouseMap.put(2, "Rosters");
		wareHouseMap.put(3, "Service Provider");
		wareHouseMap.put(4, "Broker");
		wareHouseMap.put(5, "Certification Agency");
		wareHouseMap.put(6, "Exchange");

		// Get the set
		Set set6 = (Set) wareHouseMap.entrySet();
		// Create iterator on Set
		Iterator iterator6 = set6.iterator();
		while (iterator6.hasNext()) {
			Map.Entry mapEntry = (Map.Entry) iterator6.next();
			if (masterWhCost.getFkWarehousePartyId() != null) {
				int warehosueId = masterWhCost.getFkWarehousePartyId();
				int keyValue = (int) mapEntry.getKey();
				if (warehosueId == keyValue) {
					// Get Value
					String value = (String) mapEntry.getValue();
					wareHouseCostDTO.setWareHouseValue(value);
				}

			}
		}
		wareHouseCostDTO.setWareHouse(masterWhCost.getFkWarehousePartyId());

		List<MasterWhCostStockLocAssoc> masterWhCostStockLocAssocList = masterWhCost.getMasterWhCostStockLocAssocList();

		List<MasterCostWhStockLocationAssocDto> masterCostWhStockLocationAssocDtoList = new ArrayList<>();
		for (MasterWhCostStockLocAssoc masterWhCostStockLocAssoc : masterWhCostStockLocAssocList) {

			MasterCostWhStockLocationAssocDto masterCostWhStockLocationAssocDto = new MasterCostWhStockLocationAssocDto();
			masterCostWhStockLocationAssocDto.setFkStatusId(masterWhCostStockLocAssoc.getFkStatusId());
			masterCostWhStockLocationAssocDto
					.setFkStatusName(returnStatusNameById(masterWhCostStockLocAssoc.getFkStatusId()));
			masterCostWhStockLocationAssocDto.setFkWhCostId(masterWhCostStockLocAssoc.getFkWhCostId().getPkWhCostId());
			masterCostWhStockLocationAssocDto
					.setPkWhCostStockLocAssocId(masterWhCostStockLocAssoc.getPkWhCostStockAssocId());
			masterCostWhStockLocationAssocDto
					.setFkPartyWhStockLocId(masterWhCostStockLocAssoc.getFkPartyWhStockLocationId());

			// Get the set
			Set set7 = (Set) wareHouseLocationMap.entrySet();
			// Create iterator on Set
			Iterator iterator7 = set7.iterator();
			while (iterator7.hasNext()) {
				Map.Entry mapEntry = (Map.Entry) iterator7.next();
				if (masterWhCostStockLocAssoc.getFkPartyWhStockLocationId() != 0) {
					int warehosueLocationId = masterWhCostStockLocAssoc.getFkPartyWhStockLocationId();
					int keyValue = (int) mapEntry.getKey();
					if (warehosueLocationId == keyValue) {
						// Get Value
						String value = (String) mapEntry.getValue();
						masterCostWhStockLocationAssocDto.setFkPartyWhStockLocIdName(value);
					}

				}
			}

			masterCostWhStockLocationAssocDtoList.add(masterCostWhStockLocationAssocDto);
		}
		wareHouseCostDTO.setWareHouseLocation(masterCostWhStockLocationAssocDtoList);

		wareHouseCostDTO.setIsApplicableToAllPartyInd(String.valueOf(masterWhCost.getIsApplicableToAllPartyInd()));
		wareHouseCostDTO.setIsApplicableToAllStockLocInd(masterWhCost.getIsApplicableToAllStockLocInd());
		List<MasterWhCostPartyAssoc> masterWhCostPartyAssocsList = masterWhCost.getMasterWhCostPartyAssocList();
		List<MasterCostWhPartyAssocDto> masterCostWhPartyAssocDtoList = new ArrayList<>();
		for (MasterWhCostPartyAssoc masterWhCostPartyAssoc : masterWhCostPartyAssocsList) {
			MasterCostWhPartyAssocDto masterCostWhPartyAssocDto = new MasterCostWhPartyAssocDto();
			masterCostWhPartyAssocDto.setPkWhCostPartyAssocId(masterWhCostPartyAssoc.getPkWhCostPartyAssocId());
			masterCostWhPartyAssocDto.setFkWhCostId(masterWhCostPartyAssoc.getFkWhCostId().getPkWhCostId());
			masterCostWhPartyAssocDto.setFkPartyName(getPartyName(masterWhCostPartyAssoc.getFkPartyId()));
			masterCostWhPartyAssocDto.setFkPartyId(masterWhCostPartyAssoc.getFkPartyId());
			if (masterWhCost.getFkStatusId() != null) {
				masterCostWhPartyAssocDto.setFkStatusId(masterWhCostPartyAssoc.getFkStatusId());
				masterCostWhPartyAssocDto.setFkStatusName(returnStatusNameById(masterWhCostPartyAssoc.getFkStatusId()));
			}
			masterCostWhPartyAssocDtoList.add(masterCostWhPartyAssocDto);

		}

		wareHouseCostDTO.setLegalEntity(masterCostWhPartyAssocDtoList);
		List<MasterWhCostDetail> masterWhCostDetailList = masterWhCost.getMasterWhCostDetailList();
		List<WhCostDetailArray> whCostDetailArrayList = new ArrayList<>();
		for (MasterWhCostDetail masterWhCostDetail : masterWhCostDetailList) {
			try {
				WhCostDetailArray whCostDetailArray = new WhCostDetailArray();
				whCostDetailArray.setPkWhDetailId(masterWhCostDetail.getPkWhCostDetailId());
				whCostDetailArray.setRate(masterWhCostDetail.getRate());

				//
				// Get the set
				Set set = (Set) rateBasisMap.entrySet();
				// Create iterator on Set
				Iterator iterator = set.iterator();

				while (iterator.hasNext()) {
					Map.Entry mapEntry = (Map.Entry) iterator.next();
					if (masterWhCostDetail.getFkRateBasisRefId() != 0) {
						int rateBasis = masterWhCostDetail.getFkRateBasisRefId();

						int keyValue = (int) mapEntry.getKey();
						if (rateBasis == keyValue) {
							// Get Value
							String value = (String) mapEntry.getValue();
							System.out.println("Key : " + keyValue + "= Value : " + value);
							whCostDetailArray.setFkRateBasisName(value);
						}
					}
				}
				try {
					if (masterWhCostDetail.getFkRateBasisRefId() > 0)
						whCostDetailArray.setRateBasis(masterWhCostDetail.getFkRateBasisRefId());
				} catch (Exception ex) {

				}
				try {
					whCostDetailArray.setRateCurrency(masterWhCostDetail.getFkCurrencyId());
					whCostDetailArray.setFkCurrencyName(getCurrencyName(masterWhCostDetail.getFkCurrencyId()));
				} catch (Exception ex) {
					log.info("No currency", ex);
				}
				if (whCostDetailArray.getRateBasis() == 1) {
					whCostDetailArray.setWhfkDefaultValueUomName(getUomName(masterWhCostDetail.getFkUomId()));
					whCostDetailArray.setWhfkDefaultValueUomId(masterWhCostDetail.getFkUomId());
				} else if (whCostDetailArray.getRateBasis() == 2) {
					whCostDetailArray.setWhfkDefValPrimaryPackingId(masterWhCostDetail.getFkRatePrimaryPackingId());
					whCostDetailArray.setWhfkDefValPrimaryPackingName(
							getPrimaryPackingName(masterWhCostDetail.getFkRatePrimaryPackingId()));
				} else if (whCostDetailArray.getRateBasis() == 3) {
					whCostDetailArray.setWhfkDefValSecondaryPackingId(masterWhCostDetail.getFkRateSecondaryPacking());
					whCostDetailArray.setWhfkDefValSecondaryPackingName(
							getSecondaryPackingName(masterWhCostDetail.getFkRateSecondaryPacking()));
				}

				whCostDetailArray
						.setTimebasis(masterWhCostDetail.getFkWhRentztimeBasisRefId().getPkWhRentTimeBasisRefId());
				whCostDetailArray.setFkTimeBaisisName(masterWhTimeBasisRefRepository
						.findOne(masterWhCostDetail.getFkWhRentztimeBasisRefId().getPkWhRentTimeBasisRefId())
						.getWhRentTimeBasisName());

				try {
					if (masterWhCostDetail.getFkWhCostId() != null) {
						whCostDetailArray.setCostName(masterWhCostDetail.getFkWhCostTypeRefId().getPkWhCostTypeId());
						whCostDetailArray
								.setCostNameValue(masterWhCostDetail.getFkWhCostTypeRefId().getWhCostTypeName());
					}
				} catch (Exception ex) {

				}
				whCostDetailArrayList.add(whCostDetailArray);
			} catch (Exception ex) {
				log.info("Inside wARE HOUSE", ex);
			}
		}

		wareHouseCostDTO.setWhCostDetailArray(whCostDetailArrayList);
		dtoList.add(wareHouseCostDTO);
		CostMatrixDto costMatrixDto=new CostMatrixDto();
		costMatrixDto.setWareHouseCostDto(dtoList);
		costMatrixDto.setMatrixName(masterWhCost.getWhCostName());
		costMatrixDto.setPkCostMatrixId(id);
		costMatrixDto.setMatrixCode(masterWhCost.getWhCostCode());
		costMatrixDto.setMatrixDesc(masterWhCost.getWhCostDesc());
		try {
		MasterCostGroup costGroup = masterCostGroupRepository.getByCostGroupName("Warehouse");
		costMatrixDto.setMatrixTypeId(costGroup.getPkCostGroupId());
		costMatrixDto.setMatrixTypeName(costGroup.getCostGroupName());
		if (masterWhCost.getFkWarehousePartyId() > 0) {
			costMatrixDto.setServiceProvider(getPartyName(masterWhCost.getFkWarehousePartyId()));
		} else {
			costMatrixDto.setServiceProvider("No Service Provider");
		}
		costMatrixDto.setFkStatusId(masterWhCost.getFkStatusId());
		costMatrixDto.setFkStatusName(returnStatusNameById(masterWhCost.getFkStatusId()));
		MasterCost cost = masterCostRepository.findOne(masterWhCost.getFkCostId().getPkCostId());
		costMatrixDto.setFkCostId(cost.getPkCostId());
		costMatrixDto.setFkCostName(cost.getCostName());
		}catch(Exception ex)
		{
			log.info("==================", ex);
		}
		return costMatrixDto;
	}

	@SuppressWarnings("rawtypes")
	public String returnValueFromSet(Set set, int value) {

		Iterator iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry mapEntry = (Map.Entry) iterator.next();
			if (value != 0) {
				int keyValue = (int) mapEntry.getKey();
				if (value == keyValue) {
					return (String) mapEntry.getValue();
				}
			}
		}
		return null;

	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String getPartyName(Integer partyId) {
		String partyName = null;
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String getProductName(Integer productId) {
		String productName=null;
		if(productId!=null) {
			ResponseEntity<ResponseData> responseEntityProduct=serviceCall.internalServiceCall(productURL,"/product/productservice/product/basicdetails");
			List<Map<Object, Object>> products = (List<Map<Object, Object>>) responseEntityProduct.getBody().getBody();
			if (!products.isEmpty()) {
				for (Map<Object, Object> product : products) {
					if (product.get("prodId").equals(productId)) {
						productName=(String) product.get("prodName");
						break;
					}

				}
			}
			return productName;
		}else {
			return null;
		}
	}

}

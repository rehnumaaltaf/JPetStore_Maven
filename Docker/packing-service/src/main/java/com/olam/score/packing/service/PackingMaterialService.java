package com.olam.score.packing.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.olam.score.common.constants.Constants;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.dto.StatusDto;
import com.olam.score.common.util.CommonUtil;
import com.olam.score.common.util.ScoreBaseException;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.packing.domain.MasterPackingMaterial;
import com.olam.score.packing.domain.MasterSecondaryPackingType;
import com.olam.score.packing.dto.PackingMaterialDTO;
import com.olam.score.packing.dto.UomDto;
import com.olam.score.packing.repository.PackingMaterialRepository;

/**
 *
 * This service class having the methods to connect to dataBase using hibernate.
 *
 *
 * @author prabhakar
 * @version 1.00, 21 AUGUST 2017
 */

@Service
public class PackingMaterialService {

	@Autowired
	private PackingMaterialRepository packingMaterialRepository;
	@Value("${reference.name}")
	private String referenceName;
	private int chkValue=0;
	@Autowired
	private WebServiceCallUtil webServiceCall;

	@Transactional
	public String createPackingMaterial(PackingMaterialDTO packingMaterialDTO) {
		String response;
		MasterPackingMaterial masterPackingMaterial = new MasterPackingMaterial();
		
		masterPackingMaterial.setPackingMaterialCode(packingMaterialDTO.getPackingMaterialCode());
		if (packingMaterialDTO.getPackingMaterialDesc() != null && packingMaterialDTO.getPackingMaterialDesc() != "") {
			masterPackingMaterial.setPackingMaterialDesc(packingMaterialDTO.getPackingMaterialDesc());
		} else {
			masterPackingMaterial.setPackingMaterialDesc(null);
		}
		masterPackingMaterial.setPackingMaterialName(packingMaterialDTO.getPackingMaterialName());
		masterPackingMaterial.setPackingMaterialWeight(packingMaterialDTO.getPackingMaterialWeight());
		if (packingMaterialDTO.getPackingMaterialIsBulk() != null) {
			masterPackingMaterial.setPackingMaterialIsBulk(packingMaterialDTO.getPackingMaterialIsBulk());
		}
		masterPackingMaterial.setFkUomId(Integer.parseInt(packingMaterialDTO.getUomValue()));
		String action = packingMaterialDTO.getAction();
		if (action.equalsIgnoreCase(Constants.SAVE)) {
			masterPackingMaterial.setFkStatusId(returnStatusIdByName(Constants.DRAFT));
			response = Constants.SAVE_PACKINGMATERIAL_SUCCESS_DRAFT;
		} else {
			masterPackingMaterial.setFkStatusId(returnStatusIdByName(Constants.ACTIVE));
			response = Constants.SAVE_PACKINGMATERIAL_SUCCESS_ACTIVE;
		}
		  validate(masterPackingMaterial,0);
		packingMaterialRepository.save(masterPackingMaterial);
		return response;
	}

	public String updatePackingMaterial(PackingMaterialDTO packingMaterialDTO) {

		MasterPackingMaterial masterPackingMaterial = packingMaterialRepository
				.findOne(packingMaterialDTO.getPkPackingMaterialId());

		masterPackingMaterial.setPackingMaterialCode(packingMaterialDTO.getPackingMaterialCode());
		masterPackingMaterial.setPackingMaterialDesc(packingMaterialDTO.getPackingMaterialDesc());
		masterPackingMaterial.setPackingMaterialIsBulk(packingMaterialDTO.getPackingMaterialIsBulk());
		masterPackingMaterial.setPackingMaterialName(packingMaterialDTO.getPackingMaterialName());
		masterPackingMaterial.setPackingMaterialWeight(packingMaterialDTO.getPackingMaterialWeight());
		masterPackingMaterial.setPackingMaterialIsBulk(packingMaterialDTO.getPackingMaterialIsBulk());
		masterPackingMaterial.setFkUomId(Integer.parseInt(packingMaterialDTO.getUomValue()));
		String responce = null;
		if (packingMaterialDTO.getAction().equalsIgnoreCase(Constants.SAVE)) {
			masterPackingMaterial.setFkStatusId(returnStatusIdByName(Constants.DRAFT));
			responce = Constants.SAVE_PACKINGMATERIAL_SUCCESS_DRAFT;

		} else if (packingMaterialDTO.getAction().equalsIgnoreCase(Constants.SUBMIT)) {
			masterPackingMaterial.setFkStatusId(returnStatusIdByName(Constants.ACTIVE));
			responce = Constants.SAVE_PACKINGMATERIAL_SUCCESS_ACTIVE;
		} else if (packingMaterialDTO.getAction().equalsIgnoreCase(Constants.ACTIVE)) {
			masterPackingMaterial.setFkStatusId(returnStatusIdByName(Constants.ACTIVE));
			responce = Constants.SAVE_PACKINGMATERIAL_SUCCESS_ACTIVE;
		} else if (packingMaterialDTO.getAction().equalsIgnoreCase(Constants.INACTIVE)) {
			masterPackingMaterial.setFkStatusId(returnStatusIdByName(Constants.INACTIVE));
			responce = Constants.SAVE_PACKINGMATERIAL_SUCCESS_INACTIVE;
		} else if (packingMaterialDTO.getAction().equalsIgnoreCase(Constants.UPDATE)) {

			responce = Constants.SAVE_PACKINGMATERIAL_SUCCESS_ACTIVE;
		}

		if(!packingMaterialRepository.getByPackingMaterialCodeAndPkPackingMaterialIdNotIn(masterPackingMaterial.getPackingMaterialCode(), masterPackingMaterial.getPkPackingMaterialId()).isEmpty() && !packingMaterialRepository.getByPackingMaterialNameAndPkPackingMaterialIdNotIn(masterPackingMaterial.getPackingMaterialName(), masterPackingMaterial.getPkPackingMaterialId()).isEmpty())
		{
			throw new ScoreBaseException(masterPackingMaterial.getPkPackingMaterialId(), "packing Name and code exist in database",
					HttpStatus.CONFLICT);
			
		}
		if (!packingMaterialRepository.getByPackingMaterialCodeAndPkPackingMaterialIdNotIn(masterPackingMaterial.getPackingMaterialCode(),masterPackingMaterial.getPkPackingMaterialId()).isEmpty()) {
			throw new ScoreBaseException(masterPackingMaterial.getPkPackingMaterialId(), "packing Code exist in database",
					HttpStatus.CONFLICT);
		}
		if (!packingMaterialRepository.getByPackingMaterialNameAndPkPackingMaterialIdNotIn(masterPackingMaterial.getPackingMaterialName(),masterPackingMaterial.getPkPackingMaterialId()).isEmpty()) {
			throw new ScoreBaseException(masterPackingMaterial.getPkPackingMaterialId(), "packing Name exist in database",
					HttpStatus.CONFLICT);
		}
		packingMaterialRepository.saveAndFlush(masterPackingMaterial);
		return responce;
	}

	public String validatePackingMaterial(PackingMaterialDTO packingMaterialDTO) {
		String response = null;
		String toValidate = packingMaterialDTO.getToValidate();
		if (Constants.NAME.equalsIgnoreCase(toValidate)) {
			int count = packingMaterialRepository.getByName(packingMaterialDTO.getPackingMaterialName());
			if (count != Constants.ZERO) {
				response = Constants.PM_NAME_EXISTS;
				response = response.replace(Constants.NAME, packingMaterialDTO.getPackingMaterialName());
			}
		} else if (Constants.CODE.equalsIgnoreCase(packingMaterialDTO.getToValidate())) {
			int count = packingMaterialRepository.getByCode(packingMaterialDTO.getPackingMaterialCode());
			if (count != Constants.ZERO) {
				response = Constants.PM_CODE_EXISTS;
				response = response.replace(Constants.CODE, packingMaterialDTO.getPackingMaterialCode());
			}
		}
		return response;

	}

	@Transactional
	public List<PackingMaterialDTO> readPackingmaterial() {

		List<MasterPackingMaterial> masterpackingmaterialList = packingMaterialRepository.getAllMaterials();

		List<PackingMaterialDTO> packingmaterialList = new ArrayList<>();
		if (masterpackingmaterialList != null) {
			for (MasterPackingMaterial masterpm : masterpackingmaterialList) {
				PackingMaterialDTO packingMaterialDTO = new PackingMaterialDTO();
				packingMaterialDTO.setPkPackingMaterialId(masterpm.getPkPackingMaterialId());
				packingMaterialDTO.setPackingMaterialCode(masterpm.getPackingMaterialCode());
				packingMaterialDTO.setPackingMaterialName(masterpm.getPackingMaterialName());
				packingMaterialDTO.setPackingMaterialDesc(masterpm.getPackingMaterialDesc());
				packingMaterialDTO.setPackingMaterialWeight(masterpm.getPackingMaterialWeight());
				packingMaterialDTO.setPackingMaterialIsBulk(masterpm.getPackingMaterialIsBulk());
				packingMaterialDTO.setUomValue(String.valueOf(masterpm.getFkUomId()));
				packingMaterialDTO.setUomName(returnUomName(masterpm));
				packingMaterialDTO.setStatusName(returnStatusNameById(masterpm.getFkStatusId()));
				packingmaterialList.add(packingMaterialDTO);

			}
		}

		return packingmaterialList;

	}

	public String deletePackingMaterial(Integer packingMaterialId) {
		String response = null;
		String statusname;
		MasterPackingMaterial masterPackingMaterial = packingMaterialRepository.findOne(packingMaterialId);
		statusname=returnStatusNameById(masterPackingMaterial.getFkStatusId());

		if (Constants.DRAFT.equalsIgnoreCase(statusname)) {
			response = Constants.DELETE_SUCCESS;
			packingMaterialRepository.delete(packingMaterialId);
		} else if (Constants.ACTIVE.equalsIgnoreCase(statusname)) {
			masterPackingMaterial.setFkStatusId(returnStatusIdByName(Constants.INACTIVE));
			packingMaterialRepository.save(masterPackingMaterial);
			response = Constants.UPDATE_SUCCESS;
		} else if (Constants.INACTIVE.equalsIgnoreCase(statusname)) {
			masterPackingMaterial.setFkStatusId(returnStatusIdByName(Constants.ACTIVE));
			packingMaterialRepository.save(masterPackingMaterial);
			response = Constants.UPDATE_SUCCESS;
		}
		return response;
	}

	public List<PackingMaterialDTO> readPackingMaterialId(Integer id) {
		List<PackingMaterialDTO> masterPackingMaterialsList = new ArrayList<>();
		MasterPackingMaterial masterPackingMaterial = packingMaterialRepository.findOne(id);
		PackingMaterialDTO packingMaterialDTO = new PackingMaterialDTO();
		packingMaterialDTO.setPackingMaterialCode(masterPackingMaterial.getPackingMaterialCode());
		packingMaterialDTO.setPackingMaterialDesc(masterPackingMaterial.getPackingMaterialDesc());
		packingMaterialDTO.setPackingMaterialIsBulk(masterPackingMaterial.getPackingMaterialIsBulk());
		packingMaterialDTO.setPackingMaterialName(masterPackingMaterial.getPackingMaterialName());
		packingMaterialDTO.setPackingMaterialWeight(masterPackingMaterial.getPackingMaterialWeight());
		packingMaterialDTO.setPkPackingMaterialId(masterPackingMaterial.getPkPackingMaterialId());
		packingMaterialDTO.setUomValue(String.valueOf(masterPackingMaterial.getFkUomId()));
		packingMaterialDTO.setUomName(returnUomName(masterPackingMaterial));
		packingMaterialDTO.setStatusName(returnStatusNameById(masterPackingMaterial.getFkStatusId()));
		masterPackingMaterialsList.add(packingMaterialDTO);
		return masterPackingMaterialsList;
	}

	public String changeStatus(Integer id) {

		String responce = null;
		String status;
		MasterPackingMaterial masterPackingMaterial = packingMaterialRepository.findOne(id);

		ResponseEntity<ResponseData> responseEntity = webServiceCall.internalServiceCall(referenceName, Constants.STATUS_URL);
		ResponseData<List<StatusDto>> body = responseEntity.getBody();
		List<Map<Object, Object>> list = (List<Map<Object, Object>>) body.getBody();
		
		status=returnStatusNameById(masterPackingMaterial.getFkStatusId());

		if ("Draft".equalsIgnoreCase(status)) {

			masterPackingMaterial.setFkStatusId(Constants.ACIVATEID);
			packingMaterialRepository.saveAndFlush(masterPackingMaterial);
			responce = Constants.UPDATE_SUCCESS;
		}
		return responce;
	}

	public List<String> autoSuggestion(PackingMaterialDTO dto) {
		List<String> toReturn = new ArrayList<>();
		List<MasterPackingMaterial> masterPackingMaterialsList;
		Pageable pageable = new PageRequest(0, 20);
		if (Constants.NAME.equalsIgnoreCase(dto.getToValidate())) {
			masterPackingMaterialsList = packingMaterialRepository
					.findByPackingMaterialNameContaining(dto.getPackingMaterialName(), pageable);
			for (MasterPackingMaterial packingMaterial : masterPackingMaterialsList) {
				toReturn.add(packingMaterial.getPackingMaterialName());
			}
		} else if (Constants.CODE.equalsIgnoreCase(dto.getToValidate())) {
			masterPackingMaterialsList = packingMaterialRepository
					.findByPackingMaterialCodeContaining(dto.getPackingMaterialCode(), pageable);
			for (MasterPackingMaterial packingMaterial : masterPackingMaterialsList) {
				toReturn.add(packingMaterial.getPackingMaterialCode());
			}
		}
		return toReturn;
	}
	
	public String returnStatusname(MasterPackingMaterial masterPackingMaterial)
	
	{
		ResponseEntity<ResponseData> responseEntity = webServiceCall.internalServiceCall(referenceName, Constants.STATUS_URL);
		ResponseData<List<StatusDto>> body = responseEntity.getBody();
		List<Map<Object, Object>> list = (List<Map<Object, Object>>) body.getBody();
		String statusname = null;
		
		Integer statusId;
	for (int x = 0; x < list.size(); x++) {
		statusId = (Integer) list.get(x).get(Constants.STATUS_PKID);
		if (statusId != null && masterPackingMaterial.getFkStatusId() == statusId) {
			statusname = (String) list.get(x).get(Constants.STATUS_NAME);
		}
	}
	return statusname;
	}
	
	public String returnUomName(MasterPackingMaterial masterPackingMaterial)
	{
		String uomName = null;
	ResponseEntity<ResponseData> responseEntity1 = webServiceCall.internalServiceCall(referenceName, Constants.UOM_URL);
	ResponseData<List<UomDto>> body1 = responseEntity1.getBody();
	List<Map<Object, Object>> list1 = (List<Map<Object, Object>>) body1.getBody();

	Integer uomId;
	for (int x = 0; x < list1.size(); x++) {
		uomId = (Integer) list1.get(x).get(Constants.UOM_PKID);
		if (uomId != null && masterPackingMaterial.getFkUomId() == uomId) {
			uomName = (String) list1.get(x).get(Constants.UOM_NAME);
		}
	}
	return uomName;
		
	}
	
	public int returnStatusIdByName(String name)
	{
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		Optional<Integer> statusId = statusMap.entrySet().stream()
				.filter(e -> e.getValue().equals(name)).map(Map.Entry::getKey).findFirst();
		if (statusId.isPresent()) {
			return statusId.get();
		}
		return 0;
	}
	
	public String returnStatusNameById(int id)
	{
		String toReturn=null;
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		toReturn=statusMap.get(id);
		return toReturn;
	}
	
	private void validate(MasterPackingMaterial masterPackingMaterial,Integer id){
		
		if(packingMaterialRepository.uniquePackCode(masterPackingMaterial.getPackingMaterialCode()) > chkValue && packingMaterialRepository.uniquePackName(masterPackingMaterial.getPackingMaterialName()) > chkValue){
			throw new ScoreBaseException(id, "Packing Code"+" "+masterPackingMaterial.getPackingMaterialCode()+" "+"and"+" "+"Packing Name"+" "+masterPackingMaterial.getPackingMaterialName()+" "+"exist in database",HttpStatus.CONFLICT);
		}
		if (packingMaterialRepository.uniquePackCode(masterPackingMaterial.getPackingMaterialCode()) > chkValue) {
			throw new ScoreBaseException(id, "Packing Code"+" "+masterPackingMaterial.getPackingMaterialCode()+" "+"exist in database",HttpStatus.CONFLICT);
		}
		if (packingMaterialRepository.uniquePackName(masterPackingMaterial.getPackingMaterialName()) > chkValue) {
			throw new ScoreBaseException(id, "Packing Name"+" "+masterPackingMaterial.getPackingMaterialName()+" "+" exist in database",HttpStatus.CONFLICT);
		}
		
	}

}

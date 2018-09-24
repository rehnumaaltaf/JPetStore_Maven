package com.olam.score.product.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.olam.score.common.constants.Constants;
import com.olam.score.common.util.ScoreBaseException;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.product.domain.MasterBrand;
import com.olam.score.product.dto.BrandDto;
import com.olam.score.product.repository.BrandRepository;

@Service
public class BrandService {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private BrandRepository repository;

	@Autowired
	private WebServiceCallUtil serviceCall;

	public String create(BrandDto dto) {
		String toReturn = null;
		try {
			int status = returnStatusIdByName(Constants.ACTIVE);
			if (Constants.SAVE_CAPS.equalsIgnoreCase(dto.getAction())) {
				status = returnStatusIdByName(Constants.DRAFT);
			}
			MasterBrand brand = new MasterBrand();
			if (repository.getByName(dto.getBrandName()) > 0) {
				toReturn = Constants.BRAND_NAME_EXISTS;
				toReturn = toReturn.replace(Constants.BRAND_NAME_MESSAGE, dto.getBrandName());

			}
			if (repository.getByCode(dto.getBrandCode()) > 0) {
				if (toReturn != null) {
					toReturn = Constants.BRAND_NAME_CODE_EXISTS;
					toReturn = toReturn.replace(Constants.BRAND_NAME_MESSAGE, dto.getBrandName());
					toReturn = toReturn.replace(Constants.BRAND_CODE_MESSAGE, dto.getBrandCode());
				} else {
					toReturn = Constants.BRAND_CODE_EXISTS;
					toReturn = toReturn.replace(Constants.BRAND_NAME_MESSAGE, dto.getBrandCode());
				}
			}
			if (toReturn != null) {
				throw new ScoreBaseException(0, toReturn, HttpStatus.CONFLICT);
			}
			brand = createAndReturnMasterBranch(dto, brand, status);
			repository.saveAndFlush(brand);
			if (status == returnStatusIdByName(Constants.DRAFT))
				toReturn = Constants.BRAND_ADD_SUCCESS_DRAFT;
			else
				toReturn = Constants.BRAND_ADD_SUCCESS_ACTIVE;

			toReturn = toReturn.replace(Constants.BRAND_NAME_MESSAGE, dto.getBrandName());
		} catch (Exception ex) {
			log.info("Create Brand Master", ex);
			if (toReturn != null) {
				toReturn = Constants.BRAND_ADD_FAILURE;
				toReturn = toReturn.replace(Constants.BRAND_NAME_MESSAGE, dto.getBrandName());
			}
		}
		return toReturn;
	}

	public String validateBrand(BrandDto dto) {
		String toReturn = null;
		if (Constants.BRAND_NAME.equalsIgnoreCase(dto.getToValidate())
				&& repository.getByName(dto.getBrandName()) > 0) {
			toReturn = Constants.BRAND_NAME_EXISTS;
			toReturn = toReturn.replace(Constants.BRAND_NAME_MESSAGE, dto.getBrandName());

		} else if (Constants.BRAND_CODE.equalsIgnoreCase(dto.getToValidate())
				&& repository.getByCode(dto.getBrandCode()) > 0) {

			toReturn = Constants.BRAND_NAME_EXISTS;
			toReturn = toReturn.replace(Constants.BRAND_NAME_MESSAGE, dto.getBrandCode());

		}
		return toReturn;
	}

	public MasterBrand createAndReturnMasterBranch(BrandDto dto, MasterBrand brand, int status) {
		if (dto.getBrandId() != null) {
			brand.setPkBrandId(Integer.parseInt(dto.getBrandId()));
			MasterBrand brand1 = repository.findOne(Integer.parseInt(dto.getBrandId()));
			brand.setModifiedBy("OLAM");
			brand.setModfiedDate(new Timestamp(System.currentTimeMillis()));
			brand.setCreatedBy(brand1.getCreatedBy());
			brand.setCreatedDate(brand1.getCreatedDate());
		} else {
			brand.setCreatedBy("OLAM");
			brand.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		}
		if (dto.getBrandCode() != null) {
			brand.setBrandCode(dto.getBrandCode());
		}
		if (dto.getBrandName() != null) {
			brand.setBrandName(dto.getBrandName());
		}
		if (dto.getBrandFullName() != null) {
			brand.setBrandFullname(dto.getBrandFullName());
		}
		if (dto.getBrandIsInternalCode() != null) {
			brand.setBrandIsInternal(dto.getBrandIsInternalCode());
		}
		brand.setFkStatusId(status);
		try {
			if (dto.getLogoBlob() != null) {
				byte[] img = Base64.getDecoder().decode(dto.getLogoBlob());
				brand.setLogoBlob(img);
			}
		} catch (Exception ex) {
			log.info("Base 64 to binary", ex);
		}

		return brand;
	}

	public List<BrandDto> viewBrandList() {
		List<MasterBrand> list = repository.findAllByOrderByCreatedDateDesc();
		List<BrandDto> dtoList = new ArrayList<>();
		for (MasterBrand brand : list) {
			BrandDto dto = createAndReturnDto(brand);
			dtoList.add(dto);
		}
		return dtoList;
	}

	public BrandDto viewBrand(int id) {
		MasterBrand brand = repository.findOne(id);
		return createAndReturnDto(brand);
	}

	public BrandDto createAndReturnDto(MasterBrand brand) {
		BrandDto dto = new BrandDto();
		if (brand.getBrandCode() != null) {
			dto.setBrandCode(brand.getBrandCode());
		}
		if (brand.getBrandName() != null) {
			dto.setBrandName(brand.getBrandName());
		}
		dto.setBrandId(brand.getPkBrandId().toString());
		if (brand.getBrandFullname() != null) {
			dto.setBrandFullName(brand.getBrandFullname());
		}
		if (brand.getBrandIsInternal() != null) {
			if (Constants.YES_VIEW.equalsIgnoreCase(brand.getBrandIsInternal()))
				dto.setBrandIsInternal(Constants.BRAND_INTERNAL);
			else
				dto.setBrandIsInternal(Constants.BRAND_EXTERNAL);

			dto.setBrandIsInternalCode(brand.getBrandIsInternal());
		}
		if (brand.getLogoBlob() != null) {
			dto.setLogoBlob(Base64.getEncoder().encodeToString(brand.getLogoBlob()));
		}
		dto.setStatus(returnStatusNameById(brand.getFkStatusId()));

		return dto;
	}

	public String deleteBrand(int id) {
		String response = Constants.BRAND_DELETE_FAILURE;
		MasterBrand ipType = repository.findOne(id);
		if (ipType.getPkBrandId() > 0) {
			String name = ipType.getBrandName();
			if (ipType.getFkStatusId() == returnStatusIdByName(Constants.DRAFT)) {
				repository.delete(id);
				response = Constants.BRAND_DELETE_SUCCESS;
				response = response.replace(Constants.BRAND_NAME_MESSAGE, name);
			} else if (ipType.getFkStatusId() == returnStatusIdByName(Constants.ACTIVE)) {
				repository.deactivateOrReactivatePrimaryPackingType(returnStatusIdByName(Constants.INACTIVE),
						ipType.getPkBrandId());
				response = Constants.BRAND_DEACTIVE_SUCCESS;
				response = response.replace(Constants.BRAND_NAME_MESSAGE, name);
			} else {
				repository.deactivateOrReactivatePrimaryPackingType(returnStatusIdByName(Constants.ACTIVE),
						ipType.getPkBrandId());
				response = Constants.BRAND_REACTIVE_SUCCESS;
				response = response.replace(Constants.BRAND_NAME_MESSAGE, name);
			}
		} else {
			throw new ScoreBaseException(id, response, HttpStatus.CONFLICT);
		}
		return response;
	}

	public String viewBrandAction(BrandDto dto) {
		String response = Constants.BRAND_DELETE_FAILURE;
		MasterBrand brand = repository.findOne(Integer.parseInt(dto.getBrandId()));
		if (brand.getPkBrandId() > 0) {
			String name = brand.getBrandName();
			if (Constants.BRAND_VIEW_DELETE.equalsIgnoreCase(dto.getAction())) {
				repository.delete(Integer.parseInt(dto.getBrandId()));
				response = Constants.BRAND_DELETE_SUCCESS;
				response = response.replace(Constants.BRAND_NAME_MESSAGE, name);
			} else if (Constants.BRAND_VIEW_DEACTIVATE.equalsIgnoreCase(dto.getAction())) {
				try {
					repository.deactivateOrReactivatePrimaryPackingType(returnStatusIdByName(Constants.INACTIVE),
							brand.getPkBrandId());
				} catch (Exception ex) {
					log.info("Inside brand deactivate", ex);
					throw new ScoreBaseException(Integer.parseInt(dto.getBrandId()), response, HttpStatus.CONFLICT);
				}
				response = Constants.BRAND_DEACTIVE_SUCCESS;
				response = response.replace(Constants.BRAND_NAME_MESSAGE, name);
			} else if (Constants.BRAND_VIEW_ACTIVATE.equalsIgnoreCase(dto.getAction())) {
				try {
					repository.deactivateOrReactivatePrimaryPackingType(returnStatusIdByName(Constants.ACTIVE),
							brand.getPkBrandId());
				} catch (Exception ex) {
					log.info("Inside brand Activate", ex);
					throw new ScoreBaseException(Integer.parseInt(dto.getBrandId()), response, HttpStatus.CONFLICT);
				}
				response = Constants.BRAND_REACTIVE_SUCCESS;
				response = response.replace(Constants.BRAND_NAME_MESSAGE, name);
				if (brand.getFkStatusId() == returnStatusIdByName(Constants.DRAFT))
					response = response.replace("Reactivated", "Activated");
			}
		} else {
			throw new ScoreBaseException(Integer.parseInt(dto.getBrandId()), response, HttpStatus.CONFLICT);
		}
		return response;
	}

	public String update(BrandDto dto) {
		String toReturn = null;
		try {
			int status = 0;
			MasterBrand brand = new MasterBrand();
			if (Constants.BRAND_DELETE.equalsIgnoreCase(dto.getAction())) {
				repository.delete(Integer.parseInt(dto.getBrandId()));
				toReturn = Constants.BRAND_DELETE_SUCCESS;
				toReturn = toReturn.replace(Constants.BRAND_NAME_MESSAGE, dto.getBrandName());
				return toReturn;
			}
			if (repository.getByNameForEdit(dto.getBrandName(), Integer.parseInt(dto.getBrandId())) > 0) {
				toReturn = Constants.BRAND_NAME_EXISTS;
				toReturn = toReturn.replace(Constants.BRAND_NAME_MESSAGE, dto.getBrandName());
			}

			if (repository.getByCodeForEdit(dto.getBrandCode(), Integer.parseInt(dto.getBrandId())) > 0) {
				if (toReturn != null) {
					toReturn = Constants.BRAND_NAME_CODE_EXISTS;
					toReturn = toReturn.replace(Constants.BRAND_NAME_MESSAGE, dto.getBrandName());
					toReturn = toReturn.replace(Constants.BRAND_CODE_MESSAGE, dto.getBrandCode());
				} else {
					toReturn = Constants.BRAND_CODE_EXISTS;
					toReturn = toReturn.replace(Constants.BRAND_NAME_MESSAGE, dto.getBrandCode());
				}
			}
			if (toReturn != null) {
				throw new ScoreBaseException(0, toReturn, HttpStatus.CONFLICT);
			}
			if (Constants.BRAND_UPDATE_REACTIVATE.equalsIgnoreCase(dto.getAction())) {
				status = returnStatusIdByName(Constants.ACTIVE);
				toReturn = Constants.BRAND_UPDATE_REACTIVE_SUCCESS;
				toReturn = toReturn.replace(Constants.BRAND_NAME_MESSAGE, dto.getBrandName());
				if (repository.findOne(Integer.parseInt(dto.getBrandId()))
						.getFkStatusId() == returnStatusIdByName(Constants.DRAFT)) {
					toReturn = Constants.BRAND_UPDATE_ACTIVE_SUCCESS;
					toReturn = toReturn.replace(Constants.BRAND_NAME_MESSAGE, dto.getBrandName());
				}
			} else if (Constants.BRAND_UPDATE_DEACTIVATE.equalsIgnoreCase(dto.getAction())) {
				status = returnStatusIdByName(Constants.INACTIVE);
				toReturn = Constants.BRAND_UPDATE_DEACTIVE_SUCCESS;
				toReturn = toReturn.replace(Constants.BRAND_NAME_MESSAGE, dto.getBrandName());
			} else if (Constants.BRAND_UPDATE.equalsIgnoreCase(dto.getAction())) {
				status = repository.findOne(Integer.parseInt(dto.getBrandId())).getFkStatusId();
				toReturn = Constants.BRAND_UPDATE_SUCCESS;
				toReturn = toReturn.replace(Constants.BRAND_NAME_MESSAGE, dto.getBrandName());
			}
			brand = createAndReturnMasterBranch(dto, brand, status);
			repository.saveAndFlush(brand);

		} catch (Exception ex) {
			log.info("Update PrimaryPackingType", ex);
			
				toReturn = Constants.BRAND_DELETE_FAILURE;
		}
		return toReturn;
	}

	public List<String> autoSuggestion(BrandDto dto) {
		List<String> toReturn = new ArrayList<>();
		Pageable pageable = new PageRequest(0, 20);
		if (Constants.BRAND_NAME.equalsIgnoreCase(dto.getToValidate())) {
			List<MasterBrand> brandList = repository.findByBrandNameContaining(dto.getBrandName(), pageable);
			for (MasterBrand brand : brandList) {
				toReturn.add(brand.getBrandName());
			}
		} else if (Constants.BRAND_CODE.equalsIgnoreCase(dto.getToValidate())) {
			List<MasterBrand> brandList = repository.findByBrandCodeContaining(dto.getBrandCode(), pageable);
			for (MasterBrand brand : brandList) {
				toReturn.add(brand.getBrandCode());
			}
		}
		return toReturn;
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

}

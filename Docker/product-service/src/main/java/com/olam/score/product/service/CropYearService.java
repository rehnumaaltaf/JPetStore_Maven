package com.olam.score.product.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.olam.score.common.constants.Constants;
import com.olam.score.common.util.CommonUtil;
import com.olam.score.common.util.ErrorMessageUtil;
import com.olam.score.common.util.ScoreBaseException;
import com.olam.score.common.util.ScoreBaseExceptionResponse;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.product.domain.MasterCropYear;
import com.olam.score.product.domain.MasterCropYearProductAsso;
import com.olam.score.product.domain.MasterProduct;
import com.olam.score.product.dto.CropYearDto;
import com.olam.score.product.dto.CropYearProductDto;
import com.olam.score.product.repository.CropYearProductRepository;
import com.olam.score.product.repository.CropYearRepository;
import com.olam.score.product.repository.ProductRepository;

@Service
public class CropYearService {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	public CropYearRepository cropYearRepository;

	@Autowired
	public ProductRepository productRepository;

	@Autowired
	public CropYearProductRepository cropYearproductRepository;

	@Autowired
	private WebServiceCallUtil serviceCall;

	@Autowired
	EntityManager entityManager;

	@Value("${reference.name}")
	public String referenceName;

	@Value("${product.name}")
	public String productName;

	@Transactional
	public List<CropYearDto> readAll() {
		List<String> sortColumns = new ArrayList<>();
		sortColumns.add("createdDate");
		sortColumns.add("modifiedDate");
		Sort sort = new Sort(Direction.DESC, sortColumns);
		List<MasterCropYearProductAsso> cropYearProductList = cropYearproductRepository.findAll();
		List<MasterCropYear> cropYearList = cropYearRepository.findAll(sort);
		Map<Integer, String> statusMap = serviceCall.getAllStatus();
		List<CropYearDto> cropYearDtoList = new ArrayList<>();

		for (MasterCropYear cropYear : cropYearList) {
			CropYearDto cropYearDto = new CropYearDto();
			cropYearDto.setCropYearCode(cropYear.getCropYearCode());
			cropYearDto.setCropYearId(cropYear.getPkCropYearId());
			cropYearDto.setCropYearName(cropYear.getCropYearName());
			cropYearDto.setCropYearDescription(cropYear.getCropYearDesc());
			List<CropYearProductDto> cropYearProductDtoList = new ArrayList<>();
			for (MasterCropYearProductAsso product : cropYearProductList) {
				if (cropYear.getPkCropYearId() == product.getMasterCropYear().getPkCropYearId()) {
					CropYearProductDto cropYearProductDto = new CropYearProductDto();
					cropYearProductDto.setCropYearProductAssoId(product.getPkCropYearProductId());
					cropYearProductDto.setProdCode(product.getMasterProduct().getProdCode());
					cropYearProductDto.setProdId(product.getMasterProduct().getPkProdId());
					cropYearProductDto.setProdName(product.getMasterProduct().getProdName());
					cropYearProductDtoList.add(cropYearProductDto);

				}
			}
			cropYearDto.setCropYearProductDto(cropYearProductDtoList);
			cropYearDto.setStatusId(cropYear.getFkStatusId());
			cropYearDto.setStatusName(statusMap.get(cropYear.getFkStatusId()));
			cropYearDto.setCreatedBy(cropYear.getCreatedBy());
			cropYearDto.setCreatedDate(cropYear.getCreatedDate());
			cropYearDto.setModifiedBy(cropYear.getModifiedBy());
			cropYearDto.setModifiedDate(cropYear.getModifiedDate());
			cropYearDtoList.add(cropYearDto);
		}

		return cropYearDtoList;
	}

	@Transactional
	public CropYearDto create(CropYearDto cropYearDto) {
		MasterCropYear masterCropYearName = null;
		MasterCropYear masterCropYearCode = null;
		MasterCropYear masterCropYear = null;
		CropYearDto dto;
		log.info("In CropYearService class create(). Find Cropyear with the name supplied by user");
		masterCropYearName = cropYearRepository.findByCropYearName(cropYearDto.getCropYearName().toUpperCase());
		log.info("In CropYearService class create(). Find Cropyear with the code supplied by user");
		masterCropYearCode = cropYearRepository.findByCropYearCode(cropYearDto.getCropYearCode().toUpperCase());
		// Check for duplicates and throw exception
		List<ScoreBaseExceptionResponse> errorBeans = new ArrayList<>();
		if (masterCropYearCode != null || masterCropYearName != null) {
			if (masterCropYearCode != null) {
				log.error("===Duplicate Cropyear Code found in Database===");
				ErrorMessageUtil.errorHandler(new Object[] { cropYearDto.getCropYearCode() }, errorBeans,
						Constants.ERROR_DUPLICATE_CROPYEAR_CODE, Constants.ERROR_TYPE_DUPLICATE_CODE);
			}
			if (masterCropYearName != null) {
				log.error("===Duplicate Cropyear Name found in Database===");
				ErrorMessageUtil.errorHandler(new Object[] { cropYearDto.getCropYearName() }, errorBeans,
						Constants.ERROR_DUPLICATE_CROPYEAR_NAME, Constants.ERROR_TYPE_DUPLICATE_NAME);
			}
			throw new ScoreBaseException(errorBeans, HttpStatus.CONFLICT);
		}

		log.info("Status Name : ");
		log.info(cropYearDto.getStatusName());
		Map<Integer, String> statusMap = serviceCall.getAllStatus();
		Integer statusId = null;
		Optional<Integer> statusId1 = statusMap.entrySet().stream()
				.filter(e -> e.getValue().equals(cropYearDto.getStatusName())).map(Map.Entry::getKey).findFirst();
		if (statusId1.isPresent()) {
			statusId = statusId1.get();
		}
		masterCropYear = cropYearRepository.saveAndFlush(cropYearDto.cropYearDtoToModel(statusId, Constants.CREATE));
		String statusName = null;
		List<MasterCropYearProductAsso> cropYearProductAssos = new ArrayList<>();
		for (CropYearProductDto cropYearProductDto : cropYearDto.getCropYearProductDto()) {
			MasterProduct product;
			product = productRepository.findOne(cropYearProductDto.getProdId());
			MasterCropYearProductAsso cropYearProduct = cropYearproductRepository.saveAndFlush(
					cropYearProductDtoToModel(masterCropYear, product, Constants.CREATE, cropYearProductDto));
			statusName = statusMap.get(cropYearProduct.getFkStatusId());
			cropYearProductAssos.add(cropYearProduct);
		}

		List<CropYearProductDto> productDtos = cropYearProductModelToDto(cropYearProductAssos);
		dto = cropYearModelToDto(masterCropYear, productDtos, statusName);
		return dto;
	}

	private List<CropYearProductDto> cropYearProductModelToDto(List<MasterCropYearProductAsso> cropYearProductAssos) {
		log.info("In CropYearService class cropYearProductModelToDto().");
		List<CropYearProductDto> cropYearProductDtos = new ArrayList<>();
		for (MasterCropYearProductAsso masterCropYearProductAsso : cropYearProductAssos) {
			CropYearProductDto cropYearProductDto = new CropYearProductDto();
			cropYearProductDto.setCropYearProductAssoId(masterCropYearProductAsso.getPkCropYearProductId());
			cropYearProductDto.setProdId(masterCropYearProductAsso.getMasterProduct().getPkProdId());
			cropYearProductDto.setProdName(masterCropYearProductAsso.getMasterProduct().getProdName());
			cropYearProductDto.setProdCode(masterCropYearProductAsso.getMasterProduct().getProdCode());
			/*
			 * cropYearProductDto.setStatusId(masterCropYearProductAsso.
			 * getFkStatusId());
			 * cropYearProductDto.setCreatedBy(masterCropYearProductAsso.
			 * getCreatedBy());
			 * cropYearProductDto.setCreatedDate(masterCropYearProductAsso.
			 * getCreatedDate());
			 * cropYearProductDto.setModifiedBy(masterCropYearProductAsso.
			 * getModifiedBy());
			 * cropYearProductDto.setModifiedDate(masterCropYearProductAsso.
			 * getModifiedDate()); cropYearProductDto.setStatusName(statusName);
			 */
			cropYearProductDtos.add(cropYearProductDto);
		}
		return cropYearProductDtos;

	}

	private MasterCropYearProductAsso cropYearProductDtoToModel(MasterCropYear masterCropYear, MasterProduct product,
			String action, CropYearProductDto cropYearProductDto) {
		MasterCropYearProductAsso cropYearProduct = new MasterCropYearProductAsso();
		cropYearProduct.setMasterCropYear(masterCropYear);
		if (product != null) {
			cropYearProduct.setMasterProduct(product);
		}
		if (Constants.CREATE.equalsIgnoreCase(action)) {
			cropYearProduct.setCreatedBy("Test");
			cropYearProduct.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		} else if (Constants.UPDATE.equalsIgnoreCase(action)) {
			cropYearProduct.setCreatedBy("Test");
			cropYearProduct.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			cropYearProduct.setPkCropYearProductId(cropYearProductDto.getCropYearProductAssoId());
			cropYearProduct.setModifiedBy("Test");
			cropYearProduct.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		}

		return cropYearProduct;
	}

	public void deleteCropyear(List<Map<String, Integer>> body) {
		log.info("In CropYearService class deleteCropyear().");
		Map<Integer, String> statusMap = serviceCall.getAllStatus();

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

			MasterCropYear masterCropYearProduct;
			masterCropYearProduct = cropYearRepository.findOne(map.get("cropYearId"));

			if (masterCropYearProduct.getFkStatusId() != null) {
				String currentStatusName = null;

				currentStatusName = statusMap.get(masterCropYearProduct.getFkStatusId());
				log.info("Curerent Status Name : ");
				log.info(currentStatusName);
				if (currentStatusName.equals(Constants.ACTIVE)) {
					masterCropYearProduct.setFkStatusId(inactiveStatusId);
					cropYearRepository.saveAndFlush(masterCropYearProduct);

				} else if (currentStatusName.equals(Constants.INACTIVE)) {

					masterCropYearProduct.setFkStatusId(activeStatusId);
					cropYearRepository.saveAndFlush(masterCropYearProduct);
				} else if (currentStatusName.equals(Constants.DRAFT)) {
					cropYearRepository.delete(masterCropYearProduct);
				}

			}

		}

	}

	@Modifying
	public CropYearDto update(CropYearDto cropYearDto) {

		MasterCropYear masterCropYearName = null;
		MasterCropYear masterCropYearCode = null;
		MasterCropYear masterCropYear = null;
		CropYearDto dto;
		log.info("In CropYearService class update(). Find Cropyear with the name supplied by user");
		masterCropYearName = cropYearRepository.findByCropYearName(cropYearDto.getCropYearName().toUpperCase());
		log.info("In CropYearService class update(). Find Cropyear with the code supplied by user");
		masterCropYearCode = cropYearRepository.findByCropYearCode(cropYearDto.getCropYearCode().toUpperCase());
		// Check for duplicates and throw exception
		List<ScoreBaseExceptionResponse> errorBeans = new ArrayList<>();
		if ((masterCropYearCode != null && !masterCropYearCode.getPkCropYearId().equals(cropYearDto.getCropYearId())) || (masterCropYearName != null && !masterCropYearName.getPkCropYearId().equals(cropYearDto.getCropYearId()))) {
			if (masterCropYearCode != null && !masterCropYearCode.getPkCropYearId().equals(cropYearDto.getCropYearId())) {
				log.error("===Duplicate Cropyear Code found in Database===");
				ErrorMessageUtil.errorHandler(new Object[] { cropYearDto.getCropYearCode() }, errorBeans,
						Constants.ERROR_DUPLICATE_CROPYEAR_CODE, Constants.ERROR_TYPE_DUPLICATE_CODE);
			}
			if (masterCropYearName != null && !masterCropYearName.getPkCropYearId().equals(cropYearDto.getCropYearId())) {
				log.error("===Duplicate Cropyear Name found in Database===");
				ErrorMessageUtil.errorHandler(new Object[] { cropYearDto.getCropYearName() }, errorBeans,
						Constants.ERROR_DUPLICATE_CROPYEAR_NAME, Constants.ERROR_TYPE_DUPLICATE_NAME);
			}
			throw new ScoreBaseException(errorBeans, HttpStatus.CONFLICT);
		}
		Map<Integer, String> statusMap = serviceCall.getAllStatus();
		Integer statusId = null;
		Optional<Integer> statusId1 = statusMap.entrySet().stream()
				.filter(e -> e.getValue().equalsIgnoreCase(cropYearDto.getStatusName())).map(Map.Entry::getKey)
				.findFirst();
		if (statusId1.isPresent()) {
			statusId = statusId1.get();
		}
		masterCropYear = cropYearRepository.saveAndFlush(cropYearDto.cropYearDtoToModel(statusId, Constants.UPDATE));

		String statusName = statusMap.get(masterCropYear.getFkStatusId());
		List<MasterCropYearProductAsso> cropYearProductAssos = new ArrayList<>();
		List<CropYearProductDto> productDtos = null;
		/*
		 * List<MasterCropYearProductAsso> cropYearProduct1 =
		 * cropYearproductRepository
		 * .findByCropYearId(cropYearDto.getCropYearId());
		 * 
		 * Set<Integer> existingProductIds = new HashSet<>(); for
		 * (MasterCropYearProductAsso masterCropYearProductAsso :
		 * cropYearProduct1) {
		 * existingProductIds.add(masterCropYearProductAsso.getMasterProduct().
		 * getPkProdId()); }
		 * 
		 * Set<Integer> newProductIds = new HashSet<>(); for (CropYearProductDto
		 * cropYearProductDto : cropYearDto.getCropYearProductDto()) {
		 * newProductIds.add(cropYearProductDto.getProdId()); }
		 */

		// delete all
		// add all
		cropYearproductRepository.deleteAllWithCropYear(cropYearDto.getCropYearId());

		for (CropYearProductDto cropYearProductDto : cropYearDto.getCropYearProductDto()) {
			MasterProduct product;
			product = productRepository.findOne(cropYearProductDto.getProdId());
			MasterCropYearProductAsso cropYearProduct = cropYearproductRepository.saveAndFlush(
					cropYearProductDtoToModel(masterCropYear, product, Constants.CREATE, cropYearProductDto));
			cropYearProductAssos.add(cropYearProduct);
		}
		productDtos = cropYearProductModelToDto(cropYearProductAssos);
		dto = cropYearModelToDto(masterCropYear, productDtos, statusName);
		return dto;

		/*
		 * for (CropYearProductDto cropYearProductDto :
		 * cropYearDto.getCropYearProductDto()) { Boolean flag = true; for
		 * (MasterCropYearProductAsso masterCropYearProductAsso :
		 * cropYearProduct1) { if (cropYearProductDto.getProdId() ==
		 * masterCropYearProductAsso.getMasterProduct().getPkProdId()) { flag =
		 * false; break; } } if (flag) { MasterProduct product; product =
		 * productRepository.findOne(cropYearProductDto.getProdId());
		 * MasterCropYearProductAsso cropYearProduct = cropYearproductRepository
		 * .saveAndFlush(cropYearProductDtoToModel(masterCropYear, product,
		 * statusId, Constants.UPDATE, cropYearProductDto));
		 * cropYearProductAssos.add(cropYearProduct); }
		 * 
		 * }
		 * 
		 * for (MasterCropYearProductAsso masterCropYearProductAsso :
		 * cropYearProduct1) { Boolean flag = true;
		 * 
		 * for (CropYearProductDto cropYearProductDto :
		 * cropYearDto.getCropYearProductDto()) { if
		 * (cropYearProductDto.getProdId() ==
		 * masterCropYearProductAsso.getMasterProduct().getPkProdId()) { flag =
		 * false; break; } } if (flag) { MasterProduct product; product =
		 * productRepository.findOne(cropYearProductDto.getProdId());
		 * MasterCropYearProductAsso cropYearProduct = cropYearproductRepository
		 * .saveAndFlush(cropYearProductDtoToModel(masterCropYear, product,
		 * statusId, Constants.UPDATE, cropYearProductDto));
		 * cropYearProductAssos.add(cropYearProduct); }
		 * 
		 * }
		 */
		/*
		 * for (CropYearProductDto cropYearProductDto :
		 * cropYearDto.getCropYearProductDto()) {
		 * 
		 * MasterProduct product; product =
		 * productRepository.findOne(cropYearProductDto.getProdId());
		 * MasterCropYearProductAsso cropYearProduct =
		 * cropYearproductRepository.saveAndFlush(
		 * cropYearProductDtoToModel(masterCropYear, product, statusId,
		 * Constants.UPDATE, cropYearProductDto)); statusName =
		 * statusMap.get(cropYearProduct.getFkStatusId());
		 * cropYearProductAssos.add(cropYearProduct); }
		 */

		/*
		 * MasterProduct product; product =
		 * productRepository.findOne(cropYearDto.getCropYearProductDto().get(0).
		 * getProdId()); MasterCropYearProductAsso cropYearProduct =
		 * cropYearproductRepository.saveAndFlush(cropYearProductDtoToModel(
		 * masterCropYear, product, statusId, Constants.UPDATE,
		 * cropYearDto.getCropYearProductDto().get(0))); statusName =
		 * statusMap.get(cropYearProduct.getFkStatusId());
		 * cropYearProductAssos.add(cropYearProduct);
		 */
		/*
		 * List<CropYearProductDto> productDtos =
		 * cropYearProductModelToDto(cropYearProductAssos, statusName); dto =
		 * cropYearModelToDto(masterCropYear, productDtos, statusName);
		 * 
		 * return dto;
		 */
	}

	public CropYearDto cropYearModelToDto(MasterCropYear masterCropYear, List<CropYearProductDto> productDtos,
			String statusName) {
		log.info("In CropYearService class cropYearModelToDto().");
		CropYearDto cropYearDto = new CropYearDto();
		cropYearDto.setCropYearId(masterCropYear.getPkCropYearId());
		cropYearDto.setCropYearCode(masterCropYear.getCropYearCode());
		cropYearDto.setCropYearName(masterCropYear.getCropYearName());
		cropYearDto.setCropYearDescription(masterCropYear.getCropYearDesc());
		cropYearDto.setCreatedBy(masterCropYear.getCreatedBy());
		cropYearDto.setCreatedDate(masterCropYear.getCreatedDate());
		cropYearDto.setModifiedBy(masterCropYear.getModifiedBy());
		cropYearDto.setModifiedDate(masterCropYear.getModifiedDate());
		cropYearDto.setCropYearProductDto(productDtos);
		cropYearDto.setStatusName(statusName);
		return cropYearDto;

	}

	public CropYearDto readById(Integer cropYearId) {
		MasterCropYear cropYear = cropYearRepository.findOne(cropYearId);
		List<MasterCropYearProductAsso> cropYearProductList = cropYearproductRepository.findByCropYearId(cropYearId);
		Map<Integer, String> statusMap = serviceCall.getAllStatus();
		CropYearDto cropYearDto = new CropYearDto();
		cropYearDto.setCropYearCode(cropYear.getCropYearCode());
		cropYearDto.setCropYearId(cropYear.getPkCropYearId());
		cropYearDto.setCropYearName(cropYear.getCropYearName());
		cropYearDto.setCropYearDescription(cropYear.getCropYearDesc());
		List<CropYearProductDto> cropYearProductDtoList = new ArrayList<>();
		for (MasterCropYearProductAsso product : cropYearProductList) {
			CropYearProductDto cropYearProductDto = new CropYearProductDto();
			cropYearProductDto.setCropYearProductAssoId(product.getPkCropYearProductId());
			cropYearProductDto.setProdCode(product.getMasterProduct().getProdCode());
			cropYearProductDto.setProdId(product.getMasterProduct().getPkProdId());
			cropYearProductDto.setProdName(product.getMasterProduct().getProdName());
			cropYearProductDtoList.add(cropYearProductDto);
		}
		cropYearDto.setCropYearProductDto(cropYearProductDtoList);
		cropYearDto.setStatusId(cropYear.getFkStatusId());
		cropYearDto.setStatusName(statusMap.get(cropYear.getFkStatusId()));
		cropYearDto.setCreatedBy(cropYear.getCreatedBy());
		cropYearDto.setCreatedDate(cropYear.getCreatedDate());
		cropYearDto.setModifiedBy(cropYear.getModifiedBy());
		cropYearDto.setModifiedDate(cropYear.getModifiedDate());
		return cropYearDto;

	}
}

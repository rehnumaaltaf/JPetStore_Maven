package com.olam.score.reference.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.olam.score.common.constants.Constants;
import com.olam.score.common.constants.ResponseConstants;
import com.olam.score.reference.domain.MasterCurrency;
import com.olam.score.reference.dto.CurrencyDTO;
import com.olam.score.reference.repository.CurrencyRepository;
import com.olam.score.common.util.ScoreBaseException;
import com.olam.score.common.util.WebServiceCallUtil;

@Service
public class CurrencyService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private CurrencyRepository currencyRepository;
	
	@Autowired
	private WebServiceCallUtil serviceCall;

	@Transactional
	public String create(CurrencyDTO inputData) {
		String addStatus = ResponseConstants.CURRENCY_ADD_SUCESS;
		MasterCurrency masterCurrency = new MasterCurrency();
		masterCurrency = getMappingCurrency(inputData, masterCurrency);
		if(currencyRepository.uniqueCurrCode(masterCurrency.getCurrencyCode()) > 0 && currencyRepository.uniqueCurrName(masterCurrency.getCurrencyName()) > 0)
		{
			throw new ScoreBaseException(masterCurrency.getPkCurrencyId(), "Currency Name and code exist in database",
					HttpStatus.CONFLICT);
			
		}
		if (currencyRepository.uniqueCurrCode(masterCurrency.getCurrencyCode()) > 0) {
			throw new ScoreBaseException(masterCurrency.getPkCurrencyId(), "Currency Code exist in database",
					HttpStatus.CONFLICT);
		}
		if (currencyRepository.uniqueCurrName(masterCurrency.getCurrencyName()) > 0) {
			throw new ScoreBaseException(masterCurrency.getPkCurrencyId(), "Currency Name exist in database",
					HttpStatus.CONFLICT);
		}
		
		currencyRepository.saveAndFlush(masterCurrency);
		return addStatus;
	}

	private MasterCurrency getMappingCurrency(CurrencyDTO inputData, MasterCurrency masterCurrency) {
		String action = inputData.getAction();
        if (action.equalsIgnoreCase(Constants.SAVE)) {
              log.info("===draft action to perform for create ===");
              masterCurrency.setFkStatusId(returnStatusIdByName(Constants.DRAFT));
        } else if (action.equalsIgnoreCase(Constants.SUBMIT)) {
              log.info("===save(active) action to perform for create ===");
              masterCurrency.setFkStatusId(returnStatusIdByName(Constants.ACTIVE));
        }else if (action.equalsIgnoreCase(Constants.DEACTIVE)) {
              log.info("===save(Deactive) action to perform for create ===");
              masterCurrency.setFkStatusId(returnStatusIdByName(Constants.INACTIVE));
        }
        else if (action.equalsIgnoreCase(Constants.INACTIVE)) {
            log.info("===save(Deactive) action to perform for create ===");
            masterCurrency.setFkStatusId(returnStatusIdByName(Constants.ACTIVE));
      }
        else if (action.equalsIgnoreCase(Constants.ACTIVE)) {
            log.info("===save(Deactive) action to perform for create ===");
            masterCurrency.setFkStatusId(returnStatusIdByName(Constants.INACTIVE));
      }


		masterCurrency.setPkCurrencyId(inputData.getPkCurrencyId());
		masterCurrency.setCreatedBy(inputData.getCreatedBy());
		masterCurrency.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		masterCurrency.setCurrencyCode(inputData.getCurrencyCode());
		masterCurrency.setCurrencyDesc(inputData.getCurrencyDesc());
		masterCurrency.setCurrencyName(inputData.getCurrencyName());
		masterCurrency.setCurrencySymbol(inputData.getCurrencySymbol());
		
		//masterCurrency.setFkStatusId(inputData.getFkStatusId());

		return masterCurrency;
	}

	@Transactional
	public List<CurrencyDTO> readAll() {

		List<MasterCurrency> listCurrency = currencyRepository.getAllCurrency();
		List<CurrencyDTO> masterCurrencyList = new ArrayList<>();
		for (MasterCurrency listCurr : listCurrency) {
			CurrencyDTO model = new CurrencyDTO();
			model.setPkCurrencyId(listCurr.getPkCurrencyId());
			model.setCurrencyCode(listCurr.getCurrencyCode());
			model.setCurrencyDesc(listCurr.getCurrencyDesc());
			model.setCurrencyName(listCurr.getCurrencyName());
			model.setCurrencySymbol(listCurr.getCurrencySymbol());
			model.setCreatedBy(listCurr.getCreatedBy());
			model.setCreatedDate(listCurr.getCreatedDate());
			model.setModifiedBy(listCurr.getModifiedBy());
			model.setModifiedDate(listCurr.getModifiedDate());
			model.setFkStatusId(listCurr.getFkStatusId());
			model.setStatusName(returnStatusNameById(listCurr.getFkStatusId()));
			masterCurrencyList.add(model);
		}
		return masterCurrencyList;
	}
	
	public String returnStatusNameById(int id)
	{
		Map<Integer, String> statusMap = serviceCall.getAllStatus();
		return statusMap.get(id);
	}

	@Transactional
	public String update(CurrencyDTO inputData) {
		String addStatus = ResponseConstants.CURRENCY_UPDATED_SUCESS;
		MasterCurrency masterCurrency = new MasterCurrency();
		masterCurrency = getMappingCurrency(inputData, masterCurrency);
		if(currencyRepository.getByCurrencyCodeAndPkCurrencyIdNotIn(masterCurrency.getCurrencyCode(), masterCurrency.getPkCurrencyId()).size()>0 && currencyRepository.getByCurrencyNameAndPkCurrencyIdNotIn(masterCurrency.getCurrencyName(), masterCurrency.getPkCurrencyId()).size()>0)
		{
			throw new ScoreBaseException(masterCurrency.getPkCurrencyId(), "Currency Name and code exist in database",
					HttpStatus.CONFLICT);
			
		}
		if (currencyRepository.getByCurrencyCodeAndPkCurrencyIdNotIn(masterCurrency.getCurrencyCode(), masterCurrency.getPkCurrencyId()).size()>0) {
			throw new ScoreBaseException(masterCurrency.getPkCurrencyId(), "Currency Code exist in database",
					HttpStatus.CONFLICT);
		}
		if (currencyRepository.getByCurrencyNameAndPkCurrencyIdNotIn(masterCurrency.getCurrencyName(), masterCurrency.getPkCurrencyId()).size()>0) {
			throw new ScoreBaseException(masterCurrency.getPkCurrencyId(), "Currency Name exist in database",
					HttpStatus.CONFLICT);
		}
		masterCurrency.setModifiedBy(inputData.getModifiedBy());
		masterCurrency.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		// masterCurrency.setFkStatusId(inputData.getFkStatusId());
		currencyRepository.saveAndFlush(masterCurrency);

		return addStatus;
	}

	@Transactional
	public String delete(Integer currencyId) {

		String deleteStatus = ResponseConstants.CURRENCY_DELETE_SUCESS;
		try {
			currencyRepository.delete(currencyId);
		} catch (Exception e) {
			deleteStatus = ResponseConstants.CURRENCY_DELETE_FAILURE;
			log.error("Delete:", e);

		}
		return deleteStatus;
	}

	public List<String> suggestName(String currencyName) {

		List<String> suggestCurrName = null;
		try {
			suggestCurrName = currencyRepository.suggestCurrName(currencyName);
		} catch (Exception e) {
			log.error("Suggest Name:", e);
		}

		return suggestCurrName;
	}

	public List<String> suggestCode(String currencyCode) {

		List<String> suggestCurrCode = null;
		try {
			suggestCurrCode = currencyRepository.suggestCurrCode(currencyCode);
		} catch (Exception e) {
			log.error("Suggest Code:", e);
		}

		return suggestCurrCode;
	}

	public int uniqueName(String currencyName) {

		int uniqueCurrName = 0;
		try {
			uniqueCurrName = currencyRepository.uniqueCurrName(currencyName);
		} catch (Exception e) {
			log.error("Unique Name:", e);
		}

		return uniqueCurrName;
	}

	public int uniqueCode(String currencyCode) {

		int uniqueCurrCode = 0;
		try {
			uniqueCurrCode = currencyRepository.uniqueCurrCode(currencyCode);
		} catch (Exception e) {
			log.error("Unique Code:", e);
		}

		return uniqueCurrCode;
	}

	public List<String> getDropdownList() {
		List<String> currSymbollist = new ArrayList<>();
		currSymbollist.add("ƒ");
		currSymbollist.add("$");
		currSymbollist.add("BZ$");
		currSymbollist.add("R$");
		currSymbollist.add("£");
		currSymbollist.add("€");
		currSymbollist.add("¢");
		currSymbollist.add("¥");
		return currSymbollist;

	}

	private Sort sortByIdDsc() {
		return new Sort(Sort.Direction.DESC, "pkCurrencyId");
	}

	public List<MasterCurrency> getAllCurrency() {
		return currencyRepository.getActiveCurrency(1);
	}

	public MasterCurrency returnCurrencyById(int id) {
		return currencyRepository.getByPkCurrencyId(id);
	}

	public CurrencyDTO getCurrencyById(Integer id) {
		CurrencyDTO currencyDTO = null;
		MasterCurrency masterCurrency = currencyRepository.getCurrencyById(id);
		if (masterCurrency != null) {
			currencyDTO = new CurrencyDTO();
			currencyDTO.setPkCurrencyId(masterCurrency.getPkCurrencyId());
			currencyDTO.setCurrencyCode(masterCurrency.getCurrencyCode());
			currencyDTO.setCurrencyName(masterCurrency.getCurrencyName());
			currencyDTO.setCurrencyDesc(masterCurrency.getCurrencyDesc());
			currencyDTO.setCurrencySymbol(masterCurrency.getCurrencySymbol());
			currencyDTO.setFkStatusId(masterCurrency.getFkStatusId());
			currencyDTO.setStatusName(returnStatusNameById(masterCurrency.getFkStatusId()));
		}
		return currencyDTO;
	}
	
	public int returnStatusIdByName(String name)
    {
          Map<Integer, String> statusMap = serviceCall.getAllStatus();
          Optional<Integer> statusId = statusMap.entrySet().stream()
                      .filter(e -> e.getValue().equals(name)).map(Map.Entry::getKey).findFirst();
          if (statusId.isPresent()) {
                log.info("===Got Status Id in Status map ===");
                return statusId.get();
          }
          return 0;
    }

}

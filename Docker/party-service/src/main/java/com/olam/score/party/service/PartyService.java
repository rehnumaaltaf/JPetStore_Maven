package com.olam.score.party.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.olam.score.common.util.ScoreBaseException;
import com.olam.score.party.domain.MasterParty;
import com.olam.score.party.dto.PartyDto;
import com.olam.score.party.repository.PartyRepository;

@Service
public class PartyService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private PartyRepository repository;

	@Autowired
	EntityManager entityManager;

	@Transactional
	public List<PartyDto> readAll() {
		List<PartyDto> newList = new ArrayList<>();
		List<String> sortColumns = new ArrayList<>();
		sortColumns.add("createdDate");
		sortColumns.add("modifiedDate");
		Sort sort = new Sort(Direction.DESC, sortColumns);
		List<MasterParty> oldList = repository.findAll(sort);
		if (oldList == null)
			throw new ScoreBaseException(0, "No Record Found", HttpStatus.NOT_FOUND);
		for (MasterParty masterParty : oldList) {
			PartyDto dto = generatePartyDto(masterParty);
			newList.add(dto);
		}
		return newList;
	}

	@Transactional
	public PartyDto readById(Integer id) {
		PartyDto dto;
		if (id == null)
			throw new ScoreBaseException(0, "PartyId : " + id, HttpStatus.NOT_FOUND);
		MasterParty masterParty = repository.findOne(id);
		if (masterParty == null)
			throw new ScoreBaseException(0, "PartyId : " + id + " not found in database", HttpStatus.NOT_FOUND);

		dto = generatePartyDto(masterParty);
		return dto;
	}

	private PartyDto generatePartyDto(MasterParty masterParty) {
		PartyDto dto = new PartyDto();
		dto.setPartyId(masterParty.getPkPartyId());
		dto.setPartyName(masterParty.getPartyName());
		dto.setPartyCode(masterParty.getPartyCode());
		return (dto);
	}

	@Transactional
	public List<PartyDto> readAllByPartyTypeName(String partyTypeName) {
		List<PartyDto> newList = new ArrayList<>();
		Query query = entityManager.createNativeQuery(
				"select p.* from party.MASTER_PARTY p "
						+ "join party.MASTER_PARTY_PARTY_TYPE ppt on p.PK_PARTY_ID=ppt.FK_PARTY_ID "
						+ "join party.MASTER_PARTY_TYPE pt on pt.PK_PARTY_TYPE_ID=ppt.FK_PARTY_TYPE_ID where upper(pt.PARTY_TYPE_NAME) = :partyTypeName",
				MasterParty.class);
		query.setParameter("partyTypeName", partyTypeName);
		List<MasterParty> oldList = query.getResultList();

		for (MasterParty masterGeo : oldList) {
			PartyDto partyDto = generatePartyDto(masterGeo);

			newList.add(partyDto);
		}

		return newList;
	}
	
	public List<PartyDto> readAllPartyForLimitByClass(String partyForLimitByClass, String party) {

		List<PartyDto> newList = new ArrayList<>();
		Query query;
		if (party.equalsIgnoreCase("TRUE")) {
			query = entityManager.createNativeQuery("select  party.* from party.MASTER_PARTY party where "
					+ "party.PK_PARTY_ID not in(select p.PK_PARTY_ID from party.MASTER_PARTY p "
					+ "join party.MASTER_PARTY_PARTY_CLASS ppc on p.PK_PARTY_ID=ppc.FK_PARTY_ID "
					+ "join party.MASTER_PARTY_CLASSIFICATION pc on pc.PK_PARTY_CLASS_ID=ppc.FK_PARTY_CLASS_ID where upper(pc.PARTY_CLASS_NAME) = :partyForLimitByClass)",
					MasterParty.class);
		}else {
			query = entityManager.createNativeQuery(
					"select p.* from party.MASTER_PARTY p "
							+ "join party.MASTER_PARTY_PARTY_CLASS ppc on p.PK_PARTY_ID=ppc.FK_PARTY_ID "
							+ "join party.MASTER_PARTY_CLASSIFICATION pc on pc.PK_PARTY_CLASS_ID=ppc.FK_PARTY_CLASS_ID where upper(pc.PARTY_CLASS_NAME) = :partyForLimitByClass",
					MasterParty.class);
		}
		query.setParameter("partyForLimitByClass", partyForLimitByClass);
		List<MasterParty> oldList = query.getResultList();

		for (MasterParty masterGeo : oldList) {
			PartyDto partyDto = generatePartyDto(masterGeo);

			newList.add(partyDto);
		}

		return newList;
	
	}
}

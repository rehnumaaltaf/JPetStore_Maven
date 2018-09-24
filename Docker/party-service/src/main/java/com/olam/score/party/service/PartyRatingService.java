package com.olam.score.party.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olam.score.party.dto.PartyRatingDTO;
import com.olam.score.party.repository.PartyRatingRepository;


@Service
public class PartyRatingService {

	@Autowired
	private PartyRatingRepository ratingRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	public List<PartyRatingDTO> fetchPartyRating()
	{
		List<PartyRatingDTO> ratingList = new ArrayList<PartyRatingDTO>();
		ratingRepo.findAll().forEach(data -> {
			ratingList.add(modelMapper.map(data, PartyRatingDTO.class));
		});
		return ratingList;
	}

}

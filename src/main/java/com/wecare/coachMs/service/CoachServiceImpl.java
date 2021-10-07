package com.wecare.coachMs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecare.coachMs.dto.CoachDTO;
import com.wecare.coachMs.dto.LoginDTO;
import com.wecare.coachMs.entity.CoachEntity;
import com.wecare.coachMs.repo.CoachRepo;

@Service
public class CoachServiceImpl implements CoachService {

	@Autowired
	CoachRepo coachRepo;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	@Transactional
	public String createRoute(CoachDTO coachDTO) {
		log.info("inside createRoute ");
		CoachEntity coachEntity=null;
		try {
			coachEntity=CoachDTO.prepareCoachEntity(coachDTO);
			coachRepo.saveAndFlush(coachEntity);
		} catch (Exception e) {
			log.error("error in creating Coach Entity for : "+coachDTO +":"+e);
			e.printStackTrace();
		}
		
		log.info("coach created with coach Id : "+coachEntity.getCoachId());
		return coachEntity.getCoachId();
	}

	@Override
	public Boolean logInCoach(LoginDTO loginDTO) {
		log.info("inside logInCoach service for : "+loginDTO);
		List<CoachEntity> coachEntities=null;
		try {
			coachEntities=coachRepo.findByName(loginDTO.getName());
			for(CoachEntity c :coachEntities)
				if(c.getPassword().equals(loginDTO.getPassword()))
					return true;
		} catch (Exception e) {
			log.error("error in logInCoach for : "+loginDTO+" || "+e);
		}
		return false;
	}

	@Override
	public CoachDTO getCoach(String coachId) {
		log.info("inside getCoach service for : "+coachId);
		CoachDTO coachDTO=null;
		Optional<CoachEntity> coachEntityOptional=null;
		CoachEntity coachEntity=null;
		try {
			coachEntityOptional=coachRepo.findById(coachId);
			if(coachEntityOptional.isPresent())
				coachEntity=coachEntityOptional.get();
			coachDTO=coachEntity!=null?CoachDTO.prepareCoachDTO(coachEntity):null;
		} catch (Exception e) {
			log.error("error in fetching Coach for coachId : "+coachId);
		}
		
		return coachDTO;
	}


	@Override
	public List<CoachDTO> getAllCoach() {
		log.info("inside getAllCoach service ");
		List<CoachDTO> coachDtos=null;
		List<CoachEntity> coaches=null;
		
		try {
			coaches=coachRepo.findAll();
			coachDtos=new ArrayList<>();
			for(CoachEntity c : coaches)
				coachDtos.add(CoachDTO.prepareCoachDTO(c));
			
		} catch (Exception e) {
			log.error("Error in fething allCoaches service");
			e.printStackTrace();
			coachDtos=null;
		}
		return coachDtos;
	}
}

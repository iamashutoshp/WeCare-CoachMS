package com.wecare.coachMs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecare.coachMs.dto.CoachDTO;
import com.wecare.coachMs.entity.CoachEntity;
import com.wecare.coachMs.repo.CoachRepo;

@Service
public class CoachServiceImpl implements CoachService {

	@Autowired
	CoachRepo coachRepo;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
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
}

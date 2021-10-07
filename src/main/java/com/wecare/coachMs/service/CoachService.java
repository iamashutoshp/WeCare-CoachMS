package com.wecare.coachMs.service;

import java.util.List;

import com.wecare.coachMs.dto.CoachDTO;
import com.wecare.coachMs.dto.LoginDTO;

public interface CoachService {

	String createRoute(CoachDTO coachDTO);
	Boolean logInCoach(LoginDTO loginDTO);
	CoachDTO getCoach(String coachId);
	List<CoachDTO> getAllCoach();
}

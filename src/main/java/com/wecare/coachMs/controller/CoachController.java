package com.wecare.coachMs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wecare.coachMs.dto.CoachDTO;
import com.wecare.coachMs.responseModel.CustomResponse;
import com.wecare.coachMs.service.CoachService;

@RestController
@RequestMapping("/coaches")
@Validated
@CrossOrigin
public class CoachController {

	@Autowired
	private CoachService coachService;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@PostMapping(consumes = "application/json")
	@HystrixCommand(fallbackMethod = "createCoachFallback")
	public CustomResponse createCoach(@Valid @RequestBody CoachDTO coachDTO, Errors errors) {
		log.info("post request made for creating coach : "+coachDTO);
		log.info("Inside createCoach func");
		String coachId="";
		CustomResponse response = new CustomResponse(HttpStatus.OK, "", "");
		if (errors.hasErrors()) {
			log.info("Validation failed in createCoach");
			List<FieldError> allErrors = errors.getFieldErrors();
			List<String> details = new ArrayList<>();
			for (FieldError e : allErrors)
				details.add(e.getField().toUpperCase() + ":" + e.getDefaultMessage() + " | ");

			response.setMessage("Invalid input");
			response.setDetails(details.toString());
			return response;
		}

		coachId=coachService.createRoute(coachDTO);
		response.setMessage(coachId);
		response.setDetails("New Coach created");
		
		log.info("Coach Created with CoachID : "+coachId);
		return response;
	}
	
	public CustomResponse createCoachFallback(CoachDTO coachDTO, Errors errors) {
		log.info("createCoachFallback : error in creating coach : "+coachDTO);
		CustomResponse response = new CustomResponse(HttpStatus.OK, "Error in creating coach", errors.toString());
		return response;
	}
	
}

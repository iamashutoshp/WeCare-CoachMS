package com.wecare.coachMs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wecare.coachMs.dto.BookingDTO;
import com.wecare.coachMs.dto.CoachDTO;
import com.wecare.coachMs.dto.LoginDTO;
import com.wecare.coachMs.exception.NoCoachPresentException;
import com.wecare.coachMs.exception.NoSuchCoachException;
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
		log.info("post request made for creating coach : " + coachDTO);
		log.info("Inside createCoach func");
		String coachId = "";
		CustomResponse response = new CustomResponse(HttpStatus.OK, "", "");
		if (errors.hasErrors()) {
			log.info("Validation failed in createCoach");
			List<FieldError> allErrors = errors.getFieldErrors();
			List<String> details = new ArrayList<>();
			for (FieldError e : allErrors)
				details.add(e.getField().toUpperCase() + ":" + e.getDefaultMessage() + " | ");

			response.setMessage("Invalid input");
			response.setResult(details.toString());
			return response;
		}

		coachId = coachService.createRoute(coachDTO);
		response.setMessage("New Coach created");
		response.setResult(coachId);

		log.info("Coach Created with CoachID : " + coachId);
		return response;
	}

	public CustomResponse createCoachFallback(CoachDTO coachDTO, Errors errors) {
		log.info("createCoachFallback : error in creating coach : " + coachDTO);
		CustomResponse response = new CustomResponse(HttpStatus.OK, "Error in creating coach", errors.toString());
		return response;
	}

	@PostMapping(consumes = "application/json", value = "/login")
	@HystrixCommand(fallbackMethod = "loginCoachFallback")
	public ResponseEntity<Boolean> loginCoach(@RequestBody LoginDTO login) {
		log.info("inside loginCoach | creds : " + login);
		Boolean res = coachService.logInCoach(login);
		return ResponseEntity.ok(res);
	}

	public ResponseEntity<Boolean> loginCoachFallback(LoginDTO login) {
		log.info("inside loginCoachFallBack : some error occured in logInCoach | creds : " + login);
		return ResponseEntity.ok(false);
	}

	@GetMapping(value = "/{coachId}")
	@HystrixCommand(fallbackMethod = "getCoachProfileFallback")
	public ResponseEntity<CoachDTO> getCoachProfile(@PathVariable("coachId") String coachId) throws NoSuchCoachException {
		log.info("inside getCoachProfile for coachId : " + coachId);
		CoachDTO coachDTO = null;
		coachDTO = coachService.getCoach(coachId);
		
		if(coachDTO==null)
			throw new NoSuchCoachException("Coach profile does not exists");
		return ResponseEntity.ok(coachDTO);
	}
	
	public ResponseEntity<String> getCoachProfileFallback(@PathVariable("coachId") String coachId) {
		log.info("inside getCoachProfileFallback for coachId : " + coachId);
		return ResponseEntity.ok("Unable to Fetch Profile.");
	}

	@GetMapping(value = "/all")
	@HystrixCommand(fallbackMethod = "showAllCoachesFallback")
	public List<CoachDTO> showAllCoaches() throws NoCoachPresentException{
		log.info("inside showAllCoaches method");
		List<CoachDTO> coaches=null;
		
		coaches=coachService.getAllCoach();
		if(coaches==null)
			throw new NoCoachPresentException("No coaches are present");
		return coaches;
	}
	
	public List<CoachDTO> showAllCoachesFallback(){
		log.info("error in showAllCoaches : inside showAllCoachesFallback method");
		List<CoachDTO> coaches=null;
		return coaches;
	}


	@GetMapping(value = "/booking/{coachId}")
	public List<BookingDTO> showMySchedule(@PathVariable("coachId") String coachId){
		log.info("inside showMySchedule for coachId : "+coachId);
		List<BookingDTO> bookingDtos=null;
//		bookingDtos= fetch Schedule of Coach from Booking Rest End point
		return bookingDtos;
	}
}

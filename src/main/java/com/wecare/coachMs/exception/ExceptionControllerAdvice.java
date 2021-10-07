package com.wecare.coachMs.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.wecare.coachMs.responseModel.CustomResponse;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	private ResponseEntity<CustomResponse> handleEntityNotFound(EntityNotFoundException ex) {
		CustomResponse error = new CustomResponse(HttpStatus.NOT_FOUND, "Entity not found", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoSuchCoachException.class)
	public ResponseEntity<ErrorMessage> noSuchCoachException(NoSuchCoachException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}
}
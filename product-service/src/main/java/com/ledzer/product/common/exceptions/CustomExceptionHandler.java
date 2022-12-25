package com.ledzer.product.common.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	private String INCORRECT_REQUEST = "INCORRECT_REQUEST";
	private String BAD_REQUEST = "BAD_REQUEST";
	private String CONFLICT = "CONFLICT";

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorMessage error = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), INCORRECT_REQUEST, details);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ErrorMessage error = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), BAD_REQUEST, details);
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(Exception ex, WebRequest request) {

		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());

		ErrorMessage error = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), BAD_REQUEST, details);
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {

		List<String> details = new ArrayList<String>();
		details.add(ex.getLocalizedMessage());
		
		ErrorMessage error = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), "Error occurred", details);
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

}

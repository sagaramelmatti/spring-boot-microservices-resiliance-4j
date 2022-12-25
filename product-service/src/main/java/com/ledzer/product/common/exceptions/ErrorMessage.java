package com.ledzer.product.common.exceptions;

import java.util.Date;
import java.util.List;

public class ErrorMessage {
	
	private int statusCode;
	private Date timestamp;
	private String message;
	private List<String> description;

	public ErrorMessage(int statusCode, Date timestamp, String message, List<String> description) {
		this.statusCode = statusCode;
		this.timestamp = timestamp;
		this.message = message;
		this.description = description;
	}

	public ErrorMessage(String bAD_REQUEST, List<String> details) {
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDescription() {
		return description;
	}

	public void setDescription(List<String> description) {
		this.description = description;
	}


}
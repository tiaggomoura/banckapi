package com.bankapi.vo;

import lombok.Data;

@Data
public class ResponseVO {

	private Integer httpCode;
	private String message;
	private Object response;

	public ResponseVO() {
	}

	public ResponseVO(Integer httpCode, String message, Object response) {
		this.httpCode = httpCode;
		this.message = message;
		this.response = response;
	}

	public ResponseVO(Integer httpCode, String message) {
		this.httpCode = httpCode;
		this.message = message;
		this.response = null;
	}
}
package com.bankapi.exception;

import org.springframework.http.HttpStatus;

public class CvcBankApiException extends Exception {

	private static final long serialVersionUID = 1L;

	private final HttpStatus httpStatus;
	private final String message;

	public CvcBankApiException(final String message, final Throwable exception) {
		super(message, exception);
		this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		this.message = message;
	}

	public CvcBankApiException(final String message, final Throwable exception, final HttpStatus httpStatus) {
		super(message, exception);
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public CvcBankApiException(final String message, final HttpStatus httpStatus) {
		super(message, null);
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	@Override
	public String getMessage() {
		return message;
	}
}

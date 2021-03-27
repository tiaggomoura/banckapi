package com.bankapi.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bankapi.vo.ErrorDetailsVO;
import com.bankapi.vo.ResponseVO;

/**
 * 
 * @author Tiago M Rodrigues
 *
 */
@ControllerAdvice
public class CvcBankApiExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LogManager.getLogger(CvcBankApiExceptionHandler.class);

	/**
	 * Método para padronizar resposta as exceções da aplicação.
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(CvcBankApiException.class)
	public ResponseEntity<ResponseVO> customHandleCvcBankApi(CvcBankApiException ex, WebRequest request) {
		ResponseVO response = new ResponseVO(ex.getHttpStatus().value(), ex.getMessage());
		LOGGER.error(ex.getMessage() + " - path= " + request.getContextPath(), ex);

		return new ResponseEntity<>(response, ex.getHttpStatus());
	}

	/**
	 * Sobrescrevendo esse método conseguimos acessar a lista de campos com erro dos
	 * nossos atributos anotados com validations ex: @NotNull...já que anotamos
	 * nosso método no controller com @Valid
	 */

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errorList = ex.getBindingResult().getFieldErrors().stream()
				.map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.toList());
		ErrorDetailsVO errorDetails = new ErrorDetailsVO(HttpStatus.BAD_REQUEST, "Please, check the fields with errors",
				errorList);
		return handleExceptionInternal(ex, errorDetails, headers, errorDetails.getStatus(), request);
	}

}

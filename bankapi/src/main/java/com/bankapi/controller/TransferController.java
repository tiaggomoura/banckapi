package com.bankapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapi.exception.CvcBankApiException;
import com.bankapi.service.TransferService;
import com.bankapi.vo.RequestTransferVO;
import com.bankapi.vo.ResponseVO;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/transfers")
public class TransferController {

	@Autowired
	private TransferService service;

	@ApiOperation(value = "Agendar uma nova Transferência")
	@PostMapping("/schedule")
	public ResponseVO create(@RequestBody @Valid RequestTransferVO requestTransferVO) throws CvcBankApiException {
		ResponseVO response = new ResponseVO();
		response.setResponse(this.service.create(requestTransferVO));
		response.setHttpCode(HttpStatus.CREATED.value());
		response.setMessage(HttpStatus.CREATED.name());
		return response;
	}

	@ApiOperation(value = "Buscar Transferência por ID")
	@GetMapping("/{id}")
	public ResponseVO findById(@PathVariable Long id) throws CvcBankApiException {
		ResponseVO response = new ResponseVO();
		response.setResponse(this.service.findById(id));
		response.setHttpCode(HttpStatus.OK.value());
		response.setMessage(HttpStatus.OK.name());
		return response;
	}

	@ApiOperation(value = "Buscar Transferências de uma conta")
	@GetMapping("/customer/{id}")
	public ResponseVO findAllByCustomer(@PathVariable Long id) throws CvcBankApiException {
		ResponseVO response = new ResponseVO();
		response.setResponse(this.service.findByAccount(id));
		response.setHttpCode(HttpStatus.OK.value());
		response.setMessage(HttpStatus.OK.name());
		return response;
	}

}
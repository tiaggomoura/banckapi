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

import com.bankapi.entities.Customer;
import com.bankapi.exception.CvcBankApiException;
import com.bankapi.service.AccountService;
import com.bankapi.service.CustomerService;
import com.bankapi.vo.RequestAccountVO;
import com.bankapi.vo.ResponseVO;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/account")
public class AccountController {

	@Autowired
	private AccountService service;

	@Autowired
	private CustomerService customerService;

	@ApiOperation(value = "Buscar todas as contas.")
	@GetMapping
	public ResponseVO getAll() throws CvcBankApiException {
		ResponseVO response = new ResponseVO();
		response.setResponse(service.finddAll());
		response.setHttpCode(HttpStatus.OK.value());
		response.setMessage(HttpStatus.OK.name());
		return response;
	}

	// @GetMapping("/customer/{id}")
	// public ResponseVO getByCustomer(@PathVariable("id") Long id) throws
	// CvcBankApiException {
	// ResponseVO response = new ResponseVO();
	// response.setResponse(service.findById(id)); // ajustar
	// response.setHttpCode(HttpStatus.OK.value());
	// response.setMessage(HttpStatus.OK.name());
	// return response;
	// }

	@ApiOperation(value = "Buscar conta por ID.")
	@GetMapping("/{id}")
	public ResponseVO getInfo(@PathVariable("id") Long id) throws CvcBankApiException {
		ResponseVO response = new ResponseVO();
		response.setResponse(service.findById(id));
		response.setHttpCode(HttpStatus.OK.value());
		response.setMessage(HttpStatus.OK.name());
		return response;
	}

	 @ApiOperation(value = "Criar uma nova Conta.")
	@PostMapping(value = "/create")
	public ResponseVO create(@Valid @RequestBody RequestAccountVO requestAccountVO) throws CvcBankApiException {
		ResponseVO response = new ResponseVO();
		Customer customer = this.customerService.findById(requestAccountVO.getCustomerId());
		response.setResponse(this.service.create(requestAccountVO, customer));
		response.setHttpCode(HttpStatus.CREATED.value());
		response.setMessage(HttpStatus.CREATED.name());
		return response;
	}

	// @PutMapping("/update/{id}")
	// public ResponseVO updateCustumerAddress(@RequestBody RequestAccountVO
	// requestAccountVO,
	// @PathVariable("id") final Long id) throws CvcBankApiException {
	//
	// ResponseVO response = new ResponseVO();
	// response.setResponse(this.service.update(requestAccountVO, id));
	// response.setHttpCode(HttpStatus.OK.value());
	// response.setMessage(HttpStatus.OK.name());
	//
	// return response;
	// }
	//
	// @DeleteMapping("/delete/{id}")
	// public ResponseVO deleteBy(@PathVariable("id") Long id) throws
	// CvcBankApiException {
	// ResponseVO response = new ResponseVO();
	// response.setResponse(this.service.delete(id));
	// response.setHttpCode(HttpStatus.OK.value());
	// response.setMessage(HttpStatus.OK.name());
	// return response;
	// }

}

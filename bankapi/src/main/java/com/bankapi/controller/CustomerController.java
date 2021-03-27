package com.bankapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapi.entities.Customer;
import com.bankapi.exception.CvcBankApiException;
import com.bankapi.service.CustomerService;
import com.bankapi.vo.RequestCustomerVO;
import com.bankapi.vo.ResponseVO;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

	@Autowired
	private CustomerService service;

	@ApiOperation(value = "Buscar todas as clientes.")
	@GetMapping
	public ResponseVO getAll() throws CvcBankApiException {
		ResponseVO response = new ResponseVO();
		response.setResponse(this.service.finddAll());
		response.setHttpCode(HttpStatus.OK.value());
		response.setMessage(HttpStatus.OK.name());
		return response;
	}

	@ApiOperation(value = "Buscar cliente por ID.")
	@GetMapping("/{id}")
	public ResponseVO getInfo(@PathVariable("id") Long id) throws CvcBankApiException {
		ResponseVO response = new ResponseVO();
		response.setResponse(this.service.findById(id));
		response.setHttpCode(HttpStatus.OK.value());
		response.setMessage(HttpStatus.OK.name());
		return response;
	}
	
	@ApiOperation(value = "Criar um novo cliente.")
	@PostMapping(value = "/create")
	public ResponseVO create(@RequestBody RequestCustomerVO requestCustomerVO) throws CvcBankApiException {
		ResponseVO response = new ResponseVO();
		response.setResponse(this.service.create(new Customer(),requestCustomerVO));
		response.setHttpCode(HttpStatus.CREATED.value());
		response.setMessage(HttpStatus.CREATED.name());
		return response;
	}

	@ApiOperation(value = "Atualizar um cliente.")
	@PutMapping("/update/{id}")
	public ResponseVO updateCustumerAddress(@RequestBody RequestCustomerVO requestCustomerVO,
			@PathVariable("id") final Long id) throws CvcBankApiException {
		ResponseVO response = new ResponseVO();
		response.setResponse(this.service.update(id, requestCustomerVO));
		response.setHttpCode(HttpStatus.OK.value());
		response.setMessage(HttpStatus.OK.name());
		return response;
	}

	@ApiOperation(value = "Deletar um cliente.")
	@DeleteMapping("/delete/{id}")
	public ResponseVO deleteBy(@PathVariable("id") Long id) throws CvcBankApiException {
		ResponseVO response = new ResponseVO();
		response.setResponse(this.service.delete(id));
		response.setHttpCode(HttpStatus.OK.value());
		response.setMessage(HttpStatus.OK.name());
		return response;
	}

}

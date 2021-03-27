package com.bankapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapi.vo.ResponseVO;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping
public class HealthCheck {
	
	@ApiOperation(value = "Ponto de checagem.")
	 @GetMapping(value = "/health")
	  public ResponseVO getHealthResponse() {
	    return new ResponseVO(HttpStatus.OK.value(), HttpStatus.OK.name());
	  }
}

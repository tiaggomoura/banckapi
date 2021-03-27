package com.bankapi.service;

import java.math.BigDecimal;

import com.bankapi.entities.Transfer;
import com.bankapi.exception.CvcBankApiException;

public interface TaxService {

	BigDecimal calculateTax(Transfer transfer) throws CvcBankApiException;

}

package com.bankapi.strategy;

import java.math.BigDecimal;

import com.bankapi.exception.CvcBankApiException;

public interface TaxCalculator {

	BigDecimal calculateTax() throws CvcBankApiException;

}

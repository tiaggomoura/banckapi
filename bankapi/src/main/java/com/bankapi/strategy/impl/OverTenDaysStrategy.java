package com.bankapi.strategy.impl;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;

import com.bankapi.exception.CvcBankApiException;
import com.bankapi.strategy.TaxCalculator;

public class OverTenDaysStrategy implements TaxCalculator {

	protected long days;
	protected BigDecimal transferAmount;

	public OverTenDaysStrategy(long days, BigDecimal transferAmount) {
		this.days = days;
		this.transferAmount = transferAmount;
	}

	@Override
	public BigDecimal calculateTax() throws CvcBankApiException {

		BigDecimal percentTax = BigDecimal.ZERO;

		if (this.days <= 20) {
			percentTax = new BigDecimal(0.08);
		} else if (this.days <= 30) {
			percentTax = new BigDecimal(0.06);
		} else if (this.days <= 40) {
			percentTax = new BigDecimal(0.04);
		} else if (this.days > 40 && this.isGreater1000()) {
			percentTax = new BigDecimal(0.02);
		} else {
			throw new CvcBankApiException("There is no fee for the scheduling period.", HttpStatus.NOT_ACCEPTABLE);
		}

		return percentTax.multiply(this.transferAmount);
	}

	private boolean isGreater1000() {
		return this.transferAmount.compareTo(BigDecimal.valueOf(100000)) >= 0;
	}

}

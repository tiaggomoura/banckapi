package com.bankapi.strategy.impl;

import java.math.BigDecimal;

import com.bankapi.strategy.TaxCalculator;

public class UpTenDaysStrategy implements TaxCalculator {

	protected long days;
	protected BigDecimal transferAmount;

	public UpTenDaysStrategy(long days, BigDecimal transferAmount) {
		this.days = days;
		this.transferAmount = transferAmount;
	}

	@Override
	public BigDecimal calculateTax() {
		return new BigDecimal(12).multiply(BigDecimal.valueOf(days));
	}

}

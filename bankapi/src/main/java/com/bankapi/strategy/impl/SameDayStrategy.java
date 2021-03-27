package com.bankapi.strategy.impl;

import java.math.BigDecimal;

import com.bankapi.strategy.TaxCalculator;

public class SameDayStrategy implements TaxCalculator {

	protected BigDecimal transferAmount;

	public SameDayStrategy(BigDecimal transferAmount) {
		this.transferAmount = transferAmount;
	}

	@Override
	public BigDecimal calculateTax() {
		return new BigDecimal(3).add((BigDecimal.valueOf(0.03).multiply(this.transferAmount)));
	}
}

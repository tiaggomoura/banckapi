package com.bankapi.strategy;

import java.math.BigDecimal;

import com.bankapi.exception.CvcBankApiException;
import com.bankapi.strategy.impl.OverTenDaysStrategy;
import com.bankapi.strategy.impl.SameDayStrategy;
import com.bankapi.strategy.impl.UpTenDaysStrategy;

public class CalculatorHelper {

	protected TaxCalculator calculationStrategy;

	public CalculatorHelper(long days, BigDecimal transferAmount) {

		if (days == 0) {
			this.calculationStrategy = new SameDayStrategy(transferAmount);
		} else if (days <= 10) {
			this.calculationStrategy = new UpTenDaysStrategy(days, transferAmount);
		} else {
			this.calculationStrategy = new OverTenDaysStrategy(days, transferAmount);
		}

	}

	public BigDecimal calculateTax() throws CvcBankApiException {
		return calculationStrategy.calculateTax();
	}

}

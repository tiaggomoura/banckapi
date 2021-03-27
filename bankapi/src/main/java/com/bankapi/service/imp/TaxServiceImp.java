package com.bankapi.service.imp;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.bankapi.entities.Transfer;
import com.bankapi.exception.CvcBankApiException;
import com.bankapi.service.TaxService;
import com.bankapi.strategy.CalculatorHelper;
import com.bankapi.util.DataUtils;

@Service
public class TaxServiceImp implements TaxService {

	@Override
	@SuppressWarnings("deprecation")
	public BigDecimal calculateTax(Transfer transfer) throws CvcBankApiException {

		long days = DataUtils.diffDays(transfer.getScheduleDate(), transfer.getTransferDate());

		CalculatorHelper helper = new CalculatorHelper(days, transfer.getTransferAmount());

		return helper.calculateTax().setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

}

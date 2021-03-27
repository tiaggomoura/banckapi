package com.bankapi.validator;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;

import com.bankapi.entities.Transfer;
import com.bankapi.exception.CvcBankApiException;

public class ValidatorUtil {

	public static void validTransfer(Transfer source) throws CvcBankApiException {

		if (source.getTransferAmount().compareTo(BigDecimal.ZERO) <= 0) {
			throw new CvcBankApiException("Operation denied: Enter a value greater than ZERO.", HttpStatus.BAD_REQUEST);
		}

		if (source.getFavored().equals(source.getOringin())) {
			throw new CvcBankApiException("Operation denied: Transfer accounts cannot be the same.",
					HttpStatus.BAD_REQUEST);
		}

		if (source.getOringin().getBalance().compareTo(source.getTransferAmount()) < 0) {
			throw new CvcBankApiException(
					"Operation denied: The source account does not have enough balance for the transfer.",
					HttpStatus.BAD_REQUEST);
		}

	}

}

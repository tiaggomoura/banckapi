package com.bankapi.service.imp;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bankapi.entities.Account;
import com.bankapi.entities.Transfer;
import com.bankapi.exception.CvcBankApiException;
import com.bankapi.repository.TransferRepository;
import com.bankapi.service.AccountService;
import com.bankapi.service.TaxService;
import com.bankapi.service.TransferService;
import com.bankapi.validator.ValidatorUtil;
import com.bankapi.vo.RequestTransferVO;

@Service
public class TransferServiceImp implements TransferService {

	@Autowired
	private TransferRepository repository;

	@Autowired
	private AccountService accountService;

	@Autowired
	private TaxService taxService;

	@Override
	public Transfer create(RequestTransferVO source) throws CvcBankApiException {

		try {

			Transfer transfer = new Transfer(source).origin(this.getOriginAccount(source))
					.favored(getFavoredAccount(source));

			ValidatorUtil.validTransfer(transfer);

			BigDecimal tax = this.taxService.calculateTax(transfer);
			transfer.setTax(tax);
			transfer.calculateBalanceOrigin();
			transfer.calculateBalanceFavored();

			return this.repository.save(transfer);
		} catch (ParseException e) {
			throw new CvcBankApiException("Invalid Date Format", HttpStatus.BAD_REQUEST);
		}

	}

	private Account getFavoredAccount(RequestTransferVO source) throws CvcBankApiException {
		Account favoredTransfer = accountService.findById(source.getFavored());
		return favoredTransfer;
	}

	private Account getOriginAccount(RequestTransferVO source) throws CvcBankApiException {
		Account originTransfer = accountService.findById(source.getOringin());
		return originTransfer;
	}

	@Override
	public Transfer findById(Long id) throws CvcBankApiException {
		return repository.findById(id)
				.orElseThrow(() -> new CvcBankApiException("Transfer not found for Id: " + id, HttpStatus.NOT_FOUND));
	}

	@Override
	public List<Transfer> findByAccount(Long id) throws CvcBankApiException {
		Account account = this.accountService.findById(id);
		return this.repository.findByOringinOrderByScheduleDateDesc(account);
	}

}

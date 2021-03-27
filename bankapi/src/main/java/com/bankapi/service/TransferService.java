package com.bankapi.service;

import java.util.List;

import com.bankapi.entities.Transfer;
import com.bankapi.exception.CvcBankApiException;
import com.bankapi.vo.RequestTransferVO;

public interface TransferService {

	Transfer create(RequestTransferVO source) throws CvcBankApiException;

	Transfer findById(Long id) throws CvcBankApiException;

	List<Transfer> findByAccount(Long id) throws CvcBankApiException;

}

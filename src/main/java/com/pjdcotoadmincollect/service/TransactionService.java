package com.pjdcotoadmincollect.service;

import com.pjdcotoadmincollect.dto.TransactionDto;
import com.pjdcotoadmincollect.dto.TransactionRequestDto;

import java.util.List;

public interface TransactionService {

    TransactionDto saveTransaction(TransactionRequestDto transactionDto);

    List<TransactionDto> getAllTransactions(Long id);

    TransactionDto findTransactionByUnitId(Long unitId);

    TransactionDto findTransactionById(Long transactionId);
    List<TransactionDto> findTransactionByUnitIdAndAppId(Long unitId, Long appId);

}

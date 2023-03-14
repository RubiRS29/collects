package com.pjdcotoadmincollect.service;

import com.pjdcotoadmincollect.dto.TransactionDto;

import java.util.List;

public interface TransactionService {

    TransactionDto saveTransaction(TransactionDto transactionDto);

    List<TransactionDto> getAllTransactions();

    TransactionDto findTransactionByUnitId(Long unitId);

    TransactionDto findTransactionById(Long transactionId);

}

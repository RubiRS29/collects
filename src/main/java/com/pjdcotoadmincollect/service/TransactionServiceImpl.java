package com.pjdcotoadmincollect.service;

import com.pjdcotoadmincollect.dto.*;
import com.pjdcotoadmincollect.entity.Balance;
import com.pjdcotoadmincollect.entity.Concept;
import com.pjdcotoadmincollect.entity.Transaction;
import com.pjdcotoadmincollect.exceptions.BadRequestException;
import com.pjdcotoadmincollect.exceptions.ResourceNotFoundException;
import com.pjdcotoadmincollect.repository.BalanceRepository;
import com.pjdcotoadmincollect.repository.ConceptRepository;
import com.pjdcotoadmincollect.repository.TransactionRepository;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.pjdcotoadmincollect.mapper.BalanceMapper.INSTANCE_BALANCE;
import static com.pjdcotoadmincollect.mapper.TransactionMapper.INSTANCE_TRANSACTION;
@Service
public class TransactionServiceImpl implements TransactionService{

    private final Logger LOGGER =  LogManager.getLogger(TransactionServiceImpl.class);
    private final TransactionRepository transactionRepository;
    private final ConceptRepository conceptRepository;
    private final BalanceRepository balanceRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  ConceptRepository conceptRepository,
                                  BalanceRepository balanceRepository) {
        this.transactionRepository = transactionRepository;
        this.conceptRepository = conceptRepository;
        this.balanceRepository = balanceRepository;
    }
    @Transactional
    @Override
    public TransactionDto saveTransaction(TransactionRequestDto transactionDto) {

        LOGGER.info("transactionDto");
        LOGGER.info(transactionDto.toString());

        if(ObjectUtils.isEmpty(transactionDto.getAppId())
                || ObjectUtils.isEmpty(transactionDto.getUnit().getUnitId()) ){
            LOGGER.error("App id or Unit id can not be null");
            throw new BadRequestException("App id or Unit id can not be null");
        }

        if(ObjectUtils.isEmpty(transactionDto.getQuantity())
                || ObjectUtils.isEmpty(transactionDto.getConceptId())){
            LOGGER.error("Quantity or Concept id can not be null");
            throw new BadRequestException("Quantity or Concept id can not be null");
        }


        Transaction transaction = INSTANCE_TRANSACTION.requestToTransaction(transactionDto);
        Concept concept = conceptRepository.findById(transactionDto.getConceptId())
                .orElseThrow(() -> new ResourceNotFoundException("Concept no found"));

        transaction.setConcept(concept);

        Balance balance = balanceRepository.findByUnitIdAndAppId(transactionDto.getUnit().getUnitId(), transactionDto.getAppId()).orElse(null);


        if(concept.getOperation().name().equals("SUB")) {

            if (balance != null) {
                balance.setBalance(balance.getBalance() - transactionDto.getQuantity());
                LOGGER.info("update balance of sub");
                balanceRepository.save(balance);

            } else {
                Balance blc = new Balance(
                        transaction.getAppId(),
                        transactionDto.getUnit().getUnitId(),
                        -transactionDto.getQuantity()
                );
                balanceRepository.save(blc);
            }

        }else{

            if(balance !=  null ){

                LOGGER.info("update balance of sum");
                balance.setBalance(balance.getBalance() + transactionDto.getQuantity());
                balanceRepository.save(balance);

            }else{
                Balance blc = new Balance(
                        transaction.getAppId(),
                        transactionDto.getUnit().getUnitId(),
                        transactionDto.getQuantity()
                );
                balanceRepository.save(blc);
            }
        }

        transaction.setUnitName(transactionDto.getUnit().getUnitName());
        transaction.setUnitId(transactionDto.getUnit().getUnitId());

        LOGGER.info("Transaction");
        LOGGER.info(transaction.toString());

        return INSTANCE_TRANSACTION.transactionToDto(transactionRepository.save(transaction));
    }

    @Override
    public List<TransactionDto> getAllTransactions(Long appId) {

        LOGGER.info("ALL TRANSACTIONS BY APP ID" + appId);

        List<TransactionDto> transactions = INSTANCE_TRANSACTION.transactionsToDtos(transactionRepository.findAllByAppId(appId));

        for (TransactionDto transaction:  transactions) {

            transaction.setBalance(INSTANCE_BALANCE.balanceToDto(balanceRepository
                                        .findByUnitId(transaction.getUnitId())
                                        .orElseThrow(() -> new ResourceNotFoundException("balance no found"))));

        }

        return transactions;
    }

    @Override
    public TransactionDto findTransactionByUnitId(Long unitId) {
        return INSTANCE_TRANSACTION.transactionToDto(transactionRepository.findByUnitId(unitId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Transaction not found with unit id %s", unitId))));
    }

    @Override
    public TransactionDto findTransactionById(Long transactionId) {
        return INSTANCE_TRANSACTION.transactionToDto(transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Transaction not found with id %s", transactionId))));
    }

    @Override
    public List<TransactionDto> findTransactionByUnitIdAndAppId(Long unitId, Long appId) {
        List<TransactionDto> transactions = INSTANCE_TRANSACTION.transactionsToDtos(transactionRepository.findAllByUnitIdAndAppId(unitId, appId));

        BalanceDto balance = INSTANCE_BALANCE.balanceToDto(balanceRepository.findByUnitIdAndAppId(unitId, appId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("No balances relacionados con la unidad %s", unitId))));

//        ConceptDto concept = INSTANCE_CONCEPT.conceptToDto(conceptRepository.getReferenceById(transactions.stream().findFirst().get().getConceptDto().getId()));

        for (TransactionDto transaction:  transactions) {
            transaction.setBalance(balance);
//            transaction.setConceptDto(concept);
        }

        return transactions;
    }
}

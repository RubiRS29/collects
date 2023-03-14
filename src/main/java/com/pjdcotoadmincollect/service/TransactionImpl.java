package com.pjdcotoadmincollect.service;

import com.pjdcotoadmincollect.dto.TransactionDto;
import com.pjdcotoadmincollect.entity.Balance;
import com.pjdcotoadmincollect.entity.Concept;
import com.pjdcotoadmincollect.entity.Transaction;
import com.pjdcotoadmincollect.exceptions.BadRequestException;
import com.pjdcotoadmincollect.exceptions.ResourceNotFoundException;
import com.pjdcotoadmincollect.repository.BalanceRepository;
import com.pjdcotoadmincollect.repository.ConceptRepository;
import com.pjdcotoadmincollect.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

import static com.pjdcotoadmincollect.mapper.TransactionMapper.INSTANCE_TRANSACTION;
@Service
public class TransactionImpl implements TransactionService{

    private final Logger LOGGER =  LogManager.getLogger(TransactionImpl.class);
    private final TransactionRepository transactionRepository;
    private final ConceptRepository conceptRepository;
    private final BalanceRepository balanceRepository;

    public TransactionImpl(TransactionRepository transactionRepository,
                           ConceptRepository conceptRepository,
                           BalanceRepository balanceRepository) {
        this.transactionRepository = transactionRepository;
        this.conceptRepository = conceptRepository;
        this.balanceRepository = balanceRepository;
    }

    @Override
    public TransactionDto saveTransaction(TransactionDto transactionDto) {

        if(ObjectUtils.isEmpty(transactionDto.getAppId())
                || ObjectUtils.isEmpty(transactionDto.getUnitId())){
            LOGGER.error("App id or Unit id can not be null");
            throw new BadRequestException("App id or Unit id can not be null");
        }

        if(ObjectUtils.isEmpty(transactionDto.getQuantity())
                || ObjectUtils.isEmpty(transactionDto.getConceptId())){
            LOGGER.error("Quantity or Concept id can not be null");
            throw new BadRequestException("Quantity or Concept id can not be null");
        }

        Transaction transaction = INSTANCE_TRANSACTION.dtoToTransaction(transactionDto);

        Concept concept = conceptRepository.getReferenceById(transactionDto.getConceptId());
        transaction.setConcept(concept);

        LOGGER.info(concept.toString());

        Balance balance = balanceRepository.findByUnitId(transactionDto.getUnitId()).orElse(null);


        if(concept.getOperation().name().equals("SUB")) {

            if (balance != null) {

                balance.setBalance(balance.getBalance() - transactionDto.getQuantity());
                LOGGER.info("update balance of sub");
                balanceRepository.save(balance);

            } else {
                Balance blc = new Balance(
                        transaction.getAppId(),
                        transactionDto.getUnitId(),
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
                        transactionDto.getUnitId(),
                        transactionDto.getQuantity()
                );

                balanceRepository.save(blc);
            }
        }

        return INSTANCE_TRANSACTION.
                transactionToDto(transactionRepository.save(transaction));
    }

    @Override
    public List<TransactionDto> getAllTransactions() {
        return INSTANCE_TRANSACTION.transactionsToDtos(transactionRepository.findAll());
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
}

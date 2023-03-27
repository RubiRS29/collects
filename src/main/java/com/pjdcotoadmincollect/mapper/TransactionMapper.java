package com.pjdcotoadmincollect.mapper;

import com.pjdcotoadmincollect.dto.TransactionDto;
import com.pjdcotoadmincollect.dto.TransactionRequestDto;
import com.pjdcotoadmincollect.entity.Transaction;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(
        componentModel = "spring"
)
public interface TransactionMapper {

    TransactionMapper INSTANCE_TRANSACTION = Mappers.getMapper(TransactionMapper.class);

    @Mapping(target="id", source = "transactionId")
    @Mapping(target="concept", source = "concept")
    @Mapping(target="date", source = "createdOn")
    TransactionDto transactionToDto(Transaction transaction);

    @InheritInverseConfiguration
    Transaction dtoToTransaction(TransactionDto transactionDto);

    @Mapping(target="id", source = "transactionId")
    @Mapping(target="concept", source = "concept")
    @Mapping(target="date", source = "createdOn")
    List<TransactionDto> transactionsToDtos(List<Transaction> transaction);

    @InheritInverseConfiguration
    List<Transaction> dtosToTransactions(List<TransactionDto> transactionDto);

    Transaction requestToTransaction(TransactionRequestDto transactionDto);

    @InheritInverseConfiguration
    List<Transaction> requestToTransaction(List<TransactionRequestDto> transactionDto);

}

package com.pjdcotoadmincollect.mapper;

import com.pjdcotoadmincollect.dto.TransactionDto;
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

    @Mapping(target="uuid", source = "referenceId")
    TransactionDto transactionToDto(Transaction transaction);

    @InheritInverseConfiguration
    Transaction dtoToTransaction(TransactionDto transactionDto);


    @Mapping(target="uuid", source = "referenceId")
    List<TransactionDto> transactionsToDtos(List<Transaction> transaction);

    @InheritInverseConfiguration
    List<Transaction> dtosToTransactions(List<TransactionDto> transactionDto);


}

package com.pjdcotoadmincollect.mapper;


import com.pjdcotoadmincollect.dto.BalanceDto;
import com.pjdcotoadmincollect.entity.Balance;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface BalanceMapper {
    BalanceMapper INSTANCE_BALANCE = Mappers.getMapper(BalanceMapper.class);

    BalanceDto balanceToDto(Balance balance);

    Balance dtoToBalance(BalanceDto balanceDto);

    List<BalanceDto> balanceToDto(List<Balance> balance);

    List<Balance> dtoToBalance(List<BalanceDto> balanceDto);


}

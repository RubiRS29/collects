package com.pjdcotoadmincollect.service;

import com.pjdcotoadmincollect.dto.BalanceDto;
import com.pjdcotoadmincollect.entity.Balance;
import com.pjdcotoadmincollect.exceptions.ResourceNotFoundException;
import com.pjdcotoadmincollect.repository.BalanceRepository;

import java.util.List;

import static com.pjdcotoadmincollect.mapper.BalanceMapper.INSTANCE_BALANCE;

public class BalanceImpl implements BalanceService{

    private final BalanceRepository balanceRepository;

    public BalanceImpl(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }


    @Override
    public BalanceDto getBalanceById(Long id) {

        return INSTANCE_BALANCE.balanceToDto(balanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Balance not found with id %s", id))));
    }

    @Override
    public BalanceDto getBalanceByUnitId(Long balanceId) {
        return INSTANCE_BALANCE.balanceToDto(balanceRepository.findByUnitId(balanceId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Balance not found with id %s", balanceId))));
    }

    @Override
    public List<BalanceDto> getAllBalances() {

        return INSTANCE_BALANCE.balanceToDto(balanceRepository.findAll());
    }
}

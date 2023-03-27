package com.pjdcotoadmincollect.service;

import com.pjdcotoadmincollect.dto.BalanceDto;
import com.pjdcotoadmincollect.exceptions.ResourceNotFoundException;
import com.pjdcotoadmincollect.repository.BalanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.pjdcotoadmincollect.mapper.BalanceMapper.INSTANCE_BALANCE;

@Service
public class BalanceServiceImpl implements BalanceService{

    private final BalanceRepository balanceRepository;

    public BalanceServiceImpl(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }


    @Override
    public BalanceDto getBalanceById(Long id) {

        return INSTANCE_BALANCE.balanceToDto(balanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Balance not found with id %s", id))));
    }

    @Override
    public BalanceDto getBalanceByUnitIdAndAppId(Long balanceId, Long appId) {
        return INSTANCE_BALANCE.balanceToDto(balanceRepository.findByUnitIdAndAppId(balanceId, appId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Balance not found with id %s", balanceId))));
    }

    @Override
    public List<BalanceDto> getAllBalances(Long id) {

        return INSTANCE_BALANCE.balanceToDto(balanceRepository.findAllByAppId(id));
    }
}

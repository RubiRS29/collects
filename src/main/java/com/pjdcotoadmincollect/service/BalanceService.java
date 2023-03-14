package com.pjdcotoadmincollect.service;

import com.pjdcotoadmincollect.dto.BalanceDto;
import com.pjdcotoadmincollect.entity.Balance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BalanceService {

    BalanceDto getBalanceById (Long id);

    BalanceDto getBalanceByUnitId(Long balanceId);

    List<BalanceDto> getAllBalances();

}

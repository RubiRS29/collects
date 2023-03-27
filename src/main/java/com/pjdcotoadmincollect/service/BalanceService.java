package com.pjdcotoadmincollect.service;

import com.pjdcotoadmincollect.dto.BalanceDto;
import com.pjdcotoadmincollect.entity.Balance;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BalanceService {

    BalanceDto getBalanceById (Long id);

    BalanceDto getBalanceByUnitIdAndAppId(Long balanceId, Long appId);

    List<BalanceDto> getAllBalances(Long id);

}

package com.pjdcotoadmincollect.repository;


import com.pjdcotoadmincollect.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

    Optional<Balance> findByUnitIdAndAppId(Long unitId, Long appId);

    Optional<Balance> findByUnitId(Long unitId);

    List<Balance> findAllByAppId(Long id);

}

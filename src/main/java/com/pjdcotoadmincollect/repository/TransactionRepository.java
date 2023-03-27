package com.pjdcotoadmincollect.repository;

import com.pjdcotoadmincollect.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByUnitId(Long id);
    List<Transaction> findAllByAppId(Long id);

    List<Transaction> findAllByUnitIdAndAppId(Long unit, Long appid);

}

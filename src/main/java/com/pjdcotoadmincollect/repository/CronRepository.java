package com.pjdcotoadmincollect.repository;

import com.pjdcotoadmincollect.entity.Cron;
import com.pjdcotoadmincollect.entity.CronType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CronRepository extends JpaRepository<Cron, Long> {

    Cron findByCron(CronType cronType);

}

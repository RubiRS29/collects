package com.pjdcotoadmincollect.repository;

import com.pjdcotoadmincollect.entity.SchedulerJobInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulerRepository extends JpaRepository<SchedulerJobInfo, Long> {

    SchedulerJobInfo findByJobName(String jobName);

}

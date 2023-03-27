package com.pjdcotoadmincollect.repository;

import com.pjdcotoadmincollect.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> getAllByAppId(Long id);

}

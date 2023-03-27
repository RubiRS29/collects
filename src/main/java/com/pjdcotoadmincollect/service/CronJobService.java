package com.pjdcotoadmincollect.service;

import com.pjdcotoadmincollect.dto.JobRequestDto;

public interface CronJobService {

    void saveJob(JobRequestDto jobRequestDto);

    void saveOrUpdate(JobRequestDto jobRequestDto);

//    List<JobDto> getAllJobs(Long id);
//
//    List<JobDto> getAllJobsByUnitId(Long id);
//
//    List<CronDto> getAllCrons();
}

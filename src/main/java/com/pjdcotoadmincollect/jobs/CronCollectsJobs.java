package com.pjdcotoadmincollect.jobs;


import com.pjdcotoadmincollect.dto.JobRequestDto;
import com.pjdcotoadmincollect.entity.Concept;
import com.pjdcotoadmincollect.entity.Cron;
import com.pjdcotoadmincollect.entity.Job;
import com.pjdcotoadmincollect.entity.UnitJob;
import com.pjdcotoadmincollect.exceptions.ResourceNotFoundException;
import com.pjdcotoadmincollect.repository.ConceptRepository;
import com.pjdcotoadmincollect.repository.CronRepository;
import com.pjdcotoadmincollect.repository.JobRepository;
import com.pjdcotoadmincollect.repository.UnitJobRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.ArrayList;
import java.util.List;

import static com.pjdcotoadmincollect.mapper.JobMapper.INSTANCE_JOB;
import static com.pjdcotoadmincollect.mapper.UnitJobMapper.INSTANCE_UNIT_JOB;

public class CronCollectsJobs extends QuartzJobBean {

    private final Logger LOGGER =  LogManager.getLogger(CronCollectsJobs.class);

    private final JobRepository jobRepository;
    private final CronRepository cronRepository;
    private final UnitJobRepository unitJobRepository;
    private final ConceptRepository conceptRepository;

    public CronCollectsJobs(JobRepository jobRepository,
                            CronRepository cronRepository, UnitJobRepository unitJobRepository,
                            ConceptRepository conceptRepository) {
        this.jobRepository = jobRepository;
        this.cronRepository = cronRepository;
        this.unitJobRepository = unitJobRepository;
        this.conceptRepository = conceptRepository;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        LOGGER.info("SampleCronJob Start................");

        JobDataMap dataMap = context.getJobDetail().getJobDataMap();

        JobRequestDto jobRequest = (JobRequestDto) dataMap.get("jobRequestDto");

        Job job = INSTANCE_JOB.dtoRequestToJob(jobRequest);

        Cron cron = cronRepository.findByCron(jobRequest.getCron());
        List<UnitJob> unitsJobs = new ArrayList<>(INSTANCE_UNIT_JOB.dtosToUnitJob(jobRequest.getUnitJob()));
        Concept concept = conceptRepository.findById(jobRequest.getConceptId())
                .orElseThrow(() -> new ResourceNotFoundException("Concept no found"));

        job.setCron(cron);
        job.setUnitJob(unitsJobs);
        job.setConcept(concept);

        INSTANCE_JOB.jobToDto(jobRepository.save(job));

    }
}

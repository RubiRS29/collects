package com.pjdcotoadmincollect.service;

import com.pjdcotoadmincollect.component.JobScheduleCreator;
import com.pjdcotoadmincollect.dto.JobRequestDto;
import com.pjdcotoadmincollect.entity.*;
import com.pjdcotoadmincollect.exceptions.BadRequestException;
import com.pjdcotoadmincollect.jobs.CronCollectsJobs;
import com.pjdcotoadmincollect.repository.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.Date;

@Service
public class CronJobImpl implements CronJobService {

    private final CronRepository cronRepository;
    private final JobRepository jobRepository;

    private final UnitJobRepository unitJobRepository;
    private final ConceptRepository conceptRepository;

    private final Scheduler scheduler;
    private final SchedulerFactoryBean schedulerFactoryBean;
    private final SchedulerRepository schedulerRepository;

    private final ApplicationContext context;

    private final JobScheduleCreator scheduleCreator;

    private final Logger LOGGER =  LogManager.getLogger(CronJobService.class);

    public CronJobImpl(CronRepository cronRepository, JobRepository jobRepository,
                       UnitJobRepository unitJobRepository, ConceptRepository conceptRepository,
                       Scheduler scheduler, SchedulerFactoryBean schedulerFactoryBean,
                       SchedulerRepository schedulerRepository, ApplicationContext context,
                       JobScheduleCreator scheduleCreator) {
        this.cronRepository = cronRepository;
        this.jobRepository = jobRepository;
        this.unitJobRepository = unitJobRepository;
        this.conceptRepository = conceptRepository;
        this.scheduler = scheduler;
        this.schedulerFactoryBean = schedulerFactoryBean;
        this.schedulerRepository = schedulerRepository;
        this.context = context;
        this.scheduleCreator = scheduleCreator;
    }


    @Override
    public void saveJob(JobRequestDto jobRequestDto) {

        SchedulerJobInfo jobInfo = new SchedulerJobInfo();

        jobInfo.setJobName(jobRequestDto.getJobName());
        jobInfo.setJobGroup("Collects");
        jobInfo.setJobStatus("SCHEDULED");
        jobInfo.setDescription(jobRequestDto.getDescription());

        if(ObjectUtils.isEmpty(jobRequestDto.getAppId())){
            LOGGER.info("App Id is missing");
            throw new BadRequestException("App Id is missing");
        }

        if(ObjectUtils.isEmpty(jobRequestDto.getConceptId()) || ObjectUtils.isEmpty(jobRequestDto.getQuantity())
            || ObjectUtils.isEmpty(jobRequestDto.getCron()) ){
            LOGGER.info("Quantity or Cron or Concept id is missing");
            throw new BadRequestException("Quantity or Cron or Concept id are missing");
        }

        LOGGER.info(jobRequestDto.toString());

        Cron cron = cronRepository.findByCron(jobRequestDto.getCron());

        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();

            jobInfo.setJobClass(CronCollectsJobs.class.getName());

            JobDetail jobDetail = JobBuilder
                    .newJob((Class<? extends QuartzJobBean>) Class.forName(jobInfo.getJobClass()))
                    .withIdentity(jobInfo.getJobName(), jobInfo.getJobGroup()).build();

            if (!scheduler.checkExists(jobDetail.getKey())) {



                jobDetail = scheduleCreator.createJob(
                        (Class<? extends QuartzJobBean>) Class.forName(jobInfo.getJobClass()), false, context,
                        jobInfo.getJobName(), jobInfo.getJobGroup(), jobRequestDto);


                Trigger trigger = scheduleCreator.createCronTrigger(jobInfo.getJobName(), new Date(),
                        cron.getCronExpression(), SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);

                scheduler.scheduleJob(jobDetail, trigger);
                schedulerRepository.save(jobInfo);
                LOGGER.info(">>>>> jobName = [" + jobInfo.getJobName() + "]" + " scheduled.");

            } else {
                LOGGER.error("scheduleNewJobRequest.jobAlreadyExist");
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error("Class Not Found - {}", jobInfo.getJobClass(), e);
        } catch (SchedulerException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public void saveOrUpdate(JobRequestDto scheduleJob) {

    }

//    @Override
//    public List<JobDto> getAllJobs(Long id) {
//        LOGGER.info(String.format("Get All Recurrent Jobs by %s", id));
//
//
//
//        return INSTANCE_JOB.jobsToDto(jobRepository.getAllByAppId(id));
//    }
//
//    @Override
//    public List<JobDto> getAllJobsByUnitId(Long id) {
//        return null;
//    }
//
//    public List<CronDto> getAllCrons(){
//        return INSTANCE_CRON.cronsToDto(cronRepository.findAll());
//    }


}

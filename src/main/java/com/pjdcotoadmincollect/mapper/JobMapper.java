package com.pjdcotoadmincollect.mapper;

import com.pjdcotoadmincollect.dto.JobDto;
import com.pjdcotoadmincollect.dto.JobRequestDto;
import com.pjdcotoadmincollect.entity.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface JobMapper {

    JobMapper INSTANCE_JOB = Mappers.getMapper(JobMapper.class);
    @Mapping(target="unitJob", source = "unitJob")
    @Mapping(target="isEnable", source = "isEnable")
    JobDto jobToDto(Job job);

    @Mapping(target="unitJob", source = "unitJob")
    Job dtoToJob(JobDto jobDto);


    List<JobDto> jobsToDto(List<Job> jobs);

    List<Job>  dtosToJob(List<JobDto> jobs);

//    @Mapping(target="cron", source = "cron")
//    JobRequestDto jobToDtoRequest(Job  job);

    Job dtoRequestToJob(JobRequestDto jobDto);


}

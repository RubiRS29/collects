package com.pjdcotoadmincollect.mapper;

import com.pjdcotoadmincollect.dto.UnitJobDto;
import com.pjdcotoadmincollect.entity.UnitJob;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface UnitJobMapper {

    UnitJobMapper INSTANCE_UNIT_JOB = Mappers.getMapper(UnitJobMapper.class);

    UnitJobDto unitJobToDto(UnitJob unitJob);

    UnitJob dtoToUnitJob(UnitJobDto unitJobDto);

    List<UnitJob> dtosToUnitJob(List<UnitJobDto>  unitJobDtos);

}

package com.pjdcotoadmincollect.mapper;

import com.pjdcotoadmincollect.dto.CronDto;
import com.pjdcotoadmincollect.entity.Cron;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface CronMapper {

    CronMapper INSTANCE_CRON = Mappers.getMapper(CronMapper.class);

    CronDto cronToDto(Cron cron);

    Cron dtoToCron(CronDto cronDto);

    List<CronDto> cronsToDto(List<Cron> crons);

}

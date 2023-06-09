package com.pjdcotoadmincollect.dto;

import com.pjdcotoadmincollect.entity.Concept;

import com.pjdcotoadmincollect.entity.Cron;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobDto {

    private Long id;
    private Long appId;
    private Concept concept;
    private Double quantity;
    private CronDto cron;
    private String description;
    private Boolean isEnable;
    private List<UnitJobDto> unitJob;

}

package com.pjdcotoadmincollect.dto;

import com.pjdcotoadmincollect.entity.CronType;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobRequestDto implements Serializable {

    private Long appId;
    private Long conceptId;
    private Double quantity;
    private CronType cron;
    private Boolean isEnable;
    private String description;
    private String jobName;
    private List<UnitJobDto> unitJob;

}

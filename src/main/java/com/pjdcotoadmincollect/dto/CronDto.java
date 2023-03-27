package com.pjdcotoadmincollect.dto;

import com.pjdcotoadmincollect.entity.CronType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CronDto {

    private CronType cron;
    private String description;

}

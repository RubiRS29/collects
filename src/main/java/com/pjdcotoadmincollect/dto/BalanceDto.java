package com.pjdcotoadmincollect.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BalanceDto {

    private Double balance;
    private LocalDateTime date;

}

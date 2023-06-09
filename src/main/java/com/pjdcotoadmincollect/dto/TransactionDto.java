package com.pjdcotoadmincollect.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionDto {

    private Long id;
    private Long appId;
    private String uuid;
    private Long unitId;
    private String unitName;
    private Long conceptId;
    private ConceptDto concept;
    private String description;
    private Double quantity;
    private String reference;
    private BalanceDto balance;
    private LocalDateTime date;

}

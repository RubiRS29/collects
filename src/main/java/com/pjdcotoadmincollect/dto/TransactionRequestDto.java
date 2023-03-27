package com.pjdcotoadmincollect.dto;

import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionRequestDto {

    private Long appId;
    private UnitDto unit;
    private Long conceptId;
    private String description;
    private Double quantity;
    private String reference;

}

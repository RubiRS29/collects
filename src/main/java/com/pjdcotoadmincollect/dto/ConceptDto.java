package com.pjdcotoadmincollect.dto;

import com.pjdcotoadmincollect.entity.OperationEnum;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConceptDto {

    private Long id;
    private Long app_id;
    private String description;
    private OperationEnum operation;

}

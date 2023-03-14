package com.pjdcotoadmincollect.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "concept")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Concept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long conceptId;

    @Column(nullable = false)
    private Long app_id;

    private String description;

    private OperationEnum operation;


    public Concept(Long app_id, String description, OperationEnum operation) {
        this.app_id = app_id;
        this.description = description;
        this.operation = operation;
    }
}

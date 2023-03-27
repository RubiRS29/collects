package com.pjdcotoadmincollect.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "cron")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cron {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CronType cron;
    private String description;
    private String cronExpression;

    public Cron(CronType cron, String description, String cronExpression){
        this.cron = cron;
        this.description = description;
        this.cronExpression = cronExpression;
    }
    
}

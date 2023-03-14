package com.pjdcotoadmincollect.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name = "unit_job")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UnitJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

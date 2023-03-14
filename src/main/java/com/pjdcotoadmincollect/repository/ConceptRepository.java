package com.pjdcotoadmincollect.repository;

import com.pjdcotoadmincollect.entity.Concept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConceptRepository extends JpaRepository<Concept, Long> {
}

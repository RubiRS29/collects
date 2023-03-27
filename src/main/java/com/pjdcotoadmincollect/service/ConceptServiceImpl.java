package com.pjdcotoadmincollect.service;

import com.pjdcotoadmincollect.dto.ConceptDto;
import com.pjdcotoadmincollect.repository.ConceptRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.pjdcotoadmincollect.mapper.ConceptMapper.INSTANCE_CONCEPT;

@Service
public class ConceptServiceImpl implements ConceptService{

    private final ConceptRepository conceptRepository;

    public ConceptServiceImpl(ConceptRepository conceptRepository) {
        this.conceptRepository = conceptRepository;
    }

    @Override
    public List<ConceptDto> getAllConcepts() {
        return INSTANCE_CONCEPT.conceptToDto(conceptRepository.findAll());
    }
}

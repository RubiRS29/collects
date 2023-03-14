package com.pjdcotoadmincollect.mapper;

import com.pjdcotoadmincollect.dto.ConceptDto;
import com.pjdcotoadmincollect.entity.Concept;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface ConceptMapper {
    ConceptMapper INSTANCE_CONCEPT = Mappers.getMapper(ConceptMapper.class);

    ConceptDto conceptToDto(Concept concept);

    Concept dtoToConcept(ConceptDto conceptDto);

    List<ConceptDto> conceptToDto(List<Concept> concepts);

    List<Concept> dtoToConcept(List<ConceptDto> conceptDtos);
}

package com.pjdcotoadmincollect.mapper;

import com.pjdcotoadmincollect.dto.ConceptDto;
import com.pjdcotoadmincollect.entity.Concept;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface ConceptMapper {
    ConceptMapper INSTANCE_CONCEPT = Mappers.getMapper(ConceptMapper.class);

//    @Mapping(target="id", source = "conceptId")
    ConceptDto conceptToDto(Concept concept);
    @InheritInverseConfiguration
    Concept dtoToConcept(ConceptDto conceptDto);

//    @Mapping(target="id", source = "conceptId")
    List<ConceptDto> conceptToDto(List<Concept> concepts);
    @InheritInverseConfiguration
    List<Concept> dtoToConcept(List<ConceptDto> conceptDtos);
}

package com.pjdcotoadmincollect.data;

import com.pjdcotoadmincollect.entity.Concept;
import com.pjdcotoadmincollect.entity.OperationEnum;
import com.pjdcotoadmincollect.repository.ConceptRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InitialData implements CommandLineRunner {
    private final Logger LOGGER  = LogManager.getLogger(InitialData.class);

    private final ConceptRepository conceptRepository;

    public InitialData(ConceptRepository conceptRepository) {
        this.conceptRepository = conceptRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Concept> listOfConcepts = new ArrayList<>();

        Concept rent = new Concept(1L, "renta", OperationEnum.SUM);
        Concept rentDebt = new Concept(2L, "pago de renta", OperationEnum.SUB);
        Concept maintenance = new Concept(3L, "mantenimiento", OperationEnum.SUB);
        Concept notDebt = new Concept(4L, "multa", OperationEnum.SUB);
        Concept maintenanceDebt = new Concept(5L, "pago demantenimiento", OperationEnum.SUM);
        Concept debt = new Concept(6L, "pago demulta", OperationEnum.SUM);

        LOGGER.info("get into initial data");
        listOfConcepts.add(rent);
        listOfConcepts.add(rentDebt);
        listOfConcepts.add(maintenance);
        listOfConcepts.add(maintenanceDebt);
        listOfConcepts.add(debt);
        listOfConcepts.add(notDebt);

        List<Concept> concepts = conceptRepository.findAll();

        if(concepts.size() != listOfConcepts.size()){

            LOGGER.info("get into load data");

            List<Concept> collect = listOfConcepts.stream()
                    .filter(type -> !concepts.stream()
                            .filter(tp -> tp.getDescription().equals(type.getDescription())).findFirst().isPresent() )
                    .collect(Collectors.toList());

            collect.forEach(type -> System.out.println(type.toString()));

            List<Concept> allTypeAnnouncement = conceptRepository.saveAll(collect);

            allTypeAnnouncement.forEach(type -> System.out.println(type.toString()));

        }



    }
}

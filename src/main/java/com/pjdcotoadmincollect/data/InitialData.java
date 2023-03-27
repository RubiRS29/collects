package com.pjdcotoadmincollect.data;

import com.pjdcotoadmincollect.entity.Concept;
import com.pjdcotoadmincollect.entity.Cron;
import com.pjdcotoadmincollect.entity.CronType;
import com.pjdcotoadmincollect.entity.OperationEnum;
import com.pjdcotoadmincollect.repository.ConceptRepository;
import com.pjdcotoadmincollect.repository.CronRepository;
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

    private final CronRepository cronRepository;

    public InitialData(ConceptRepository conceptRepository, CronRepository cronRepository) {
        this.conceptRepository = conceptRepository;
        this.cronRepository = cronRepository;
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


        Cron  everyMinute = new Cron(CronType.EVERY_MINUTE, "cada minuto", "* * * * * ?");
        Cron  oneTimePerMonth = new Cron(CronType.ONE_TIME_PER_MONTH, "Una vez al mes", "0 0 1 * *");
        Cron  firstDayOfTheMonth = new Cron(CronType.FIRST_DAY_OF_THE_MONTH, "Primer dia del mes", "");
        Cron  lastDayOfTheMonth = new Cron(CronType.LAST_DAT_OF_THE_MONTH, "Ultimo dia del mes", "0 0 L * *");
        Cron  each15Days = new Cron(CronType.EACH_15_DAYS, "Cada 15 dias", "0 0 15 * *");
        Cron  eachSixMonths = new Cron(CronType.EACH_6_MONTHS, "Cada 6 meses", "0 0 1 */6 *");
        Cron  oneTimeForYear = new Cron(CronType.ONE_TIME_FOR_YEAR, "Una vez cada año", "0 0 1 1 *");

        List<Cron> crons = new ArrayList<>();
        crons.add(everyMinute);
        crons.add(oneTimePerMonth);
        crons.add(firstDayOfTheMonth);
        crons.add(lastDayOfTheMonth);
        crons.add(each15Days);
        crons.add(eachSixMonths);
        crons.add(oneTimeForYear);

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

//            collect.forEach(type -> System.out.println(type.toString()));

            List<Concept> allTypeAnnouncement = conceptRepository.saveAll(collect);

            allTypeAnnouncement.forEach(type -> System.out.println(type.toString()));

        }

        List<Cron> cronsFromDb = cronRepository.findAll();

        List<Cron> cronToSave = crons.stream()
                .filter(type -> !cronsFromDb.stream()
                        .filter(tp -> tp.getCron().equals(type.getCron())).findFirst().isPresent() )
                .collect(Collectors.toList());

        List<Cron> cronSave = cronRepository.saveAll(cronToSave);

        cronSave.forEach(type -> System.out.println(type.toString()));

    }
}

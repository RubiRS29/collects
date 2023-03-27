package com.pjdcotoadmincollect.config;

import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class SchedulerConfig {

    private final DataSource dataSource;
    private final ApplicationContext applicationContext;

    private final QuartzProperties quartzProperties;


    public SchedulerConfig(DataSource dataSource, ApplicationContext applicationContext, QuartzProperties quartzProperties) {
        this.dataSource = dataSource;
        this.applicationContext = applicationContext;
        this.quartzProperties = quartzProperties;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {

        SchedulerJobFactory jobFactory = new SchedulerJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        Properties properties = new Properties();
        properties.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.PostgreSQLDelegate");
//        properties.put("org.quartz.scheduler.instanceName", "springBootQuartzApp");
//        properties.put("org.quartz.scheduler.instanceId", "AUTO");
//        properties.put("org.quartz.threadPool.threadCount", "50");
//        properties.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
//        properties.put("org.quartz.jobStore.useProperties", "true");
//        properties.put("org.quartz.jobStore.isClustered", "true");
//        properties.put("org.quartz.plugin.shutdownHook.class", "org.quartz.plugins.management.ShutdownHookPlugin");
//        properties.put("org.quartz.plugin.shutdownHook.cleanShutdown", "TRUE");

        System.out.println("properties for quartz ");
        System.out.println(quartzProperties.getProperties());

        properties.putAll(quartzProperties.getProperties());

        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(dataSource);
        factory.setQuartzProperties(properties);
        factory.setJobFactory(jobFactory);
        return factory;
    }
}

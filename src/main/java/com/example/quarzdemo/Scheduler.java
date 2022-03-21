package com.example.quarzdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static org.springframework.scheduling.annotation.Scheduled.CRON_DISABLED;

@Component
public class Scheduler {
    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final SchedulerProperties schedulerProperties;
    private final RestTemplate restTemplate;

    public Scheduler(SchedulerProperties schedulerProperties, RestTemplateBuilder restTemplateBuilder) {

        this.schedulerProperties = schedulerProperties;
        this.restTemplate = restTemplateBuilder.build();
    }

    @Scheduled(timeUnit = TimeUnit.SECONDS, fixedRate = 2)
    public void scheduleTaskWithFixedRate() {

        var result = this.restTemplate.getForEntity("https://www.google.ch", String.class);
        var status = result.getStatusCodeValue();

        logger.info("Fixed Rate Task :: Execution Time - {} :: Status - {}",
                dateTimeFormatter.format(LocalDateTime.now()),
                status);

        for (var val : this.schedulerProperties.getSomeArray()) {
            logger.info(val);
        }
    }

    @Scheduled(timeUnit = TimeUnit.SECONDS, fixedRate = 2)
    public void scheduleTaskWithFixedDelay() {
        logger.info("Fixed Delay Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            logger.error("Ran into an error {}", ex);
            throw new IllegalStateException(ex);
        }
    }

    @Scheduled(timeUnit = TimeUnit.SECONDS, fixedRate = 2, initialDelay = 5)
    public void scheduleTaskWithInitialDelay() {
        logger.info("Fixed Rate Task with Initial Delay :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
    }

    @Scheduled(cron = "#{schedulerProperties.cron}")
    public void scheduleTaskWithCronExpression() {
        logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));

        for (var value : this.schedulerProperties.getSomeArray()) {
            logger.info(value);
        }
    }

    @Scheduled(cron = CRON_DISABLED)
    public void scheduleTaskDisabled() {
        logger.info("You'll never see me!");
    }
}

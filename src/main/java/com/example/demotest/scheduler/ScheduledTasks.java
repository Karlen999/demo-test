package com.example.demotest.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime(){
        log.info("The time is now {}", dateFormat.format(new Date()));
    }

    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask(){
        log.info("Fixed delay task {}",  dateFormat.format(new Date()));
    }

    @Scheduled(cron = "*/2 * * * * *")
    public void scheduleTaskUsingCronExpression(){
        log.info("Cron expression task {}", dateFormat.format(new Date()));
    }
}

package com.example.demotest.scheduler;

import com.example.demotest.service.TickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class DynamicSchedulingConfig {

    @Autowired
    private TickService tickService;

    public Executor taskExecutor(){
        return Executors.newSingleThreadScheduledExecutor();
    }
}

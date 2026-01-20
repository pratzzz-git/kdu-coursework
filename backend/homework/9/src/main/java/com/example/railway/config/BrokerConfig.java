package com.example.railway.config;

import com.example.railway.broker.EventBroker;
import com.example.railway.broker.SimpleEventBroker;
import com.example.railway.dto.event.TicketBookedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class BrokerConfig {

    @Bean
    public EventBroker<TicketBookedEvent> ticketBookedEventBroker(
            TaskExecutor eventTaskExecutor
    ) {
        return new SimpleEventBroker<>(eventTaskExecutor);
    }
}

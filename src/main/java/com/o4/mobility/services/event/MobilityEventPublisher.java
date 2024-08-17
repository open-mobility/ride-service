package com.o4.mobility.services.event;

import com.o4.mobility.common.dtos.events.EventType;
import com.o4.mobility.common.dtos.events.MobilityEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MobilityEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public MobilityEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishCustomEvent(EventType type, final Object data) {
        log.info("Publishing custom event {} with data {}", type, data);
        MobilityEvent event = new MobilityEvent(this, type, data);

        applicationEventPublisher.publishEvent(event);
    }
}
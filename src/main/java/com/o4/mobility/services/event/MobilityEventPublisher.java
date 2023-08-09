package com.o4.mobility.services.event;

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

    public void publishCustomEvent(final String message) {
        log.info("Publishing custom event. {}", message);
        MobilityEvent event = new MobilityEvent(this, message);

        applicationEventPublisher.publishEvent(event);
    }
}
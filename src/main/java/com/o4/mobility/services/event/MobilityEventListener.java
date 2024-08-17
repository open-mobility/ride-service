package com.o4.mobility.services.event;

import com.o4.mobility.common.dtos.events.MobilityEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MobilityEventListener implements ApplicationListener<MobilityEvent> {
    @Override
    public void onApplicationEvent(MobilityEvent event) {
      log.info("Received event: {} with data {}", event.getType(), event.getData()) ;
    }
}
package com.o4.mobility.common.dtos.events;

import org.springframework.context.ApplicationEvent;

public class MobilityEvent extends ApplicationEvent {
    private String message;

    public MobilityEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
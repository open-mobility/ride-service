package com.o4.mobility.common.dtos.events;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
/**
 * Event Detail Object
 *
 * <p>This Class holds the information related to Mobility event</p>
 *
 * @author M. Mazhar Hassan
 * @see ApplicationEvent
 * @since 1.0
 */

@Getter
@Setter
public class MobilityEvent extends ApplicationEvent {
    private EventType type;
    private Object data;

    public MobilityEvent(Object source, EventType type, Object data) {
        super(source);
        this.type = type;
        this.data = data;
    }
}
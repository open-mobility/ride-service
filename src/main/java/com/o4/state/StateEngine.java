package com.o4.state;

import com.o4.mobility.common.exceptions.ApplicationException;
import com.o4.mobility.common.exceptions.Errors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class StateEngine {

    Map<String, StateTransition> stateMachine = new HashMap<>();

    public void add(StateTransition stateTransition) {
        stateMachine.put(stateTransition.getCurrent(), stateTransition);
    }

    public void validate(String from, String to) {
        if (!stateMachine.containsKey(from)) {
            throw new ApplicationException(Errors.STATE_ERROR_UNKNOWN_STATE, "Invalid state machine: " + from);
        } else if (!stateMachine.containsKey(to)) {
            throw new ApplicationException(Errors.STATE_ERROR_UNKNOWN_STATE, "Invalid state machine: " + to);
        } else if (!stateMachine.get(from).getNext().contains(to)) {
            throw new ApplicationException(Errors.STATE_ERROR_ILLEGAL_TRANSITION, "Illegal transition from " + from + " to " + to);
        }
    }

    public void validate(List<String> states) {

        String prvState = null;
        log.info("Checking .... {}", states);

        for (String state : states) {
            if (prvState != null) {
                validate(prvState, state);
            }
            prvState = state;
        }
    }

    public void add(String fromStatus, String... toStatuses) {
        add(new StateTransition(fromStatus, List.of(toStatuses)));
    }
}
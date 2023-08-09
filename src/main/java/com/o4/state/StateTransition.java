package com.o4.state;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StateTransition {
    private String current;
    private List<String> next;

    public StateTransition(String state) {
        this.current = state;
        this.next = null;
    }
}

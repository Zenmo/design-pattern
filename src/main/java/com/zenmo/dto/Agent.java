package com.zenmo.dto;

import java.util.Objects;

enum AgentType {
    SIMPLE, NIGHT
}

record Agent(
        String name,
        float consumptionKw,
        AgentType type
) {

}

/*
final class Agent {
    private final String name;
    private final float consumptionKw;
    private final AgentType type;

    public Agent(String name, float consumptionKw, AgentType type) {
        this.name = name;
        this.consumptionKw = consumptionKw;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public float getConsumptionKw() {
        return consumptionKw;
    }

    public AgentType getType() {
        return type;
    }
}
*/
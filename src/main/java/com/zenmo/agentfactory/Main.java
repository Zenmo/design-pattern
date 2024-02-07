package com.zenmo.agentfactory;

import java.time.LocalDateTime;
import java.util.Arrays;

interface AgentFactory {
    Iterable<Agent> createAgents();
}

class HessenPoortAgentFactory implements AgentFactory {
    @Override
    public Iterable<Agent> createAgents() {
        // Opportunity to read from database, csv, api, etc.
        return Arrays.asList(
                new SimpleAgent("Alice", 1),
                new SimpleAgent("Bob", 0.5f),
                new NightAgent("Charlie", 2)
        );
    }
}

public class Main {
    public static void main(String[] args) {
        var agentFactory = new HessenPoortAgentFactory();

        var simulation = new Simulation(agentFactory.createAgents());
        simulation.run();
    }
}

/**
 * contract for agents
 */
interface Agent {
    String getName();
    void runOneHour(LocalDateTime startTime);
    double getConsumedKwh();
}

class Simulation {
    protected Iterable<Agent> agents;

    public Simulation(Iterable<Agent> agents) {
        this.agents = agents;
    }

    void run() {
        runOneDay(agents);
        var totalConsumedKwh = accumulateConsumpedKwh(agents);

        System.out.println("Total consumed kwh: " + totalConsumedKwh);
    }

    void runOneDay(Iterable<Agent> agents) {
        var dateTime = LocalDateTime.of(2021, 1, 1, 0, 0, 0);
        for (var hour = 0; hour < 24; hour++) {
            for (var agent : agents) {
                System.out.println("Running " + agent.getName() + " at " + dateTime);
                agent.runOneHour(dateTime);
            }
            dateTime = dateTime.plusHours(1);
        }
    }

    double accumulateConsumpedKwh(Iterable<Agent> agents) {
        var totalKwh = 0.0;
        for (var agent : agents) {
            totalKwh += agent.getConsumedKwh();
        }
        return totalKwh;
    }

}


class SimpleAgent implements Agent {
    protected final String name;
    protected final float consumptionKw;
    protected double consumedKwh = 0.0;

    public SimpleAgent(String name, float consumptionKw) {
        this.name = name;
        this.consumptionKw = consumptionKw;
    }

    @Override
    public void runOneHour(LocalDateTime startTime) {
        this.consumedKwh += consumptionKw;
    }

    @Override
    public double getConsumedKwh() {
        return consumedKwh;
    }

    @Override
    public String getName() {
        return "SimpleAgent " + name;
    }
}

class NightAgent implements Agent {
    protected final String name;
    protected final float consumptionKw;
    protected double consumedKwh = 0.0;

    public NightAgent(String name, float consumptionKw) {
        this.name = name;
        this.consumptionKw = consumptionKw;
    }

    @Override
    public void runOneHour(LocalDateTime startTime) {
        if (startTime.getHour() > 20 || startTime.getHour() < 6) {
            this.consumedKwh += consumptionKw;
        }
    }

    @Override
    public double getConsumedKwh() {
        return consumedKwh;
    }

    @Override
    public String getName() {
        return "NightAgent " + name;
    }
}

package com.zenmo.parameterized;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        var simulation = new Simulation(12);
        simulation.run();
    }
}

class HessenpoortSImulation extends Simulation {
    public HessenpoortSImulation() {
        super(24);
    }

    @Override
    Iterable<Agent> createAgents() {
        return Arrays.asList(
                new Agent("Alice", 1),
                new Agent("Bob", 0.5f),
                new Agent("Charlie", 2),
                new Agent("Dennis", 1.5f)
        );
    }
}

/**
 * Engine
 */
class Simulation {
    protected final int durationHours;

    public Simulation(int durationHours) {
        this.durationHours = durationHours;
    }

    public void run() {
        var agents = createAgents();
        run(agents);
        var totalConsumedKwh = accumulateConsumpedKwh(agents);

        System.out.println("Total consumed kwh: " + totalConsumedKwh);
    }

    Iterable<Agent> createAgents() {
        return Arrays.asList(
                new Agent("Alice", 1),
                new Agent("Bob", 0.5f),
                new Agent("Charlie", 2)
        );
    }

    void run(Iterable<Agent> agents) {
        for (var hour = 0; hour < this.durationHours; hour++) {
            for (var agent : agents) {
                agent.runOneHour();
            }
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

/**
 * Agent
 */
class Agent {
    protected final String name;
    protected final float consumptionKw;
    protected double consumedKwh = 0.0;

    public Agent(String name, float consumptionKw) {
        this.name = name;
        this.consumptionKw = consumptionKw;
    }

    public void runOneHour() {
        System.out.println("Running agent " + name);
        this.consumedKwh += consumptionKw;
    }

    public double getConsumedKwh() {
        return consumedKwh;
    }
}

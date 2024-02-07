package com.zenmo.procedural;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        var simulation = new Simulation();
        simulation.run();
    }
}


/**
 * Engine
 */
class Simulation {
    public void run() {
        var agents = createAgents();
        runOneDay(agents);
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

    void runOneDay(Iterable<Agent> agents) {
        for (var hour = 0; hour < 24; hour++) {
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

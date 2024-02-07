package com.zenmo.loadstrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class ElectricGridNode {
    protected float ratingKw;

    protected List<GridConnection> gridConnections;

    protected LoadStrategy loadStrategy;

    public ElectricGridNode(float ratingKw, LoadStrategy loadStrategy, List<GridConnection> gridConnections) {
        this.ratingKw = ratingKw;
        this.loadStrategy = loadStrategy;
        this.gridConnections = gridConnections;
    }

    void replaceStrategy(LoadStrategy loadStrategy) {
        this.loadStrategy = loadStrategy;
    }

    public void run(SimulationData data) {
        // ... code here ...
        loadStrategy.handleLoad(this, 100);
        // ... more code here ...
    }

    public void connect (GridConnection gridConnection) {
        gridConnections.add(gridConnection);
    }

    public Optional<GridConnection> randomDisconnect() {
        if (gridConnections.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(gridConnections.removeLast());
    }

    public float getRatingKw() {
        return ratingKw;
    }
}

interface SimulationData {}

interface GridConnection {
    float getConsumptionKw();
}

interface LoadStrategy {
    void handleLoad(ElectricGridNode transformer, float loadKw);
}

class PermanentDisconnectStrategy implements LoadStrategy {
    @Override
    public void handleLoad(ElectricGridNode transformer, float loadKw) {
        if (loadKw > transformer.getRatingKw() * 1.1) {
            transformer.randomDisconnect();
        }
    }
}

class TemporaryDisconnectStrategy implements LoadStrategy {
    protected final List<GridConnection> disconnectedConnections = new ArrayList<>();

    @Override
    public void handleLoad(ElectricGridNode transformer, float loadKw) {
        if (loadKw < transformer.getRatingKw() * 0.9) {
            if (disconnectedConnections.isEmpty()) {
                return;
            }

            transformer.connect(
                    disconnectedConnections.removeFirst()
            );
        }

        while (loadKw > transformer.getRatingKw()) {
            var optGridConnection = transformer.randomDisconnect();
            if (optGridConnection.isEmpty()) {
                break;
            } else {
                disconnectedConnections.add(optGridConnection.get());
            }
        }
    }
}

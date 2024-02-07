package com.zenmo.carobserver;

class Main {
    public static void main(String[] args) {
        var observer = new CarChargeObserver() {
            @Override
            public void onChargeChange(CarChargeEvent event) {
                System.out.println("Car charged with " + event.chargeAdded());
            }
        };

        var car = new ElectricCar("Tesla van Peter", 100, 90, observer);

        car.runOneHour();
        car.runOneHour();
        car.runOneHour();
        car.runOneHour();
    }
}

class ElectricCar {
    protected String id;
    protected final float capacityKwh;

    protected float chargeKwh;

    protected CarChargeObserver observer;

    public ElectricCar(String id, float capacityKwh, float chargeKwh, CarChargeObserver observer) {
        this.id = id;
        this.capacityKwh = capacityKwh;
        this.chargeKwh = chargeKwh;
        this.observer = observer;
    }

    public void runOneHour() {
        if (chargeKwh < capacityKwh) {
            var chargeAdded = Math.min(3.5f, capacityKwh - chargeKwh);
            chargeKwh += chargeAdded;

            observer.onChargeChange(new CarChargeEvent(id, chargeAdded));
        }
    }
}

interface CarChargeObserver {
    void onChargeChange(CarChargeEvent event);
}

// DTO
record CarChargeEvent(String carId, float chargeAdded) {
}




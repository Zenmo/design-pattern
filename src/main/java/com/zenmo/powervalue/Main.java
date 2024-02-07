package com.zenmo.powervalue;

import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        var pwr1 = new Power(1);
        var pwr2 = new Power(2);
        var pwr3 = pwr1.add(pwr2);
        System.out.println(pwr3.kw());
    }
}

record Power(float kw) {
    Power add(Power other) {
        return new Power(kw + other.kw);
    }

    Energy multiply(Duration duration) {
        return new Energy(kw * duration.toHours());
    }
}

record Energy(float kwh) {
    static Energy min(Energy left, Energy right) {
        return new Energy(Math.min(left.kwh, right.kwh));
    }

    Energy minus(Energy other) {
        return new Energy(kwh - other.kwh);
    }

    Energy plus(Energy other) {
        return new Energy(kwh + other.kwh);
    }
}

// more complex value object
record Car(String id, Energy capacityKwh, Energy chargeKwh) {
    Car charge(Power rate, Duration duration) {
        var chargeAdded = Energy.min(
                rate.multiply(duration),
                capacityKwh.minus(chargeKwh)
        );

        return new Car(id, capacityKwh, chargeKwh.plus(chargeAdded));
    }
}

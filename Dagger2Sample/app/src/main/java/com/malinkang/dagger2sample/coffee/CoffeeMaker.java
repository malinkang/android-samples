package com.malinkang.dagger2sample.coffee;

import android.util.Log;

import javax.inject.Inject;

import dagger.Lazy;

public class CoffeeMaker {


    Lazy<Heater> heater;
    Pump pump;

    @Inject
    CoffeeMaker(Lazy<Heater> heater, Pump pump) {
        this.heater = heater;
        this.pump = pump;
    }

    public void brew() {
        heater.get().on();
        pump.pump();
        Log.d("Dagger", " [_]P coffee! [_]P ");
        heater.get().off();
    }
}

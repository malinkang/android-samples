package com.malinkang.dagger.coffee;

import android.util.Log;

import javax.inject.Inject;

import dagger.Lazy;

public class CoffeeMaker {

//  @Inject Lazy<Heater> heater; // Don't want to create a possibly costly heater until we need it.
//  @Inject Pump pump;

//  public void brew() {
//    heater.get().on();
//    pump.pump();
//    Log.d("Dagger"," [_]P coffee! [_]P ");
//    heater.get().off();
//  }

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

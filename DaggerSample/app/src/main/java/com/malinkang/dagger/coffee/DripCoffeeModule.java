package com.malinkang.dagger.coffee;

import com.malinkang.dagger.MainActivity;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(
        injects = MainActivity.class
)
public class DripCoffeeModule {
    @Provides
    @Singleton
    Heater provideHeater() {
        return new ElectricHeater();

    }

    @Provides
    public Pump providePump(Thermosiphon pump) {
        return pump;
    }
}

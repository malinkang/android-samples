package com.malinkang.dagger.coffee;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(complete = false, library = true)
class PumpModule {
//    @Provides @Singleton
//    public Thermosiphon provideThermosiphon(Heater heater) {
//        return new Thermosiphon(heater);
//    }
    @Provides
    public Pump providePump(Thermosiphon pump){
        return pump;
    }

}

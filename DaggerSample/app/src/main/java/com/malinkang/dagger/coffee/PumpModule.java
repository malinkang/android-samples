package com.malinkang.dagger.coffee;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(complete = false, library = true)
class PumpModule {
    @Singleton
    @Provides
    public Pump providePump(Thermosiphon pump) {
        return pump;
    }

}

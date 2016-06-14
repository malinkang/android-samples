package com.malinkang.dagger2sample.coffee;

import dagger.Module;
import dagger.Provides;

@Module
public class PumpModule {
    @Provides
    public Pump providePump(Thermosiphon pump){
        return pump;
    }

}

package com.malinkang.dagger2sample.coffee;


import dagger.Module;
import dagger.Provides;

@Module
public class DripCoffeeModule {
  @Provides
  Heater provideHeater() {
    return new ElectricHeater();
  }
}

package com.malinkang.dagger2sample.coffee;

import com.malinkang.dagger2sample.MainActivity;

import javax.inject.Inject;

import dagger.Component;

/**
 * Created by malinkang on 14/11/20.
 */
@Component(modules = {DripCoffeeModule.class,PumpModule.class})
public interface CoffeeShopComponent {
    void inject(MainActivity activity);
}

package com.malinkang.dagger.coffee;

import javax.inject.Inject;

/**
 * Created by malinkang on 14/11/20.
 */
public class CoffeeApp {

    private CoffeeMaker coffeeMaker;

    @Inject
    public CoffeeApp(CoffeeMaker coffeeMaker){
        this.coffeeMaker=coffeeMaker;
    }

    public void run(){
        coffeeMaker.brew();
    }
}

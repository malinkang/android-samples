package com.malinkang.dagger2sample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.malinkang.dagger2sample.coffee.CoffeeMaker;
import com.malinkang.dagger2sample.coffee.DaggerCoffeeShopComponent;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    CoffeeMaker coffeeMaker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //The implementation has the same name as the interface prefixed with Dagger.
        // Obtain an instance by invoking the builder() method on that implementation
        // and use the returned builder to set dependencies and build() a new instance.
//        CoffeeApp coffeeApp = DaggerCoffeeApp.builder()
//                .dripCoffeeModule(new DripCoffeeModule())
//                .pumpModule(new PumpModule()).build();
//        coffeeApp.coffeeMaker().brew();

        //Any module with an accessible default constructor can be elided(省略) as the builder
        // will construct an instance automatically if none is set.
        // And for any module whose @Provides methods are all static,
        // the implementation doesn’t need an instance at all.
        // If all dependencies can be constructed without the user creating a dependency instance,
        // then the generated implementation will also have a create() method that
        // can be used to get a new instance without having to deal with the builder.
        //如果用户没有创建一个依赖实例所有的依赖可以被构造，那么这个生成的实现类将也有一个create()方法,
        // 这个方法可以在不使用builder的情况下创建一个实例。
//        DaggerCoffeeShopComponent daggerCoffeeShopComponent = DaggerCoffeeApp.create();
//        daggerCoffeeShopComponent.coffeeMaker().brew();

//        DaggerCoffeeShopComponent.create().abc(this);
//        coffeeMaker.brew();
    }

}

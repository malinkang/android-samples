package com.malinkang.dagger;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.malinkang.dagger.coffee.CoffeeApp;
import com.malinkang.dagger.coffee.CoffeeMaker;
import com.malinkang.dagger.coffee.DripCoffeeModule;

import javax.inject.Inject;

import dagger.ObjectGraph;


public class MainActivity extends FragmentActivity {


    //@Inject CoffeeMaker coffeeMaker;

    @Inject CoffeeApp coffeeApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ObjectGraph objectGraph = ObjectGraph.create(new DripCoffeeModule());
        objectGraph.inject(this);
       // coffeeMaker.brew();
        coffeeApp.run();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

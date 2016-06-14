package com.malinkang.dagger;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.malinkang.dagger.coffee.CoffeeMaker;
import com.malinkang.dagger.coffee.DripCoffeeModule;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.ObjectGraph;
import dagger.internal.ArrayQueue;


public class MainActivity extends FragmentActivity {


    @Inject
    CoffeeMaker coffeeMaker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ObjectGraph objectGraph = ObjectGraph.create(new DripCoffeeModule());
        objectGraph.inject(this);
        coffeeMaker.brew();
//        ArrayList<String> list = new ArrayList<>();
//
//        ArrayQueue<String> queue = new ArrayQueue<>();
//        queue.add("a");
//        int i = 0;
//        String s;
//        while ((s = queue.poll()) != null) {
//            i++;
//            queue.add("" + i);
//            Log.e(MainActivity.class.getSimpleName(), s);
//        }
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

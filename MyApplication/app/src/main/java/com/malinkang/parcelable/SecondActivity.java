package com.malinkang.parcelable;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import org.parceler.Parcels;

/**
 * Created by malinkang on 2014/9/15.
 */
public class SecondActivity extends Activity{
    private final static String TAG=SecondActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Book book= getIntent().getParcelableExtra("book");
        Log.d(TAG,"book title->"+book.getTitle());
        Log.d(TAG,"book author name->"+book.getAuthor().getName());
    }

}

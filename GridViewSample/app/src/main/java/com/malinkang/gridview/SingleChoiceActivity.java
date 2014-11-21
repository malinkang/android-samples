package com.malinkang.gridview;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by malinkang on 14/11/1.
 */
public class SingleChoiceActivity extends FragmentActivity implements AdapterView.OnItemClickListener{


    private String TAG=SingleChoiceActivity.class.getSimpleName();
    @InjectView(R.id.gridview)
    GridView mGridview;

    private String[] items={"Android","Java","C#","C++","Ruby","Python","Object-C","PHP","Swift","GO","C"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_choice);
        ButterKnife.inject(this);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.simple_list_item_single_choice,items);
        mGridview.setAdapter(adapter);
        mGridview.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
        mGridview.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.e(TAG,"点击了"+i);
    }
}

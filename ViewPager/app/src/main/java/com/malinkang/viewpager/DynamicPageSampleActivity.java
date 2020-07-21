package com.malinkang.viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.malinkang.viewpager.adapter.InnerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DynamicPageSampleActivity extends AppCompatActivity {
  @BindView(R.id.viewPager) ViewPager mViewPager;
  private InnerAdapter mAdapter;
  ArrayList<Character> mLetters;
  private  char[] chars = {
     'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
      'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
  };
  private int index = -1;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dynamic_page_sample);
    ButterKnife.bind(this);
    mLetters = new ArrayList<>();
    mAdapter = new InnerAdapter(getSupportFragmentManager(),mLetters);
    mViewPager.setAdapter(mAdapter);
  }

  @OnClick(R.id.btn_add)
  public void addPage(){
    Log.d("malinkang","addPage "+mLetters.size());
    index++;
    mLetters.add(chars[index%chars.length]);
    mAdapter.notifyDataSetChanged();
  }

  @OnClick(R.id.btn_delete)
  public void deletePage(){
    if(index>=0){
      Log.d("malinkang","deletePage "+mLetters.size());

      mLetters.remove(index);
      mAdapter.notifyDataSetChanged();
      index--;
    }else {
      Toast.makeText(this, "当前没有可删除的页数", Toast.LENGTH_SHORT).show();
    }
  }


}
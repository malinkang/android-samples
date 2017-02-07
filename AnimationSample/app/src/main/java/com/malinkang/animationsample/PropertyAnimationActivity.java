package com.malinkang.animationsample;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by ldzs_android_1 on 16/5/18.
 */
public class PropertyAnimationActivity extends AppCompatActivity {

    public static final String TAG = PropertyAnimationActivity.class.getSimpleName();

    @BindView(R.id.iv)
    ImageView mImageView;
    @BindView(R.id.text)
    TextView mTextView;


    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        unbinder = ButterKnife.bind(this);
    }

    @OnClick(R.id.btn1)
    public void btn1() {
        //
//        ValueAnimator anim = ValueAnimator.ofFloat(0f, 1f);
//        anim.setDuration(300);
//        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float currentValue = (float) animation.getAnimatedValue();
//                Log.d(TAG, "cuurent value is " + currentValue);
//            }
//        });
//        anim.start();
        //ObjectAnimator内部的工作机制并不是直接对我们传入的属性名进行操作的，而是会去寻找这个属性名对应的get和set方法
//        ObjectAnimator animator = ObjectAnimator.ofFloat(mTextView, "textSize", 12f, 32f, 12f);
//        animator.setDuration(5000);
//        animator.start();

        //组合动画
//        ObjectAnimator moveIn = ObjectAnimator.ofFloat(mImageView, "translationX", -500f, 0f);
//        ObjectAnimator rotate = ObjectAnimator.ofFloat(mImageView, "rotation", 0f, 360f);
//        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(mImageView, "alpha", 1f, 0f, 1f);
//        AnimatorSet animSet = new AnimatorSet();
//        animSet.play(rotate).with(fadeInOut).after(moveIn);
//        animSet.setDuration(5000);
//        animSet.start();

        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.sample);
        animator.setTarget(mImageView);
        animator.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}

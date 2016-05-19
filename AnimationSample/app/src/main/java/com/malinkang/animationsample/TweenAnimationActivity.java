package com.malinkang.animationsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TweenAnimationActivity extends AppCompatActivity {


    @BindView(R.id.iv)
    ImageView mImageView;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_animation);
        unbinder = ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_alpha)
    public void startAlphaAnimation() {
        AlphaAnimation alphaAnimation = (AlphaAnimation) AnimationUtils.loadAnimation(this, R.anim.alpha);
        mImageView.startAnimation(alphaAnimation);
    }

    @OnClick(R.id.btn_rotate)
    public void startRotateAnimation() {
        RotateAnimation rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(this, R.anim.rotate);
        mImageView.startAnimation(rotateAnimation);
    }

    @OnClick(R.id.btn_scale)
    public void startScaleAnimation() {
        ScaleAnimation scaleAnimation = (ScaleAnimation) AnimationUtils.loadAnimation(this, R.anim.scale);
        mImageView.startAnimation(scaleAnimation);


    }

    @OnClick(R.id.btn_translate)
    public void startTranslateAnimation() {
        TranslateAnimation translateAnimation = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.translate);
        mImageView.startAnimation(translateAnimation);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent = new Intent(this, MainActivity.class);
            setResult(RESULT_OK, intent);
            finish();
            overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
            return false;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


}

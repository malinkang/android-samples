package com.malinkang.rxandroidsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.util.LongSparseArray;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.view.ViewClickEvent;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private TextView mTextView;

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.text);
        mButton = (Button) findViewById(R.id.btn);
//        Observable.just(1, 2, 3, 4).takeFirst(new Func1<Integer, Boolean>() {
//            @Override
//            public Boolean call(Integer integer) {
//                return integer > 2;
//            }
//        }).subscribe(new Subscriber<Integer>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                Log.d(MainActivity.class.getSimpleName(), "integer=" + integer);
//            }
//        });
        //butter();
//        Observable<Integer> observable1 = Observable.just(1,2,3);
//        Observable<Integer> observable2 = Observable.just(4,5,6);
//        Observable<Integer> observable3 = Observable.just(7,8,9);
//        Observable.concat(observable1,observable2,observable3).subscribe(new Subscriber<Integer>() {
//            @Override
//            public void onCompleted() {}
//            @Override
//            public void onError(Throwable e) {}
//            @Override
//            public void onNext(Integer integer) {
//                Log.d(TAG,"integer="+integer);// 1,2,3,4,5,6,7,8,9
//            }
//        });

        Observable.just(1, 2, 3).count().subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {

            }
        })
    }

    public void butter() {
        RxView.clickEvents(mButton)
                .buffer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ViewClickEvent>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<ViewClickEvent> viewClickEvents) {
                        if (viewClickEvents.size() > 0) {
                            Toast.makeText(MainActivity.this, "2秒内点击了" + viewClickEvents.size() + "次", Toast.LENGTH_SHORT).show();
                        } else {

                        }
                    }
                });
    }

    public void interval() {
        // 间隔时间1秒
        Observable.interval(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        mTextView.setText(aLong + "秒");
                    }
                });
    }
}

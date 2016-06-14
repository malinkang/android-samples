package com.malinkang.rxandroidsample;

import android.net.Uri;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.util.LongSparseArray;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.view.ViewClickEvent;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Notification;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.FuncN;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;
import rx.schedulers.TimeInterval;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private TextView mTextView;
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.text);
        mButton = (Button) findViewById(R.id.btn);
        Observable.just(1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {

                    }
                });
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

//        Observable<Integer> observable1 = Observable.just(1, 2, 3);
//        Observable<Integer> observable2 = Observable.create(new Observable.OnSubscribe<Integer>() {
//            @Override
//            public void call(Subscriber<? super Integer> subscriber) {
//                subscriber.onError(new Throwable("error"));
//            }
//
//        });
//        Observable<Integer> observable3 = Observable.just(7, 8, 9);
//        Observable.concat(observable1, observable2, observable3).subscribe(new Subscriber<Integer>() {
//            @Override
//            public void onCompleted() {}
//            @Override
//            public void onError(Throwable e) {
//                Log.e(TAG, e.getMessage());
//            }
//            @Override
//            public void onNext(Integer integer) {
//                Log.d(TAG, "integer=" + integer);// 1,2,3,4,5,6,7,8,9
//            }
//        });

//        Observable.just(1, 2, 3).count().subscribe(new Subscriber<Integer>() {
//            @Override
//            public void onCompleted() {
//            }
//
//            @Override
//            public void onError(Throwable e) {
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                Log.d(TAG, "integer=" + integer); // integer=3
//            }
//        });
//        Observable.from(new Integer[]{1,2,3,4,5,6,7,8,9,10}).reduce(new Func2<Integer, Integer, Integer>() {
//            @Override
//            public Integer call(Integer x, Integer y) {
//                return x+y; // 1+2+3+4+5+6+7+8+9+10
//            }
//        }).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                Log.d(TAG,"result="+ integer); // result = 55
//            }
//        });

//        Observable.from(new Integer[]{1,2,3,4,5,6,7,8,9,10}).collect(new Func0<Integer>() {
//            @Override
//            public Integer call() {
//                return 5;
//            }
//        }, new Action2<Integer, Integer>() {
//            @Override
//            public void call(Integer x, Integer y) {
//                Log.d(TAG,"x = "+x+ "y="+y);
//
//            }
//        }).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                Log.d(TAG,"subscribe integer = "+integer);
//            }
//        });
//        Observable.from(new Integer[]{1,2,3,4,5,6,7,8,9,10}).all(new Func1<Integer, Boolean>() {
//            @Override
//            public Boolean call(Integer integer) {
//                return integer<=10;
//            }
//        }).subscribe(new Action1<Boolean>() {
//            @Override
//            public void call(Boolean aBoolean) {
//                Log.d(TAG,"result is "+ aBoolean); //result is true
//            }
//        });

//        Observable<Integer> delay3 = Observable.just(1, 2, 3).delay(3000, TimeUnit.MILLISECONDS);
//        Observable<Integer> delay2 = Observable.just(4, 5, 6).delay(2000, TimeUnit.MILLISECONDS);
//        Observable<Integer> delay1 = Observable.just(7, 8, 9).delay(1000, TimeUnit.MILLISECONDS);
//        Observable.amb(delay1, delay2, delay3).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                Log.d(TAG, "integer=" + integer); // 7,8,9
//            }
//        });

//        Observable.from(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
//                .contains(11)
//                .subscribe(new Action1<Boolean>() {
//                    @Override
//                    public void call(Boolean aBoolean) {
//                        Log.d(TAG, "result is " + aBoolean);//result is false
//                    }
//                });

//        Observable.create(new Observable.OnSubscribe<Integer>() {
//            @Override
//            public void call(Subscriber<? super Integer> subscriber) {
//                subscriber.onCompleted();
//            }
//        }).defaultIfEmpty(100).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                Log.d(TAG, "integer= " + integer); // 100
//            }
//        });

//        Observable.sequenceEqual(Observable.just(1,2,3),Observable.just(1,2,3)).subscribe(new Action1<Boolean>() {
//            @Override
//            public void call(Boolean aBoolean) {
//                Log.d(TAG,"result is "+ aBoolean);//result is true
//            }
//        });
//        Observable.sequenceEqual(Observable.just(1,2),Observable.just(1,2,3)).subscribe(new Action1<Boolean>() {
//            @Override
//            public void call(Boolean aBoolean) {
//                Log.d(TAG,"result is "+ aBoolean);//result is false
//            }
//        });
//        Observable.interval(1, TimeUnit.SECONDS)
//                .skipUntil(Observable.timer(3, TimeUnit.SECONDS)) //延迟3s
//                .subscribe(new Action1<Long>() {
//                    @Override
//                    public void call(Long aLong) {
//                        Log.d(TAG, "aLong = " + aLong); //2,3,4...
//                    }
//                });
//        Observable.from(new Integer[]{1, 2, 3, 4, 5, 6, 5, 4,3,2,1})
//                .skipWhile(new Func1<Integer, Boolean>() {
//                    @Override
//                    public Boolean call(Integer integer) { //1,2,3,4,5
//                        Log.d(TAG,"integer -> "+integer ); //如果首次为true后面的将不进行判断
//                        return integer<5; //
//                    }
//                }).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                Log.d(TAG, "integer = " + integer); //5, 6, 5, 4,3,2,1
//            }
//        });
//        Observable.interval(1, TimeUnit.SECONDS)
//                .takeUntil(Observable.timer(3, TimeUnit.SECONDS)) //延迟3s
//                .subscribe(new Action1<Long>() {
//                    @Override
//                    public void call(Long aLong) {
//                        Log.d(TAG, "aLong = " + aLong); //0,1
//                    }
//                });

//        Observable.from(new Integer[]{1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1})
//                .takeWhile(new Func1<Integer, Boolean>() {
//                    @Override
//                    public Boolean call(Integer integer) { //1,2,3,4,5
//                        Log.d(TAG, "integer -> " + integer); //如果首次为false后面的将不进行判断
//                        return integer < 5; //
//                    }
//                }).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                Log.d(TAG, "integer = " + integer); //1,2,3,4,5
//            }
//        });
//        Observable.just(1, 2, 3, 4)
//                .doOnEach(new Action1<Notification<? super Integer>>() {
//                    @Override
//                    public void call(Notification<? super Integer> notification) {
//                        Log.d(TAG, "doOnEach" + notification.getKind().name()); // onNext,onNext,onNext,onNext,onCompleted
//                    }
//                })
//                .doOnSubscribe(new Action0() {
//                    @Override
//                    public void call() {
//                        Log.d(TAG, "doOnSubscribe");
//                    }
//                })
//                .doOnUnsubscribe(new Action0() {
//                    @Override
//                    public void call() {
//                        Log.d(TAG, "doOnUnsubscribe");
//                    }
//                })
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        Log.d(TAG, "integer = " + integer);
//                    }
//                });
//        Observable.just(1,2,3)
//                .materialize()
//                .subscribe(new Action1<Notification<Integer>>() {
//                    @Override
//                    public void call(Notification<Integer> integerNotification) {
//                        Log.d(TAG,"kind="+integerNotification.getKind().name()+"value="+integerNotification.getValue());
//                    }
//                });

//        Observable.interval(1,TimeUnit.SECONDS,AndroidSchedulers.mainThread()).
//        timeInterval().subscribe(new Action1<TimeInterval<Long>>() {
//            @Override
//            public void call(TimeInterval<Long> longTimeInterval) {
//                Log.d(TAG,"value = "+longTimeInterval.getIntervalInMilliseconds());//
//            }
//        });
//        Observable.range(5, 5).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                Log.d(TAG, "integer=" + integer); //5,6,7,8,9
//            }
//        });

//        Observable.defer(new Func0<Observable<Integer>>() {
//            @Override
//            public Observable<Integer> call() {
//                return Observable.just(1,2,3);
//            }
//        }).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//
//            }
//        });
//    }
//        Observable.just(1, 2, 3).repeat(5).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                Log.d(TAG, "integer=" + integer); // 1,2,3,1,2,3...重复5次
//            }
//        });
//        Observable.timer(3, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Long>() {
//                    @Override
//                    public void call(Long aLong) {
//                        Log.d(TAG, "aLong=" + aLong); // 延时3s
//                    }
//                });

//        Observable.from(new Integer[]{1,2,3,4,5,6,7,8,9,10}).scan(new Func2<Integer, Integer, Integer>() {
//            @Override
//            public Integer call(Integer x, Integer y) {
//                return x+y;
//            }
//        }).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                Log.d(TAG, "integer=" + integer);// 1,3,6,10,15,21,28,36,45,55
//            }
//        });
//        Observable.from(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9})
//                .filter(new Func1<Integer, Boolean>() {
//                    @Override
//                    public Boolean call(Integer integer) {
//                        return integer < 5;
//                    }
//                })
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        Log.d(TAG, "integer=" + integer); //1,2,3,4
//
//                    }
//                });

//        Observable.from(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9})
//                .take(2)
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        Log.d(TAG, "integer=" + integer); //1,2
//
//                    }
        //   });
////          (200)(400)(600) (800) (1000)
//        // ---0--- 5---10----15----20
//        //    (300)   (600)  (900) (1200)(1500)
//        //------0------5------10---  15----20
//        Observable<Long> observable1=Observable.interval(200,TimeUnit.MILLISECONDS)
//                .map(new Func1<Long, Long>() {
//                    @Override
//                    public Long call(Long aLong) {
//                        return aLong*5;
//                    }
//                }).take(5);
//        Observable<Long> observable2=Observable.interval(300,TimeUnit.MILLISECONDS)
//                .map(new Func1<Long, Long>() {
//                    @Override
//                    public Long call(Long aLong) {
//                        return aLong*5;
//                    }
//                }).take(5);
//        Observable.zip(observable1, observable2, new Func2<Long, Long, Long>() {
//            @Override
//            public Long call(Long aLong, Long aLong2) {
//                return aLong+aLong2;
//            }
//        }).subscribe(new Action1<Long>() {
//            @Override
//            public void call(Long aLong) {
//                Log.d(TAG, "aLong=" + aLong); //0,10,20,30,40
//            }
//        });
//        Observable.just(1, 2, 3, 4, 5, 6)
//                .groupBy(new Func1<Integer, Boolean>() {
//                    @Override
//                    public Boolean call(Integer integer) {
//                        return integer % 2 == 0;
//                    }
//                })
//                .subscribe(new Action1<GroupedObservable<Boolean, Integer>>() {
//                    @Override
//                    public void call(final GroupedObservable<Boolean, Integer> observable) {
//                        //toList方法转换为Observable<List<T>>
//                        observable.toList().subscribe(new Action1<List<Integer>>() {
//                            @Override
//                            public void call(List<Integer> integers) {
//                                Log.d(TAG, "key=" + observable.getKey() + ",values=" + integers);
//                                //key=false,values=[1, 3, 5]
//                                //key=true,values=[2, 4, 6]
//                            }
//                        });
//                    }
//                });
//        Observable<Long> observable1 = Observable.interval(200, TimeUnit.MILLISECONDS)
//                .map(new Func1<Long, Long>() {
//                    @Override
//                    public Long call(Long aLong) {
//                        return aLong * 5;
//                    }
//                }).take(5);
//        Observable<Long> observable2 = Observable.interval(300, TimeUnit.MILLISECONDS)
//                .map(new Func1<Long, Long>() {
//                    @Override
//                    public Long call(Long aLong) {
//                        return aLong * 5;
//                    }
//                }).take(5);
//        Observable.merge(observable1, observable2).subscribe(new Action1<Long>() {
//            @Override
//            public void call(Long aLong) {
//                Log.d(TAG, "aLong=" + aLong); //0,0,5,10,5,15,10,20,15,20
//            }
//        });

//        Observable.just(1, 2, 3, 4).startWith(-1, 0)
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        Log.d(TAG,"integer="+integer); // -1,0,1,2,3,4
//                    }
//                });
//        Observable.just(1,2,3,4).startWith(Observable.just(-1,0))
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        Log.d(TAG,"integer="+integer); // -1,0,1,2,3,4
//                    }
//                });

        test();

    }

    private void test() {
        ArrayList<Observable<Uri>> strings = new ArrayList<>();
        Log.e(TAG, "test 0----" + System.currentTimeMillis());
        strings.add(Observable.just("1").map(new Func1<String, Uri>() {
            @Override
            public Uri call(String s) {
                try {
                    Log.e(TAG, "test 1  start" + System.currentTimeMillis());
                    Thread.sleep(2000);
                    Log.e(TAG, "test 1 " + System.currentTimeMillis() + Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return Uri.fromParts("http:\\", "test", "1");
            }
        }).subscribeOn(Schedulers.newThread()));
        Log.e(TAG, "test 1----" + System.currentTimeMillis());
        strings.add(Observable.just("2").map(new Func1<String, Uri>() {
            @Override
            public Uri call(String s) {
                try {
                    Thread.sleep(1000);
                    Log.e(TAG, "test 2 " + System.currentTimeMillis() + Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return Uri.fromParts("http:\\", "test", "2");
            }
        }).subscribeOn(Schedulers.newThread()));
        Log.e(TAG, "test 2----" + System.currentTimeMillis());
        strings.add(Observable.just("3").map(new Func1<String, Uri>() {
            @Override
            public Uri call(String s) {
                try {
                    Thread.sleep(3000);
                    Log.e(TAG, "test 3 " + System.currentTimeMillis() + Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return Uri.fromParts("http:\\", "test", "3");
            }
        }).subscribeOn(Schedulers.newThread()));
        Log.e(TAG, "test 3----" + System.currentTimeMillis());
        strings.add(Observable.just("4").map(new Func1<String, Uri>() {
            @Override
            public Uri call(String s) {
                try {
                    Thread.sleep(1000);
                    Log.e(TAG, "test 4 " + System.currentTimeMillis() + Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return Uri.fromParts("http:\\", "test", "4");
            }
        }).subscribeOn(Schedulers.newThread()));
//        Observable.amb(strings).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<Uri>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Uri uri) {
//                        Log.d(TAG,uri.toString());
//                    }
//                });
//        Observable.zip(strings, new FuncN<ArrayList<Uri>>() {
//            @Override
//            public ArrayList<Uri> call(Object... args) {
//                for (Object o : args) {
//                      Log.e(TAG,"test " + o);
//                }
//
//                return null;
//            }
//        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<ArrayList<Uri>>() {
//                    @Override
//                    public void onCompleted() {
//                          Log.e(TAG,"test yes");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(ArrayList<Uri> uris) {
//                          Log.e(TAG,"test onNext"+Thread.currentThread());
//                    }
//                });
        Observable.merge(strings).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Uri>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "test yes");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Uri uri) {
                        Log.e(TAG, "test uri " + uri);
                    }
                });
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

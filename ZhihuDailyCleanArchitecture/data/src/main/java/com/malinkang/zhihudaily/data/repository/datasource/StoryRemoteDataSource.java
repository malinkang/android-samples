package com.malinkang.zhihudaily.data.repository.datasource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.malinkang.zhihudaily.data.entity.StoryEntity;
import com.malinkang.zhihudaily.data.entity.StoryListEntity;
import com.malinkang.zhihudaily.data.net.RestApi;

import java.util.List;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class StoryRemoteDataSource implements StoryDataSource {

    public static final String BASE_URL = "http://news.at.zhihu.com/api/4/";

    private RestApi mRestApi;

    private final Action1<List<StoryEntity>> mSaveStoriesToDB = new Action1<List<StoryEntity>>() {
        @Override
        public void call(List<StoryEntity> stories) {
            //缓存到数据库
        }
    };

    @Inject
    public StoryRemoteDataSource() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mRestApi = retrofit.create(RestApi.class);
    }

    @Override
    public Observable<List<StoryEntity>> stories(String date) {
        return mRestApi.getStories(date)
                .flatMap(new Func1<StoryListEntity, Observable<List<StoryEntity>>>() {
                    @Override
                    public Observable<List<StoryEntity>> call(final StoryListEntity storyListEntity) {
                        return Observable.create(new Observable.OnSubscribe<List<StoryEntity>>() {
                            @Override
                            public void call(Subscriber<? super List<StoryEntity>> subscriber) {
                                subscriber.onNext(storyListEntity.getStories());
                            }
                        });
                    }
                })
                .doOnNext(mSaveStoriesToDB);
    }

//    @Override public Observable<List<UserEntity>> userEntityList() {
//      return this.restApi.userEntityList();
//    }
//
//    @Override public Observable<UserEntity> userEntityDetails(final int userId) {
//      return this.restApi.userEntityById(userId).doOnNext(saveToCacheAction);
//    }
}
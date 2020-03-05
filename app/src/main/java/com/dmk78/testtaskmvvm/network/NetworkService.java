package com.dmk78.testtaskmvvm.network;

import android.util.Log;


import androidx.lifecycle.MutableLiveData;

import com.dmk78.testtaskmvvm.model.Quote;

import org.reactivestreams.Subscription;

import java.util.List;
import java.util.Objects;

import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private Retrofit mRetrofit;
    private static String BASE_URL = "https://oico.app/";


    public NetworkService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }


    public void getQuotesList(int limit, int offset, MutableLiveData<List<Quote>> callback) {

        getJSONApi().getQuotesList(limit, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Quote>>() {
                    @Override
                    public void onSuccess(List<Quote> quotes) {
                        callback.postValue(quotes);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }


    public void getQuote(int id, MutableLiveData<Quote> callback) {
        getJSONApi().getQuoteDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Quote>() {
                    @Override
                    public void onSuccess(Quote quote) {
                        callback.postValue(quote);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }


    public JsonPlaceHolderApi getJSONApi() {
        return mRetrofit.create(JsonPlaceHolderApi.class);
    }


    public interface QuotesListLoadListener {
        void onQuotesListLoaded(List<Quote> quotes);
    }

    public interface QuoteDetailsLoadListener {
        void onQuoteLoaded(Quote quote);
    }
}
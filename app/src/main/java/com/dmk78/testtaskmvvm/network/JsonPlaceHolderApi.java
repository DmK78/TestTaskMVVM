package com.dmk78.testtaskmvvm.network;




import com.dmk78.testtaskmvvm.model.Quote;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    @GET("test")
    Single<List<Quote>> getQuotesList(
            @Query("limit") int limit,
            @Query("offset") int offset
    );

    @GET("test/{id}")
    Single<Quote> getQuoteDetails(@Path("id") int id);


}

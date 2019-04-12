package com.kgcorner.sdk;

import com.kgcorner.sdk.models.Topic;
import com.kgcorner.sdk.models.Quote;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VachanService {

    @GET("/quotes")
    Observable<List<Quote>> getQuotes();

    @GET("/quotes")
    Observable<List<Quote>> getQuotes(@Query("page") int maxItems);

    @GET("/categories")
    Observable<List<Topic>> getTopics();
}

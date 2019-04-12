package com.kgcorner.vachan.services;

import com.kgcorner.sdk.VachanService;
import com.kgcorner.vachan.BuildConfig;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Configures Vachan SDK
 */
@Module
public abstract class ServicesModule {
    public static final int CONNECT_TIMEOUT_IN_MS = 30000;

    @Provides
    static OkHttpClient provideOkHttpClient(RequestInterceptor requestInterceptor) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(requestInterceptor)
                .connectTimeout(CONNECT_TIMEOUT_IN_MS, TimeUnit.MILLISECONDS);
        if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(loggingInterceptor);
        }
        return clientBuilder.build();
    }

    @Provides
    static Retrofit providesRetrofit(OkHttpClient client) {
        return new Retrofit
                .Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    static VachanService provideVachanService(Retrofit retrofit) {
        return retrofit.create(VachanService.class);
    }
}

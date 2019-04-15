package com.kgcorner.vachan.services;

import com.kgcorner.vachan.BuildConfig;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Request interceptor. Responsible for injecting version tag into request
 */
public class RequestInterceptor implements Interceptor {

    private static final String TAG = "RequestInterceptor";
    private static final String VERSION = BuildConfig.APP_NAME + ":"
            + BuildConfig.VERSION_NAME;
    private static final String VERSION_HEADER = "X-SOURCE";

    @Inject
    RequestInterceptor() {}

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request newRequest = original.newBuilder().addHeader(VERSION_HEADER, VERSION).build();

        return chain.proceed(newRequest);
    }
}

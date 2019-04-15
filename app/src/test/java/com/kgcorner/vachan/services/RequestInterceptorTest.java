package com.kgcorner.vachan.services;

import com.kgcorner.vachan.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class RequestInterceptorTest {
    private static final String VERSION = BuildConfig.APP_NAME + ":"
            + BuildConfig.VERSION_NAME;
    private static final String VERSION_HEADER = "X-SOURCE";
    @Test
    public void interceptTest() {
        try {
            MockWebServer mockWebServer = new MockWebServer();
            mockWebServer.start();
            mockWebServer.enqueue(new MockResponse());

            OkHttpClient okHttpClient = ServicesModule.provideOkHttpClient(new RequestInterceptor());
            okHttpClient.newCall(new Request.Builder().url(mockWebServer.url("/")).build())
                    .execute();

            RecordedRequest request = mockWebServer.takeRequest();
            assertEquals(VERSION, request.getHeader(VERSION_HEADER));

            mockWebServer.shutdown();
        } catch(Exception x) {
            x.printStackTrace();
        }
    }

}

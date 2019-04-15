package com.kgcorner.vachan.services;

import com.kgcorner.sdk.VachanService;
import com.kgcorner.vachan.BuildConfig;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@RunWith(MockitoJUnitRunner.class)
public class ServiceModuleTest {

    @Test
    public void provideOkHttpClientTest() {
        RequestInterceptor requestInterceptor = new RequestInterceptor();
        OkHttpClient client = ServicesModule.provideOkHttpClient(requestInterceptor);
        Assert.assertNotNull("OkHttpClient is null", client);
        Assert.assertNotNull("Interceptor is not set", client.interceptors());
        Assert.assertTrue(client.interceptors().size() > 0);
        Assert.assertEquals("timeout is not matching",
                ServicesModule.CONNECT_TIMEOUT_IN_MS, client.connectTimeoutMillis());
    }

    @Test
    public void providesRetrofitTest() {
        OkHttpClient client = Mockito.mock(OkHttpClient.class);
        Retrofit retrofit = ServicesModule.providesRetrofit(client);
        Assert.assertEquals("Base url is not matching",
                BuildConfig.API_BASE_URL, retrofit.baseUrl().toString());
        Assert.assertNotNull("No converter factory found", retrofit.converterFactories());
    }

    @Test
    public void provideVachanServiceTest() {
        OkHttpClient client = Mockito.mock(OkHttpClient.class);
        Retrofit retrofit = ServicesModule.providesRetrofit(client);
        VachanService service = ServicesModule.provideVachanService(retrofit);
        Assert.assertNotNull("Vachan Service is null", service);
    }
}

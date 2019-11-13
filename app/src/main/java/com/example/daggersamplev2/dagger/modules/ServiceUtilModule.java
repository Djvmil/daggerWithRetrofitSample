package com.example.daggersamplev2.dagger.modules;
/**
 * Created by Djvmil_ on 13/11/2019.
 */
import com.example.daggersamplev2.dagger.backend.ServiceUtil;
import com.example.daggersamplev2.dagger.scopes.CustomApplicationScope;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = NetworkModule.class)
public class ServiceUtilModule {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @Provides
    @CustomApplicationScope
    ServiceUtil getServiceUtil(Retrofit retrofit) {   // How does the retrofit object came as a parameter to this function.You see @Provides annotation on every method this means it providing that object.
        return retrofit.create(ServiceUtil.class);
    }

    @Provides
    @CustomApplicationScope
    Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @CustomApplicationScope
    Retrofit getRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build();
    }
}
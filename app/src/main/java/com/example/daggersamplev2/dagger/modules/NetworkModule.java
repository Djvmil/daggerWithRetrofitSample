package com.example.daggersamplev2.dagger.modules;
/**
 * Created by Djvmil_ on 13/11/2019.
 */
import android.content.Context;

import com.example.daggersamplev2.dagger.modules.ApplicationContextModule;
import com.example.daggersamplev2.dagger.qualifiers.ApplicationContextQualifier;
import com.example.daggersamplev2.dagger.scopes.CustomApplicationScope;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

@Module(includes = ApplicationContextModule.class)
public class NetworkModule {
    @Provides
    @CustomApplicationScope
    HttpLoggingInterceptor getInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> Timber.i(message));
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return interceptor;
    }

    @Provides
    @CustomApplicationScope
    Cache getCache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1000 * 1000);  // 10 MiB cache
    }

    @Provides
    @CustomApplicationScope
    File getFile(@ApplicationContextQualifier Context context) {
        File file = new File(context.getFilesDir(), "cache_dir");
        if (!file.exists())
            file.mkdirs();
        return file;
    }

    @Provides
    @CustomApplicationScope
    OkHttpClient getOkHttpClient(HttpLoggingInterceptor interceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(interceptor)
                .build();
    }
}
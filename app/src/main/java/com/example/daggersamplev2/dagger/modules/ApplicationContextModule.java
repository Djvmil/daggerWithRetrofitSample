package com.example.daggersamplev2.dagger.modules;
/**
 * Created by Djvmil_ on 13/11/2019.
 */
import android.content.Context;

import com.example.daggersamplev2.dagger.qualifiers.ApplicationContextQualifier;
import com.example.daggersamplev2.dagger.scopes.CustomApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationContextModule {
    private final Context context;
    public ApplicationContextModule(Context context) {
        this.context = context.getApplicationContext();
    }
    @Provides
    @CustomApplicationScope
    @ApplicationContextQualifier
    Context getContext() {
        return context;
    }
}
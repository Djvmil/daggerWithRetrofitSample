package com.example.daggersamplev2.dagger.components;
/**
 * Created by Djvmil_ on 13/11/2019.
 */
import com.example.daggersamplev2.MainActivity;
import com.example.daggersamplev2.dagger.backend.ServiceUtil;
import com.example.daggersamplev2.dagger.modules.PicassoModule;
import com.example.daggersamplev2.dagger.modules.ServiceUtilModule;
import com.example.daggersamplev2.dagger.scopes.CustomApplicationScope;
import com.squareup.picasso.Picasso;

import dagger.Component;

@CustomApplicationScope
@Component(modules = {ServiceUtilModule.class, PicassoModule.class})
public interface ServiceApplicationComponent {
    Picasso getPicasso();
    ServiceUtil getServiceUtil();

    void inject(MainActivity mainActivity);
}
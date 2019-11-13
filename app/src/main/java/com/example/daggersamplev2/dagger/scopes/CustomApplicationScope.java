package com.example.daggersamplev2.dagger.scopes;
/**
 * Created by Djvmil_ on 13/11/2019.
 */
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.CLASS)
public @interface CustomApplicationScope {
}
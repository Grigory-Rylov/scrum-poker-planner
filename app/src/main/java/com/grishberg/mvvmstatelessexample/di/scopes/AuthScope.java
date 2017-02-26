package com.grishberg.mvvmstatelessexample.di.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by grishberg on 26.02.17.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthScope {

}


package com.grishberg.mvvmstatelessexample.domain.repository.common;

/**
 * Created by grishberg on 26.02.17.
 */

public interface SoftErrorDelegate<T> {
    Throwable checkSoftError(T body);
}

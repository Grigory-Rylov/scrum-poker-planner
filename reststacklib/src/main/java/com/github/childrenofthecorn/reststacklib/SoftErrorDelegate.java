package com.github.childrenofthecorn.reststacklib;

/**
 * Created by grishberg on 23.04.17.
 */

public interface SoftErrorDelegate<T> {
    Throwable checkSoftError(T body);
}
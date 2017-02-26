package com.grishberg.mvvmstatelessexample.utils;

/**
 * Created by grishberg on 26.02.17.
 */

public interface Logger {
    void d(String tag, String message);

    void e(String tag, String message);

    void e(String tag, String message, Throwable throwable);
}

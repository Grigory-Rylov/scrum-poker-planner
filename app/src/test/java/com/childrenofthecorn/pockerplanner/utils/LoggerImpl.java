package com.childrenofthecorn.pockerplanner.utils;

/**
 * Created by grishberg on 26.02.17.
 */

public class LoggerImpl implements Logger {
    @Override
    public void d(final String tag, final String message) {
        System.out.println(message);
    }

    @Override
    public void e(final String tag, final String message) {
        System.out.println(message);
    }

    @Override
    public void e(final String tag, final String message, final Throwable throwable) {
        throwable.printStackTrace();
    }
}

package com.github.grigoryrylov.reststacklib.exception;

/**
 * Created by grishberg on 23.04.17.
 */

public class BaseException extends Exception {
    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }
}

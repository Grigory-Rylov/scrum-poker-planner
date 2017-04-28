package com.childrenofthecorn.pockerplanner.domain.exceptions;

/**
 * Created by grishberg on 26.02.17.
 */

public final class ErrorCodes {
    public static final int WRONG_SPRINT_TOKEN_EXCEPTION = 1000;
    public static final int WRONG_ACCESS_TOKEN_EXCEPTION = 1001;
    public static final int EMPTY_REQUIRED_ARGUMENTS_EXCEPTION = 1002;

    private ErrorCodes() throws NoSuchMethodException {
        throw new NoSuchMethodException();
    }
}

package com.childrenofthecorn.pockerplanner.domain.exceptions;

import android.support.annotation.NonNull;

import com.childrenofthecorn.pockerplanner.domain.model.rest.RestError;
import com.github.grigoryrylov.reststacklib.exception.BaseException;

import lombok.Getter;

/**
 * Created by grishberg on 26.02.17.
 */

@Getter
public class AppException extends BaseException {
    private final int code;

    public AppException(@NonNull RestError error) {
        super(error.getMessage());
        this.code = error.getCode();
    }

    public AppException(String message) {
        super(message);
        code = 0;
    }

    public static final class WrongSprintTokenException extends AppException {
        public WrongSprintTokenException(RestError error) {
            super(error);
        }
    }

    public static final class WrongAccessTokenException extends AppException {
        public WrongAccessTokenException(RestError error) {
            super(error);
        }
    }

    public static final class UnknownException extends AppException {
        public UnknownException(RestError error) {
            super(error);
        }
    }

    public static final class NoInternetException extends AppException {
        public NoInternetException() {
            super("no internet");
        }
    }
}

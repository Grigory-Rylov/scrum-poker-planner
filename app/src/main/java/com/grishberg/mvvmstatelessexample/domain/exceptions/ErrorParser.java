package com.grishberg.mvvmstatelessexample.domain.exceptions;

import android.support.annotation.NonNull;

import com.grishberg.mvvmstatelessexample.domain.model.rest.RestResponse;
import com.grishberg.reststacklib.SoftErrorDelegate;

import static com.grishberg.mvvmstatelessexample.domain.exceptions.ErrorCodes.EMPTY_REQUIRED_ARGUMENTS_EXCEPTION;
import static com.grishberg.mvvmstatelessexample.domain.exceptions.ErrorCodes.WRONG_ACCESS_TOKEN_EXCEPTION;
import static com.grishberg.mvvmstatelessexample.domain.exceptions.ErrorCodes.WRONG_SPRINT_TOKEN_EXCEPTION;

/**
 * Created by grishberg on 26.02.17.
 */

public class ErrorParser implements SoftErrorDelegate<RestResponse> {
    @Override
    public Throwable checkSoftError(@NonNull final RestResponse body) {
        if (body.getError() == null) {
            return null;
        }
        switch (body.getError().getCode()) {
            case WRONG_SPRINT_TOKEN_EXCEPTION:
                return new AppException.WrongSprintTokenException(body.getError());
            case WRONG_ACCESS_TOKEN_EXCEPTION:
                return new AppException.WrongAccessTokenException(body.getError());
            default:
                return new AppException.UnknownException(body.getError());
        }
    }
}

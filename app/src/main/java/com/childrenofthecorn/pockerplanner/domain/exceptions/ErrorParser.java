package com.childrenofthecorn.pockerplanner.domain.exceptions;

import android.support.annotation.NonNull;

import com.childrenofthecorn.pockerplanner.domain.model.rest.RestResponse;
import com.github.grigoryrylov.reststacklib.SoftErrorDelegate;

import static com.childrenofthecorn.pockerplanner.domain.exceptions.ErrorCodes.WRONG_ACCESS_TOKEN_EXCEPTION;
import static com.childrenofthecorn.pockerplanner.domain.exceptions.ErrorCodes.WRONG_SPRINT_TOKEN_EXCEPTION;

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

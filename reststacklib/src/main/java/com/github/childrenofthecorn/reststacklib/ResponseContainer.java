package com.github.childrenofthecorn.reststacklib;

import android.support.annotation.Nullable;

import com.github.childrenofthecorn.reststacklib.exception.BaseException;

import java.io.Serializable;

/**
 * Created by grishberg on 23.04.17.
 */

public interface ResponseContainer<T extends Serializable> {
    @Nullable
    T getPayload();

    @Nullable
    BaseException getException();
}

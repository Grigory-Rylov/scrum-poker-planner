package com.grishberg.reststacklib;

import android.support.annotation.Nullable;

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

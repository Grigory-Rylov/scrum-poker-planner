package com.childrenofthecorn.pockerplanner.domain.model.rest;

import lombok.Getter;

/**
 * Created by grishberg on 26.02.17.
 */
@Getter
public class RestResponse<T> {
    private final boolean isCached;

    private final T data;

    private RestError error;

    public RestResponse(final T data) {
        this.data = data;
        this.isCached = false;
    }

    public RestResponse(final T data, final boolean isCached) {
        this.data = data;
        this.isCached = isCached;
    }
}


package com.grishberg.mvvmstatelessexample.domain.repository.common;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by grishberg on 27.11.16.
 */
public class RxErrorHandlingCallAdapterFactory<T> extends CallAdapter.Factory {
    private final RxJavaCallAdapterFactory original;
    private final SoftErrorDelegate<T> softErrorDelegate;

    private RxErrorHandlingCallAdapterFactory(final SoftErrorDelegate<T> softErrorDelegate) {
        this.softErrorDelegate = softErrorDelegate;
        original = RxJavaCallAdapterFactory.create();
    }

    public static <T> CallAdapter.Factory create(final SoftErrorDelegate<T> softErrorDelegate) {
        return new RxErrorHandlingCallAdapterFactory(softErrorDelegate);
    }

    @Override
    public CallAdapter<?> get(final Type returnType, final Annotation[] annotations, final Retrofit retrofit) {
        return new RxCallAdapterWrapper(
                original.get(returnType, annotations, retrofit),
                softErrorDelegate);
    }

    private static class RxCallAdapterWrapper<T> implements CallAdapter<Observable<?>> {
        private final CallAdapter<?> wrapped;
        private final SoftErrorDelegate<T> softErrorDelegate;

        public RxCallAdapterWrapper(
                final CallAdapter<?> wrapped,
                final SoftErrorDelegate<T> softErrorDelegate) {
            this.wrapped = wrapped;
            this.softErrorDelegate = softErrorDelegate;
        }

        @Override
        public Type responseType() {
            return wrapped.responseType();
        }

        @SuppressWarnings("unchecked")
        @Override
        public <R> Observable<?> adapt(final Call<R> call) {
            return ((Observable<T>) wrapped.adapt(call))
                    .flatMap(restResponse -> {
                        final Throwable throwable = softErrorDelegate.checkSoftError(restResponse);
                        if (throwable != null) {
                            return Observable.error(throwable);
                        }
                        return Observable.just(restResponse);
                    }
        }

        );
    }
}
}

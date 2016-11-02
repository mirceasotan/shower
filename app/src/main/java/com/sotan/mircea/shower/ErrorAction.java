package com.sotan.mircea.shower;

import retrofit2.adapter.rxjava.HttpException;
import rx.functions.Action1;

/**
 * @author mircea sotan
 */

public abstract class ErrorAction implements Action1<Throwable> {

    public abstract void onConnectionError();

    public abstract void onApiError();

    @Override
    public void call(Throwable throwable) {
        if (throwable instanceof HttpException) {
            onApiError();
        } else {
            onConnectionError();
        }
    }
}

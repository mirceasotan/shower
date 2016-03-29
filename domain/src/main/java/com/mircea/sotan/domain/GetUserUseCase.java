package com.mircea.sotan.domain;

import android.support.annotation.Nullable;

/**
 * Created by mircea
 */
public interface GetUserUseCase extends UseCase {
    void getUser(@Nullable DataListener dataListener);
}

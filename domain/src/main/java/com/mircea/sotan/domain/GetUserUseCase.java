package com.mircea.sotan.domain;

import android.support.annotation.Nullable;

import com.mircea.sotan.model.PublicUser;

/**
 * Created by mircea
 */
public interface GetUserUseCase extends UseCase {
    /**
     * Use case for fetching detailed profile information about the current
     * user (including the current user’s username)
     *
     * @param dataListener
     */
    void getUser(@Nullable DataListener<PublicUser> dataListener);
}

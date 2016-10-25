package com.mircea.sotan.domain.profiles;

import android.support.annotation.Nullable;

import com.mircea.sotan.domain.DataListener;
import com.mircea.sotan.domain.UseCase;
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

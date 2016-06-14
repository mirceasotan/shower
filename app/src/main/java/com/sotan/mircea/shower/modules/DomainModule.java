package com.sotan.mircea.shower.modules;

import com.mircea.sotan.domain.GetUserUseCase;
import com.mircea.sotan.domain.GetUserUseCaseImpl;
import com.mircea.sotan.repository.apis.UserRestApi;

import dagger.Module;
import dagger.Provides;

/**
 * @author mircea on 4/4/16
 */
@Module
public class DomainModule {

    @Provides
    public GetUserUseCase providesGetUserUseCase(UserRestApi restApi) {
        return new GetUserUseCaseImpl(restApi);
    }
}

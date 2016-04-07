package com.sotan.mircea.shower.modules;

import com.mircea.sotan.domain.GetUserUseCase;
import com.mircea.sotan.domain.GetUserUseCaseImpl;
import com.mircea.sotan.repository.apis.UserRestApi;
import com.sotan.mircea.shower.ConfigConstants;
import com.sotan.mircea.shower.ConfigurationManager;

import dagger.Module;
import dagger.Provides;

/**
 * @author mircea on 4/4/16
 */
@Module
public class DomainModule {

    @Provides
    public GetUserUseCase providesGetUserUseCase(ConfigurationManager configurationManager, UserRestApi restApi) {
        return new GetUserUseCaseImpl(configurationManager.getValueForKey(ConfigConstants.BASE_URI) +
                configurationManager.getValueForKey(ConfigConstants.GET_CURRENT_USER_PROFILE_PATH), restApi);
    }
}

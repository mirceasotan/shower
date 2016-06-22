package com.sotan.mircea.shower.modules;

import com.mircea.sotan.domain.GetAlbumDetailUseCaseImpl;
import com.mircea.sotan.domain.GetAlbumDetailsUseCase;
import com.mircea.sotan.domain.GetNewReleasesUseCase;
import com.mircea.sotan.domain.GetNewReleasesUseCaseImpl;
import com.mircea.sotan.domain.GetUserUseCase;
import com.mircea.sotan.domain.GetUserUseCaseImpl;
import com.mircea.sotan.repository.apis.AlbumsRestApi;
import com.mircea.sotan.repository.apis.BrowseRestApi;
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

    @Provides
    public GetNewReleasesUseCase providesGetNewReleaseUseCase(BrowseRestApi restApi) {
        return new GetNewReleasesUseCaseImpl(restApi);
    }

    @Provides
    public GetAlbumDetailsUseCase provideGetAlbumDetailsUseCase(AlbumsRestApi albumsRestApi) {
        return new GetAlbumDetailUseCaseImpl(albumsRestApi);
    }
}

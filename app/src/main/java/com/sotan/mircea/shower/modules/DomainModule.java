package com.sotan.mircea.shower.modules;

import com.mircea.sotan.domain.albums.GetAlbumDetailUseCaseImpl;
import com.mircea.sotan.domain.albums.GetAlbumDetailsUseCase;
import com.mircea.sotan.domain.browse.GetNewReleasesUseCase;
import com.mircea.sotan.domain.browse.GetNewReleasesUseCaseImpl;
import com.mircea.sotan.domain.profiles.GetUserUseCase;
import com.mircea.sotan.domain.profiles.GetUserUseCaseImpl;
import com.mircea.sotan.repository.BrowseDataSource;
import com.mircea.sotan.repository.BrowseRepository;
import com.mircea.sotan.repository.apis.AlbumsRestApi;
import com.mircea.sotan.repository.apis.BrowseRestApi;
import com.mircea.sotan.repository.apis.UserRestApi;
import com.mircea.sotan.repository.cache.BrowseCache;
import com.mircea.sotan.repository.cache.BrowseCacheImpl;

import dagger.Module;
import dagger.Provides;

/**
 * @author mircea on 4/4/16
 */
@Module
public class DomainModule {

    @Provides
    public BrowseCache providesCache() {
        return new BrowseCacheImpl();
    }

    @Provides
    public BrowseDataSource providesDataSource(BrowseRestApi api, BrowseCache cache) {
        return new BrowseRepository(api, cache);
    }

    @Provides
    public GetUserUseCase providesGetUserUseCase(UserRestApi restApi) {
        return new GetUserUseCaseImpl(restApi);
    }

    @Provides
    public GetNewReleasesUseCase providesGetNewReleaseUseCase(BrowseDataSource browseRepository) {
        return new GetNewReleasesUseCaseImpl(browseRepository);
    }

    @Provides
    public GetAlbumDetailsUseCase provideGetAlbumDetailsUseCase(AlbumsRestApi albumsRestApi) {
        return new GetAlbumDetailUseCaseImpl(albumsRestApi);
    }
}

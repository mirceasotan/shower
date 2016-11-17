package com.mircea.sotan.domain.artists;

import com.mircea.sotan.model.BasePaging;
import com.mircea.sotan.model.SimpleAlbum;

import rx.Observable;

/**
 * @author mirceasotan
 */

public interface GetArtistAlbumsUseCase {

    Observable<BasePaging<SimpleAlbum>> getAlbums(String id, int offset, int limit);
}

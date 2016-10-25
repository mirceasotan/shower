package domain.test;

import com.mircea.sotan.domain.DataListener;
import com.mircea.sotan.domain.albums.GetAlbumDetailUseCaseImpl;
import com.mircea.sotan.domain.albums.GetAlbumDetailsUseCase;
import com.mircea.sotan.model.FullAlbum;
import com.mircea.sotan.repository.apis.AlbumsRestApi;
import com.mircea.sotan.repository.networking.Listener;
import com.mircea.sotan.repository.networking.NetworkError;
import com.mircea.sotan.repository.networking.ResponseContainer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author mirceasotan
 */
@RunWith(MockitoJUnitRunner.class)
public class GetAlbumDetailUseCaseTest {
    @Mock
    private AlbumsRestApi albumsRestApi;
    @Mock
    private DataListener<FullAlbum> dataListenerMock;
    @Captor
    private ArgumentCaptor<Listener<FullAlbum>> argumentCaptor;
    @Mock
    private FullAlbum album;
    @Mock
    private NetworkError networkError;
    private GetAlbumDetailsUseCase useCase;
    private final String id = "fakeId6532647";

    @Before
    public void init() {
        initMocks(this);
        useCase = new GetAlbumDetailUseCaseImpl(albumsRestApi);
    }

    @Test
    public void test_getAlbumDetail_success_nullDataListener_nonNullId() {
        useCase.getAlbumDetail(id, null);

        verify(albumsRestApi).getAlbumDetailsAsync(Matchers.contains(id), Matchers.<Listener<FullAlbum>>any());
        verify(albumsRestApi, times(1)).getAlbumDetailsAsync(Matchers.contains(id), argumentCaptor.capture());

        argumentCaptor.getValue().onResponse(new ResponseContainer<>(album, 200));

        verify(dataListenerMock, never()).onResponse(album);
    }

    @Test
    public void test_getNewReleases_success_nonNullDataListener() {
        useCase.getAlbumDetail(id, dataListenerMock);

        verify(albumsRestApi, times(1)).getAlbumDetailsAsync(Matchers.contains(id), argumentCaptor.capture());

        argumentCaptor.getValue().onResponse(new ResponseContainer<>(album, 200));

        verify(dataListenerMock).onResponse(album);
    }

    @Test
    public void test_getNewReleases_error_nullDataListener() {
        useCase.getAlbumDetail(id, null);

        verify(albumsRestApi, times(1)).getAlbumDetailsAsync(Matchers.contains(id), argumentCaptor.capture());

        argumentCaptor.getValue().onError(networkError);

        verify(dataListenerMock, never()).onError(networkError);
    }

    @Test
    public void test_getNewReleases_error_nonNullDataListener() {
        useCase.getAlbumDetail(id, dataListenerMock);

        verify(albumsRestApi, times(1)).getAlbumDetailsAsync(Matchers.contains(id), argumentCaptor.capture());

        argumentCaptor.getValue().onError(networkError);

        verify(dataListenerMock).onError(networkError);
    }
}

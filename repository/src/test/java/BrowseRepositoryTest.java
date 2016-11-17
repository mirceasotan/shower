import com.mircea.sotan.model.NewReleases;
import com.mircea.sotan.repository.BrowseRepository;
import com.mircea.sotan.repository.DataListener;
import com.mircea.sotan.repository.apis.BrowseRestApi;
import com.mircea.sotan.repository.cache.BrowseCache;
import com.mircea.sotan.repository.networking.ApiListener;
import com.mircea.sotan.repository.networking.NetworkError;
import com.mircea.sotan.repository.networking.ResponseContainer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author mirceasotan
 */

@RunWith(MockitoJUnitRunner.class)
public class BrowseRepositoryTest {
    @Mock
    BrowseRestApi apiMock;
    @Mock
    BrowseCache cacheMock;
    @Mock
    DataListener<NewReleases> dataListenerMock;
    @Mock
    NewReleases newReleasesMock;
    @Mock
    NetworkError networkErrorMock;
    @Captor
    private ArgumentCaptor<ApiListener<NewReleases>> argumentCaptor;
    private BrowseRepository browseRepository;

    @Before
    public void init() {
        browseRepository = new BrowseRepository(apiMock, cacheMock);
    }

    @Test
    public void test_getNewReleases_nonNullListener_positiveOffset_positiveLimit_emptyCache_returnsRemoteResponseSuccessfully() {
        int offset = 0;
        int limit = 10;

        browseRepository.getNewReleases(dataListenerMock, offset, limit);

        verify(dataListenerMock, never()).onResponse(cacheMock.getNewReleases());
        verify(apiMock, times(1)).getNewReleasesAsync(argumentCaptor.capture(), eq(offset), eq(limit));
        argumentCaptor.getValue().onResponse(new ResponseContainer<>(newReleasesMock, 200));
        verify(dataListenerMock).onResponse(newReleasesMock);
    }

    @Test
    public void test_getNewReleases_nonNullListener_positiveOffset_positiveLimit_emptyCache_returnsRemoteResponseWithError() {
        int offset = 0;
        int limit = 10;

        browseRepository.getNewReleases(dataListenerMock, offset, limit);

        verify(dataListenerMock, never()).onResponse(cacheMock.getNewReleases());
        verify(apiMock, times(1)).getNewReleasesAsync(argumentCaptor.capture(), eq(offset), eq(limit));
        argumentCaptor.getValue().onError(networkErrorMock);
        verify(dataListenerMock).onError(networkErrorMock);
    }

    @Test
    public void test_getNewReleases_nullListener_positiveOffset_positiveLimit_emptyCache_noSuccessCallback() {
        int offset = 0;
        int limit = 10;

        browseRepository.getNewReleases(null, offset, limit);

        verify(dataListenerMock, never()).onResponse(cacheMock.getNewReleases());
        verify(apiMock, times(1)).getNewReleasesAsync(argumentCaptor.capture(), eq(offset), eq(limit));
        argumentCaptor.getValue().onResponse(new ResponseContainer<>(newReleasesMock, 200));
        verify(dataListenerMock, never()).onResponse(newReleasesMock);
    }


    @Test
    public void test_getNewReleases_nullListener_positiveOffset_positiveLimit_emptyCache_noErrorCallback() {
        int offset = 0;
        int limit = 10;

        browseRepository.getNewReleases(null, offset, limit);

        verify(dataListenerMock, never()).onResponse(cacheMock.getNewReleases());
        verify(apiMock, times(1)).getNewReleasesAsync(argumentCaptor.capture(), eq(offset), eq(limit));
        argumentCaptor.getValue().onError(networkErrorMock);
        verify(dataListenerMock, never()).onError(networkErrorMock);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getNewReleases_nonNullListener_negativeOffset_positiveLimit_emptyCache_throwsException() {
        int offset = -8;
        int limit = 10;

        browseRepository.getNewReleases(dataListenerMock, offset, limit);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getNewReleases_nullListener_negativeOffset_positiveLimit_emptyCache_throwsException() {
        int offset = -8;
        int limit = 10;

        browseRepository.getNewReleases(null, offset, limit);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getNewReleases_nonNullListener_positiveOffset_negativeLimit_emptyCache_throwsException() {
        int offset = 0;
        int limit = -10;

        browseRepository.getNewReleases(dataListenerMock, offset, limit);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getNewReleases_nullListener_positiveOffset_negativeLimit_emptyCache_throwsException() {
        int offset = 0;
        int limit = -10;

        browseRepository.getNewReleases(null, offset, limit);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getNewReleases_nonNullListener_negativeOffset_negativeLimit_emptyCache_throwsException() {
        int offset = -1;
        int limit = -10;

        browseRepository.getNewReleases(dataListenerMock, offset, limit);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getNewReleases_nullListener_negativeOffset_negativeLimit_emptyCache_throwsException() {
        int offset = -1;
        int limit = -10;

        browseRepository.getNewReleases(null, offset, limit);
    }

    @Test
    public void test_getNewReleases_nonNullListener_positiveOffset_positiveLimit_notEmptyCache_returnsFromCache() {
        int offset = 0;
        int limit = 10;

        when(cacheMock.getNewReleases()).thenReturn(newReleasesMock);

        browseRepository.getNewReleases(dataListenerMock, offset, limit);

        verify(dataListenerMock, times(1)).onResponse(newReleasesMock);
        verify(apiMock, never()).getNewReleasesAsync(argumentCaptor.capture(), eq(offset), eq(limit));
    }
}

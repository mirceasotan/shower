package domain.test;

import com.mircea.sotan.domain.DataListener;
import com.mircea.sotan.domain.GetNewReleasesUseCaseImpl;
import com.mircea.sotan.model.NewReleases;
import com.mircea.sotan.repository.apis.BrowseRestApi;
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
public class GetNewReleasesUseCaseTest {
    @Mock
    private BrowseRestApi browseRestApiMock;
    @Mock
    private DataListener<NewReleases> dataListenerMock;
    @Captor
    private ArgumentCaptor<Listener<NewReleases>> argumentCaptor;
    @Mock
    private NewReleases newReleases;
    @Mock
    private NetworkError networkError;
    private GetNewReleasesUseCaseImpl getNewReleasesUseCase;

    @Before
    public void init() {
        initMocks(this);
        getNewReleasesUseCase = new GetNewReleasesUseCaseImpl(browseRestApiMock);
    }

    @Test
    public void test_getNewReleases_success_nullDataListener() {
        getNewReleasesUseCase.getNewReleases(null);

        verify(browseRestApiMock).getNewReleasesAsync(Matchers.<Listener<NewReleases>>any());
        verify(browseRestApiMock, times(1)).getNewReleasesAsync(argumentCaptor.capture());

        argumentCaptor.getValue().onResponse(new ResponseContainer<>(newReleases, 200));

        verify(dataListenerMock, never()).onResponse(newReleases);
    }

    @Test
    public void test_getNewReleases_success_nonNullDataListener() {
        getNewReleasesUseCase.getNewReleases(dataListenerMock);

        verify(browseRestApiMock).getNewReleasesAsync(Matchers.<Listener<NewReleases>>any());
        verify(browseRestApiMock, times(1)).getNewReleasesAsync(argumentCaptor.capture());

        argumentCaptor.getValue().onResponse(new ResponseContainer<>(newReleases, 200));

        verify(dataListenerMock).onResponse(newReleases);
    }

    @Test
    public void test_getNewReleases_error_nullDataListener() {
        getNewReleasesUseCase.getNewReleases(null);

        verify(browseRestApiMock).getNewReleasesAsync(Matchers.<Listener<NewReleases>>any());
        verify(browseRestApiMock, times(1)).getNewReleasesAsync(argumentCaptor.capture());

        argumentCaptor.getValue().onError(networkError);

        verify(dataListenerMock, never()).onError(networkError);
    }

    @Test
    public void test_getNewReleases_error_nonNullDataListener() {
        getNewReleasesUseCase.getNewReleases(dataListenerMock);

        verify(browseRestApiMock).getNewReleasesAsync(Matchers.<Listener<NewReleases>>any());
        verify(browseRestApiMock, times(1)).getNewReleasesAsync(argumentCaptor.capture());

        argumentCaptor.getValue().onError(networkError);

        verify(dataListenerMock).onError(networkError);
    }
}
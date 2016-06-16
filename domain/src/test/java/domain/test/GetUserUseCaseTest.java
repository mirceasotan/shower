package domain.test;

import com.mircea.sotan.domain.DataListener;
import com.mircea.sotan.domain.GetUserUseCaseImpl;
import com.mircea.sotan.model.NewReleases;
import com.mircea.sotan.repository.apis.UserRestApi;
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
public class GetUserUseCaseTest {
    @Mock
    private UserRestApi userRestApi;
    @Mock
    private DataListener<NewReleases> dataListenerMock;
    @Captor
    private ArgumentCaptor<Listener<NewReleases>> argumentCaptor;
    @Mock
    private NewReleases newReleases;
    @Mock
    private NetworkError networkError;
    private GetUserUseCaseImpl useCase;

    @Before
    public void init() {
        initMocks(this);
        useCase = new GetUserUseCaseImpl(userRestApi);
    }

    @Test
    public void test_getNewReleases_success_nullDataListener() {
        useCase.getUser(null);

        verify(userRestApi).getCurrentUserAsync(Matchers.<Listener<NewReleases>>any());
        verify(userRestApi, times(1)).getCurrentUserAsync(argumentCaptor.capture());

        argumentCaptor.getValue().onResponse(new ResponseContainer<>(newReleases, 200));

        verify(dataListenerMock, never()).onResponse(newReleases);
    }

    @Test
    public void test_getNewReleases_success_nonNullDataListener() {
        useCase.getUser(dataListenerMock);

        verify(userRestApi).getCurrentUserAsync(Matchers.<Listener<NewReleases>>any());
        verify(userRestApi, times(1)).getCurrentUserAsync(argumentCaptor.capture());

        argumentCaptor.getValue().onResponse(new ResponseContainer<>(newReleases, 200));

        verify(dataListenerMock).onResponse(newReleases);
    }

    @Test
    public void test_getNewReleases_error_nullDataListener() {
        useCase.getUser(null);

        verify(userRestApi).getCurrentUserAsync(Matchers.<Listener<NewReleases>>any());
        verify(userRestApi, times(1)).getCurrentUserAsync(argumentCaptor.capture());

        argumentCaptor.getValue().onError(networkError);

        verify(dataListenerMock, never()).onError(networkError);
    }

    @Test
    public void test_getNewReleases_error_nonNullDataListener() {
        useCase.getUser(dataListenerMock);

        verify(userRestApi).getCurrentUserAsync(Matchers.<Listener<NewReleases>>any());
        verify(userRestApi, times(1)).getCurrentUserAsync(argumentCaptor.capture());

        argumentCaptor.getValue().onError(networkError);

        verify(dataListenerMock).onError(networkError);
    }
}
import com.mircea.sotan.model.PublicUser;
import com.mircea.sotan.repository.apis.UserRestApi;
import com.mircea.sotan.repository.apis.UserRestApiImpl;
import com.mircea.sotan.repository.networking.Listener;
import com.mircea.sotan.repository.networking.RequestLog;
import com.mircea.sotan.repository.networking.ResponseContainer;
import com.mircea.sotan.repository.networking.TokenStorage;
import com.mircea.sotan.repository.services.UserService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author mirceasotan
 */
@RunWith(MockitoJUnitRunner.class)
public class UserRestApiImplTest {
    @Mock
    UserService userService;
    @Mock
    Call<PublicUser> call;
    @Mock
    RequestLog log;
    @Mock
    Listener<PublicUser> listener;
    @Mock
    TokenStorage storage;
    @Captor
    private ArgumentCaptor<Callback<PublicUser>> argumentCaptor;
    private MockRetrofit mockRetrofit;

    @Before
    public void init() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://test.com")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetworkBehavior behavior = NetworkBehavior.create();

        mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();
    }

    @Test
    public void test_getCurrentUserAsyncWithSuccess_nonNullListener_onResponseCalledOnce() throws IOException {
        UserRestApi userRestApi = new UserRestApiImpl(userService, log, storage);
        when(userService.getCurrentUser(storage.getAuthToken())).thenReturn(call);
        userRestApi.getCurrentUserAsync(listener);
        verify(call, times(1)).enqueue(argumentCaptor.capture());
        Response<PublicUser> response = createDummyResponse();
        argumentCaptor.getValue().onResponse(this.call, response);
        ResponseContainer<PublicUser> responseContainer = new ResponseContainer<>(response.body(), 200);
        verify(listener, times(1)).onResponse(argThat(new ArgumentMatcherImpl<>(responseContainer)));
    }

    @Test
    public void test_getCurrentUserAsyncWithSuccess_nullListener_onResponseCalledNever() throws IOException {
        UserRestApi userRestApi = new UserRestApiImpl(userService, log, storage);
        when(userService.getCurrentUser(storage.getAuthToken())).thenReturn(call);
        userRestApi.getCurrentUserAsync(null);
        verify(call, times(1)).enqueue(argumentCaptor.capture());
        Response<PublicUser> response = createDummyResponse();
        argumentCaptor.getValue().onResponse(this.call, response);
        ResponseContainer<PublicUser> responseContainer = new ResponseContainer<>(response.body(), 200);
        verify(listener, never()).onResponse(argThat(new ArgumentMatcherImpl<>(responseContainer)));
    }

    private Response<PublicUser> createDummyResponse() throws IOException {
        BehaviorDelegate<UserService> delegate = mockRetrofit.create(UserService.class);
        UserService userService = new Mock200UserService(delegate);
        Call<PublicUser> call = userService.getCurrentUser(storage.getAuthToken());
        return call.execute();
    }
}

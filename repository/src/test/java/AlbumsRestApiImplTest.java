import com.mircea.sotan.model.FullAlbum;
import com.mircea.sotan.repository.networking.ApiListener;
import com.mircea.sotan.repository.networking.RequestLog;
import com.mircea.sotan.repository.services.AlbumsService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import retrofit2.mock.MockRetrofit;

import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author mirceasotan
 */
@RunWith(MockitoJUnitRunner.class)
public class AlbumsRestApiImplTest {
    @Mock
    MockRetrofit retrofit;
    @Mock
    RequestLog requestLog;
    @Mock
    String id;
    @Mock
    ApiListener<FullAlbum> apiListener;
    @Mock
    AlbumsService albumsService;

    @Before
    public void init() {
        initMocks(this);
    }

    @Test
    public void test() {
//        Mockito.when(retrofit.create(AlbumsService.class)).thenReturn(albumsService);
//
//        AlbumsRestApiImpl api = new AlbumsRestApiImpl(retrofit, requestLog);
//
//        api.getAlbumDetailsAsync(id, apiListener);
//
//        Mockito.verify(albumsService).getAlbumDetail(Matchers.eq(id));
    }


}

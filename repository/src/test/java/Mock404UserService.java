import com.mircea.sotan.model.PublicUser;
import com.mircea.sotan.repository.networking.RestApi;
import com.mircea.sotan.repository.services.UserService;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.mock.BehaviorDelegate;

/**
 * @author mirceasotan
 */
public class Mock404UserService implements UserService {
    private final BehaviorDelegate<UserService> delegate;

    public Mock404UserService(BehaviorDelegate<UserService> delegate) {
        this.delegate = delegate;
    }



    @Override
    public Call<PublicUser> getCurrentUser(@Header(RestApi.HttpHeader.AUTHORIZATION) String authorization) {
        return null;
    }
}

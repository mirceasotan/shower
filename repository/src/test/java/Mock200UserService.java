import com.mircea.sotan.model.PublicUser;
import com.mircea.sotan.repository.networking.HttpHeader;
import com.mircea.sotan.repository.services.UserService;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.mock.BehaviorDelegate;

/**
 * @author mirceasotan
 */
public class Mock200UserService implements UserService {
    private final BehaviorDelegate<UserService> delegate;

    public Mock200UserService(BehaviorDelegate<UserService> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Call<PublicUser> getCurrentUser(@Header(HttpHeader.AUTHORIZATION) String authorization) {
        PublicUser user = new PublicUser();
        return delegate.returningResponse(user).getCurrentUser("");
    }
}

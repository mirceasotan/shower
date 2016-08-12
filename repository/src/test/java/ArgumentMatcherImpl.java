import com.mircea.sotan.repository.networking.ResponseContainer;

import org.mockito.ArgumentMatcher;

/**
 * @author mirceasotan
 */
public class ArgumentMatcherImpl<T> extends ArgumentMatcher<ResponseContainer<T>> {
    private ResponseContainer<T> object;

    public ArgumentMatcherImpl(ResponseContainer<T> object) {
        this.object = object;
    }

    @Override
    public boolean matches(Object argument) {
        return object.equals(argument);
    }
}

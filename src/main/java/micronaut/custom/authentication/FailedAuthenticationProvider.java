package micronaut.custom.authentication;

import io.micronaut.security.authentication.*;
import io.reactivex.Flowable;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;
import java.util.Collections;

@Slf4j
@Singleton
public class FailedAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
//        return Flowable.just(new AuthenticationFailed());
//        return Flowable.empty();
        return Flowable.error(new Exception());
    }
}
package authentication;

import models.User;
import play.mvc.Http;
import play.mvc.Security;
import services.AuthService;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

public class AuthenticationSupport extends Security.Authenticator {

    private final AuthService authService;

    @Inject
    public AuthenticationSupport(AuthService authService) {
        this.authService = authService;
    }

    public CompletionStage<Optional<User>> getUser(Http.RequestHeader requestHeader) {
        String token = authService.getTokenFromHeader(requestHeader).isPresent()
                ? authService.getTokenFromHeader(requestHeader).get()
                : "";
        return authService.getUserByToken(token)
                .thenApplyAsync(user -> user);
    }

}

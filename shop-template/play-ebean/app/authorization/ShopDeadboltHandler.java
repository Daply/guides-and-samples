package authorization;

import authentication.AuthenticationSupport;
import be.objectify.deadbolt.java.AbstractDeadboltHandler;
import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.models.Permission;
import be.objectify.deadbolt.java.models.Subject;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import repositories.UserRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

//@HandlerQualifiers.MainHandler
@Security.Authenticated(AuthenticationSupport.class)
public class ShopDeadboltHandler extends AbstractDeadboltHandler {

    private final AuthenticationSupport authenticator;
    private final UserRepository userRepository;

    @Inject
    public ShopDeadboltHandler(final AuthenticationSupport authenticator, final UserRepository userRepository) {
        this.authenticator = authenticator;
        this.userRepository = userRepository;
    }

    @Override
    public CompletionStage<Optional<? extends Subject>> getSubject(Http.RequestHeader requestHeader) {
        return authenticator.getUser(requestHeader).thenApplyAsync(user -> user);
    }

//    @Override
//    public CompletionStage<Optional<Result>> beforeAuthCheck(Http.RequestHeader requestHeader, Optional<String> content) {
//        return authenticator.getUser(requestHeader).thenApplyAsync(user -> {
//           if (content.isPresent()) {
//               String permittedRole = content.get();
//               if (user.isPresent()) {
//                   boolean permit = user.get().getRoles().stream().anyMatch(role -> role.getName().equals(permittedRole));
//                   if (permit)
//                       return Optional.of(ok());
//               }
//           }
//           return Optional.of(unauthorized());
//        });
//    }
}

package controllers;


import actions.UserAction;
import models.Status;
import models.StatusCode;
import models.User;
import parsers.UserBodyParser;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;
import repositories.UserRepository;
import services.AuthService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import static play.libs.Json.toJson;

@Singleton
@With(UserAction.class)
public class UserController extends Controller {

    public final UserRepository userRepository;
    public final HttpExecutionContext httpExecutionContext;
    private final AuthService authService;

    @Inject
    public UserController(UserRepository userRepository, HttpExecutionContext httpExecutionContext, AuthService authService) {
        this.userRepository = userRepository;
        this.httpExecutionContext = httpExecutionContext;
        this.authService = authService;
    }

    public CompletionStage<Result> getAllUsers() {
        int numberOfRecordsPerPage = 20;
        return userRepository
                .getAllUsers(numberOfRecordsPerPage)
                .thenApplyAsync(userStream ->
                        ok(toJson(userStream.collect(Collectors.toList()))),
                           httpExecutionContext.current());
    }

    public CompletionStage<Result> getCurrentUser(Http.Request request) {
        Optional<String> tokenOptional = authService.getTokenFromHeader(request);
        String token = tokenOptional.orElse("");
        return authService.getUserByToken(token)
                .thenApplyAsync(user -> user.isPresent() ? ok(toJson(user)) : unauthorized(toJson(user)),
                httpExecutionContext.current());
    }

    //@SubjectPresent
    public CompletionStage<Result> getUserById(long userId) {
        return userRepository
                .getUserById(userId)
                .thenApplyAsync(user -> user.isPresent() ? ok(toJson(user)) : unauthorized(),
                        httpExecutionContext.current());
    }

    @BodyParser.Of(UserBodyParser.class)
    public Result updateUser(long userId, Http.Request request) {
        Http.RequestBody body = request.body();
        User user = body.as(User.class);
        user.setUserId(userId);
        userRepository.updateUser(userId, user);
        return ok(toJson(user));
    }

    @BodyParser.Of(UserBodyParser.class)
    public Result addNewUser(Http.Request request) {
        Http.RequestBody body = request.body();
        User user = body.as(User.class);
        userRepository.addUser(user);
        return ok(toJson(user));
    }

    public Result deleteUser(long userId) {
        userRepository.deleteUser(userId);
        return ok(toJson(new Status("user deleted", StatusCode.OK)));
    }

}

package controllers;

import be.objectify.deadbolt.java.models.Role;
import lombok.extern.slf4j.Slf4j;
import models.Status;
import models.StatusCode;
import models.User;
import parsers.UserBodyParser;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.AuthService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.management.relation.RoleUnresolved;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import static play.libs.Json.toJson;

@Singleton
@Slf4j
public class LoginController extends Controller {

    private final HttpExecutionContext httpExecutionContext;
    private final AuthService authService;

    @Inject
    public LoginController(HttpExecutionContext httpExecutionContext, AuthService authService) {
        this.httpExecutionContext = httpExecutionContext;
        this.authService = authService;
    }

    @BodyParser.Of(UserBodyParser.class)
    public CompletionStage<Result> login(Http.Request request) {
        log.debug("hello");
        Http.RequestBody body = request.body();
        User userLogin = body.as(User.class);
        return authService.generateToken(userLogin).thenApplyAsync(token -> {
            if (!token.isEmpty()) {
                return ok(toJson(new Status("user logged in", StatusCode.OK)))
                        .withHeader("X-AUTH-TOKEN", token);
            }
            return ok(toJson(new Status("user doesnt exist", StatusCode.ENTITY_DOESNT_EXIST)));
        });
    }

    public Result logout(Http.Request request) {
        Optional<String> oldToken = request.header("X-AUTH-TOKEN");
        oldToken.ifPresent(authService::invalidateToken);
        return ok(toJson(new Status("user logged off", StatusCode.OK)));
    }

    public CompletionStage<Result> getRoles(Http.Request request) {
        Optional<String> token = request.header("X-AUTH-TOKEN");
        return authService.getUserRoles(token.get()).thenApplyAsync(roles ->
                ok(toJson(roles
                .stream()
                .map(Role::getName)
                .collect(Collectors.toList()))));
    }

}

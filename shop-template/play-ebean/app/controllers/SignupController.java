package controllers;

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
import java.util.concurrent.CompletionStage;

import static play.libs.Json.toJson;

@Singleton
public class SignupController extends Controller {

    public final AuthService authService;
    private final HttpExecutionContext httpExecutionContext;

    @Inject
    public SignupController(AuthService authService, HttpExecutionContext httpExecutionContext) {
        this.authService = authService;
        this.httpExecutionContext = httpExecutionContext;
    }

    @BodyParser.Of(UserBodyParser.class)
    public CompletionStage<Result> signup(Http.Request request) {
        Http.RequestBody body = request.body();
        User userSignup = body.as(User.class);
        return authService.generateTokenForNewUser(userSignup).thenApplyAsync(token -> {
            if (!token.isEmpty()) {
                return ok(toJson(new Status("user signed up and logged in", StatusCode.OK)))
                        .withHeader("X-AUTH-TOKEN", token);
            }
            return ok(toJson(new Status("user with such username already exists", StatusCode.ALREADY_EXISTS)));
        });
    }
}

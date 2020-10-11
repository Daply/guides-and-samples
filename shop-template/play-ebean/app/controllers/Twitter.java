package controllers;

import play.libs.oauth.OAuth;
import play.libs.ws.WSClient;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class Twitter extends Controller {

    static final OAuth.ConsumerKey KEY = new OAuth.ConsumerKey("...", "...");

    private static final OAuth.ServiceInfo SERVICE_INFO = new OAuth.ServiceInfo(
            "https://api.twitter.com/oauth/request_token",
            "https://api.twitter.com/oauth/access_token",
            "https://api.twitter.com/oauth/authorize",
            KEY
    );

    private static final OAuth TWITTER = new OAuth(SERVICE_INFO);

    private final WSClient ws;

    @Inject
    public Twitter(WSClient ws) {
        this.ws = ws;
    }

    public CompletionStage<Result> homeTimeline(Http.Request request) {
        Optional<OAuth.RequestToken> sessionTokenPair = getSessionTokenPair(request);
        if (sessionTokenPair.isPresent()) {
            return ws
                    .url("https://api.twitter.com/1.1/statuses/home_timeline.json")
                    .sign(new OAuth.OAuthCalculator(Twitter.KEY, sessionTokenPair.get()))
                    .get()
                    .thenApply(result -> ok(result.asJson()));
        }
        return CompletableFuture.completedFuture(redirect(routes.Twitter.auth()));
    }

    public Result auth(Http.Request request) {
        Optional<String> verifier = Optional.ofNullable(request.getQueryString("oauth_verifier"));
        return verifier
                        .filter(s -> !s.isEmpty())
                        .map(s -> {
                            OAuth.RequestToken token = getSessionTokenPair(request).get();
                            OAuth.RequestToken accessToken = TWITTER.retrieveAccessToken(token, s);
                            return redirect(routes.Twitter.homeTimeline())
                                    .addingToSession(request, "token", accessToken.token)
                                    .addingToSession(request, "secret", accessToken.secret);
                        })
                        .orElseGet(() -> {
                           String url = routes.Twitter.auth().absoluteURL(request);
                            OAuth.RequestToken requestToken = TWITTER.retrieveRequestToken(url);
                            return redirect(TWITTER.redirectUrl(requestToken.token))
                                    .addingToSession(request, "token", requestToken.token)
                                    .addingToSession(request, "secret", requestToken.secret);
                        });
    }

    public Optional<OAuth.RequestToken> getSessionTokenPair(Http.Request request) {
        return request.session().getOptional("secret")
                .map(token -> new OAuth.RequestToken(token, request.session().getOptional("secret").get()));
    }

}

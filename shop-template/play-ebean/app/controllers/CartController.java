package controllers;

import models.Cart;
import models.Product;
import models.Status;
import models.StatusCode;
import parsers.CartBodyParser;
import parsers.ProductBodyParser;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import repositories.CartRepository;
import repositories.UserRepository;
import services.AuthService;
import services.CartService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import static play.libs.Json.toJson;

@Singleton
public class CartController extends Controller {

    public final HttpExecutionContext httpExecutionContext;
    public final CartService cartService;

    public final CartRepository cartRepository;
    public final UserRepository userRepository;
    private final AuthService authService;

    @Inject
    public CartController(HttpExecutionContext httpExecutionContext,
                          CartService cartService,
                          CartRepository cartRepository,
                          UserRepository userRepository,
                          AuthService authService) {
        this.httpExecutionContext = httpExecutionContext;
        this.cartService = cartService;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @BodyParser.Of(CartBodyParser.class)
    public CompletionStage<Result> updateCart(Http.Request request) {
        Http.RequestBody body = request.body();
        Cart cart = body.as(Cart.class);
        return cartRepository.updateCart(cart)
                .thenApplyAsync(cartUpdated -> cartUpdated.isPresent()
                        ? ok(toJson(cartUpdated))
                        : ok(toJson(cart)));
    }

    public CompletionStage<Result> getCurrentCart(Http.Request request) {
        Optional<String> tokenOptional = request.header("X-AUTH-TOKEN");
        String token = tokenOptional.orElse("");
        return cartService.getCartByUserAuthToken(token)
                .thenApplyAsync(cart -> cart.isPresent()
                                ? ok(toJson(cart))
                                : ok(toJson(new Status("cart not found", StatusCode.ENTITY_DOESNT_EXIST))),
                httpExecutionContext.current());
    }

    public CompletionStage<Result> getCartProducts(long cartId) {
        return cartRepository.getCartProducts(cartId).thenApplyAsync(products ->
                        ok(toJson(products.collect(Collectors.toList()))),
                httpExecutionContext.current());
    }

    public CompletionStage<Result> deleteProductFromCart(long cartId, long productId) {
        return cartService.deleteProductFromCart(cartId, productId)
                .thenApplyAsync(cart ->
                                cart.isPresent()
                                        ? ok(toJson(cart))
                                        : ok(toJson(new Status("cart not found", StatusCode.ENTITY_DOESNT_EXIST))),
                        httpExecutionContext.current());
    }

    public CompletionStage<Result> addProductToCart(long cartId, long productId) {
        return cartService.addProductToCart(cartId, productId)
                .thenApplyAsync(cart ->
                        cart.isPresent()
                                ? ok(toJson(cart))
                                : ok(toJson(new Status("cart not found", StatusCode.ENTITY_DOESNT_EXIST))),
                httpExecutionContext.current());
    }

    public CompletionStage<Result> removeProductFromCart(long cartId, long productId) {
        return cartService.removeProductFromCart(cartId, productId)
                .thenApplyAsync(cart ->
                        cart.isPresent()
                                ? ok(toJson(cart))
                                : ok(toJson(new Status("cart not found", StatusCode.ENTITY_DOESNT_EXIST))),
                httpExecutionContext.current());
    }

}

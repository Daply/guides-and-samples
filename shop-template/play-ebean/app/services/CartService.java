package services;

import models.Cart;
import models.Product;
import models.User;
import parsers.ProductBodyParser;
import play.mvc.BodyParser;
import play.mvc.Http;
import play.mvc.Result;
import repositories.CartRepository;
import repositories.UserRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

import static play.libs.Json.toJson;

@Singleton
public class CartService {

    public final CartRepository cartRepository;
    public final UserRepository userRepository;
    private final AuthService authService;

    @Inject
    public CartService(CartRepository cartRepository, UserRepository userRepository, AuthService authService) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.authService = authService;
    }

    public CompletionStage<Optional<Cart>> getCartByUserAuthToken(String token) {
        return authService.getUserByToken(token)
                .thenApplyAsync(user -> user.map(User::getCart));
    }

    public CompletionStage<Optional<Cart>> getCartById(long cartId) {
        return cartRepository.getCartById(cartId)
                .thenApplyAsync(cart -> cart);
    }

    public CompletionStage<Optional<Cart>> getCartByUserId(long userId) {
        return userRepository.getUserById(userId)
                .thenApplyAsync(user -> user.map(User::getCart));
    }

    public CompletionStage<Optional<Cart>> addProductToCart(long cartId, long productId) {
        return cartRepository.addProduct(cartId, productId).thenApplyAsync(cart -> cart);
    }

    public CompletionStage<Optional<Cart>> removeProductFromCart(long cartId, long productId) {
        return cartRepository.removeProduct(cartId, productId).thenApplyAsync(cart -> cart);
    }

    public CompletionStage<Optional<Cart>> deleteProductFromCart(long cartId, long productId) {
        return cartRepository.deleteProduct(cartId, productId).thenApplyAsync(cart -> cart);
    }

}

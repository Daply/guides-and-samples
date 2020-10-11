package repositories;

import io.ebean.Ebean;
import io.ebean.EbeanServer;
import models.Cart;
import models.Product;
import models.ProductCartQuantity;
import models.User;
import play.db.ebean.EbeanConfig;
import play.db.ebean.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class CartRepository {

    private final EbeanServer ebeanServer;
    private final DatabaseExecutionContext databaseExecutionContext;

    @Inject
    public CartRepository(EbeanConfig ebeanConfig, DatabaseExecutionContext databaseExecutionContext) {
        this.ebeanServer = Ebean.getServer(ebeanConfig.defaultServer());
        this.databaseExecutionContext = databaseExecutionContext;
    }

    public CompletionStage<Optional<Cart>> updateCart(Cart cart) {
        return supplyAsync(() -> {
            cart.update();
            return Optional.ofNullable(cart);
        }, databaseExecutionContext);
    }

    public CompletionStage<Optional<Cart>> getCartById(long cartId) {
        return supplyAsync(() -> {
            Cart found = ebeanServer.find(Cart.class, cartId);
            return Optional.ofNullable(found);
        }, databaseExecutionContext);
    }

    @Transactional
    public CompletionStage<Stream<Product>> getCartProducts(long cartId) {
        return supplyAsync(() -> {
            Cart found = ebeanServer.find(Cart.class, cartId);
            List<Product> cartProducts = null;
            if (found != null) {
                cartProducts = ebeanServer.find(ProductCartQuantity.class)
                        .where()
                        .ilike("products_cart_id", String.valueOf(found.getCartId()))
                        .findList()
                        .stream()
                        .map(ProductCartQuantity::getProduct)
                        .collect(Collectors.toList());
            }
            return cartProducts.stream();
        }, databaseExecutionContext);
    }

    @Transactional
    public CompletionStage<Optional<Cart>> addProduct(long cartId, long productId) {
        return supplyAsync(() -> {
            Cart cart = ebeanServer.find(Cart.class, cartId);
            ProductCartQuantity cartProduct = null;
            if (cart != null) {
                cartProduct = ebeanServer.find(ProductCartQuantity.class)
                        .where()
                        .ilike("products_cart_id", String.valueOf(cart.getCartId()))
                        .ilike("product_id", String.valueOf(productId))
                        .findOne();
                if (cartProduct != null) {
                    cartProduct.increaseQuantity();
                    cartProduct.update();
                }
                else {
                    Product product = ebeanServer.find(Product.class)
                            .where()
                            .ilike("product_id", String.valueOf(productId))
                            .findOne();
                    cartProduct = new ProductCartQuantity();
                    cartProduct.setProduct(product);
                    cartProduct.setCart(cart);
                    cartProduct.increaseQuantity();
                    cart.addProductCartQuantity(cartProduct);
                    cart.update();
                }
            }
            return Optional.ofNullable(cart);
        }, databaseExecutionContext);
    }

    @Transactional
    public CompletionStage<Optional<Cart>> removeProduct(long cartId, long productId) {
        return supplyAsync(() -> {
            Cart cart = ebeanServer.find(Cart.class, cartId);
            ProductCartQuantity cartProduct = null;
            if (cart != null) {
                cartProduct = ebeanServer.find(ProductCartQuantity.class)
                        .where()
                        .ilike("products_cart_id", String.valueOf(cart.getCartId()))
                        .ilike("product_id", String.valueOf(productId))
                        .findOne();
                assert cartProduct != null;
                cartProduct.decreaseQuantity();
                if (cartProduct.getQuantity() <= 0)
                    cartProduct.delete();
                cartProduct.update();
                cart.setTotalPrice(cart.getTotalPrice() - cartProduct.getProduct().getPrice());
                cart.update();
            }
            return Optional.ofNullable(cart);
        }, databaseExecutionContext);
    }

    @Transactional
    public CompletionStage<Optional<Cart>> deleteProduct(long cartId, long productId) {
        return supplyAsync(() -> {
            Cart cart = ebeanServer.find(Cart.class, cartId);
            ProductCartQuantity cartProduct = null;
            if (cart != null) {
                cartProduct = ebeanServer.find(ProductCartQuantity.class)
                        .where()
                        .ilike("products_cart_id", String.valueOf(cart.getCartId()))
                        .ilike("product_id", String.valueOf(productId))
                        .findOne();
                cart.removeProductCartQuantity(cartProduct);
//                assert cartProduct != null;
//                cartProduct.delete();
                cart.setTotalPrice(cart.getTotalPrice() - cartProduct.getPrice());
                cart.update();
            }
            return Optional.ofNullable(cart);
        }, databaseExecutionContext);
    }
}

package repositories;

import io.ebean.Ebean;
import io.ebean.EbeanServer;
import models.Product;
import models.Status;
import models.StatusCode;
import play.db.ebean.EbeanConfig;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class ProductRepository {

    private final EbeanServer ebeanServer;
    private final DatabaseExecutionContext databaseExecutionContext;

    @Inject
    public ProductRepository(EbeanConfig ebeanConfig, DatabaseExecutionContext databaseExecutionContext) {
        this.ebeanServer = Ebean.getServer(ebeanConfig.defaultServer());
        this.databaseExecutionContext = databaseExecutionContext;
    }

    public CompletionStage<Product> getProductById(long id) {
        return supplyAsync(() ->
                ebeanServer.find(Product.class)
                .where()
                .ilike("productId", String.valueOf(id))
                .findOne(),
                databaseExecutionContext);
    }

    public CompletionStage<Stream<Product>> getProductsByQuerySearch(String query) {
        return supplyAsync(() ->
                ebeanServer.find(Product.class)
                .where()
                .contains("name", query)
                .findList()
                .stream(),
                databaseExecutionContext);
    }

    public CompletionStage<Stream<Product>> getAllProducts() {
        return supplyAsync(() ->
            ebeanServer.find(Product.class)
                    .findList()
                    .stream(),
            databaseExecutionContext);
    }

    public CompletionStage<Stream<String>> getAllProductsImagesPaths() {
        return supplyAsync(() ->
                        ebeanServer.find(Product.class)
                                .findList()
                                .stream()
                                .map(Product::getImageName),
                databaseExecutionContext);
    }

    public CompletionStage<Integer> getAllProductsNumber() {
        return supplyAsync(() ->
                        ebeanServer.find(Product.class)
                                .findCount(),
                databaseExecutionContext);
    }

    public CompletionStage<Stream<Product>> getAllProductsByPage(int page, int count) {
        return supplyAsync(() ->
                        ebeanServer.find(Product.class)
                                .setFirstRow(page)
                                .setMaxRows(count)
                                .findPagedList()
                                .getList()
                                .stream(),
                databaseExecutionContext);
    }

    public CompletionStage<Stream<Product>> getProducts(List<Long> ids) {
        return supplyAsync(() -> !ids.isEmpty()
                        ? ebeanServer.find(Product.class)
                                .where()
                                .idIn(ids)
                                .findList()
                                .stream()
                        : new ArrayList<Product>().stream(),
                databaseExecutionContext);
    }

    public CompletionStage<Optional<Product>> addProduct(Product product) {
        return supplyAsync(() -> {
                        product.insert();
                        return Optional.ofNullable(product);
                },
                databaseExecutionContext);
    }

    public CompletionStage<Status> deleteProduct(long productId) {
        return supplyAsync(() -> {
                    Product product = ebeanServer.find(Product.class, productId);
                    if (product != null) {
                        product.delete();
                    }
                    return new Status("product deleted", StatusCode.OK);
                },
                databaseExecutionContext);
    }
}

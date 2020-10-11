package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import com.typesafe.config.Config;
import lombok.extern.slf4j.Slf4j;
import models.Product;
import play.libs.Files;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import repositories.ProductRepository;
import services.AuthService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import static play.libs.Json.toJson;

@Singleton
@Slf4j
public class ProductsController extends Controller {

    private final AuthService authService;
    public final ProductRepository productRepository;
    public final HttpExecutionContext httpExecutionContext;
    public final Config config;

    @Inject
    public ProductsController(AuthService authService, ProductRepository productRepository,
                              HttpExecutionContext httpExecutionContext, Config config) {
        this.authService = authService;
        this.productRepository = productRepository;
        this.httpExecutionContext = httpExecutionContext;
        this.config = config;
    }

    public CompletionStage<Result> getProductById(long productId) {
        return productRepository.getProductById(productId)
                .thenApplyAsync(product -> product != null
                    ? ok(toJson(product))
                    : badRequest(),
                        httpExecutionContext.current());
    }

    public CompletionStage<Result> getProductsByQuerySearch(String query) {
        return productRepository.getProductsByQuerySearch(query)
                .thenApplyAsync(productStream -> {
                            if (productStream != null) {
                                return ok(toJson(productStream.collect(Collectors.toList())));
                            }
                            else {
                                return badRequest();
                            }
                        },
                        httpExecutionContext.current()
                );
    }

    public CompletionStage<Result> getAllProductsNumber() {
        return productRepository.getAllProductsNumber()
                .thenApplyAsync(productsNumber -> {
                        if (productsNumber != null) {
                            return ok(toJson(productsNumber));
                        }
                        else {
                            return badRequest();
                        }
                    },
                        httpExecutionContext.current()
                );
    }

    public CompletionStage<Result> getAllProductsByPage(int page, int numberOfRecords) {
        return productRepository.getAllProductsByPage(page, numberOfRecords)
                .thenApplyAsync(productStream -> {
                        if (productStream != null) {
                            return ok(toJson(productStream.collect(Collectors.toList())));
                        }
                        else {
                            return badRequest();
                        }
                    },
                        httpExecutionContext.current()
                );
    }

    public CompletionStage<Result> getAllProducts() {
        return productRepository.getAllProducts()
                .thenApplyAsync(productStream -> {
                        if (productStream != null) {
                            return ok(toJson(productStream.collect(Collectors.toList())));
                        }
                        else {
                            return badRequest();
                        }
                    },
                        httpExecutionContext.current()
                );
    }

    public CompletionStage<Result> getProducts(List<Long> ids) {
        return productRepository.getProducts(ids)
                .thenApplyAsync(productStream -> ok(toJson(productStream.collect(Collectors.toList()))),
                        httpExecutionContext.current()
                );
    }

    @Restrict({ @Group("ADMIN") })
    public CompletionStage<Result> deleteProduct(long productId) {
        return productRepository.deleteProduct(productId)
                .thenApplyAsync(status -> ok(toJson(status)),
                        httpExecutionContext.current()
                );
    }

    @Restrict({ @Group("ADMIN") })
    public CompletionStage<Result> addProduct(Http.Request request) throws IOException {
        Http.MultipartFormData<Files.TemporaryFile> body = request.body().asMultipartFormData();
        Product newProduct = createProduct(body.asFormUrlEncoded());

        Http.MultipartFormData.FilePart<Files.TemporaryFile> productImage = body.getFile("file");
        newProduct.setImage(saveFile(productImage));

        return productRepository.addProduct(newProduct)
                .thenApplyAsync(product -> product.isPresent()
                                ? ok("Product added")
                                : badRequest("Product wasn't added"),
                        httpExecutionContext.current());
    }

    private Product createProduct(Map<String, String[]> params) {
        Product newProduct = new Product();
        newProduct.setName(params.get("name")[0]);
        newProduct.setDescription(params.get("description")[0]);
        newProduct.setPrice(Double.parseDouble(params.get("price")[0]));
        return newProduct;
    }

    private String saveFile(Http.MultipartFormData.FilePart<Files.TemporaryFile> file) throws IOException {
        String path = config.getString("imagesFilesPath") + file.getFilename();
        Files.TemporaryFile newFile = file.getRef();
        newFile.copyTo(Paths.get(path), true);
        return path;
    }

}

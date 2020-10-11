package parsers;

import akka.util.ByteString;
import com.fasterxml.jackson.databind.JsonNode;
import models.Product;
import play.libs.F;
import play.libs.streams.Accumulator;
import play.mvc.BodyParser;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Inject;
import java.util.concurrent.Executor;

public class ProductBodyParser implements BodyParser<Product> {

    private BodyParser.Json jsonParser;
    private Executor executor;

    @Inject
    public ProductBodyParser(Json jsonParser, Executor executor) {
        this.jsonParser = jsonParser;
        this.executor = executor;
    }

    @Override
    public Accumulator<ByteString, F.Either<Result, Product>> apply(Http.RequestHeader request) {
        Accumulator<ByteString, F.Either<Result, JsonNode>> accumulator = jsonParser.apply(request);
        return accumulator.map(resultJsonNodeEither -> {
            if (resultJsonNodeEither.left.isPresent()) {
                return F.Either.Left(resultJsonNodeEither.left.get());
            }
            else {
                JsonNode json = resultJsonNodeEither.right.get();
                try {
                    Product product = play.libs.Json.fromJson(json, Product.class);
                    return F.Either.Right(product);
                }
                catch (Exception e) {
                    return F.Either.Left(Results.badRequest("Cant read product from json" + e.getMessage()));
                }
            }
        }, executor);
    }
}

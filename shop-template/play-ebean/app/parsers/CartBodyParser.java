package parsers;

import akka.util.ByteString;
import com.fasterxml.jackson.databind.JsonNode;
import models.Cart;
import play.libs.F;
import play.libs.streams.Accumulator;
import play.mvc.BodyParser;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Inject;
import java.util.concurrent.Executor;

public class CartBodyParser implements BodyParser<Cart> {

    private BodyParser.Json jsonParser;
    private Executor executor;

    @Inject
    public CartBodyParser(Json jsonParser, Executor executor) {
        this.jsonParser = jsonParser;
        this.executor = executor;
    }

    @Override
    public Accumulator<ByteString, F.Either<Result, Cart>> apply(Http.RequestHeader request) {
        Accumulator<ByteString, F.Either<Result, JsonNode>> accumulator = jsonParser.apply(request);
        return accumulator.map(resultJsonNodeEither -> {
            if (resultJsonNodeEither.left.isPresent()) {
                return F.Either.Left(resultJsonNodeEither.left.get());
            }
            else {
                JsonNode jsonNode = resultJsonNodeEither.right.get();
                Cart cart = play.libs.Json.fromJson(jsonNode, Cart.class);
                try {
                    return F.Either.Right(cart);
                }
                catch (Exception e) {
                    return F.Either.Left(Results.badRequest("Cant read cart from json" + e.getMessage()));
                }
            }
        }, executor);
    }
}

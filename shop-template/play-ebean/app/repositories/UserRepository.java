package repositories;

import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.Transaction;
import models.Cart;
import models.User;
import play.db.ebean.EbeanConfig;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class UserRepository {

    private final EbeanServer ebeanServer;
    private final DatabaseExecutionContext databaseExecutionContext;

    @Inject
    public UserRepository(EbeanConfig ebeanConfig, DatabaseExecutionContext databaseExecutionContext) {
        this.ebeanServer = Ebean.getServer(ebeanConfig.defaultServer());
        this.databaseExecutionContext = databaseExecutionContext;
    }

    public CompletionStage<Stream<User>> getAllUsers(int maxRows) {
        return supplyAsync(() ->
            ebeanServer.find(User.class)
                    .setMaxRows(maxRows)
                    .findPagedList()
                    .getList()
                    .stream(),
            databaseExecutionContext);
    }

    public CompletionStage<Optional<User>> getUserById(long userId) {
        return supplyAsync(() ->
            Optional.ofNullable(ebeanServer.find(User.class, userId)),
            databaseExecutionContext);
    }

    public CompletionStage<Optional<User>> updateUser(long userId, User user) {
        return supplyAsync(() -> {
            Transaction transaction = ebeanServer.beginTransaction();
            User found = null;
            try {
                found = ebeanServer.find(User.class, userId);
                if (found != null) {
                    found.setName(user.getName());
                    found.setSurname(user.getSurname());
                    found.setUsername(user.getUsername());
                    found.update();
                    transaction.commit();
                }
            }
            finally {
                transaction.end();
            }
            return Optional.ofNullable(user);
        }, databaseExecutionContext);
    }

    public CompletionStage<Optional<User>> addUser(User user) {
        return supplyAsync(() -> {
            User exists = ebeanServer.find(User.class)
                            .where()
                            .ilike("username", user.getUsername())
                            .findOne();
            if (exists == null) {
                Cart cart = new Cart();
                user.setCart(cart);
                cart.setUser(user);
                user.insert();
                return Optional.ofNullable(user);
            }
            return Optional.ofNullable(null);
        }, databaseExecutionContext);
    }

    public CompletableFuture<Optional<Long>> deleteUser(long userId) {
        return supplyAsync(() -> {
            Optional<User> found = Optional.ofNullable(ebeanServer.find(User.class, userId));
            found.ifPresent(User::delete);
            return found.map(user -> user.getUserId());
        }, databaseExecutionContext);
    }

    public CompletableFuture<Optional<User>> getUserByUsername(String username) {
        return supplyAsync(() -> {
            Optional<User> found = Optional.ofNullable(ebeanServer
                .find(User.class)
                .where()
                .ilike("username", username)
                .findOne());
            return found;
        }, databaseExecutionContext);
    }

}

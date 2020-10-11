package services;

import be.objectify.deadbolt.java.models.Role;
import models.User;
import models.UserRole;
import play.mvc.Http;
import repositories.UserRepository;
import token.TokenProvider;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

@Singleton
public class AuthService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    @Inject
    public AuthService(UserRepository userRepository, TokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
    }

    public CompletionStage<String> generateToken(User user) {
        return validateUser(user).thenApplyAsync(userExist ->
                userExist ? tokenProvider.getToken(user.getUsername(), user.getPassword()): "");
    }

    public CompletionStage<String> generateTokenForNewUser(User user) {
        return this.userRepository.addUser(user).thenApplyAsync(newUser ->
                newUser.isPresent() ? tokenProvider.getToken(user.getUsername(), user.getPassword()): "");
    }

    public void invalidateToken(String token) {
        // do something with token
    }

    public Optional<String> getTokenFromHeader(final Http.RequestHeader header) {
        return header.header("X-AUTH-TOKEN");
    }

    public CompletionStage<Optional<User>> getUserByToken(String token) {
        return userRepository.getUserByUsername(tokenProvider.getUsernameFromToken(token))
                .thenApplyAsync(user -> user);
    }

    public CompletionStage<List<? extends Role>> getUserRoles(String token) {
        return userRepository.getUserByUsername(tokenProvider.getUsernameFromToken(token))
                .thenApplyAsync(user -> user.isPresent()
                                        ? user.get().getRoles()
                                        : new ArrayList<>());
    }

    public CompletionStage<Boolean> validateUser(User user) {
        return userRepository.getUserByUsername(user.getUsername())
                .thenApplyAsync(foundUser ->
                        foundUser.filter(value ->
                                user.getPassword().equals(value.getPassword())).isPresent());
    }

}

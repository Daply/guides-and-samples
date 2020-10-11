package authorization;

import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.cache.HandlerCache;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ShopHandlerCache implements HandlerCache {

    private final DeadboltHandler defaultHandler;

    @Inject
    public ShopHandlerCache(DeadboltHandler defaultHandler) {
        this.defaultHandler = defaultHandler;
    }

    @Override
    public DeadboltHandler apply(String s) {
        return defaultHandler;
    }

    @Override
    public DeadboltHandler get() {
        return defaultHandler;
    }
}

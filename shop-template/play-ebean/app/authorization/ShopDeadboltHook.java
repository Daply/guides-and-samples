package authorization;

import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.cache.HandlerCache;
import com.typesafe.config.Config;
import play.inject.Binding;
import play.inject.Module;

import javax.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

public class ShopDeadboltHook extends Module {
    @Override
    public List<Binding<?>> bindings(play.Environment environment, Config config) {
        List<Binding<?>> bindings = new ArrayList<>();
        bindings.add(bindClass(DeadboltHandler.class).to(ShopDeadboltHandler.class).in(Singleton.class));
        bindings.add(bindClass(HandlerCache.class).to(ShopHandlerCache.class).in(Singleton.class));
        return bindings;
    }
}

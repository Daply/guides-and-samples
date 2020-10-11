package tasks;

import com.google.inject.AbstractModule;
import play.libs.akka.AkkaGuiceSupport;
import tasks.actors.CheckerActor;

public class TasksModule extends AbstractModule implements AkkaGuiceSupport {

    @Override
    protected void configure() {
        bindActor(CheckerActor.class, "checker-actor");
        bind(CheckerTask.class).asEagerSingleton();
    }
}

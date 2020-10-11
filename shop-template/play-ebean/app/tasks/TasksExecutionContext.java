package tasks;

import akka.actor.ActorSystem;
import play.libs.concurrent.CustomExecutionContext;

import javax.inject.Inject;

public class TasksExecutionContext extends CustomExecutionContext {

    @Inject
    public TasksExecutionContext(ActorSystem actorSystem, String name) {
        super(actorSystem, "tasks-dispatcher");
    }
}

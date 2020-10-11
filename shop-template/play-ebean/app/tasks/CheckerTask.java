package tasks;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import scala.concurrent.ExecutionContext;
import tasks.actors.CheckerActorProtocol;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.Duration;

public class CheckerTask {

    private final ActorRef actorRef;
    private final ActorSystem actorSystem;
    private final TasksExecutionContext executionContext;

    @Inject
    public CheckerTask(@Named("checker-actor") ActorRef actorRef,
                       ActorSystem actorSystem,
                       TasksExecutionContext executionContext) {
        this.actorRef = actorRef;
        this.actorSystem = actorSystem;
        this.executionContext = executionContext;

        this.initialize();
    }

    public void initialize() {
        actorSystem
                .scheduler()
                .schedule(
                        Duration.ofSeconds(0),
                        Duration.ofSeconds(1200),
                        actorRef,
                        new CheckerActorProtocol.CheckerMessage("check images", 1),
                        executionContext,
                        ActorRef.noSender()
                );
    }
}

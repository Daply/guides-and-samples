package tasks.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import tasks.Checker;

import javax.inject.Inject;

public class CheckerActor extends AbstractActor {

    public final Checker checker;

    @Inject
    public CheckerActor(Checker checker) {
        this.checker = checker;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(
                        CheckerActorProtocol.CheckerMessage.class,
                        checker::job
                )
                .build();
    }

}

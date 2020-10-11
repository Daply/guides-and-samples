package tasks.actors;

public class CheckerActorProtocol {

    public static class CheckerMessage {
        public final String message;
        public final int messageNumber;

        public CheckerMessage(String message, int messageNumber) {
            this.message = message;
            this.messageNumber = messageNumber;
        }
    }

}

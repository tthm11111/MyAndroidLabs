package algonquin.cst2335.chen0914;

public class ChatMessage {
    String message;
    String timeSent;
    boolean isSentButton;

    public ChatMessage(String m, String t, boolean sent)
    {
        message = m;
        timeSent = t;
        isSentButton = sent;
    }
}

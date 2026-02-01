package notificacion.model;

public class Notification {

    private final String recipient;
    private final String subject;
    private final String content;

    public Notification(String recipient, String subject, String content) {
        this.recipient = recipient;
        this.subject = subject;
        this.content = content;
    }

    // Getters
    public String getRecipient() { return recipient; }
    public String getSubject() { return subject; }
    public String getContent() { return content; }
}

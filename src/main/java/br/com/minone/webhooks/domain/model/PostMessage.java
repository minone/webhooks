package br.com.minone.webhooks.domain.model;

public class PostMessage {

    private String messageBody;

    private ContentType contentType;

    public PostMessage(String messageBody, ContentType contentType) {
        this.messageBody = messageBody;
        this.contentType = contentType;
    }
}

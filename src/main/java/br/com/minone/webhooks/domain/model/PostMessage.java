package br.com.minone.webhooks.domain.model;

public class PostMessage {

    private String messageBody;

    private String contentType;

    public PostMessage(String messageBody, String contentType) {
        this.messageBody = messageBody;
        this.contentType = contentType;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public String getContentType() {
        return contentType;
    }
}

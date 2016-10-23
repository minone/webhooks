package br.com.minone.webhooks.application.command;

import javax.validation.constraints.NotNull;

public class PostMessageCmd {

    @NotNull(message = "{post.destination.id}")
    private String destinationId;

    @NotNull(message = "{post.contentType}")
    private String contentType;

    @NotNull(message = "{post.content}")
    private String content;

    public PostMessageCmd() {
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

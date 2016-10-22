package br.com.minone.webhooks.application.command;

import javax.validation.constraints.NotNull;

public class RegisterDestinationCmd {

    @NotNull
    private String url;

    @NotNull
    private String contentType;

    public RegisterDestinationCmd() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}

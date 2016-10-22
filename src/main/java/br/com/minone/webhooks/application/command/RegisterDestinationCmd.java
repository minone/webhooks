package br.com.minone.webhooks.application.command;

import javax.validation.constraints.NotNull;

public class RegisterDestinationCmd {

    @NotNull
    private String URL;

    @NotNull
    private String contentType;

    public RegisterDestinationCmd() {
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}

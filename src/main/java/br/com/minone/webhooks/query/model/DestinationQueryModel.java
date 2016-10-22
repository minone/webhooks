package br.com.minone.webhooks.query.model;

public class DestinationQueryModel {

    private String id;

    private String URL;

    public DestinationQueryModel(String id, String URL) {
        this.id = id;
        this.URL = URL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}

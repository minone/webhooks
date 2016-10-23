package br.com.minone.webhooks.query.model;

public class DestinationQueryModel {

    private String id;

    private String url;
    
    private String secret;

    public DestinationQueryModel(String id, String url, String secret) {
        this.id = id;
        this.url = url;
        this.secret = secret;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getSecret() {
		return secret;
	}
    
    public void setSecret(String secret) {
		this.secret = secret;
	}
}

package br.com.minone.webhooks.domain.model;

import br.com.minone.webhooks.exception.BusinessException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class Destination {

    private DestinationId id;

    private URL url;

    private UUID security;

    public Destination(DestinationId id, String url) throws BusinessException {
        this.id = id;

        this.security = UUID.randomUUID();

        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public Destination(DestinationId id, String url, String security) {
        this.id = id;

        this.security = UUID.fromString(security);

        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
        }
    }

    public DestinationId getId() {
        return id;
    }

    public String getUrl() {
        return url.toString();
    }

    public String getSecurity() {
        return security.toString();
    }
}

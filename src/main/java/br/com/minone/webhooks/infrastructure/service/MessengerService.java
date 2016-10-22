package br.com.minone.webhooks.infrastructure.service;

import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service
public class MessengerService {

    private static final String CONTENT_MD5 = "Content-MD5";

    private static final int TRIES = 3;

    public boolean deliver(String url, String content, String contentType) {

        int attempt = 1;

        boolean ok = false;

        while (attempt <= TRIES && !ok) {

            ok = post(url, content, contentType);

            if (!ok) {
                backoff(attempt);
            }

            attempt++;
        }

        return ok;
    }

    private void backoff(int attempt) {

        long time = (int) Math.pow(attempt, 2) * 500;

        try {
            Thread.sleep(time);

        } catch (InterruptedException e) {
            //TODO logar
        }
    }

    private boolean post(String url, String content, String contentType) {

        Client httpClient = ClientBuilder.newClient();

        WebTarget target = httpClient.target(url);

        Response response = target.request().post(Entity.entity(content, MediaType.valueOf(contentType)));

        return Response.Status.OK.getStatusCode() == response.getStatus();
    }
}

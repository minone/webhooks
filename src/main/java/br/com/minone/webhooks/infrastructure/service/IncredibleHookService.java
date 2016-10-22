package br.com.minone.webhooks.infrastructure.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class IncredibleHookService {

    private static final int TOTAL_ATTEMPTS = 5;

    private IncredibleHookService() {

    }

    public static IncredibleHookService newInstance() {
        return new IncredibleHookService();
    }

    public boolean deliver(String url, String content, String contentType) {

        boolean success = false;

        int attempt = 1;

        while (attempt <= TOTAL_ATTEMPTS && !success) {

            success = post(url, content, contentType);

            if (!success) {
                backoff(attempt);
            }

            attempt++;
        }

        return success;
    }

    private void backoff(int attempt) {

        long time = (int) Math.pow(attempt, 2) * 1000;

        try {
            System.out.println("Dormindo por " + time);
            Thread.sleep(time);
            System.out.println("Acordei");

        } catch (InterruptedException e) {
            // TODO logar
        }
    }

    private boolean post(String url, String content, String contentType) {

        Client httpClient = ClientBuilder.newClient();

        WebTarget target = httpClient.target(url);

        try {
            Response response = target.request().post(Entity.entity(content, MediaType.valueOf(contentType)));

            return Response.Status.OK.getStatusCode() == response.getStatus();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}

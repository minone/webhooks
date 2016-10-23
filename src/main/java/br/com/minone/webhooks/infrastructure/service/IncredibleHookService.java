package br.com.minone.webhooks.infrastructure.service;

import br.com.minone.webhooks.infrastructure.firebase.FirebaseRepository;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class IncredibleHookService {

    private static final int TOTAL_ATTEMPTS = 5;

    private static FirebaseRepository firebaseRepository;

    private IncredibleHookService() {

    }

    public static IncredibleHookService newInstance(FirebaseRepository firebaseRepository) {
        IncredibleHookService.firebaseRepository = firebaseRepository;

        return new IncredibleHookService();
    }

    public boolean deliver(String url, String content, String contentType) {

        boolean success = false;

        int attempt = 1;

        while (attempt <= TOTAL_ATTEMPTS && !success) {

            success = post(url, content, contentType);

            if (!success) {
                firebaseRepository.post(url + " > Backoff:  " + (attempt * attempt * 1000) / 1000 + " seconds");
                backoff(attempt);
            }

            attempt++;
        }

        return success;
    }

    private void backoff(int attempt) {

        long time = attempt * attempt * 1000;

        try {
            Thread.sleep(time);

        } catch (InterruptedException e) {
            // TODO logar
        }
    }

    private boolean post(String url, String content, String contentType) {

        Client httpClient = ClientBuilder.newClient();

        WebTarget target = httpClient.target(url);

        try {
            firebaseRepository.post(url + " > Trying to POST");
            Response response = target.request().post(Entity.entity(content, MediaType.valueOf(contentType)));

            boolean result = Response.Status.Family.familyOf(response.getStatus())
                    .equals(Response.Status.Family.SUCCESSFUL);

            if (result) {
                firebaseRepository.post(url + " > POST returned 200");
            }

            return result;

        } catch (Exception e) {
            //TODO logar
        }

        return false;
    }
}

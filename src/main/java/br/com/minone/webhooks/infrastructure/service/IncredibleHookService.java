package br.com.minone.webhooks.infrastructure.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class IncredibleHookService {

	private IncredibleHookService() {}

	public static IncredibleHookService newInstance() {
		return new IncredibleHookService();
	}

	public boolean deliver(String url, String content, String contentType, int attempt) {
		backoff(attempt);

		return post(url, content, contentType);
	}

	private void backoff(int attempt) {

		long time = (int) Math.pow(attempt, 2) * 1000;

		try {
			Thread.sleep(time);

		} catch (InterruptedException e) {
			// TODO logar
		}
	}

	private boolean post(String url, String content, String contentType) {

		Client httpClient = ClientBuilder.newClient();

		WebTarget target = httpClient.target(url);

		Response response = target.request().post(Entity.entity(content, MediaType.valueOf(contentType)));

		return Response.Status.OK.getStatusCode() == response.getStatus();
	}
}

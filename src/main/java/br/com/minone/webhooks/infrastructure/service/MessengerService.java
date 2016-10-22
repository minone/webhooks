package br.com.minone.webhooks.infrastructure.service;

import br.com.minone.webhooks.security.HmacAlgorithm;
import br.com.minone.webhooks.security.SignatureService;
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

    public void post(String url, String content, String contentType, String secret) {

        String signature = SignatureService.newInstance(HmacAlgorithm.HMAC_SHA1).calculateHMAC(secret, content);

        Client httpClient = ClientBuilder.newClient();

        WebTarget target = httpClient.target(url);

        Response response = target.request()
                .header(CONTENT_MD5, signature)
                .post(Entity.entity(content, MediaType.valueOf(contentType)));
    }
}

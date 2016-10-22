package br.com.minone.webhooks.presentation;

import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Component
@Singleton
@Path("/callback")
public class TestController {

    @POST
    public void send(String content) {
        System.out.println(content);
    }

}

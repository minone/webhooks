package br.com.minone.webhooks.presentation;


import br.com.minone.webhooks.application.command.RegisterDestinationCmd;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Singleton
@Path("/webhooks")
public class FrontController {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/destination")
    public String registerDestination(@Valid RegisterDestinationCmd cmd) {
        return null;
    }
}

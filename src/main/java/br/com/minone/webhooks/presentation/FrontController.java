package br.com.minone.webhooks.presentation;


import br.com.minone.webhooks.application.command.RegisterDestinationCmd;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
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

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/destination/{destinationId}")
    public void deleteDestination(@NotNull @Valid @PathParam("destinationId") String destinationId) {

    }
}

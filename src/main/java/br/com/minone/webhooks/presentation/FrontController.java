package br.com.minone.webhooks.presentation;


import br.com.minone.webhooks.application.DestinationApplicationService;
import br.com.minone.webhooks.application.command.PostMessageCmd;
import br.com.minone.webhooks.application.command.RegisterDestinationCmd;
import br.com.minone.webhooks.infrastructure.service.MessengerService;
import br.com.minone.webhooks.query.model.DestinationQueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Singleton
@Path("/webhooks")
public class FrontController {

    private final DestinationApplicationService destinationApplicationService;

    private final MessengerService messengerService;

    @Autowired
    public FrontController(DestinationApplicationService destinationApplicationService,
                           MessengerService messengerService) {
        this.destinationApplicationService = destinationApplicationService;
        this.messengerService = messengerService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/destination")
    public Response registerDestination(@Valid RegisterDestinationCmd cmd) {
        String destinationId = destinationApplicationService.registerDestination(cmd);

        return Response.status(Response.Status.OK).entity(destinationId).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/destination/{destinationId}")
    public void deleteDestination(@NotNull @Valid @PathParam("destinationId") String destinationId) {
        destinationApplicationService.deleteDestination(destinationId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/destination")
    public Response listDestinations() {
        List<DestinationQueryModel> result = destinationApplicationService.listDestinations();

        return Response.status(Response.Status.OK).entity(result).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/post-message")
    public Response postMessage(@NotNull @Valid PostMessageCmd cmd) {

        String url = "";
        String secret = "";

        messengerService.deliver(url, cmd.getContent(), cmd.getContentType(), secret);

        return Response.status(Response.Status.OK).build();
    }
}

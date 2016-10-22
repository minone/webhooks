package br.com.minone.webhooks.presentation;


import br.com.minone.webhooks.application.DestinationApplicationService;
import br.com.minone.webhooks.application.command.RegisterDestinationCmd;
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

    @Autowired
    public FrontController(DestinationApplicationService destinationApplicationService) {
        this.destinationApplicationService = destinationApplicationService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/destination")
    public Response registerDestination(@Valid RegisterDestinationCmd cmd) {
        destinationApplicationService.registerDestination(cmd);

        return Response.status(Response.Status.OK).build();
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
}

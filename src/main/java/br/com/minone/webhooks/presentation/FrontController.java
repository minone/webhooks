package br.com.minone.webhooks.presentation;

import br.com.minone.webhooks.application.DestinationApplicationService;
import br.com.minone.webhooks.application.MessengerApplicationService;
import br.com.minone.webhooks.application.command.PostMessageCmd;
import br.com.minone.webhooks.application.command.RegisterDestinationCmd;
import br.com.minone.webhooks.domain.model.Destination;
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

/***
 * Controller that receives all requests from clients using standard REST-ful conventions.
 */
@Component
@Singleton
@Path("/webhooks")
public class FrontController {

    private final DestinationApplicationService destinationApplicationService;

    private final MessengerApplicationService messengerApplicationService;

    /***
     * Constructor that receives application services.
     * @param destinationApplicationService
     * @param messengerApplicationService
     */
    @Autowired
    public FrontController(DestinationApplicationService destinationApplicationService,
                           MessengerApplicationService messengerApplicationService) {

        this.destinationApplicationService = destinationApplicationService;
        this.messengerApplicationService = messengerApplicationService;
    }

    /***
     * Register a destination based on its URL.
     * @param cmd Command that contains only a URL to be registered.
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/destination")
    public Response registerDestination(@Valid RegisterDestinationCmd cmd) {

        String destinationId = destinationApplicationService.registerDestination(cmd);

        messengerApplicationService.addQueue(destinationId);

        return Response.status(Response.Status.OK).entity(destinationId).build();
    }

    /***
     * Delete a destination based on its GUID.
     * @param destinationId GUID linked to the destination to be erased.
     */
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/destination/{destinationId}")
    public void deleteDestination(@NotNull @Valid @PathParam("destinationId") String destinationId) {
        destinationApplicationService.deleteDestination(destinationId);
    }

    /***
     * List all destinations.
     * @return A list of all destinations.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/destination")
    public Response listDestinations() {
        List<DestinationQueryModel> result = destinationApplicationService.listDestinations();

        return Response.status(Response.Status.OK).entity(result).build();
    }

    /***
     * Post a new message. This message must contain the destination identifier and its content-type.
     * @param cmd Message to post on a destination.
     * @param hmac HMAC signature that assures a secure connection between the client and server.
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/post-message")
    public Response postMessage(@NotNull @Valid PostMessageCmd cmd, @NotNull @HeaderParam("Content-MD5") String hmac) {

        Destination destination = destinationApplicationService.loadDestination(cmd.getDestinationId());

        String id = destination.getId().value();

        String url = destination.getUrl();

        String secret = destination.getSecurity();

        messengerApplicationService.postMessage(id, url, secret, hmac, cmd.getContentType(), cmd.getContent());

        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("callback")
    public Response callback(String content) {

        String helloHootSuite = "Hello Owl!";

        return Response.status(Response.Status.OK).entity(helloHootSuite).build();
    }
}

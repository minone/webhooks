package br.com.minone.webhooks.presentation;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import br.com.minone.webhooks.application.command.RegisterDestinationCmd;

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

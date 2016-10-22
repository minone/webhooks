package br.com.minone.webhooks.application;


import br.com.minone.webhooks.application.command.RegisterDestinationCmd;
import br.com.minone.webhooks.domain.model.Destination;
import br.com.minone.webhooks.domain.model.DestinationId;

import java.util.UUID;

public class DestinationApplicationService {

    public void registerDestination(RegisterDestinationCmd cmd) {
        DestinationId destinationId = new DestinationId(UUID.randomUUID());

        Destination destination = new Destination(destinationId, cmd.getURL());
    }

    public void deleteDestination(String destinationId) {
    }

    public void updateDestination(String destinationId) {
    }

    public void listDestinations() {
    }

}

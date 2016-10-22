package br.com.minone.webhooks.application;


import br.com.minone.webhooks.application.command.RegisterDestinationCmd;
import br.com.minone.webhooks.domain.model.Destination;
import br.com.minone.webhooks.domain.model.DestinationId;
import br.com.minone.webhooks.domain.model.DestinationRepository;
import br.com.minone.webhooks.query.model.DestinationFinder;
import br.com.minone.webhooks.query.model.DestinationQueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class DestinationApplicationService {

    private final DestinationRepository destinationRepository;
    private final DestinationFinder destinationFinder;

    @Autowired
    public DestinationApplicationService(DestinationRepository destinationRepository,
                                         DestinationFinder destinationFinder) {
        this.destinationRepository = destinationRepository;
        this.destinationFinder = destinationFinder;
    }

    @Transactional
    public void registerDestination(RegisterDestinationCmd cmd) {
        DestinationId destinationId = new DestinationId(UUID.randomUUID());

        Destination destination = new Destination(destinationId, cmd.getURL());

        destinationRepository.registerDestination(destination);
    }

    @Transactional
    public void deleteDestination(String destinationId) {
        DestinationId id = new DestinationId(UUID.fromString(destinationId));

        destinationRepository.deleteDestination(id);
    }

    @Transactional(readOnly = true)
    public List<DestinationQueryModel> listDestinations() {
        return destinationFinder.listDestinations();
    }
}
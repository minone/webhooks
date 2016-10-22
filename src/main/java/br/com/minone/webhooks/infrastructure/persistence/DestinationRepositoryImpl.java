package br.com.minone.webhooks.infrastructure.persistence;

import br.com.minone.webhooks.domain.model.Destination;
import br.com.minone.webhooks.domain.model.DestinationId;
import br.com.minone.webhooks.domain.model.DestinationRepository;

public class DestinationRepositoryImpl implements DestinationRepository {
    @Override
    public void registerDestination(Destination destination) {

    }

    @Override
    public void deleteDestination(DestinationId destinationId) {

    }

    @Override
    public Destination loadDestination(DestinationId destinationId) {
        return null;
    }
}

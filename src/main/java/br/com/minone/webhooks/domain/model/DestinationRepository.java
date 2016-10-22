package br.com.minone.webhooks.domain.model;

import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository {

    void registerDestination(Destination destination);

    void deleteDestination(DestinationId destinationId);
}

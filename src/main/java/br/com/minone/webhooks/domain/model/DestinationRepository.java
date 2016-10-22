package br.com.minone.webhooks.domain.model;

import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository {

    String registerDestination(Destination destination);

    void deleteDestination(DestinationId destinationId);
}

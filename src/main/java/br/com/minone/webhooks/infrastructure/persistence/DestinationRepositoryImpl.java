package br.com.minone.webhooks.infrastructure.persistence;

import br.com.minone.webhooks.domain.model.Destination;
import br.com.minone.webhooks.domain.model.DestinationId;
import br.com.minone.webhooks.domain.model.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

public class DestinationRepositoryImpl extends WebhookJdbcSupport implements DestinationRepository {

    @Autowired
    public DestinationRepositoryImpl(DataSource dataSource) {
        super(dataSource);
    }

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

package br.com.minone.webhooks.infrastructure.persistence;

import br.com.minone.webhooks.domain.model.Destination;
import br.com.minone.webhooks.domain.model.DestinationId;
import br.com.minone.webhooks.domain.model.DestinationRepository;
import br.com.minone.webhooks.exception.WebhooksConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class DestinationRepositoryImpl extends WebhookJdbcSupport
        implements DestinationRepository {

    @Autowired
    public DestinationRepositoryImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public String registerDestination(Destination destination) {
        DestinationId destinationId = destination.getId();

        String url = destination.getUrl();

        String security = destination.getSecurity();

        String insert = "insert into destination(dest_id_destination, dest_tx_url, dest_tx_secret) " +
                "values(?,?,?)";

        getJdbcTemplate().update(insert, destinationId.getId(), url, security);

        return destinationId.getId();
    }

    @Override
    public void deleteDestination(DestinationId destinationId) {
        String id = destinationId.getId().toString();

        String delete = "delete from destination where dest_id_destination = ?";

        getJdbcTemplate().update(delete, id);
    }

    @Override
    public Destination loadDestination(DestinationId destinationId) {
        String query = "select * from destination where dest_id_destination = ?";

        List<Destination> destinations =
                getJdbcTemplate().query(query, new DestinationRowMapper(), destinationId.getId());


        if (destinations == null) {
            throw new EmptyResultDataAccessException(WebhooksConstant.RECORD_NOT_FOUND, 1);
        }

//        if (destinations.isEmpty())
//            throw new
        return destinations.get(0);
    }
}

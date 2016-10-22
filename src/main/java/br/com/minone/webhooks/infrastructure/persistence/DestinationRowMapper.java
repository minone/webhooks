package br.com.minone.webhooks.infrastructure.persistence;

import br.com.minone.webhooks.domain.model.Destination;
import br.com.minone.webhooks.domain.model.DestinationId;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class DestinationRowMapper implements RowMapper<Destination> {

    @Override
    public Destination mapRow(ResultSet resultSet, int i) throws SQLException {

        String destinationId = resultSet.getString("dest_id_destination");

        DestinationId id =  new DestinationId(UUID.fromString(destinationId));

        String destinationUrl = resultSet.getString("dest_tx_url");

        String destinationSecret = resultSet.getString("dest_tx_secret");

        return new Destination(id, destinationUrl, destinationSecret);
    }
}

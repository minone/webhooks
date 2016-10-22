package br.com.minone.webhooks.infrastructure.persistence;

import br.com.minone.webhooks.query.model.DestinationFinder;
import br.com.minone.webhooks.query.model.DestinationQueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcDestinationFinder extends WebhookJdbcSupport
        implements DestinationFinder {

    @Autowired
    public JdbcDestinationFinder(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<DestinationQueryModel> listDestinations() {
        String query = "select * from destination";

        List<DestinationQueryModel> queryModels = getJdbcTemplate().query(query,
                new DestinationQueryModelRowMapper());

        return queryModels;
    }
}

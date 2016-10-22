package br.com.minone.webhooks.infrastructure.persistence;

import br.com.minone.webhooks.query.model.DestinationFinder;
import br.com.minone.webhooks.query.model.DestinationQueryModel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.List;

public class JdbcDestinationFinder extends WebhookJdbcSupport
        implements DestinationFinder {

    @Autowired
    public JdbcDestinationFinder(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<DestinationQueryModel> listDestinations() {
        String query = "";

//        List<DestinationQueryModel> queryModels = getJdbcTemplate().query(query, new EstoqueQueryModelRowMapper(),
//                queryMetaData.getQueryParameters());
//
//        int totalItems = getJdbcTemplate().queryForObject( queryCount, queryMetaData.getQueryParameters(), Integer.class);
//
//        return new QueryResult<>(queryModels, totalItems);
        return null;
    }
}

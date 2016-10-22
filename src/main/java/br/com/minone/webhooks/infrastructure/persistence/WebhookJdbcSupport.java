package br.com.minone.webhooks.infrastructure.persistence;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;

public class WebhookJdbcSupport extends JdbcDaoSupport {

    public WebhookJdbcSupport(DataSource dataSource) {
        setDataSource(dataSource);
    }
}

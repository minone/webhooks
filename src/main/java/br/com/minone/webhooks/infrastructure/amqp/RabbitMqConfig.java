package br.com.minone.webhooks.infrastructure.amqp;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@EnableRabbit
@Configuration
@PropertySources({ @PropertySource("classpath:application.properties") })
public class RabbitMqConfig {
	private static final Logger logger = Logger.getLogger(RabbitMqConfig.class);

	@Value("${amqp.host}")
	private String host;

	@Value("${amqp.port}")
	private int port;

	@Value("${amqp.username}")
	private String username;

	@Value("${amqp.password}")
	private String password;

	@Value("${amqp.exchange}")
	private String exchange;

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
		connectionFactory.setVirtualHost(username);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);

		logger.info("Creating connection factory with: " + username + "@" + host + ":" + port);

		return connectionFactory;
	}

	@Bean
	public AmqpAdmin amqpAdmin() {
		return new RabbitAdmin(connectionFactory());
	}

	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(exchange, true, false);
	}

}

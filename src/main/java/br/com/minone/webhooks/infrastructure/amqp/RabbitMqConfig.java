package br.com.minone.webhooks.infrastructure.amqp;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.TopicExchange;
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

	@Value("${amqp.port:5672}")
	private int port;

	@Value("${amqp.username}")
	private String username;

	@Value("${amqp.password}")
	private String password;

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);

		logger.info("Creating connection factory with: " + username + "@" + host + ":" + port);

		return connectionFactory;
	}

	/**
	 * Required for executing adminstration functions against an AMQP Broker
	 */
	@Bean
	public AmqpAdmin amqpAdmin() {
		return new RabbitAdmin(connectionFactory());
	}

	/**
	 * The following is a complete declaration of an exchange, a queue and a
	 * exchange-queue binding
	 */
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange("email", true, false);
	}

}

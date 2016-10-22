package br.com.minone.webhooks.infrastructure.amqp;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.minone.webhooks.domain.model.QueueRepository;

@Repository
public class QueueRepositoryImpl implements QueueRepository {

	private final AmqpAdmin amqpAdmin;

	private final TopicExchange exchange;

	private final ConnectionFactory connectionFactory;

	@Autowired
	public QueueRepositoryImpl(AmqpAdmin amqpAdmin, TopicExchange exchange, ConnectionFactory connectionFactory) {
		this.amqpAdmin = amqpAdmin;
		this.exchange = exchange;
		this.connectionFactory = connectionFactory;
	}

	@Override
	public void addQueue(String id) {

		String queueName = amqpAdmin.declareQueue(createQueue(id));

		amqpAdmin.declareBinding(new Binding(queueName, Binding.DestinationType.QUEUE, exchange.getName(), id, null));

		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();

		container.setConnectionFactory(connectionFactory);

		container.setQueueNames(queueName);

		container.setMessageListener(new MessageListenerAdapter(new RabbitMQMessageListener()));

		container.start();
	}

	private Queue createQueue(String id) {
		// This queue has the following properties:
		// name: my_durable
		// durable: true
		// exclusive: false
		// auto_delete: false
		return new Queue(id, true, false, false);
	}

}

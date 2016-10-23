package br.com.minone.webhooks.infrastructure.amqp;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.minone.webhooks.domain.model.QueueRepository;
import br.com.minone.webhooks.infrastructure.firebase.FirebaseRepository;

@Singleton
@Repository
public class QueueRepositoryImpl implements QueueRepository {

	private Map<String, SimpleMessageListenerContainer> listeners;

	private final AmqpAdmin amqpAdmin;

	private final DirectExchange exchange;

	private final ConnectionFactory connectionFactory;

	private final FirebaseRepository firebaseRepository;

	@Autowired
	public QueueRepositoryImpl(AmqpAdmin amqpAdmin, DirectExchange exchange, ConnectionFactory connectionFactory,
			FirebaseRepository firebaseRepository) {

		this.amqpAdmin = amqpAdmin;
		this.exchange = exchange;
		this.connectionFactory = connectionFactory;
		this.firebaseRepository = firebaseRepository;
		this.listeners = new HashMap<>();
	}

	@Override
	public synchronized void addQueue(String id) {

		if (!listeners.containsKey(id)) {

			String queueName = amqpAdmin.declareQueue(createQueue(id));

			amqpAdmin.declareBinding(
					new Binding(queueName, Binding.DestinationType.QUEUE, exchange.getName(), id, null));

			SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();

			container.setConnectionFactory(connectionFactory);

			container.setQueueNames(queueName);

			container.setMessageListener(new MessageListenerAdapter(new RabbitMQMessageListener(firebaseRepository)));

			container.start();

			listeners.put(id, container);
		}

	}

	@Override
	public void sendMessage(String queueId, Message message) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.convertAndSend(exchange.getName(), queueId, message);
	}

	private Queue createQueue(String id) {

		boolean durable = true;
		boolean exclusive = false;
		boolean auto_delete = false;

		return new Queue(id, durable, exclusive, auto_delete);
	}

}

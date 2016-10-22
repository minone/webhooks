package br.com.minone.webhooks.infrastructure.amqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.transaction.annotation.Transactional;

public class RabbitMQMessageListener implements MessageListener {
	

	@Transactional
	@Override
	public void onMessage(Message message) {
		System.out.println(message);
	}
}

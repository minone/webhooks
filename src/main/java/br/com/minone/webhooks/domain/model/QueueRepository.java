package br.com.minone.webhooks.domain.model;

import org.springframework.amqp.core.Message;

public interface QueueRepository {

	void addQueue(String queueId);
	
	void sendMessage(String queueId, Message message);

}

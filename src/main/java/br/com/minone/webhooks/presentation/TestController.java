package br.com.minone.webhooks.presentation;

import javax.inject.Singleton;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.minone.webhooks.domain.model.QueueRepository;

@Component
@Singleton
@Path("/test")
public class TestController {

	@Autowired
	private ConnectionFactory connectionFactory;

	@Autowired
	private QueueRepository queueRepository;

	@POST
	@Path("/queue")
	public void addQueue() {
		queueRepository.addQueue("1");
	}

	@POST
	@Path("/send")
	public void send() {
		// send something
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.convertAndSend("email", "1", "Hello, world!");
	}

}

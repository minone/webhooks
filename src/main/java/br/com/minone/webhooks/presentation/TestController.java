package br.com.minone.webhooks.presentation;

import javax.inject.Singleton;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

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
	@Path("/queue/{id}")
	public void addQueue(@PathParam("id") String id) {
		queueRepository.addQueue(id);
	}

	@POST
	@Path("/send/{q}/{m}")
	public void send(@PathParam("q") String q, @PathParam("m") String m) {
		// send something
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.convertAndSend("test-fanout", q, m);
	}

}

package br.com.minone.webhooks.application;

import java.util.Date;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.minone.webhooks.domain.model.QueueRepository;
import br.com.minone.webhooks.security.HmacAlgorithm;
import br.com.minone.webhooks.security.SignatureService;

@Service
public class MessengerApplicationService {

	private final QueueRepository queueRepository;

	@Autowired
	public MessengerApplicationService(QueueRepository queueRepository) {
		this.queueRepository = queueRepository;
	}

	public void postMessage(String id, String url, String secret, String hmac, String contentType, String content) {

		String signature = SignatureService.newInstance(HmacAlgorithm.HMAC_SHA1).calculateHMAC(secret, content);

		if (!signature.equals(hmac)) {
			throw new SecurityException("Signature does not match");
		}

		sendMessage(id, url, contentType, content);

	}

	public void addQueue(String queueId) {
		queueRepository.addQueue(queueId);
	}

	private void sendMessage(String queueId, String url, String contenType, String content) {

		MessageProperties messageProperties = new MessageProperties();

		messageProperties.setContentType(contenType);

		messageProperties.setReplyTo(url);

		messageProperties.setTimestamp(new Date());
		
		messageProperties.setMessageCount(0);

		Message message = new Message(content.getBytes(), messageProperties);

		queueRepository.sendMessage(queueId, message);
	}
}

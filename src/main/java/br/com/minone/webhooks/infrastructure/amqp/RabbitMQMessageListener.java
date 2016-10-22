package br.com.minone.webhooks.infrastructure.amqp;

import java.util.Date;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.rabbitmq.client.Channel;

import br.com.minone.webhooks.infrastructure.service.IncredibleHookService;

public class RabbitMQMessageListener implements ChannelAwareMessageListener {

	private static final int HOURS = 2;

	private static final long DAY_MILISECONDS = 60000L;

	private final IncredibleHookService incredibleHookService;

	public RabbitMQMessageListener() {
		this.incredibleHookService = IncredibleHookService.newInstance();
	}

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {

		String url = message.getMessageProperties().getReplyTo();

		String content = new String(message.getBody());

		String contentType = message.getMessageProperties().getContentType();

		boolean success = incredibleHookService.deliver(url, content, contentType);

		if (!success) {

			Date messageDate = message.getMessageProperties().getTimestamp();

			Date now = new Date();

			long diffInHours = (now.getTime() - messageDate.getTime()) / DAY_MILISECONDS;

			if (diffInHours < HOURS) {
				channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
			}

		}

	}
}

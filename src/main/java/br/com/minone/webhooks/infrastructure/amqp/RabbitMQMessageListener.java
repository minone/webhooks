package br.com.minone.webhooks.infrastructure.amqp;

import br.com.minone.webhooks.infrastructure.firebase.FirebaseRepository;
import br.com.minone.webhooks.infrastructure.service.IncredibleHookService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import java.util.Date;

public class RabbitMQMessageListener implements ChannelAwareMessageListener {

    private static final int HOURS = 2;

    private static final long DAY_MILISECONDS = 60000L;

    private final IncredibleHookService incredibleHookService;

    private final FirebaseRepository firebaseRepository;

    public RabbitMQMessageListener(FirebaseRepository firebaseRepository) {

        this.firebaseRepository = firebaseRepository;
        this.incredibleHookService = IncredibleHookService.newInstance(firebaseRepository);
    }


    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

        String url = message.getMessageProperties().getReplyTo();

        String content = new String(message.getBody());

        String contentType = message.getMessageProperties().getContentType();

        firebaseRepository.post("Trying to post url: " + url);

        boolean success = incredibleHookService.deliver(url, content, contentType);

        if (!success) {

            firebaseRepository.post("Failt to post url: " + url);

            Date messageDate = message.getMessageProperties().getTimestamp();

            Date now = new Date();

            long diffInHours = (now.getTime() - messageDate.getTime()) / DAY_MILISECONDS;


            if (diffInHours < HOURS) {
                firebaseRepository.post("Message will be deleted: " + url);
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
            }

        }

    }
}

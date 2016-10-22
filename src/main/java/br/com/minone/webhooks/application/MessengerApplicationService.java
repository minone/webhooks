package br.com.minone.webhooks.application;

import br.com.minone.webhooks.infrastructure.service.MessengerService;
import br.com.minone.webhooks.security.HmacAlgorithm;
import br.com.minone.webhooks.security.SignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessengerApplicationService {

    private final MessengerService messengerService;

    @Autowired
    public MessengerApplicationService(MessengerService messengerService) {
        this.messengerService = messengerService;
    }

    public void post(String url, String secret, String hmac, String contentType, String content) {

        String signature = SignatureService.newInstance(HmacAlgorithm.HMAC_SHA1).calculateHMAC(secret, content);

        if (signature != hmac) {
            throw new SecurityException("Signature does not match");
        }

        messengerService.deliver(url, content, contentType, secret);
    }
}

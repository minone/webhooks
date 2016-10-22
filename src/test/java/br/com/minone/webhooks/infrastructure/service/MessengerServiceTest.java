package br.com.minone.webhooks.infrastructure.service;

import org.junit.Test;

public class MessengerServiceTest {

    @Test
    public void testDeliver() throws Exception {

        String content = "conteudo";

        MessengerService messengerService = new MessengerService();

        boolean result = messengerService.deliver("http://localhost:8086/webhooks/test", content, "text/html", "56a6e7f1-1b32-4f8a-8670-2729824fc13b");

    }
}
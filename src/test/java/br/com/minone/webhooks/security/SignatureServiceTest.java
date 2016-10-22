package br.com.minone.webhooks.security;

import org.junit.Test;

public class SignatureServiceTest {


    @Test
    public void calculateHMAC() {

        String secret = "56a6e7f1-1b32-4f8a-8670-2729824fc13b";

        String data = "vanhackathon and hootsuite";

        SignatureService service = SignatureService.newInstance(HmacAlgorithm.HMAC_SHA256);

        String hash = service.calculateHMAC(secret, data);

        System.out.println(hash);
    }
}
package br.com.minone.webhooks.security;

import org.junit.Test;

public class SignatureServiceTest {


    @Test
    public void calculateHMAC() {

        String secret = "306ad26c-cb8f-4215-817c-f85288a858dc";

        String data = "vanhackathon and hootsuite";

        SignatureService service = SignatureService.newInstance(HmacAlgorithm.HMAC_SHA1);

        String hash = service.calculateHMAC(data, secret);

        //ESdRhUqTnJ8HheZdMbig00CbLec=
        //ESdRhUqTnJ8HheZdMbig00CbLec=
        System.out.println(hash);
    }
}
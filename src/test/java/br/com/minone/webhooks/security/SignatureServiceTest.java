package br.com.minone.webhooks.security;

import org.junit.Test;

public class SignatureServiceTest {


    @Test
    public void calculateHMAC() {

        String secret = "2dd9920b-c1a6-47ea-af4d-8bc1b1593a27";

        String data = "vanhackathon and hootsuite";

        SignatureService service = SignatureService.newInstance(HmacAlgorithm.HMAC_SHA1);

        String hash = service.calculateHMAC(data, secret);

        //ESdRhUqTnJ8HheZdMbig00CbLec=
        //ESdRhUqTnJ8HheZdMbig00CbLec=
        System.out.println(hash);
    }
}
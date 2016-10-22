package br.com.minone.webhooks.security;

import org.junit.Test;

public class SignatureServiceTest {


    @Test
    public void calculateHMAC() {

        String secret = "67966288-a1c7-4acc-9dc5-a3493cef25e4";

        String data = "vanhackathon and hootsuite";

        SignatureService service = SignatureService.newInstance(HmacAlgorithm.HMAC_SHA1);

        String hash = service.calculateHMAC(data, secret);

        //ESdRhUqTnJ8HheZdMbig00CbLec=
        //ESdRhUqTnJ8HheZdMbig00CbLec=
        System.out.println(hash);
    }
}
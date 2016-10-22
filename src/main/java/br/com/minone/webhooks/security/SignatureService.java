package br.com.minone.webhooks.security;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

public class SignatureService {

    private HmacAlgorithm algorithm;

    private SignatureService(HmacAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public static SignatureService newInstance(HmacAlgorithm algorithm) {
        return new SignatureService(algorithm);
    }

    public String calculateHMAC(String secret, String data) {

        try {
            SecretKeySpec sharedPK = new SecretKeySpec(secret.getBytes(), algorithm.getId());

            Mac mac = Mac.getInstance(algorithm.getId());
            mac.init(sharedPK);

            byte[] rawData = mac.doFinal(data.getBytes());

            String result = new String(Base64.getEncoder().encode(rawData), StandardCharsets.UTF_8);

            return result;

        } catch (GeneralSecurityException e) {
            throw new IllegalArgumentException();
        }
    }
}

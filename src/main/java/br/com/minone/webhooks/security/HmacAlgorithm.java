package br.com.minone.webhooks.security;


public enum HmacAlgorithm {

    HMAC_SHA1("HmacSHA1"),
    HMAC_SHA256("HmacSHA256"),
    HMAC_SHA384("HmacSHA384"),
    HMAC_SHA512("HmacSHA512");

    private String id;

    HmacAlgorithm(String id) {
        this.id = id;
    }

    public static HmacAlgorithm fromValue(String id) {

        for (HmacAlgorithm e : HmacAlgorithm.values()) {
            if (e.getId().equals(id)) {
                return e;
            }
        }

        return null;
    }

    public String getId() {
        return id;
    }
}

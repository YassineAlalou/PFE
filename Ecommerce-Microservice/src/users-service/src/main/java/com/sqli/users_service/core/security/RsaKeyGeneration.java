package com.sqli.users_service.core.security;

import java.util.UUID;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;

public class RsaKeyGeneration {
    private static RSAKey rsaKey;

    private static RsaKeyGeneration rsaKeyGeneration;
    private RsaKeyGeneration(){};
    public static RsaKeyGeneration getInstance() {
        if (rsaKeyGeneration==null){
            synchronized (RsaKeyGeneration.class)
            {
                if (rsaKeyGeneration==null) {
                    rsaKeyGeneration = new RsaKeyGeneration();
                    setRsaKey();
                }

            }
        }
        return rsaKeyGeneration;
    }

    public static void setRsaKey()  {
        try {
            RsaKeyGeneration.rsaKey = new RSAKeyGenerator(2048)
                .keyUse(KeyUse.SIGNATURE)
                .keyID(UUID.randomUUID().toString())
                .generate();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
    }

    public RSAKey getRsaKey() {
        return rsaKey;
    }

}
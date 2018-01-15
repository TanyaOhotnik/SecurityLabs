package com.tanyaohotnik.security_casino.sha;


public class SHA1Server {

    public byte[] getHash(String secret, String message) {
        SHA1 sha1 = new SHA1();
        return sha1.digest((secret + message).getBytes());
    }

    public String getHashString(String secret, String message) {
        return Converter.toHexString(getHash(secret, message));
    }

}

package com.tanyaohotnik.security_casino.sha;

import java.io.IOException;

public class Main {

    private static final String MESSAGE =
            "Guys who understand that using Hash function as Mac is " +
                    "one very bad practice: Thai Duong; Juliano Rizzo; " +
                    "Flickr (the hard way);";


    public static void main(String[] args) throws IOException {
        checkHash("supersecretpass", MESSAGE, "Tanya Ohotnik");
    }


    private static boolean checkHash(String key, String data, String append) throws IOException {
        SHA1Attacker sha1Attacker = new SHA1Attacker();
        SHA1Server sha1Server = new SHA1Server();

        String hash = sha1Server.getHashString(key, data);
        for (int i = 1; i < 64; i++) {

            Container result = sha1Attacker.getStorage(i, hash, data, append);
            byte[] newHMAC = result.getNewHMAC();
            byte[] newMessage = result.getNewMessage();
            String serverNewHMAC = sha1Attacker.getServerNewHMAC(key.getBytes(), newMessage);
            String hexNewHMAC = Converter.toHexString(newHMAC);

            System.out.println("Key length: " + i);
            System.out.println("Cracked hash: " + hexNewHMAC);
            System.out.println("Server hash: " + serverNewHMAC);
            if (serverNewHMAC.equals(hexNewHMAC)) {
                return true;
            }

        }
        return false;
    }

}

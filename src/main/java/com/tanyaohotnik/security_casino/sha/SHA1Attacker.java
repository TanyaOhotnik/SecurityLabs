package com.tanyaohotnik.security_casino.sha;


public class SHA1Attacker {

    public Container getStorage(int secretLength, String oldHash, String oldMessage, String append) {

        SHA1 sha1 = Converter.initSHA1(oldHash);

        /* Block = 512 bit = 64 bytes
        *  One element of Java String is 1 byte
        * */
        int bytesAfterData = 64 - ((secretLength + oldMessage.length()) % 64);

        if (bytesAfterData <= 8) {
            // Add one block for size part
            bytesAfterData += 64;
        }

        int oldLength = (secretLength + oldMessage.length()) * 8;

        int paddingSize = bytesAfterData - 8;

        byte[] paddingPart = new byte[paddingSize];

        byte[] sizePart = getSizePart(oldLength);

        byte[] newMsg = createNewMessage(oldMessage.getBytes(), paddingPart, sizePart, append.getBytes());

        byte[] newHMAC = getNewHMAC(oldHash, append, oldMessage.getBytes(), paddingPart, sizePart);

        return new Container(newHMAC, newMsg);
    }

    private byte[] getSizePart(int length) {
        byte[] sizePart = new byte[8];

        int index = sizePart.length - 1;

        while (length > 0) {
            sizePart[index] = (byte) (0xFF & length);
            length = length >> 8;
            index--;
        }

        return sizePart;
    }

    private byte[] createNewMessage(byte[] old, byte[] padding, byte[] size, byte[] append) {
        byte[] newMsg = new byte[old.length + padding.length + size.length + append.length];

        int position = 0;

        System.arraycopy(old, 0, newMsg, position, old.length);

        position += old.length;

        System.arraycopy(padding, 0, newMsg, position, padding.length);

        position += padding.length;

        System.arraycopy(size, 0, newMsg, position, size.length);

        position += size.length;

        System.arraycopy(append, 0, newMsg, position, append.length);

        newMsg[old.length] = (byte)0x80;

        return newMsg;
    }

    private byte[] getNewHMAC(String oldHash, String append, byte[] oldMessage, byte[] padding, byte[] size) {
        SHA1 sha1 = Converter.initSHA1(oldHash);
        return sha1.digest(append.getBytes(), oldMessage.length + padding.length + size.length);
    }

    public String getServerNewHMAC(byte[] secret, byte[] newMsg) {
        byte[] result = new byte[secret.length + newMsg.length];

        System.arraycopy(secret, 0, result, 0, secret.length);
        System.arraycopy(newMsg, 0, result, secret.length, newMsg.length);

        SHA1 sha1 = new SHA1();
        return Converter.getRealShaString(result);
    }
}

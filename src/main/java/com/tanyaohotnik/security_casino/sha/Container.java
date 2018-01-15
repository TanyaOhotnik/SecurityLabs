package com.tanyaohotnik.security_casino.sha;

public class Container {

    private byte[] newHMAC;

    private byte[] newMessage;

    public Container(byte[] newHMAC, byte[] newMessage) {
        this.newHMAC = newHMAC;
        this.newMessage = newMessage;
    }

    public byte[] getNewHMAC() {
        return newHMAC;
    }

    public void setNewHMAC(byte[] newHMAC) {
        this.newHMAC = newHMAC;
    }

    public byte[] getNewMessage() {
        return newMessage;
    }

    public void setNewMessage(byte[] newMessage) {
        this.newMessage = newMessage;
    }
}

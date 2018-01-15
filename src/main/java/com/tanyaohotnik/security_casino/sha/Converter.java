package com.tanyaohotnik.security_casino.sha;

public class Converter {
    public static String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(String.format("%02X", bytes[i] & 0xFF));
        }
        return sb.toString();
    }

    public static SHA1 initSHA1(String initValue) {
        SHA1 sha1 = new SHA1();

        sha1.a = getSHA1StartParam(initValue, 0, 8);
        sha1.b = getSHA1StartParam(initValue, 8, 16);
        sha1.c = getSHA1StartParam(initValue, 16, 24);
        sha1.d = getSHA1StartParam(initValue, 24, 32);
        sha1.e = getSHA1StartParam(initValue, 32, 40);

        return sha1;
    }

    private static int getSHA1StartParam(String value, int start, int end) {
        return Long.valueOf(value.substring(start, end), 16).intValue();
    }

    public static String getRealShaString(String s) {
        SHA1 sha1 = new SHA1();
        return Converter.toHexString(sha1.digest(s.getBytes()));
    }

    public static String getRealShaString(byte[] s) {
        SHA1 sha1 = new SHA1();
        return Converter.toHexString(sha1.digest(s));
    }
}

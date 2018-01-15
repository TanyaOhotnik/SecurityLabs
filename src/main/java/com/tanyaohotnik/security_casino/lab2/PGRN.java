package com.tanyaohotnik.security_casino.lab2;

import org.apache.commons.math3.random.MersenneTwister;

public class PGRN {
    public static long seed ;
    public static  MersenneTwister mt;
    static {
        mt = new MersenneTwister();
        seed = System.currentTimeMillis()/1000L+150;
        mt.setSeed(seed);
    }
    public static int requestPGRN() {
        return mt.nextInt();
    }
}

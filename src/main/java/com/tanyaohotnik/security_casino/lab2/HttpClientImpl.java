package com.tanyaohotnik.security_casino.lab2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.math3.random.MersenneTwister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.io.IOException;


@Service
public class HttpClientImpl {
    private static final String LCG = "Lcg";
    private static final String MT = "Mt";
    private static final String BETTER_MT = "BetterMt";
    long a = 1664525, c = 1013904223;
    int user = 12345;
    String siteUrl = "http://ec2-35-159-11-170.eu-central-1.compute.amazonaws.com/casino/play%s?id=%s&bet=%s&number=%d";
    private RestTemplate restTemplate;
    private Long money = 0L;

    @Autowired
    public HttpClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String crackLcg() throws IOException {

        long current = getRequest(user, 1, 1, LCG);
        while (money < 1000100L) {
            current = getNextLcg(current);
            current = getRequest(user, 100, current, LCG);
        }
        return String.format(siteUrl, LCG, user, 1, getNextLcg(current));
    }

    public String crackMt() throws IOException {
//        long seed = OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.UTC).toEpochSecond();
        long seed = System.currentTimeMillis() / 1000L;
        int currentNumber = PGRN.requestPGRN();
        MersenneTwister mt = new MersenneTwister();
        mt.setSeed(seed);
        long mtNumber = mt.nextInt();
        System.out.println("Initial seed " + seed);
        while (mtNumber != currentNumber) {
            seed++;
            System.out.println("current seed " + seed);
            mt.setSeed(seed);
            mtNumber = mt.nextInt();
        }
        System.out.println("Actual seed " + seed);
        for (int i = 0; i < 10; i++) {
            System.out.println("cracked mt: " + mt.nextInt() + " real mt " + PGRN.requestPGRN());
        }
        return "done";
    }


    private long getRequest(long user, long bet, long number, String mode) throws RuntimeException, IOException {
        String url = String.format(siteUrl, mode, user, bet, number);
        ResponseEntity<String> responseEntity;
        try {
            responseEntity = restTemplate.getForEntity(url, String.class);
        } catch (HttpClientErrorException ex) {
            return 0;
        }
        ObjectNode node = new ObjectMapper().readValue(responseEntity.getBody(), ObjectNode.class);
        System.out.println(responseEntity.getBody());
        if (node.has("account"))
            money = node.get("account").get("money").asLong();
        if (node.has("realNumber")) {
            System.out.println("real number " + node.get("realNumber") + " expected number " + number);
            return node.get("realNumber").asLong();
        }
        throw new RuntimeException("no such field");
    }

    //LCG
    public long getNextLcg(long last) {
        return (a * last + c) % (long) Math.pow(2, 32); // m is 2^32
    }

    //stub
    public String crackBetterMt() throws IOException {
        for (int i = 0; i < 20; i++)
            getRequest(6, 1, 1, BETTER_MT);
        return "done";
    }
}


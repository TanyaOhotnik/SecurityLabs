package com.tanyaohotnik.security_casino.lab2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrackController {
    @Autowired
    HttpClientImpl httpClient;
//    @Autowired
//    HttpClientLab3Impl httpClient3;
    @RequestMapping(value = "crackLcg")
    public String crackLcg() {
        try {
            return httpClient.crackLcg();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return "something wrong";
    }
    @RequestMapping(value = "crackMt")
    public String crackMt() {
        try {
            return httpClient.crackMt();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return "something wrong";
    }
//    @RequestMapping(value = "crackMac")
//    public String crackMac() {
//        try {
//            return httpClient3.crackMac();
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//        return "something wrong";
//    }
    @RequestMapping(value = "crackBetterMt")
    public String crackBetterMt() {
        try {
            return httpClient.crackBetterMt();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return "something wrong";
    }

}

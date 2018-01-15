package com.tanyaohotnik.security_casino.lab1;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Tanya Ohotnik on 14.09.2017.
 */
public class CharsFrequency {
    public static Map<Character,Double> charFrequency;
    public static Map<String,Double> bigrammFrequency;
    public static Map<String,Double> trigrammFrequency;
    static {
        charFrequency = new LinkedHashMap<>();
        charFrequency.put('e',0.12702);
        charFrequency.put('t',0.09056);
        charFrequency.put('a',0.08167);
        charFrequency.put('o',0.07507);
        charFrequency.put('i',0.06966);
        charFrequency.put('n',0.06749);
        charFrequency.put('s',0.06327);
        charFrequency.put('h',0.06094);
        charFrequency.put('r',0.05987);
        charFrequency.put('d',0.04253);
        charFrequency.put('l',0.04025);
        charFrequency.put('c',0.02782);
        charFrequency.put('u',0.02758);
        charFrequency.put('m',0.02406);
        charFrequency.put('w',0.02360);
        charFrequency.put('f',0.02228);
        charFrequency.put('g',0.02015);
        charFrequency.put('y',0.01974);
        charFrequency.put('p',0.01929);
        charFrequency.put('b',0.01492);
        charFrequency.put('k',0.00772);
        charFrequency.put('v',0.00978);
        charFrequency.put('j',0.00153);
        charFrequency.put('x',0.00150);
        charFrequency.put('q',0.00095);
        charFrequency.put('z',0.00074);

        bigrammFrequency = new HashMap<>();
//        bigrammFrequency.put("th",0.0271);
//        bigrammFrequency.put("he",0.0233);
//        bigrammFrequency.put("in",0.0203);
//        bigrammFrequency.put("er",0.0118);
//        bigrammFrequency.put("an",0.0161);
//        bigrammFrequency.put("re",0.0141);
//        bigrammFrequency.put("es",0.0132);
//        bigrammFrequency.put("on",0.0132);
//        bigrammFrequency.put("st",0.0125);
//        bigrammFrequency.put("nt",0.0117);
//        bigrammFrequency.put("en",0.0113);
//        bigrammFrequency.put("at",0.0112);
//
//        bigrammFrequency.put("ed",0.0108);
//        bigrammFrequency.put("nd",0.0107);
//        bigrammFrequency.put("to",0.0107);
//        bigrammFrequency.put("or",0.0106);
//        bigrammFrequency.put("ea",0.01);
//        bigrammFrequency.put("ti",0.0099);
//        bigrammFrequency.put("ar",0.0098);
//        bigrammFrequency.put("te",0.0098);
//        bigrammFrequency.put("ng",0.0089);
//        bigrammFrequency.put("al",0.0088);
//        bigrammFrequency.put("it",0.0088);
//        bigrammFrequency.put("as",0.0087);
//        bigrammFrequency.put("is",0.0086);
//        bigrammFrequency.put("ha",0.0083);
//        bigrammFrequency.put("et",0.0076);
//        bigrammFrequency.put("se",0.0073);
//        bigrammFrequency.put("ou",0.0072);
//        bigrammFrequency.put("of",0.0071);


        bigrammFrequency.put("th",0.0356);
        bigrammFrequency.put("he",0.0307);
        bigrammFrequency.put("in",0.0243);
        bigrammFrequency.put("er",0.0205);
        bigrammFrequency.put("an",0.0199);
        bigrammFrequency.put("re",0.0185);
        bigrammFrequency.put("on",0.0176);
        bigrammFrequency.put("at",0.0149);
        bigrammFrequency.put("en",0.0145);
        bigrammFrequency.put("nd",0.0135);
        bigrammFrequency.put("ti",0.0134);
        bigrammFrequency.put("es",0.0134);
        bigrammFrequency.put("or",0.0128);
        bigrammFrequency.put("te",0.0120);
        bigrammFrequency.put("of",0.0117);
        bigrammFrequency.put("ed",0.0117);
        bigrammFrequency.put("is",0.0113);
        bigrammFrequency.put("it",0.0112);
        bigrammFrequency.put("al",0.0109);
        bigrammFrequency.put("ar",0.0107);
        bigrammFrequency.put("st",0.0105);
        bigrammFrequency.put("to",0.0104);
        bigrammFrequency.put("nt",0.0104);
        bigrammFrequency.put("ng",0.0095);
        bigrammFrequency.put("se",0.0093);
        bigrammFrequency.put("ha",0.0093);
        bigrammFrequency.put("as",0.0087);
        bigrammFrequency.put("ou",0.0087);
        bigrammFrequency.put("io",0.0083);
        bigrammFrequency.put("le",0.0083);
        bigrammFrequency.put("ve",0.0083);
        bigrammFrequency.put("co",0.0079);
        bigrammFrequency.put("me",0.0079);
        bigrammFrequency.put("de",0.0076);
        bigrammFrequency.put("hi",0.0076);
        bigrammFrequency.put("ri",0.0073);
        bigrammFrequency.put("ro",0.0073);
        bigrammFrequency.put("ic",0.0070);
        bigrammFrequency.put("ic",0.0070);
        bigrammFrequency.put("ic",0.0070);
        bigrammFrequency.put("ne",0.0069);
        bigrammFrequency.put("ea",0.0069);
        bigrammFrequency.put("ra",0.0069);
        bigrammFrequency.put("ce",0.0065);
        bigrammFrequency.put("li",0.0062);
        bigrammFrequency.put("ch",0.0060);
        bigrammFrequency.put("ll",0.0058);
        bigrammFrequency.put("be",0.0058);
        bigrammFrequency.put("ma",0.0057);
        bigrammFrequency.put("si",0.0055);
        bigrammFrequency.put("om",0.0055);
        bigrammFrequency.put("ur",0.0054);

        trigrammFrequency = new HashMap<>();
        trigrammFrequency.put("the",0.0181);
        trigrammFrequency.put("and",0.0073);
        trigrammFrequency.put("ing",0.0072);
        trigrammFrequency.put("ent",0.0042);
        trigrammFrequency.put("her",0.0036);
        trigrammFrequency.put("for",0.0034);
        trigrammFrequency.put("tha",0.0033);
        trigrammFrequency.put("nth",0.0033);
        trigrammFrequency.put("int",0.0032);

    }

    public static char[] getSortedFrequencyCharArray(){
        char[] array = new char[charFrequency.size()];
        int i = 0;
        for (Map.Entry<Character, Double> entry : charFrequency.entrySet()) {
            array[i] = entry.getKey(); i++;
        }
        return array;
    }
    public static String[] getSortedFrequencyBigrammArray(){
        String[] array = new String[bigrammFrequency.size()];
        int i = 0;
        for (Map.Entry<String, Double> entry : bigrammFrequency.entrySet()) {
            array[i] = entry.getKey(); i++;
        }
        return array;
    }

}

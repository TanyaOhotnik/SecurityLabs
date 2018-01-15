package com.tanyaohotnik.security_casino.lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Tanya Ohotnik on 14.09.2017.
 */
public class Decoder1 {
    private static int[] intCharArray;

    public static void main(String[] args) throws FileNotFoundException{
        parseString(new File("crypto.txt"));
        int key = findKey();
        encodeString(key);
    }

    private static void encodeString(int key) {
        System.out.println("key = "+ key);
        for(int i = 0; i< intCharArray.length; i++){
            System.out.print((char)(intCharArray[i]^key));
        }
        System.out.println();
    }

    public static String readFromFile(File file) throws FileNotFoundException {
        StringBuilder str = new StringBuilder();
        Scanner output = new Scanner(file);
        while(output.hasNextLine())
            str.append(output.nextLine());
        intCharArray = new int[str.length()/2];
        return str.toString();
    }
    public static int getIntRepresentation(String hexString){
        int decimal = Integer.parseInt(hexString,16);
        return decimal;
    }
    public static void parseString(File file) throws FileNotFoundException{
        String cipherText = readFromFile(file);
        cipherText = cipherText.toLowerCase();
        for(int i=0;i<cipherText.length();i+=2){
            String hexString  = cipherText.charAt(i)+"" + cipherText.charAt(i+1);
            intCharArray[i/2] = getIntRepresentation(hexString);
        }

    }
    public static int findKey(){
        Map<Integer, Double> cryptoTextMap = new HashMap<>();

        for(int i = 0; i< intCharArray.length; i++){

            int currentCharacter = intCharArray[i];
            if (!cryptoTextMap.containsKey(currentCharacter))
                cryptoTextMap.put(currentCharacter, 1.);
            else
            cryptoTextMap.put(currentCharacter, (cryptoTextMap.get(currentCharacter) + 1));
        }
        for (Map.Entry<Integer, Double> entry : cryptoTextMap.entrySet()) {
            cryptoTextMap.put(entry.getKey(), entry.getValue() / intCharArray.length);
        }
        double max = 0;
        int symbol= 0;
        for (Map.Entry<Integer, Double> entry : cryptoTextMap.entrySet()) {
            if(entry.getValue()>max){
                max = entry.getValue();
                symbol = entry.getKey();
            }

        }
//        System.out.println((int)' ');
        return symbol^' ';

    }


}

package com.tanyaohotnik.security_casino.lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Tanya Ohotnik on 01.10.2017.
 */
public class Decoder3 {
    private static int ALPHABET_SIZE = 26;

    static Map<Double, Character> sortedCipherFrequency;
    static Map<String, Double> alphabetBigrammFrequency = CharsFrequency.bigrammFrequency;
    static Map<String, Double> alphabetTrigrammFrequency = CharsFrequency.trigrammFrequency;

    public static void main(String[] args) throws Exception {

        encode(new File("crypto3.txt"));
    }

    private static void encode(File file) throws FileNotFoundException {
        String text = readFromFile(file);
        char[] key = findMainKey(text);
        char[] alphabet = CharsFrequency.getSortedFrequencyCharArray();
//        String[] bigramms = CharsFrequency.getSortedFrequencyBigrammArray();
        String mainText = replaceTextWithKey(text, alphabet, key);
        System.out.println(mainText);
        System.out.println(key);
        double minBigrammRate = countBigrammRate(mainText);
        System.out.println(minBigrammRate);
        System.out.println();
        double currentBigrammRate ;
        int step = 1;
        boolean findBetterBigrammFlag;

        while (true) {
            findBetterBigrammFlag = false;
            for (int index = 0; index < ALPHABET_SIZE; index++) {
                if (index + step >= 26) break;
                char buff = key[index];
                key[index] = key[index + step];
                key[index + step] = buff;

                String secondaryText = replaceTextWithKey(text, alphabet, key);
                currentBigrammRate = countBigrammRate(secondaryText);
                System.out.println(currentBigrammRate + " ");
                if (currentBigrammRate < minBigrammRate) {
                    System.out.println(key[index]+" "+ key[index+step]);
                    System.out.println();
                    minBigrammRate = currentBigrammRate;
                    mainText = secondaryText;
                    findBetterBigrammFlag = true;
                    step = 1;
                    break;
                } else {
                    char buff2 = key[index];
                    key[index] = key[index + step];
                    key[index + step] = buff2;
                }
                System.out.println(key);
                System.out.println(secondaryText);
            }

            if(!findBetterBigrammFlag) {
                step++;
                if (step >= 25) break;
            }
        }
        System.out.println(key);
    }

    private static double countBigrammRate(String mainText) {
        Map<String, Double> bigrammFrequency = new LinkedHashMap<>();

        for (int i = 0; i < mainText.length() - 1; i++) {
            String str = mainText.charAt(i) + "" + mainText.charAt(i + 1);
            if (bigrammFrequency.containsKey(str)) continue;
            int k = 0;
            for (int j = i; j < mainText.length() - 1; j++) {
                String currentStr = mainText.charAt(j) + "" + mainText.charAt(j + 1);
                if (currentStr.equals(str)) k++;

            }
            bigrammFrequency.put(str, (double) k / mainText.length());
//            bigrammFrequency.put(str,(double)k);
        }


        Map<String, Double> trigrammFrequency = new HashMap<>();
        for (int i = 0; i < mainText.length()-2; i++) {
            String str = mainText.charAt(i) + "" + mainText.charAt(i + 1)+"" + mainText.charAt(i + 2);
            if (trigrammFrequency.containsKey(str)) continue;
            int k = 0;
            for (int j = i; j < mainText.length() - 2; j++) {
                String currentStr = mainText.charAt(j) + "" + mainText.charAt(j + 1)+ "" + mainText.charAt(j + 2);
                if (currentStr.equals(str)) k++;

            }
            trigrammFrequency.put(str, (double) k / mainText.length());
        }
//        for (Map.Entry<String, Double> entry : trigrammFrequency.entrySet()) {
//            trigrammFrequency.put(entry.getKey(), entry.getValue() / mainText.length());
//        }



        double result = 0.;
        for (Map.Entry<String, Double> entry : bigrammFrequency.entrySet()) {
            if (alphabetBigrammFrequency.containsKey(entry.getKey()))
                result +=Math.pow(-alphabetBigrammFrequency.get(entry.getKey()) + entry.getValue(),2);
            else result += entry.getValue();
        }
        for (Map.Entry<String, Double> entry : trigrammFrequency.entrySet()) {
            if (alphabetTrigrammFrequency.containsKey(entry.getKey()))
                result +=Math.pow(-alphabetTrigrammFrequency.get(entry.getKey()) + entry.getValue(),2);
            else result += entry.getValue();
        }
        return result;
    }

    private static String replaceTextWithKey(String text, char[] alphabetFrequency, char[] cipherCharFrequency) {
        int i;
        StringBuilder str = new StringBuilder();
        for (i = 0; i < text.length(); i++) {
            int index = findIndex(text.charAt(i), cipherCharFrequency);
            str.append(alphabetFrequency[index]);
        }
//        System.out.println(str);
        return str.toString();

    }

    private static int findIndex(char c, char[] charArray) {
        for (int i = 0; i < charArray.length; i++)
            if (charArray[i] == c) return i;
        return -1;
    }


//    private static int[] findKey(int keyLength, int[] cypher) {
//        int[] key = new int[keyLength];
//        for (int i = 0; i < keyLength; i++) {
//            key[i] = findFrequencyByPosition(i, keyLength, cypher);
//            key[i] = key[i]^' ';
//        }
//
//        System.out.println((char)key[0] + " " + (char)key[1] + " " + (char)key[2]);
//        return key;
//    }

    private static char[] findMainKey(String cipher) {
        Map<Character, Double> cryptoTextMap = new HashMap<>();
        for (int i = 'a'; i <= 'z'; i++)
            cryptoTextMap.put((char) i, 0.);
        for (int i = 0; i < cipher.length(); i++) {
            char currentCharacter = cipher.charAt(i);
            if (!cryptoTextMap.containsKey(currentCharacter))
                cryptoTextMap.put(currentCharacter, 1.);
            else
                cryptoTextMap.put(currentCharacter, (cryptoTextMap.get(currentCharacter) + 1));
        }
        for (Map.Entry<Character, Double> entry : cryptoTextMap.entrySet()) {
            cryptoTextMap.put(entry.getKey(), entry.getValue() / cipher.length());
        }


//        for (Map.Entry<Character, Double> entry : cryptoTextMap.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }
        sortedCipherFrequency = new TreeMap<>();
        double step = 0;
        //искусственно увеличивает частоту чтобы сохранить все буквы
        for (Map.Entry<Character, Double> entry : cryptoTextMap.entrySet()) {
            if (sortedCipherFrequency.containsKey(entry.getValue())) {
                step += 0.00001;
                sortedCipherFrequency.put(entry.getValue() + step, entry.getKey());
            } else
                sortedCipherFrequency.put(entry.getValue(), entry.getKey());
        }
//        System.out.println("Sorted map" + sortedCipherFrequency.size());
//        for (Map.Entry<Double, Character> entry : sortedCipherFrequency.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }


        char[] cipherCharFrequency = new char[ALPHABET_SIZE];
        int i = ALPHABET_SIZE - 1;
        for (Map.Entry<Double, Character> entry : sortedCipherFrequency.entrySet()) {
            cipherCharFrequency[i] = entry.getValue();
            i--;
        }
//        for (i = 0; i < cipherCharFrequency.length; i++)
//            System.out.print(cipherCharFrequency[i] + " ");
        return cipherCharFrequency;
    }


    public static String readFromFile(File file) throws FileNotFoundException {
        StringBuilder str = new StringBuilder();
        Scanner output = new Scanner(file);
        while (output.hasNextLine())
            str.append(output.nextLine());
        return str.toString().toLowerCase();
    }


}

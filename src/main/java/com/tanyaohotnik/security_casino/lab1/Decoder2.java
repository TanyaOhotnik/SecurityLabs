package com.tanyaohotnik.security_casino.lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Decoder2 {
    private static double[] frequencyArray;
    private static String[] hexCharArray;

    public static void main(String[] args) throws Exception {
        System.out.println();
        parseString(new File("crypto2.txt"));

        int[] cypher = new int[hexCharArray.length];
        for(int i =0;i<hexCharArray.length;i++)
            cypher[i] = Integer.parseInt(hexCharArray[i],16);
        int keyLength = findKeyLength(cypher);
//        findKey(keyLength,cypher);
        encode(findKey(keyLength, cypher), cypher);

    }

    private static void encode(int[] key, int[] text) {
        StringBuilder str = new StringBuilder();
        int k = 0;
        for(int i =0;i<text.length;i++){

            str.append((char)(text[i]^key[k]));
            if(k==key.length-1) k = 0;
            else k++;
        }
        System.out.println(str);
    }

    private static int[] findKey(int keyLength, int[] cypher) {
        int[] key = new int[keyLength];
        for (int i = 0; i < keyLength; i++) {
            key[i] = findFrequencyByPosition(i, keyLength, cypher);
            key[i] = key[i]^' ';
        }
        System.out.print("Key = ");
        for(int i=0;i<key.length;i++)
        System.out.print((char)key[i]+" ");
        System.out.println();
        return key;
    }

    private static int findFrequencyByPosition(int startPosition, int step, int[] cypher) {
        Map<Integer, Double> cryptoTextMap = new TreeMap<>();

        for (int i=0; i<256; i++) {
            cryptoTextMap.put(i, 0.);
        }
        for (int i = startPosition; i < cypher.length; i += step) {
            int currentCharacter = cypher[i];
            cryptoTextMap.put(currentCharacter, (cryptoTextMap.get(currentCharacter) + 1));
        }
        for (Map.Entry<Integer, Double> entry : cryptoTextMap.entrySet()) {
            cryptoTextMap.put(entry.getKey(), entry.getValue() / cypher.length/step);
        }
        double max = 0;
        int symbol= 0;
        for (Map.Entry<Integer, Double> entry : cryptoTextMap.entrySet()) {
            if(entry.getValue()>max){
                max = entry.getValue();
                symbol = entry.getKey();
            }

        }

//        System.out.println();
//////        System.out.println(cryptoTextMap);
//        for (Map.Entry<Integer, Double> entry : cryptoTextMap.entrySet()) {
//            System.out.println(entry);
//        }
        return symbol;
    }






    public static int findKeyLength(int[] cypher) throws Exception {
        frequencyArray = new double[cypher.length];
        int k = 0;
//подсчет частоты букв при сдвиге
        for (int shift = 1; shift < cypher.length; shift++) {
            for (int i = 0; i < cypher.length; i++) {
                if (i <= shift) {
                    if (cypher[i] == cypher[cypher.length - 1 - shift + i]) k++;
                } else {
                    if (i + shift >= cypher.length) {
                        frequencyArray[shift] = (double) k / cypher.length;
                        k = 0;
                        break;
                    }
                    if (cypher[i] == cypher[i - shift]) k++;
                }

            }
        }
        //масив с возможными длинами ключаей (размер приблизительный)
        int[] keyLength = new int[2 * frequencyArray.length / 3];
//        for (int i = 1; i < frequencyArray.length; i++)
//            System.out.print(frequencyArray[i] + " ");
        int prevFrequency, currentFrequency;
//        System.out.println();

        boolean flag = true;
        prevFrequency = 1;
        //переменная чтобы посчитать реальный размер массива
        int keyCounter = 0;
        //считаем пики и длину между ними, длину сохраняем в массив
        for (int i = 1; i < 2 * frequencyArray.length / 3; i++)
            if (frequencyArray[i - 1] < frequencyArray[i] && frequencyArray[i] > frequencyArray[i + 1] && frequencyArray[i] > 0.045) {
                if (flag) {
                    prevFrequency = i;
                    flag = false;
                } else {
                    flag = true;
                    currentFrequency = i;
                    keyLength[keyCounter] = currentFrequency - prevFrequency;
                    keyCounter++;
//                    System.out.print(currentFrequency - prevFrequency + " ");
                }
            }
        //тут сохраняем определенную ранее длину ключа и считаем количество повтороний этой длины
        Map<Integer, Integer> keyFrequency = new HashMap<>();
        for (int i = 0; i < keyCounter; i++)
            for (int j = 0; j < keyCounter; j++) {
                if (keyLength[j] == 0) continue;
                if (keyLength[i] == keyLength[j]) {

                    if (keyFrequency.get(keyLength[i]) == null)
                        keyFrequency.put(keyLength[i], 1);
                    else
                        keyFrequency.put(keyLength[i], keyFrequency.get(keyLength[i]) + 1);
                    keyLength[j] = 0;
                }

            }
//        for (Map.Entry<Integer, Integer> entry : keyFrequency.entrySet()) {
//            System.out.println(entry);
//        }
        int maxKeyFrequency = 0;
        int key = 0;
        //ищем значение ключа, которое повторилось максимально количество раз
        // в мапе длина ключа и кол-во повторений
        for (Map.Entry<Integer, Integer> entry : keyFrequency.entrySet()) {
            if (maxKeyFrequency < entry.getValue()) {
                maxKeyFrequency = entry.getValue();
                key = entry.getKey();
            }

        }
//        System.out.println("Key length = " + key);
        return key;
    }





    public static String readFromFile(File file) throws FileNotFoundException {
        StringBuilder str = new StringBuilder();
        Scanner output = new Scanner(file);
        while(output.hasNextLine())
            str.append(output.nextLine());
        hexCharArray = new String[str.length()/2];
        return str.toString();
    }

    public static void parseString(File file) throws FileNotFoundException{
        String cipherText = readFromFile(file);
        cipherText = cipherText.toLowerCase();
        for(int i=0;i<cipherText.length();i+=2){
            String hexString  = cipherText.charAt(i)+"" + cipherText.charAt(i+1);
            hexCharArray[i/2] = hexString;
        }

    }
}

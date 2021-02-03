package com.jrutz;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static final String FILE_PATH = "src/com/jrutz/BigNumber.txt";
    static final int DIGIT_LIMIT = 13;

    static int digit;
    static long currProduct = 1;
    static long maxProduct = 1;
    static List<Integer> digitList;
    static FileReader reader;

    public static void main(String[] args) throws Exception {

        reader = new FileReader(FILE_PATH);
        digitList = new LinkedList<>();
        load13DigitsIntoList();
        updateMax();

        while ((digit = reader.read()) != -1) {         // while unread digits remain
            if (isValidDigit()) {
                processDigit();
            }
        }

        System.out.println(maxProduct);
    }

    private static void load13DigitsIntoList() throws IOException {
        while (digitList.size() < DIGIT_LIMIT &&
                (digit = reader.read()) != -1) {
            if (isValidDigit()) {
                if (digit > 0) {
                    currProduct *= digit;
                    digitList.add(digit);
                }
                else {
                    digitList.clear();
                    currProduct = 1;
                    load13DigitsIntoList();
                }
            }
        }
    }

    private static boolean isValidDigit() {
        // get int value of digit ascii code
        digit = Character.getNumericValue((char) digit);
        return digit != -1;
    }

    private static void processDigit() throws IOException{
        if (digit > 0) {
            calculateMax();
            updateList();
        }
        else {
            digitList.clear();
            load13DigitsIntoList();
            updateMax();
        }
    }

    private static void updateMax() {
        maxProduct = currProduct > maxProduct ? currProduct : maxProduct;
    }

    private static void calculateMax() {
        currProduct = (currProduct / digitList.get(0)) * digit;
        updateMax();
    }

    private static void updateList() {
        digitList.remove(0);
        digitList.add(digit);
    }
}

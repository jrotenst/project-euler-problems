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

        loadDigitsIntoList();
        updateMax();    // save product of first full list to maxProduct

        processRemainingDigits();

        System.out.println(maxProduct);
    }

    private static void processRemainingDigits() throws IOException {
        while ((digit = reader.read()) != -1)
        {
            if (isValidDigit()) {
                processDigit();
            }
        }
    }

    private static void loadDigitsIntoList() throws IOException {
        while (digitList.size() < DIGIT_LIMIT &&
                (digit = reader.read()) != -1) {
            if (isValidDigit()) {
                if (digit > 0) {
                    currProduct *= digit;
                    digitList.add(digit);
                }
                else {
                    resetList();
                }
            }
        }
    }

    private static boolean isValidDigit() {
        // get int value of digit ascii code
        digit = Character.getNumericValue((char) digit);
        return digit != -1;
    }

    private static void resetList() throws IOException {
        digitList.clear();
        currProduct = 1;
        loadDigitsIntoList();
        updateMax();
    }

    private static void processDigit() throws IOException{
        if (digit > 0) {
            calculateMax();
            updateList();
        }
        else {
            resetList();
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

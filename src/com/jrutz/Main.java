package com.jrutz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        final String FILE_PATH = "src/com/jrutz/BigNumber.txt";

        int maxProduct = 0;


        try (FileReader reader = new FileReader(FILE_PATH)) {
            int digit;
            while ((digit = reader.read()) != -1) {
                if ((char) digit == '0') {
                    stack.clear();
                }
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}

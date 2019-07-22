package com.tigerit.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author Faisal Ahmed This is a helper class for input/output in java. Your
 * don't need to use it if you want. This is just for your convenience. Don't
 * use Scanner or System.console() stuff for input.
 */
public class IO {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private IO() {
    }

    public static String readLine() {
        String value;
        try {
            value = reader.readLine();
        } catch (IOException ex) {
            value = null;
        }
        return value;
    }

    public static ArrayList<Integer> readLineAsIntegerList() {
        ArrayList<Integer> integerList = new ArrayList<>();
        String value;
        try {
            value = reader.readLine();
            String[] word = value.split("\\s");
            for (String w : word) {
                integerList.add(Integer.parseInt(w));
            }
        } catch (IOException ex) {
            value = null;
        }
        return integerList;
    }

    public static Integer readLineAsInteger() {
        return Integer.parseInt(readLine());
    }

    public static void printLine(Object value) {
        System.out.println(value);
    }
}

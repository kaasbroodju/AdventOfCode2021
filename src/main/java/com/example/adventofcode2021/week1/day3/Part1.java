package com.example.adventofcode2021.week1.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.stream.IntStream;

public class Part1 {
    private static final int x = 1000;
    private static final int y = 12;


    public static void main(String[] args) throws IOException {
        System.out.println(old(new File("src/main/java/com/example/adventofcode2021/week1/day3/input.txt")));
        System.out.println(refactor(new File("src/main/java/com/example/adventofcode2021/week1/day3/input.txt")));
    }

    public static int old(File file) throws IOException {
        char[] input = Files.readString(file.toPath()).replaceAll("\n", "").toCharArray();
        boolean[] output = new boolean[y];

        for (int i = 0; i < y; i++) {
            int counter = 0;
            for (int j = i; j < x*y; j+=y) {
                if (input[j] == '1') {
                    counter++;
                }
            }
            output[i] = counter > x / 2;
        }

        return getDecimal(output) * getDecimalRev(output);
    }

    public static int refactor(File file) throws IOException {
        // sort
        char[] input = Files.readString(file.toPath()).replaceAll("\n", "").toCharArray();
        boolean[] output = new boolean[y];
        // todo add paralel
        IntStream.range(0, 12).forEach(i -> {
            int counter = 0;
            for (int j = i; j < x*y; j+=y) {
                if (input[j] == '1') {
                    counter++;
                }
            }
            output[i] = counter > x / 2;
        });


        return getDecimal(output) * getDecimalRev(output);
    }

    public static int refactorPlus(File file) throws IOException {
        // sort
        int[] input = new int[y];
        boolean[] output = new boolean[y];

        Files.readAllLines(file.toPath()).forEach(s -> {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '1') {
                    input[i]++;
                }
            }
        });

        for (int i = 0; i < input.length; i++) {
            output[i] = input[i] > x / 2;
        }

        return getDecimal(output) * getDecimalRev(output);
    }

    public static int getDecimal(boolean[] booleans) { // input should be boolean array
        // remove this
        int total = 0;
        for (int i = booleans.length - 1; i >= 0; i--) {
            if (booleans[i]) {
                total += Math.pow(2, booleans.length - 1 - i);
            }
        }
        return total;
    }

    public static int getDecimalRev(boolean[] booleans) {
        int total = 0;
        for (int i = 0; i < booleans.length; i++) {
            if (booleans[i]) {
                total += Math.pow(2, i);
            }
        }
        return total;
    }

    public static boolean[][] readFile(File file) {
        boolean[][] output = new boolean[x][y];
        try {
            int j = 0;
            Iterator<String> itr = Files.newBufferedReader(file.toPath()).lines().iterator();

            while (itr.hasNext()) {
                boolean[] row = new boolean[y];
                char[] bits = itr.next().toCharArray();
                for (int i = 0; i < bits.length; i++) {
                    row[i] = bits[i] == '1';
                }
                output[j] = row;
                j++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return output;
    }

    public static String toString(boolean[] booleans) {
        StringBuilder sb = new StringBuilder();
        for (boolean aBoolean : booleans) {
            sb.append(aBoolean ? '1' : '0');
        }
        return sb.toString();
    }
}

package com.example.adventofcode2021.week1.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Part1 {
    private static int x = 1000;
    private static int y = 12;


    public static void main(String[] args) {
        StringBuilder mostCommon = new StringBuilder();
        StringBuilder leastCommon = new StringBuilder();
        boolean[][] input = readFile(new File("src/main/java/com/example/adventofcode2021/week1/day3/input.txt"));
        System.out.println(Arrays.deepToString(input));
        for (int i = 0; i < y; i++) {
            int counter = 0;
            for (int j = 0; j < input.length; j++) {
                counter += input[j][i] ? 1 : 0;
            }
            System.out.println(counter);
            mostCommon.append(counter > x / 2 ? "1" : "0");
            leastCommon.append(counter < x / 2 ? "1" : "0");
        }

        int total = 0;
        for (int i = 0; i < mostCommon.toString().toCharArray().length; i++) {
            total += Math.pow(2, i);
        }
        System.out.println(getDecimal(mostCommon.toString()) * getDecimal(leastCommon.toString()));
    }

    public static int getDecimal(String s) {
        char[] a = s.toCharArray();
        int total = 0;
        int n = a.length;
        int i;
        char t;
        for (i = 0; i < n / 2; i++) {
            t = a[i];
            a[i] = a[n - i - 1];
            a[n - i - 1] = t;
        }

        for (int j = 0; j < a.length; j++) {
            if (a[j] == '1') {
                total += Math.pow(2, j);
            }
        }
        System.out.println(total);
        return total;
    }

    public static boolean[][] readFile(File file) {
        boolean[][] output = new boolean[x][y];
        try {
            Scanner myReader = new Scanner(file);
            int j = 0;
            while (myReader.hasNextLine()) {
                boolean[] row = new boolean[y];
                char[] bits = myReader.nextLine().toCharArray();
                for (int i = 0; i < bits.length; i++) {
                    row[i] = bits[i] == '1';
                }
                output[j] = row;
                j++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return output;
    }
}

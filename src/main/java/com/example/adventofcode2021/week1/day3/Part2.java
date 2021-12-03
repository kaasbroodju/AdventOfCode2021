package com.example.adventofcode2021.week1.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part2 {
    private static int x = 1000;
    private static int y = 12;


    public static void main(String[] args) {
        StringBuilder mostCommon = new StringBuilder();
        StringBuilder leastCommon = new StringBuilder();
        boolean[][] input = readFile(new File("src/main/java/com/example/adventofcode2021/week1/day3/input.txt"));
        System.out.println(Arrays.deepToString(input));
        List<boolean[]> cache = new ArrayList<>();

        for (int i = 0; i < x; i++) {
            cache.add(input[i]);
        }

        System.out.println(recursion(cache, 0, new StringBuilder()));
        System.out.println(temp(cache, 0, new StringBuilder()));


        System.out.println(getDecimal(recursion(cache, 0, new StringBuilder())) * getDecimal(temp(cache, 0, new StringBuilder())));
    }

    public static String recursion(List<boolean[]> list, int z, StringBuilder sb) {
        // determine most occured
        boolean mostoccured = true;
        int counter = 0;
        for (int i = 0; i < list.size(); i++) {
            counter += list.get(i)[z] ? 1 : 0;
        }
        mostoccured = counter >= Math.ceil((double) list.size() / (double) 2);
        sb.append(mostoccured ? '1' : '0');
        // remove all entries who dont have most occored on position
        boolean finalMostoccured = mostoccured;
        list = list.stream().filter(e -> e[z] == finalMostoccured).collect(Collectors.toList());

        if (list.size() <= 1) {
            return toString(list.get(0));
        }
        return recursion(list, z+1, sb);
    }

    public static String temp(List<boolean[]> list, int z, StringBuilder sb) {
        // determine most occured
        boolean mostoccured = true;
        int counter = 0;
        for (int i = 0; i < list.size(); i++) {
            counter += list.get(i)[z] ? 1 : 0;
        }
        mostoccured = counter < Math.ceil((double) list.size() / (double) 2);
        sb.append(mostoccured ? '1' : '0');
        // remove all entries who dont have most occored on position
        boolean finalMostoccured = mostoccured;
        list = list.stream().filter(e -> e[z] == finalMostoccured).collect(Collectors.toList());

        if (list.size() <= 1) {
            return toString(list.get(0));
        }
        return temp(list, z+1, sb);
    }

    public static String toString(boolean[] booleans) {
        StringBuilder sb = new StringBuilder();
        for (boolean aBoolean : booleans) {
            sb.append(aBoolean ? '1' : '0');
        }
        return sb.toString();
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

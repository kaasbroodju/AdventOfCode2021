package com.example.adventofcode2021.week1.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Part2 {
    private static final int x = 1000;
    private static final int y = 12;


    public static void main(String[] args) {
        System.out.println(old(new File("src/main/java/com/example/adventofcode2021/week1/day3/input.txt")));
        System.out.println(refactor(new File("src/main/java/com/example/adventofcode2021/week1/day3/input.txt")));
    }

    public static int old(File file) {
        boolean[][] input = readFile(file);
        List<boolean[]> cache = new ArrayList<>();

        for (int i = 0; i < x; i++) {
            cache.add(input[i]);
        }

        return getDecimal(recursion(cache, 0, new StringBuilder())) * getDecimal(temp(cache, 0, new StringBuilder()));
    }

    public static int refactor(File file) {
        boolean[][] input = readFile(file);
        List<boolean[]> cache = new ArrayList<>();

        for (int i = 0; i < x; i++) {
            cache.add(input[i]);
        }


        return getDecimal(getMostOccurred(cache, 0)) * getDecimal(getLeastOccurred(cache, 0));
    }

    public static boolean[] getMostOccurred(List<boolean[]> list, int z) {
        if (list.size() == 1) return list.get(0);
        long counter = list.stream().filter(b -> b[z]).count();

        boolean mostOccurred = counter >= Math.ceil((double) list.size() / (double) 2);

        return getMostOccurred(
                list.stream().filter(e -> e[z] == mostOccurred).collect(Collectors.toList()),
                z+1);
    }

    public static boolean[] getLeastOccurred(List<boolean[]> list, int z) {
        boolean mostOccurred;
        int counter = 0;
        for (int i = 0; i < list.size(); i++) {
            counter += list.get(i)[z] ? 1 : 0;
        }
        mostOccurred = counter < Math.ceil((double) list.size() / (double) 2);
        // remove all entries who dont have most occored on position
        boolean finalMostOccurred = mostOccurred;
        list = list.stream().filter(e -> e[z] == finalMostOccurred).collect(Collectors.toList());

        if (list.size() <= 1) {
            return list.get(0);
        }
        return getLeastOccurred(list, z+1);
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
        return total;
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

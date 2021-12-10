package com.example.adventofcode2021.week1.day7;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class Part1 {

    private static final int MAX_LIFE_CYCLE = 8;
    private static final int MAX_INCUBATION_TIME = 6;

    public static void main(String[] args) throws IOException {
        System.out.println(old(
                new File("src/main/java/com/example/adventofcode2021/week1/day7/input.txt"),
                80));

        System.out.println(recursion(0, 11));
    }

    public static int old(File file, int days) throws IOException {
        int[] input = Arrays.stream(Files.readString(file.toPath()).split(",")).mapToInt(Integer::parseInt).toArray();

        int max = Arrays.stream(input).max().getAsInt();
        int min = Arrays.stream(input).min().getAsInt();

        System.out.println(min + ":" + max);
        long fuelRequired;
        long lowest = Long.MAX_VALUE;
        int lowestIndex = -1;
        for (int i = min; i < max; i++) {
            System.out.println(i);
            fuelRequired = 0L;
            for (int number : input) {
                fuelRequired += recursion(0, Math.abs(number - i));
            }
            if (fuelRequired < lowest) {
                lowest = fuelRequired;
                lowestIndex = i;
            }
        }
        System.out.println(lowest);

        return lowestIndex;
    }

    public static int recursion(int amount, int n) {
        if (n == 0) return amount;
        return recursion(amount + n, n-1);
    }

}

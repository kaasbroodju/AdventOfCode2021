package com.example.adventofcode2021.week1.day6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Part1 {

    private static final int MAX_LIFE_CYCLE = 8;
    private static final int MAX_INCUBATION_TIME = 6;

    public static void main(String[] args) throws IOException {
        System.out.println(old(
                new File("src/main/java/com/example/adventofcode2021/week1/day6/input.txt"),
                80));
    }

    public static long old(File file, int days) throws IOException {
        String input = Files.readString(file.toPath());

        long[] groups = new long[MAX_LIFE_CYCLE+1];

        for (int i = 0; i < MAX_LIFE_CYCLE; i++) {
            int finalI = i;
            groups[i] = input.codePoints().filter(ch -> ch == 48 + finalI).count();
        }

        long swapValue;
        long tempValue;
        for (int i = 0; i < days; i++) {
            tempValue = groups[MAX_LIFE_CYCLE];
            for (int j = MAX_LIFE_CYCLE; j > 0; j--) {
                swapValue = tempValue;
                tempValue = groups[j-1];
                groups[j-1] = swapValue;
            }

            groups[MAX_INCUBATION_TIME] += tempValue;
            groups[MAX_LIFE_CYCLE] = tempValue;
        }

        return Arrays.stream(groups).sum();
    }

}

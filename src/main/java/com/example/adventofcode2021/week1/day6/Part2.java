package com.example.adventofcode2021.week1.day6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class Part2 {

    private static final int MAX_LIFE_CYCLE = 8;
    private static final int MAX_INCUBATION_TIME = 6;

    public static void main(String[] args) throws IOException {
        System.out.println(answer(
                new File("src/main/java/com/example/adventofcode2021/week1/day6/input.txt"),
                256));
    }

    public static long answer(File file, int days) throws IOException {
        long[] groups = new long[MAX_LIFE_CYCLE+1];

        Files.readString(file.toPath()).codePoints().forEach(ch ->  {
            int delta = ch - '0';
            if (delta >= 0) groups[delta]++;
        });

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

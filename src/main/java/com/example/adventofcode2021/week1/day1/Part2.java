package com.example.adventofcode2021.week1.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Stream;

public class Part2 {

    public static void main(String[] args) throws IOException {
        System.out.println(getAnswer(new File("src/main/java/com/example/adventofcode2021/week1/day1/input.txt")));
    }

    public static int getAnswer(File file) throws IOException {
        int counter = 0;
        Iterator<String > iterator = Files.newBufferedReader(file.toPath()).lines().iterator();
        int previousPreviousMeasurement = Integer.parseInt(iterator.next());
        int previousMeasurement = Integer.parseInt(iterator.next());
        int previousSum = Integer.MAX_VALUE;
        while (iterator.hasNext()) {
            int newMeasurement = Integer.parseInt(iterator.next());
            int newSum = previousPreviousMeasurement + previousMeasurement + newMeasurement;
            if (newSum > previousSum) {
                counter++;
            }
            previousPreviousMeasurement = previousMeasurement;
            previousMeasurement = newMeasurement;
            previousSum = newSum;
        }
        return counter;
    }
}

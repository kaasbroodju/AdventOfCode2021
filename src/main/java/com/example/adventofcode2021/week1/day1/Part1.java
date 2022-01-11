package com.example.adventofcode2021.week1.day1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;

public class Part1 {


    public static void main(String[] args) throws IOException {
        System.out.println(getAnswer(new File("src/main/java/com/example/adventofcode2021/week1/day1/input.txt")));
    }

    public static int getAnswer(File file) throws IOException {
        int counter = 0;
        int previousMeasurement = Integer.MAX_VALUE;
        Iterator<String > iterator = Files.newBufferedReader(file.toPath()).lines().iterator();
        while (iterator.hasNext()) {
            int measurement = Integer.parseInt(iterator.next());
            if (measurement > previousMeasurement) {
                counter++;
            }
            previousMeasurement = measurement;
        }
        return counter;
    }

}

package com.example.adventofcode2021.week1.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Part2 {
    private static int counter = 0;

    public static void main(String[] args) {
        int previousMeasurement = -1;
        int[] measurements = readFile(new File("src/main/java/com/example/adventofcode2021/week1/day1/input.txt"));
        for (int i = 0; i < measurements.length - 2; i++) {
            int measurement = measurements[i] + measurements[i+1] + measurements[i+2];
            if (measurement > previousMeasurement) {
                counter++;
            }
            previousMeasurement = measurement;
        }
        System.out.println(counter - 1);
    }

    public static int[] readFile(File file) {
        int[] outputArray;
        try (Stream<String> amount = Files.lines(file.toPath())) {
            outputArray = new int[(int) amount.count()];
        } catch (IOException ioException) {
            outputArray = new int[]{};
        }
        try {
            Scanner myReader = new Scanner(file);
            int i = 0;
            while (myReader.hasNextInt()) {
                outputArray[i] = myReader.nextInt();
                i++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
//        System.out.println(Arrays.toString(outputArray));

        return outputArray;
    }
}

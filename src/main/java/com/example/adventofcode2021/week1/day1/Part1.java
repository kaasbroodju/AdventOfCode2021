package com.example.adventofcode2021.week1.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part1 {
    private static int counter = 0;


    public static void main(String[] args) {
        int previousMeasurement = -1;
        for (Integer measurement : readFile(new File("src/main/java/com/example/adventofcode2021/week1/day1/input.txt"))) {
            if (measurement > previousMeasurement) {
                counter++;
            }
            previousMeasurement = measurement;
        }
        System.out.println(counter - 1);
    }

    public static List<Integer> readFile(File file) {
        List<Integer> output = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                output.add(Integer.valueOf(myReader.nextLine()));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return output;
    }
}

package com.example.adventofcode2021.week1.day2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Part1 {
    private static final int lines = 1000;
    private static int horizontalP;
    private static int depth;

    public static void main(String[] args) throws IOException {
        System.out.println(getAnswer(new File("src/main/java/com/example/adventofcode2021/week1/day2/input.txt")));
    }

    public static int getAnswer(File file) throws IOException {
        Files.newBufferedReader(file.toPath()).lines().forEach(
                s -> addPosition(s.charAt(0), Character.getNumericValue(s.charAt(s.length()-1)))
        );
        return horizontalP * depth;
    }

    public static void addPosition(char command, int amount) {
        switch (command) {
            case 'd' -> depth += amount;
            case 'u' -> depth -= amount;
            default -> horizontalP += amount;
        }
    }
}

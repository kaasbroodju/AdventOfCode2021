package com.example.adventofcode2021.week1.day2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Part1 {
    private static int horizontalP;
    private static int depth;

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(new File("src/main/java/com/example/adventofcode2021/week1/day2/input.txt").toPath());
        int result;
        long start = System.nanoTime();
        result = getAnswer(input);
        long end = System.nanoTime();
        System.out.println(result);
        System.out.println(((end - start) / 1000000.0) + "ms");
    }

    public static int getAnswer(List<String> input) throws IOException {
        input.forEach(s -> addPosition(s.charAt(0), s.charAt(s.length() - 1) - '0'));
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

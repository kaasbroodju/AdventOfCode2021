package com.example.adventofcode2021.week1.day6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Part1 {

    public static void main(String[] args) throws IOException {
        System.out.println(old(new File("src/main/java/com/example/adventofcode2021/week1/day6/input.txt")));
    }

    public static int old(File file) throws IOException {
        List<Integer> input = Arrays.stream(Files.readString(file.toPath()).split(",")).map(Integer::parseInt).collect(Collectors.toList());
        System.out.println(input);
        for (int i = 0; i < 80; i++) {
            for (int j = 0; j < input.size(); j++) {
                int internalClock = input.get(j);
                if (internalClock == 0) {
                    input.set(j, 6);
                    input.add(9);
                } else {
                    input.set(j, internalClock - 1);
                }
            }
            System.out.println(input);

        }
        return input.size();
    }

}

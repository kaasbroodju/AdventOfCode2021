package com.example.adventofcode2021.week1.day6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Part2 {

    public static void main(String[] args) throws IOException {
        System.out.println(old(new File("src/main/java/com/example/adventofcode2021/week1/day6/input.txt")));
    }

    public static long old(File file) throws IOException {
        List<Long> input = Arrays.stream(Files.readString(file.toPath()).split(",")).map(Long::parseLong).collect(Collectors.toList());

        // group them
        Map<Integer, Long> groups = new HashMap<>();
        groups.put(0, 0L);
        groups.put(1, 0L);
        groups.put(2, 0L);
        groups.put(3, 0L);
        groups.put(4, 0L);
        groups.put(5, 0L);
        groups.put(6, 0L);
        groups.put(7, 0L);
        groups.put(8, 0L);
        for (Long integer : input) {
            groups.put(Integer.parseInt(String.valueOf(integer)), groups.get(Integer.parseInt(String.valueOf(integer))) + 1);
        }
        long swapValue;
        long tempValue;
        for (int i = 0; i < 256; i++) {
            swapValue = groups.get(8);
            tempValue = groups.get(7);
            groups.put(7, swapValue);
            swapValue = tempValue;
            tempValue = groups.get(6);
            groups.put(6, swapValue);
            swapValue = tempValue;
            tempValue = groups.get(5);
            groups.put(5, swapValue);
            swapValue = tempValue;
            tempValue = groups.get(4);
            groups.put(4, swapValue);
            swapValue = tempValue;
            tempValue = groups.get(3);
            groups.put(3, swapValue);
            swapValue = tempValue;
            tempValue = groups.get(2);
            groups.put(2, swapValue);
            swapValue = tempValue;
            tempValue = groups.get(1);
            groups.put(1, swapValue);
            swapValue = tempValue;
            tempValue = groups.get(0);
            groups.put(0, swapValue);

            // end
            groups.put(6, groups.get(6) + tempValue);
            groups.put(8, tempValue);
        }

        long tempOut = 0;
        for (Integer integer : groups.keySet()) {
            tempOut+= groups.get(integer);
        }

        return tempOut;
    }

}

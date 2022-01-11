package com.example.adventofcode2021.week1.day5;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Part1 {

    public static void main(String[] args) throws IOException {
        System.out.println(old(new File("src/main/java/com/example/adventofcode2021/week1/day5/input.txt")));
    }

    public static int old(File file) throws IOException {
        int[][] grid = new int[1000][1000];
        for (String s : Files.readAllLines(file.toPath())) {

            String[] split = s.split(" -> ");
            String[] a = split[0].split(",");
            String[] b = split[1].split(",");

            int x1 = Integer.parseInt(a[0]);
            int y1 = Integer.parseInt(a[1]);
            int x2 = Integer.parseInt(b[0]);
            int y2 = Integer.parseInt(b[1]);

            int length;

            // todo convert to 2 for loop cuz one of the i is always 1
            if (x1 == x2) {
                length = y2 - y1;
                grid[x1][y1]++;
                grid[x2][y2]++;
                if (length > 0) {
                    for (int j = 1; j < length; j++) {
                        grid[x1][y1 + j]++;
                    }
                } else {
                    length *= -1;
                    for (int j = 1; j < length; j++) {
                        grid[x1][y1 - j]++;
                    }
                }
            } else if (y1 == y2) {
                length = x2 - x1;
                grid[x1][y1]++;
                grid[x2][y2]++;
                if (length > 0) {
                    for (int j = 1; j < length; j++) {
                        grid[x1 + j][y1]++;
                    }
                } else {
                    length *= -1;
                    for (int j = 1; j < length; j++) {
                        grid[x1 - j][y1]++;
                    }
                }
            }
        }

        int counter = 0;

        for (int[] ints : grid) {
            for (int anInt : ints) {
                if (anInt >= 2) counter++;
            }
        }

        return counter;
    }

}

package com.example.adventofcode2021.week1.day5;

import com.example.adventofcode2021.week1.day4.BingoCard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Part1 {


    public static void main(String[] args) throws IOException {
        System.out.println(old(new File("src/main/java/com/example/adventofcode2021/week1/day5/input.txt")));
    }

    public static int old(File file) throws IOException {

        System.out.println(Arrays.toString(new Line(new Cords(6, 4), new Cords(2, 0)).getPoints()));
        System.out.println(Arrays.toString(new Line(new Cords(8, 2), new Cords(5, 5)).getPoints()));

        Line[] lines = new Line[500];
        int[][] grid = new int[1000][1000];
        List<String> temp = Files.readAllLines(file.toPath());
        for (int i = 0; i < temp.size(); i++) {
            String[] split = temp.get(i).split(" -> ");
            String[] a = split[0].split(",");
            String[] b = split[1].split(",");
            lines[i] = new Line(new Cords(Integer.parseInt(a[0]), Integer.parseInt(a[1])), new Cords(Integer.parseInt(b[0]), Integer.parseInt(b[1])));

        }

        for (Line line : lines) {
            for (Cords cord : line.getPoints()) {
                grid[cord.x()][cord.y()]++;
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

package com.example.adventofcode2021.week2.day9;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Stream;

public class Part2 {

private static final int width = 100;
private static final int heigth = 100;

    public static void main(String[] args) throws IOException {
        System.out.println(
                old(
                        new File("src/main/java/com/example/adventofcode2021/week2/day9/input.txt")
                )
        );

    }

    public static int old(File file) throws IOException {
        int[][] field = new int[heigth][width];
        List<String> rows = Files.readAllLines(file.toPath());

        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < rows.get(i).split("").length; j++) {
                field[i][j] = Integer.parseInt(rows.get(i).split("")[j]);
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                int finalI = i;
                int finalJ = j;
                if (Stream.of(
                        inBounds(i - 1, j - 1) ? field[i-1][j-1] : null,
                        inBounds(i - 1, j) ? field[i-1][j] : null,
                        inBounds(i - 1, j + 1) ? field[i-1][j+1] : null,
                        inBounds(i, j - 1) ? field[i][j-1] : null,
                        inBounds(i, j + 1) ? field[i][j+1] : null,
                        inBounds(i + 1, j - 1) ? field[i+1][j-1] : null,
                        inBounds(i + 1, j) ? field[i+1][j] : null,
                        inBounds(i + 1, j + 1) ? field[i+1][j+1] : null
                ).filter(Objects::nonNull).allMatch(neighbour -> neighbour > field[finalI][finalJ])) result.add(getBasinSize(field, i, j));
            }
        }

        Collections.sort(result);
        return result.subList(result.size() - 3, result.size()).stream().reduce(1, (acc, i) -> {
            acc *= i;
            return acc;
        });
    }

    public static int getBasinSize(int[][] field, int y, int x)  {
        Set<Cords> output = new HashSet<>();
        getBasinSize(field, y, x, output);
        return output.size();
    }

    public static void getBasinSize(int[][] field, int y, int x, Set<Cords> cords)  {
        if(!inBounds(y, x) || (field[y][x] == 9 || cords.contains(new Cords(x, y)))) return;
        cords.add(new Cords(x, y));
        getBasinSize(field, y-1, x, cords);
        getBasinSize(field, y, x-1, cords);
        getBasinSize(field, y, x+1, cords);
        getBasinSize(field, y+1, x, cords);
    }

    public static boolean inBounds(int y, int x) {
        return y >= 0 &&
                x >= 0 &&
                y < heigth &&
                x < width
        ;
    }

}

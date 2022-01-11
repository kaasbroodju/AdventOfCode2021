package com.example.adventofcode2021.week2.day9;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Part1 {

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

        int result = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
//                System.out.print(field[i][j]);
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
                ).filter(Objects::nonNull).allMatch(neighbour -> neighbour > field[finalI][finalJ])) result+= field[i][j]+1;
            }
            System.out.println();
        }


        return result;
    }

    public static boolean inBounds(int y, int x) {
        return y >= 0 &&
                x >= 0 &&
                y < heigth &&
                x < width;
    }

}

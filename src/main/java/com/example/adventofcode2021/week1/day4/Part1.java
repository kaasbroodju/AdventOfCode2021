package com.example.adventofcode2021.week1.day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.stream.Stream;

public class Part1 {


    public static void main(String[] args) throws IOException {
        System.out.println(old(new File("src/main/java/com/example/adventofcode2021/week1/day4/input.txt")));
    }

    private static int[] numbers;
    private static int[][][] cards;

    public static int old(File file) throws IOException {
        BufferedReader br = Files.newBufferedReader(file.toPath());

        numbers = Arrays.stream(br.readLine().split(",")).mapToInt(Integer::parseInt).toArray();

        cards = new int[100][5][5];
        for (int i = 0; i < cards.length; i++) {
            br.readLine();
            for (int y = 0; y < cards[i].length; y++) {
                cards[i][y] = Arrays.stream(br.readLine().split(" ")).filter(s -> !s.equals("")).mapToInt(Integer::parseInt).toArray();
            }
        }


        for (int number : numbers) {
            for (int[][] card : cards) {
                if (mark(card, number)) {
                    return number * getRemaining(card);
                }
            }
        }

        return 0;
    }

    public static boolean mark(int[][] card, int number) {
        for (int i = 0; i < card.length; i++) {
            for (int j = 0; j < card[i].length; j++) {
                if(card[i][j] == number) {
                    card[i][j] = -1;
                    return bingo(card, i ,j);
                }
            }
        }
        return false;
    }

    private static boolean bingo(int[][] card, int y, int x) {
        return Arrays.stream(card[y]).allMatch(n -> n == -1) || Stream.of(card[0][x], card[1][x], card[2][x], card[3][x], card[4][x]).allMatch(n -> n == -1);
    }

    public static int getRemaining(int[][] card) {
        return Arrays.stream(card).flatMapToInt(Arrays::stream).filter(i -> i > -1).sum();
    }
}

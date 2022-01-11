package com.example.adventofcode2021.week1.day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Part2 {

    private static final int totalcards = 100;

    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();
        System.out.println(refactor(new File("src/main/java/com/example/adventofcode2021/week1/day4/input.txt")));
        long end = System.nanoTime();
        System.out.println((end - start) / 1000000.0);
        System.out.println(refactor(new File("src/main/java/com/example/adventofcode2021/week1/day4/input.txt")));

    }

    public static int old(File file) throws IOException {
        BufferedReader br = Files.newBufferedReader(file.toPath());

        int[] numbers = Arrays.stream(br.readLine().split(",")).mapToInt(Integer::parseInt).toArray();

        int[][][] cards = new int[100][5][5];
        for (int i = 0; i < totalcards; i++) {
            br.readLine();
            int[][] card = new int[5][5];
            for (int y = 0; y < card.length; y++) {
                cards[i][y] = Arrays.stream(br.readLine().split(" ")).filter(s -> !s.equals("")).mapToInt(Integer::parseInt).toArray();
            }
        }

        int cardsthatgotbingo = 0;
        for (int number : numbers) {
            for (int i = 0; i < cards.length; i++) {
                if (cards[i] == null) continue;
                if (mark(cards[i], number)) {

                    cardsthatgotbingo++;
                    if (cardsthatgotbingo == totalcards) {
                        return number * getRemaining(cards[i]);
                    }
                    cards[i] = null;
                }
            }

        }

        return 0;
    }

    public static int refactor(File file) throws IOException {
        BufferedReader br = Files.newBufferedReader(file.toPath());

        int[] numbers = Arrays.stream(br.readLine().split(",")).mapToInt(Integer::parseInt).toArray();

        List<int[][]> cards = new ArrayList<>();
        for (int i = 0; i < totalcards; i++) {
            br.readLine();
            int[][] card = new int[5][5];
            for (int y = 0; y < card.length; y++) {
                card[y] = Arrays.stream(br.readLine().split(" ")).filter(s -> !s.equals("")).mapToInt(Integer::parseInt).toArray();
            }
            cards.add(card);
        }

        int cardsthatgotbingo = cards.size();
        for (int number : numbers) {
            for (int i = 0; i < cards.size(); i++) {
                int[][] card = cards.get(i);
                if (mark(card, number)) {
                    cardsthatgotbingo--;
                    if (cardsthatgotbingo == 0) {
                        return number * getRemaining(card);
                    }
                    cards.remove(i);
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

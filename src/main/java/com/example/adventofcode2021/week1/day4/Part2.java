package com.example.adventofcode2021.week1.day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class Part2 {

    private static int totalcards = 100;

    public static void main(String[] args) throws IOException {
        System.out.println(old(new File("src/main/java/com/example/adventofcode2021/week1/day4/input.txt")));

    }

    public static int old(File file) throws IOException {
        int cardsthatgotbingo = 0;
        int[] drawnNumbers = ints(file);
        BingoCard[] cards = getCards(file);
//        System.out.println(Arrays.toString(drawnNumbers));
        for (int drawnNumber : drawnNumbers) {
            System.out.println(drawnNumber);
            for (int i = 0; i < cards.length; i++) {
                if (cards[i] == null) continue;
                if (cards[i].mark(drawnNumber)) {

                    cardsthatgotbingo++;
                    if (cardsthatgotbingo == totalcards) {
                        System.out.println(drawnNumber + " * " + cards[i].getRemaining());
                        return drawnNumber * cards[i].getRemaining();
                    }
                    cards[i] = null;
                }
            }

        }

        return 0;
    }

    public static int[] ints(File file) throws IOException {
        return Arrays.stream(Files.readAllLines(file.toPath()).get(
                0
        ).split(",")).mapToInt(Integer::parseInt).toArray();
    }

    public static BingoCard[] getCards(File file) throws IOException {
        BingoCard[] cards = new BingoCard[totalcards];
        try {
            Scanner myReader = new Scanner(file);
            int totalI = 0;
            myReader.nextLine();
            String line;
            int[][] cardArray = new int[5][5];
            while (myReader.hasNextLine()) {
                myReader.nextLine();
                for (int z = 0; z < 5; z++) {
                    line = myReader.nextLine();
                    int[] row = new int[5];
                    List<String> numbers = Arrays.stream(line.split(" ")).filter(s -> !Objects.equals(s, "")).collect(Collectors.toList());
                    for (int i = 0; i < numbers.size(); i++) {
                        row[i] = Integer.parseInt(numbers.get(i));
                    }
                    cardArray[z] = row;
                }
                cards[totalI] = new BingoCard(cardArray);
                totalI++;
                cardArray = new int[5][5];
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
//        System.out.println(Arrays.deepToString(cardArray));

        return cards;
    }
}

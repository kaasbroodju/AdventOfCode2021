package com.example.adventofcode2021.week1.day4;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BingoCard {

    private int[][] card;

    public BingoCard(int[][] numbers) {
        this.card = numbers;
    }
    // returns boolean if bingo
    public boolean mark(int number) {
        for (int i = 0; i < card.length; i++) {
            for (int j = 0; j < card[i].length; j++) {
                if(card[i][j] == number) {
                    card[i][j] = -1;
                    return bingo();
                }
            }
        }
        return false;
    }

    private boolean bingo() {
        System.out.println(this);

        for (int i = 0; i < this.card.length; i++) {
            if (Arrays.stream(this.card[i]).allMatch(x -> x == -1)) return true;
            if (Stream.of(this.card[0][i], this.card[1][i], this.card[2][i], this.card[3][i], this.card[4][i]).allMatch(x -> x == -1)) return true;
        }


//        for (int i = 0; i < row; i++) {
////            System.out.println((i*5) + " " + ((i * 5) + 5));
//            if (values.subList(i*5, (i * 5) + 5).stream().allMatch(e -> e)) {
////                System.out.println(this);
////                System.out.println(i*5 + " " + (i * 5) + 5);
//                return true;
//            }
//            if (Stream.of(values.get(i), values.get(i + 5), values.get(i+10), values.get(i+15), values.get(i+20)).allMatch(e -> e)) {
////                System.out.println(this);
////                System.out.println("vertical");
//                return true;
//            }
//        }
        return false;
    }

    public int getRemaining() {
        return Arrays.stream(this.card).flatMapToInt(Arrays::stream).filter(i -> i > -1).sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.card.length; i++) {
            sb.append("\n");
            for (int j = 0; j < this.card[i].length; j++) {
                sb.append(this.card[i][j]);
            }
        }
        return sb.toString();
    }
}

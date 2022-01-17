package com.example.adventofcode2021.week1.day4;

import com.example.adventofcode2021.Day;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.stream.Stream;

public class DayFour extends Day<DayFour.Draw, Integer> {

	public static void main(String[] args) throws IOException {
		DayFour result = new DayFour();
		Draw input = result.readInput(new File("src/main/java/com/example/adventofcode2021/week1/day4/input.txt"));
		System.out.println(result.partOne(input));
		System.out.println(result.partTwo(input));
	}
	
	@Override
	public Draw readInput(File file) throws IOException {
		int[] numbers;
		BufferedReader br = Files.newBufferedReader(file.toPath());

		numbers = Arrays.stream(br.readLine().split(",")).mapToInt(Integer::parseInt).toArray();

		int[][][] cards = new int[100][5][5];
		for (int i = 0; i < cards.length; i++) {
			br.readLine();
			for (int y = 0; y < cards[i].length; y++) {
				cards[i][y] = Arrays.stream(br.readLine().split(" ")).filter(s -> !s.equals("")).mapToInt(Integer::parseInt).toArray();
			}
		}
		return new Draw(numbers, cards);
	}

	@Override
	public Integer partOne(Draw input) {
		for (int number : input.numbers) {
			for (int[][] card : input.cards) {
				if (mark(card, number)) {
					return number * getRemaining(card);
				}
			}
		}
		return null;
	}

	@Override
	public Integer partTwo(Draw input) {
		int cardsthatgotbingo = input.cards.length;
		boolean[] bingoList = new boolean[cardsthatgotbingo];
		for (int number : input.numbers) {
			for (int i = 0; i < input.cards.length; i++) {
				if (bingoList[i]) continue;
				int[][] card = input.cards[i];
				if (mark(card, number)) {
					cardsthatgotbingo--;
					if (cardsthatgotbingo == 0) {
						return number * getRemaining(card);
					}
					bingoList[i] = true;
//					cards.remove(i);
				}
			}
		}
		return null;
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

	public record Draw(int[] numbers, int[][][] cards){}
}

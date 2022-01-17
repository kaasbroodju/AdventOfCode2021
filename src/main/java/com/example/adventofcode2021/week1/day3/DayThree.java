package com.example.adventofcode2021.week1.day3;

import com.example.adventofcode2021.Day;
import jdk.incubator.vector.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.IntConsumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DayThree extends Day<boolean[], Integer> {

	public static void main(String[] args) throws IOException {
		DayThree result = new DayThree();
		boolean[] input = result.readInput(new File("src/main/java/com/example/adventofcode2021/week1/day3/input.txt"));
		System.out.println(result.partOne(input));
//		new DayThree().main(new File("src/main/java/com/example/adventofcode2021/week1/day1/input.txt"));
//		this.main(new File("src/main/java/com/example/adventofcode2021/week1/day1/input.txt"));
	}

	private static final VectorSpecies<Short> SPECIES = ShortVector.SPECIES_256;
	private static final VectorSpecies<Integer> INTEGER_VECTOR_SPECIES = IntVector.SPECIES_256;


	private static final int x = 1000;
	private static final int y = 12;

	@Override
	public boolean[] readInput(File file) throws IOException {
		char[] input = Files.readString(file.toPath()).replaceAll("\n", "").toCharArray();
		boolean[] output = new boolean[y*x];

		for (int i = 0; i < input.length; i++) {
			output[i] = input[i] == '1';
		}

		return output;
	}

	@Override
	public Integer partOne(boolean[] input) {
		int i = 0;

		Vector<Short> acc = SPECIES.zero();
		Vector<Short> ones = SPECIES.broadcast(1);
		for (; i < input.length-y; i+=y) {
			acc = acc.add(ones, SPECIES.loadMask(input, i));
		}
		int[] temp = acc.toIntArray();

		for (; i < input.length; i++) {
			if(input[i]) temp[i%y]++;
		}

		boolean[] results = new boolean[16];

		IntVector.fromArray(INTEGER_VECTOR_SPECIES, temp, 0).compare(VectorOperators.GT, INTEGER_VECTOR_SPECIES.broadcast(500)).intoArray(results, 0);
		IntVector.fromArray(INTEGER_VECTOR_SPECIES, temp, 8).compare(VectorOperators.GT, INTEGER_VECTOR_SPECIES.broadcast(500)).intoArray(results, 8);

		return getDecimal(results) * getDecimalRev(results);
	}

	@Override
	public Integer partTwo(boolean[] input) {
//		char[] inputTest = Files.readString(file.toPath()).replaceAll("\n", "").toCharArray();
		boolean[] output = new boolean[y];
		// todo add paralel
		IntStream.range(0, 12).forEach(i -> {
			int counter = 0;
			for (int j = i; j < x*y; j+=y) {
				if (input[j]) {
					counter++;
				}
			}
			output[i] = counter > x / 2;
		});

		return getDecimal(output) * getDecimalRev(output);
	}

	public static int getDecimal(boolean[] booleans) { // input should be boolean array
		int n = 0;
		for (int i = 0; i < 12; ++i) {
			n = (n << 1) + (booleans[i] ? 1 : 0);
		}
		return n;
	}

	public static int getDecimalRev(boolean[] booleans) {
		int n = 0;
		for (int i = 0; i < 12; ++i) {
			n = (n << 1) + (booleans[i] ? 0 : 1);
		}
		return n;
	}
}

package com.example.adventofcode2021.week1.day1;

import com.example.adventofcode2021.Day;
import jdk.incubator.vector.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DayOne extends Day<short[], Integer> {

	// todo to shortVector
	private static final VectorSpecies<Short> SPECIES = ShortVector.SPECIES_PREFERRED;
	private static final int CHUNK_SIZE = SPECIES.length();

	public static void main(String[] args) throws IOException {
		DayOne result = new DayOne();
		short[] input = result.readInput(new File("src/main/java/com/example/adventofcode2021/week1/day1/input.txt"));
		System.out.println(result.partOne(input));
		System.out.println(result.partTwo(input));
	}

	@Override
	public short[] readInput(File file) throws IOException {
		short[] output = new short[2000];
		Scanner scanner = new Scanner(file.toPath());
		for (int i = 0; i < output.length; i++) {
			output[i] = scanner.nextShort();
		}
		return output;

	}

	@Override
	public Integer partOne(short[] input) {
		return internals(input, 1);
	}

	@Override
	public Integer partTwo(short[] input) {
		return internals(input, 3);
	}

	private int internals(short[] input, int offset) {
		int results = 0;

		int loopBound = SPECIES.loopBound(input.length-offset);
		int  i = 0;
		for (; i < loopBound; i+=CHUNK_SIZE) {
			/*
			[100, 101, 105, 106, 103, 104, 106, 108]
			[106, 103, 104, 106, 108, 112, 123, 125]
			<
			Mask[TT..TTTT]
			results += 6
			 */
			results += ShortVector.fromArray(SPECIES, input,  i).compare(VectorOperators.LT, ShortVector.fromArray(SPECIES, input, i + offset)).trueCount();
		}

		for (; i < input.length-offset; i++) {
			if (input[i] < input[i+offset]) results++;
		}


		return results;
	}
}

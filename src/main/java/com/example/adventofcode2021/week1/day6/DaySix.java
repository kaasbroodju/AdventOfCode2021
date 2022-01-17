package com.example.adventofcode2021.week1.day6;

import com.example.adventofcode2021.Day;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class DaySix extends Day<long[], Long> {

	public static void main(String[] args) throws IOException {
		DaySix result = new DaySix();
		long[] input = result.readInput(new File("src/main/java/com/example/adventofcode2021/week1/day6/input.txt"));
		System.out.println(result.partOne(input));
		System.out.println(result.partTwo(input));
	}

	private static final int MAX_LIFE_CYCLE = 8;
	private static final int MAX_INCUBATION_TIME = 6;

	@Override
	public long[] readInput(File file) throws IOException {
		long[] groups = new long[MAX_LIFE_CYCLE+1];

		Files.readString(file.toPath()).codePoints().forEach(ch ->  {
			int delta = ch - '0';
			if (delta >= 0) groups[delta]++;
		});

		return groups;
	}

	@Override
	public Long partOne(long[] input) {
		return internals(input, 80);
	}

	@Override
	public Long partTwo(long[] input) {
		return internals(input, 256);
	}

	private long internals(long[] groups, int days) {
		long swapValue;
		long tempValue;
		for (int i = 0; i < days; i++) {
			tempValue = groups[MAX_LIFE_CYCLE];
			for (int j = MAX_LIFE_CYCLE; j > 0; j--) {
				swapValue = tempValue;
				tempValue = groups[j-1];
				groups[j-1] = swapValue;
			}

			groups[MAX_INCUBATION_TIME] += tempValue;
			groups[MAX_LIFE_CYCLE] = tempValue;
		}

		return Arrays.stream(groups).sum();
	}
}

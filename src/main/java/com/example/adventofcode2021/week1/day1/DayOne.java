package com.example.adventofcode2021.week1.day1;

import com.example.adventofcode2021.Day;
import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.Vector;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorSpecies;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DayOne extends Day<int[], Integer> {

	private static final VectorSpecies<Integer> SPECIES = IntVector.SPECIES_PREFERRED;
	private static final int CHUNK_SIZE = SPECIES.length();

	public static void main(String[] args) throws IOException {
		new DayOne().main(new File("src/main/java/com/example/adventofcode2021/week1/day1/input.txt"));
//		this.main(new File("src/main/java/com/example/adventofcode2021/week1/day1/input.txt"));
	}

	@Override
	public int[] readInput(File file) throws IOException {
		return Files.newBufferedReader(file.toPath()).lines().mapToInt(Integer::parseInt).toArray();

	}

	@Override
	public Integer partOne(int[] input) {
		return internalsRef(input, 3);
	}

	@Override
	public Integer partTwo(int[] input) {
		return internals(input, 3);
	}

	private int internals(int[] input, int offset) {
		boolean[] results = new boolean[input.length];
		int loopBound = SPECIES.loopBound(input.length-offset);
		int  i = 0;
		for (; i < loopBound; i+=CHUNK_SIZE) {
			IntVector.fromArray(SPECIES, input,  i).compare(VectorOperators.LT, IntVector.fromArray(SPECIES, input, i + offset)).intoArray(results, i);
		}

		for (; i < input.length-offset; i++) {
			if (input[i] < input[i+offset]) results[i] = true;
		}

		int counter = 0;
		for (boolean result : results) {
			if (result) counter++;
		}

		return counter;
	}

	private int internalsRef(int[] input, int offset) {
		Vector<Integer> results = SPECIES.zero();
		Vector<Integer> ones = SPECIES.broadcast(1);


		int loopBound = SPECIES.loopBound(input.length-offset);
		int  i = 0;
		for (; i < loopBound; i+=CHUNK_SIZE) {
			results = results.add(ones, IntVector.fromArray(SPECIES, input,  i).compare(VectorOperators.LT, IntVector.fromArray(SPECIES, input, i + offset)));
		}

		int counter = 0;
		for (; i < input.length-offset; i++) {
			if (input[i] < input[i+offset]) counter++;
		}

		return (int) (results.reduceLanesToLong(VectorOperators.ADD) + counter);
	}
}

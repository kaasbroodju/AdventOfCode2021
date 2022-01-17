package com.example.adventofcode2021.week1.day7;

import com.example.adventofcode2021.Day;
import com.example.adventofcode2021.week1.day1.DayOne;
import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.Vector;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorSpecies;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class DaySeven extends Day<int[], Long> {

	public static void main(String[] args) throws IOException {
		DaySeven result = new DaySeven();
		int[] input = result.readInput(new File("src/main/java/com/example/adventofcode2021/week1/day7/input.txt"));
		System.out.println(result.partOne(input));
		System.out.println(result.partTwo(input));
	}

	@Override
	public int[] readInput(File file) throws IOException {
		return Arrays.stream(Files.readString(file.toPath()).split(",")).mapToInt(Integer::parseInt).toArray();
	}

	@Override
	public Long partOne(int[] input) {
		Arrays.sort(input);
		IntVector[] vectors = new IntVector[input.length/8];
		for (int i = 0; i < input.length / 8; i++) {
			vectors[i] = IntVector.fromArray(SPECIES, input, i*8);
		}

		long fuelRequired;
		long lowest = Long.MAX_VALUE;
		Vector<Integer> sumLanes;
		for (int i = 0; i < input[input.length-1] - input[0]; i++) {
			sumLanes = SPECIES.zero();
			for (IntVector vector : vectors) {
				sumLanes = sumLanes.add(vector.sub(i).abs());
			}
			fuelRequired = sumLanes.reduceLanesToLong(VectorOperators.ADD);
			if (fuelRequired < lowest) {
				lowest = fuelRequired;
			} else {
				break;
			}
		}

		return lowest;
	}

	private static final VectorSpecies<Integer> SPECIES = IntVector.SPECIES_PREFERRED;

	@Override
	public Long partTwo(int[] input) {
		Arrays.sort(input);
		IntVector[] vectors = new IntVector[input.length/8];
		for (int i = 0; i < input.length / 8; i++) {
			vectors[i] = IntVector.fromArray(SPECIES, input, i*8);
		}

		long fuelRequired;
		long lowest = Long.MAX_VALUE;
		Vector<Integer> sumLanes;
		for (int i = 0; i < input[input.length-1] - input[0]; i++) {
			sumLanes = SPECIES.zero();
			for (IntVector vector : vectors) {
				sumLanes = sumLanes.add(formulaSIMD(vector.sub(i).abs()));
			}
			fuelRequired = sumLanes.reduceLanesToLong(VectorOperators.ADD);
			if (fuelRequired < lowest) {
				lowest = fuelRequired;
			} else {
				break;
			}
		}

		return lowest;
	}

	public static IntVector formulaSIMD(IntVector vector) {
		return vector.mul(vector.add(1)).div(2);
	}
}

package com.example.adventofcode2021.week1.day2;

import com.example.adventofcode2021.Day;
import com.example.adventofcode2021.week1.day1.DayOne;
import jdk.incubator.vector.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class DayTwo extends Day<Day2Data, Integer> {

	private static final VectorSpecies<Byte> SPECIES = ByteVector.SPECIES_PREFERRED;

	public static void main(String[] args) throws IOException {
		DayTwo result = new DayTwo();
		Day2Data input = result.readInput(new File("src/main/java/com/example/adventofcode2021/week1/day1/input.txt"));
		System.out.println(result.partOne(input));
		System.out.println(result.partTwo(input));

	}
	@Override
	public Day2Data readInput(File file) throws IOException {
		List<String> input = Files.readAllLines(new File("src/main/java/com/example/adventofcode2021/week1/day2/input.txt").toPath());
		byte[] measurements = new byte[input.size()];
		byte[] instructions = new byte[input.size()];
		int l = input.size();
		String s;
		for (int j = 0; j < l; j++) {
			s = input.get(j);
			instructions[j] = (byte) s.charAt(0);
			measurements[j] = (byte) (s.charAt(s.length() - 1) - '0');
		}

		return new Day2Data(measurements, instructions);
	}

	@Override
	public Integer partOne(Day2Data input) {
		return partOne(input.measurements(), input.instructions());
	}

	@Override
	public Integer partTwo(Day2Data input) {
		return partTwo(input.measurements(), input.instructions());
	}

	public Integer partOne(byte[] measurements, byte[] instructions) {
		Vector<Byte> accHorizontalP = SPECIES.zero();
		Vector<Byte> accDepth = SPECIES.zero();
		Vector<Byte> measurementVector;
		Vector<Byte> instructionVector;
		VectorMask<Byte> upMask;
		VectorMask<Byte> downMask;
		long depth = 0;
		long horizontalP = 0;
		int loopBound = SPECIES.loopBound(measurements.length);
		int chunkSize = SPECIES.length();
		int i = 0;
		for (int j = 1; j <= loopBound / chunkSize / 14; j++) { // byte accumulator overflow prevention
			for (; i < j * chunkSize * 14; i+= chunkSize) {
				measurementVector = SPECIES.fromArray(measurements, i);
				instructionVector = SPECIES.fromArray(instructions, i);
				upMask = instructionVector.eq(SPECIES.broadcast('u'));
				downMask = instructionVector.eq(SPECIES.broadcast('d'));
				accDepth = accDepth.add(measurementVector, downMask).sub(measurementVector, upMask);
				accHorizontalP = accHorizontalP.add(measurementVector, upMask.or(downMask).not());
			}
			horizontalP += Arrays.stream(accHorizontalP.toIntArray()).sum();
			depth += Arrays.stream(accDepth.toIntArray()).sum();
			accHorizontalP = SPECIES.zero();
			accDepth = SPECIES.zero();
		}
		for (; i < loopBound; i+= chunkSize) {
			measurementVector = SPECIES.fromArray(measurements, i);
			instructionVector = SPECIES.fromArray(instructions, i);
			upMask = instructionVector.eq(SPECIES.broadcast('u'));
			downMask = instructionVector.eq(SPECIES.broadcast('d'));
			accDepth = accDepth.add(measurementVector, downMask).sub(measurementVector, upMask);
			accHorizontalP = accHorizontalP.add(measurementVector, upMask.or(downMask).not());
		}

		horizontalP += Arrays.stream(accHorizontalP.toIntArray()).sum();
		depth += Arrays.stream(accDepth.toIntArray()).sum();

		for (; i < measurements.length; i++) {
			switch (instructions[i]) {
				case 'd' -> depth += measurements[i];
				case 'u' -> depth -= measurements[i];
				default -> horizontalP += measurements[i];
			}
		}

		return (int) horizontalP * (int) depth;
	}

	public Integer partTwo(byte[] measurements, byte[] instructions) {
		int horizontalP = 0;
		int depth = 0;
		int aim = 0;
		for (int i = 0; i < instructions.length; i++) {
			switch (instructions[i]) {
				case 'd' -> aim += measurements[i];
				case 'u' -> aim -= measurements[i];
				default -> {
					horizontalP += measurements[i];
					depth += aim * measurements[i];
				}
			}
		}

		return horizontalP * depth;
	}
}

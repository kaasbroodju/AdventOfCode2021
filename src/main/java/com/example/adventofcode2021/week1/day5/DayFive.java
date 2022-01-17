package com.example.adventofcode2021.week1.day5;

import com.example.adventofcode2021.Day;
import jdk.incubator.vector.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class DayFive extends Day<short[], Integer> {

	public static void main(String[] args) throws IOException {
		DayFive result = new DayFive();
		short[] input = result.readInput(new File("src/main/java/com/example/adventofcode2021/week1/day5/input.txt"));
		System.out.println(result.partOne(input));
		System.out.println(result.partTwo(input));
	}

	@Override
	public short[] readInput(File file) throws IOException {
		short[] output = new short[500*4];
		List<String> lines = Files.readAllLines(file.toPath());

		for (int i = 0; i < lines.size(); i++) {
			String s = lines.get(i);

			String[] split = s.split(" -> ");
			String[] a = split[0].split(",");
			String[] b = split[1].split(",");

			output[i] = Short.parseShort(a[0]);
			output[500+i] = Short.parseShort(a[1]);
			output[500*2+i] = Short.parseShort(b[0]);
			output[500*3+i] = Short.parseShort(b[1]);
		}

		return output;
	}

	private static final VectorSpecies<Byte> BYTE_VECTOR_SPECIES = ByteVector.SPECIES_PREFERRED;
	private static final VectorSpecies<Short> SHORT_VECTOR_SPECIES = ShortVector.SPECIES_PREFERRED;

	@Override
	public Integer partOne(short[] cords) {
		byte[][] grid = new byte[1000][1000];
		short[] instructions = new short[cords.length/4];
		short[] minX = new short[cords.length/4];
		short[] minY = new short[cords.length/4];
		short[] maxX = new short[cords.length/4];
		short[] maxY = new short[cords.length/4];

		int instructionLoopBound = SHORT_VECTOR_SPECIES.loopBound(instructions.length);
		ShortVector ones = ShortVector.broadcast(SHORT_VECTOR_SPECIES, 1);
		ShortVector threes = ShortVector.broadcast(SHORT_VECTOR_SPECIES, 3);

		ShortVector xones;
		ShortVector xtwos;
		ShortVector yones;
		ShortVector ytwos;

		int chunkSize = SHORT_VECTOR_SPECIES.length();
		int z = 0;
		for (; z < instructionLoopBound; z+=chunkSize) {
			xones = ShortVector.fromArray(SHORT_VECTOR_SPECIES, cords, z);
			yones = ShortVector.fromArray(SHORT_VECTOR_SPECIES, cords, 500+z);
			xtwos = ShortVector.fromArray(SHORT_VECTOR_SPECIES, cords, 500*2+z);
			ytwos = ShortVector.fromArray(SHORT_VECTOR_SPECIES, cords, 500*3+z);

			xones.min(xtwos).intoArray(minX, z);
			yones.min(ytwos).intoArray(minY, z);
			xones.max(xtwos).intoArray(maxX, z);
			yones.max(ytwos).intoArray(maxY, z);

			ones.intoArray(instructions, z, xones.eq(xtwos));
			threes.intoArray(instructions, z, yones.eq(ytwos));
		}

		z = instructions.length - chunkSize;
		xones = ShortVector.fromArray(SHORT_VECTOR_SPECIES, cords, z);
		yones = ShortVector.fromArray(SHORT_VECTOR_SPECIES, cords, 500+z);
		xtwos = ShortVector.fromArray(SHORT_VECTOR_SPECIES, cords, 500*2+z);
		ytwos = ShortVector.fromArray(SHORT_VECTOR_SPECIES, cords, 500*3+z);

		xones.min(xtwos).intoArray(minX, z);
		yones.min(ytwos).intoArray(minY, z);
		xones.max(xtwos).intoArray(maxX, z);
		yones.max(ytwos).intoArray(maxY, z);

		ones.intoArray(instructions, z, xones.eq(xtwos));
		threes.intoArray(instructions, z, yones.eq(ytwos));

		for (int i = 0; i < cords.length/4; i++) {
			switch (instructions[i]) {
				case 1 -> {
					for (int y = minY[i]; y <= maxY[i]; y++) {
						grid[maxX[i]][y]++;
					}
				}
				case 3 -> {
					for (int x = minX[i]; x <= maxX[i]; x++) {
						grid[x][maxY[i]]++;
					}
				}
			}
		}

		return getResult(grid);
	}

	private int getResult(byte[][] grid) {
		int counter = 0;
		Vector<Byte> acc;
		int loopBound = BYTE_VECTOR_SPECIES.loopBound(grid[0].length);
		int chunkSize = BYTE_VECTOR_SPECIES.length();
		int i;
		for (byte[] bytes : grid) {
			acc = BYTE_VECTOR_SPECIES.zero();
			i = 0;
			for (; i < loopBound; i+= chunkSize) {
//				System.out.println(SPECIES.fromArray(bytes, i).compare(VectorOperators.GT, 1).toVector());
				acc = acc.add(BYTE_VECTOR_SPECIES.fromArray(bytes, i).compare(VectorOperators.GT, 1).toVector());
			}

			counter -= Arrays.stream(acc.toIntArray()).sum();

			for (; i < bytes.length; i++) {
				if (bytes[i] > 1) counter++;
			}
		}

		return counter;
	}

	@Override
	public Integer partTwo(short[] cords) {
		byte[][] grid = new byte[1000][1000];
		short[] instructions = new short[cords.length/4];
		short[] minX = new short[cords.length/4];
		short[] minY = new short[cords.length/4];
		short[] maxX = new short[cords.length/4];
		short[] maxY = new short[cords.length/4];

		int instructionLoopBound = SHORT_VECTOR_SPECIES.loopBound(instructions.length);
		ShortVector ones = ShortVector.broadcast(SHORT_VECTOR_SPECIES, 1);
		ShortVector threes = ShortVector.broadcast(SHORT_VECTOR_SPECIES, 3);

		ShortVector xones;
		ShortVector xtwos;
		ShortVector yones;
		ShortVector ytwos;

		int chunkSize = SHORT_VECTOR_SPECIES.length();
		int z = 0;
		for (; z < instructionLoopBound; z+=chunkSize) {
			xones = ShortVector.fromArray(SHORT_VECTOR_SPECIES, cords, z);
			yones = ShortVector.fromArray(SHORT_VECTOR_SPECIES, cords, 500+z);
			xtwos = ShortVector.fromArray(SHORT_VECTOR_SPECIES, cords, 500*2+z);
			ytwos = ShortVector.fromArray(SHORT_VECTOR_SPECIES, cords, 500*3+z);

			xones.min(xtwos).intoArray(minX, z);
			yones.min(ytwos).intoArray(minY, z);
			xones.max(xtwos).intoArray(maxX, z);
			yones.max(ytwos).intoArray(maxY, z);

			ones.intoArray(instructions, z, xones.eq(xtwos));
			threes.intoArray(instructions, z, yones.eq(ytwos));
		}

		z = instructions.length - chunkSize;
		xones = ShortVector.fromArray(SHORT_VECTOR_SPECIES, cords, z);
		yones = ShortVector.fromArray(SHORT_VECTOR_SPECIES, cords, 500+z);
		xtwos = ShortVector.fromArray(SHORT_VECTOR_SPECIES, cords, 500*2+z);
		ytwos = ShortVector.fromArray(SHORT_VECTOR_SPECIES, cords, 500*3+z);

		xones.min(xtwos).intoArray(minX, z);
		yones.min(ytwos).intoArray(minY, z);
		xones.max(xtwos).intoArray(maxX, z);
		yones.max(ytwos).intoArray(maxY, z);

		ones.intoArray(instructions, z, xones.eq(xtwos));
		threes.intoArray(instructions, z, yones.eq(ytwos));

		for (int i = 0; i < cords.length/4; i++) {
			switch (instructions[i]) {
				case 1 -> {
					for (int y = minY[i]; y <= maxY[i]; y++) {
						grid[maxX[i]][y]++;
					}
				}
				case 3 -> {
					for (int x = minX[i]; x <= maxX[i]; x++) {
						grid[x][maxY[i]]++;
					}
				}
				default -> {
					if (cords[500*2+i] > cords[i]) {
						if (cords[500*3+i] > cords[500+i]) {
							for (int delta = 0; delta <= maxX[i] - minX[i]; delta++) {
								grid[minX[i]+delta][minY[i]+delta]++;
							}
						} else {
							for (int delta = 0; delta <= maxX[i] - minX[i]; delta++) {
								grid[minX[i]+delta][maxY[i]-delta]++;
							}
						}
					} else {
						if (cords[500*3+i] > cords[500+i]) {
							for (int delta = 0; delta <= maxX[i] - minX[i]; delta++) {
								grid[maxX[i]-delta][minY[i]+delta]++;
							}
						} else {
							for (int delta = 0; delta <= maxX[i] - minX[i]; delta++) {
								grid[maxX[i]-delta][maxY[i]-delta]++;
							}
						}
					}
				}
			}
		}

		return getResult(grid);
	}
}

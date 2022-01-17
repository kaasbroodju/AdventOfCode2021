package com.example.adventofcode2021.week2.day9;

import com.example.adventofcode2021.Day;
import com.example.adventofcode2021.week1.day7.DaySeven;
import jdk.incubator.vector.ByteVector;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorSpecies;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class DayNine extends Day<byte[][], Integer> {

	private static final int width = 100;
	private static final int heigth = 100;
	private static final VectorSpecies<Byte> SPECIES = ByteVector.SPECIES_64;

	public static void main(String[] args) throws IOException {
		DayNine result = new DayNine();
		byte[][] input = result.readInput(new File("src/main/java/com/example/adventofcode2021/week2/day9/input.txt"));
		System.out.println(result.partOne(input));
		System.out.println(result.partTwo(input));
	}


	@Override
	public byte[][] readInput(File file) throws IOException {
		byte[][] field = new byte[heigth+2][width+2];
		List<String> rows = Files.readAllLines(file.toPath());

		Arrays.fill(field[0], Byte.MAX_VALUE);
		Arrays.fill(field[field.length-1], Byte.MAX_VALUE);

		for (int y = 0; y < heigth; y++) {
			for (int x = 0; x < width; x++) {
				field[y+1][x+1] = Byte.parseByte(rows.get(y).split("")[x]);
			}
			field[y+1][0] = Byte.MAX_VALUE;
			field[y+1][width+1] = Byte.MAX_VALUE;
		}
		return field;
	}

	@Override
	public Integer partOne(byte[][] field) {
		int result = 0;
		for (short y = 1; y < heigth+1; y++) {
			for (short x = 1; x < width+1; x++) {
				byte[] surrounds = new byte[]{field[y-1][x-1], field[y-1][x], field[y-1][x+1], field[y][x-1], field[y][x+1], field[y+1][x-1], field[y+1][x], field[y+1][x+1]};
				if (ByteVector.fromArray(SPECIES, surrounds, 0).compare(VectorOperators.GT, field[y][x]).allTrue()) {
					result += field[y][x] + 1;
				}
			}
		}

		return result;
	}

	@Override
	public Integer partTwo(byte[][] field) {
		List<Integer> result = new ArrayList<>();
		for (short y = 1; y < heigth+1; y++) {
			for (short x = 1; x < width+1; x++) {
				byte[] surrounds = new byte[]{field[y-1][x-1], field[y-1][x], field[y-1][x+1], field[y][x-1], field[y][x+1], field[y+1][x-1], field[y+1][x], field[y+1][x+1]};
				if (ByteVector.fromArray(SPECIES, surrounds, 0).compare(VectorOperators.GT, field[y][x]).allTrue()) {
					result.add(getBasinSize(field, y, x));
				}
			}
		}

		Integer a = Collections.max(result);
		result.remove(a);
		Integer b = Collections.max(result);
		result.remove(b);
		Integer c = Collections.max(result);

		return a * b * c;
	}

	public static int getBasinSize(byte[][] field, int y, int x)  {
		Set<Cords> output = new HashSet<>();
		getBasinSize(field, y, x, output);
		return output.size();
	}

	public static void getBasinSize(byte[][] field, int y, int x, Set<Cords> cords)  {
		if(field[y][x] == Byte.MAX_VALUE || (field[y][x] == 9 || cords.contains(new Cords(x, y)))) return;
		cords.add(new Cords(x, y));
		getBasinSize(field, y-1, x, cords);
		getBasinSize(field, y, x-1, cords);
		getBasinSize(field, y, x+1, cords);
		getBasinSize(field, y+1, x, cords);
	}
}

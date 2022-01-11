package com.example.adventofcode2021;

import java.io.File;
import java.io.IOException;

public abstract class Day<INPUT_TYPE, RETURN_TYPE> {

	public void main(File file) throws IOException {
		INPUT_TYPE input = readInput(file);
		System.out.println(partOne(input));
		System.out.println(partTwo(input));
	}

	public abstract INPUT_TYPE readInput(File file) throws IOException;
	public abstract RETURN_TYPE partOne(INPUT_TYPE input);
	public abstract RETURN_TYPE partTwo(INPUT_TYPE input);
}

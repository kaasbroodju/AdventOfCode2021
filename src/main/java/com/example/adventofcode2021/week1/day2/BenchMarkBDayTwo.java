package com.example.adventofcode2021.week1.day2;

import org.openjdk.jmh.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Fork(value = 1, warmups = 1)
public class BenchMarkBDayTwo {

	@State(Scope.Thread)
	public static class Input {
		public Day2Data data;

		@Setup
		public void setUp() {
			try {
				data = new DayTwo().readInput(new File("src/main/java/com/example/adventofcode2021/week1/day2/input.txt"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@TearDown
		public void nothing() {}
	}

	@Benchmark @BenchmarkMode(Mode.AverageTime) @OutputTimeUnit(TimeUnit.MICROSECONDS)
	public int partOne(Input input) throws IOException {
		return new DayTwo().partOne(input.data);
	}
	@Benchmark @BenchmarkMode(Mode.AverageTime) @OutputTimeUnit(TimeUnit.MICROSECONDS)
	public int partTwo(Input input) throws IOException {
		return new DayTwo().partTwo(input.data);
	}
}

package com.example.adventofcode2021.week1.day5;

import org.openjdk.jmh.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Fork(value = 1, warmups = 1)
public class BenchMarkEDayFive {

	@State(Scope.Thread)
	public static class Input {
		public short[] ints;

		@Setup
		public void setUp() {
			try {
				ints = new DayFive().readInput(new File("src/main/java/com/example/adventofcode2021/week1/day5/input.txt"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@TearDown
		public void nothing() {}
	}

	@Benchmark @BenchmarkMode(Mode.AverageTime) @OutputTimeUnit(TimeUnit.MICROSECONDS)
	public long partOne(Input input) throws IOException {
		return new DayFive().partOne(input.ints);
	}
	@Benchmark @BenchmarkMode(Mode.AverageTime) @OutputTimeUnit(TimeUnit.MICROSECONDS)
	public long partTwo(Input input) throws IOException {
		return new DayFive().partTwo(input.ints);
	}
}

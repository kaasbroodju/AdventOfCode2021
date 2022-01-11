package com.example.adventofcode2021.week1.day1;

import org.openjdk.jmh.annotations.*;

import java.io.File;
import java.io.IOException;

@Fork(value = 1, warmups = 1)
public class BenchMarkTest {

    @State(Scope.Thread)
    public static class Input {
        public int[] ints;

        @Setup
        public void setUp() {
			try {
				ints = new DayOne().readInput(new File("src/main/java/com/example/adventofcode2021/week1/day1/input.txt"));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }

        @TearDown
        public void nothing() {}
    }

	@Benchmark
	public int partOne(Input input) throws IOException {
		return new DayOne().partOne(input.ints);
	}
    @Benchmark
    public int partTwo(Input input) throws IOException {
        return new DayOne().partTwo(input.ints);
    }
}

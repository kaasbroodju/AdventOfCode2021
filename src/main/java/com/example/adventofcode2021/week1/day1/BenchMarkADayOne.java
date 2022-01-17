package com.example.adventofcode2021.week1.day1;

import org.openjdk.jmh.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Fork(value = 1, warmups = 1)
public class BenchMarkADayOne {

    @State(Scope.Thread)
    public static class Input {
        public short[] ints;

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

	@Benchmark @BenchmarkMode(Mode.AverageTime) @OutputTimeUnit(TimeUnit.MICROSECONDS)
	public int partOne(Input input) throws IOException {
		return new DayOne().partOne(input.ints);
	}
    @Benchmark @BenchmarkMode(Mode.AverageTime) @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public int partTwo(Input input) throws IOException {
        return new DayOne().partTwo(input.ints);
    }
}

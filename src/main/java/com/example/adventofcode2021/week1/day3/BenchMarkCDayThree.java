package com.example.adventofcode2021.week1.day3;

import org.openjdk.jmh.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Fork(value = 1, warmups = 1)
public class BenchMarkCDayThree {

    @State(Scope.Thread)
    public static class Input {
        public boolean[] booleans;

        @Setup
        public void setUp() {
            try {
                booleans = new DayThree().readInput(new File("src/main/java/com/example/adventofcode2021/week1/day3/input.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @TearDown
        public void nothing() {}
    }

    @Benchmark @BenchmarkMode(Mode.AverageTime) @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public long partOne(Input input) throws IOException {
        return new DayThree().partOne(input.booleans);
    }
    @Benchmark @BenchmarkMode(Mode.AverageTime) @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public long partTwo(Input input) throws IOException {
        return new DayThree().partTwo(input.booleans);
    }
}

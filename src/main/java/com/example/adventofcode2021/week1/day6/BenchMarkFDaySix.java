package com.example.adventofcode2021.week1.day6;

import org.openjdk.jmh.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Fork(value = 1, warmups = 1)
public class BenchMarkFDaySix {

    @State(Scope.Thread)
    public static class Input {
        public long[] longs;

        @Setup
        public void setUp() {
            try {
                longs = new DaySix().readInput(new File("src/main/java/com/example/adventofcode2021/week1/day6/input.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @TearDown
        public void nothing() {}
    }

    @Benchmark @BenchmarkMode(Mode.AverageTime) @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public long partOne(Input input) throws IOException {
        return new DaySix().partOne(input.longs);
    }
    @Benchmark @BenchmarkMode(Mode.AverageTime) @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public long partTwo(Input input) throws IOException {
        return new DaySix().partTwo(input.longs);
    }
}

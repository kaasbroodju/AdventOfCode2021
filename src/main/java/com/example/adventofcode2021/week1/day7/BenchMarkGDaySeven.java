package com.example.adventofcode2021.week1.day7;

import org.openjdk.jmh.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Fork(value = 1, warmups = 1)
public class BenchMarkGDaySeven {


    @State(Scope.Thread)
    public static class Input {
        public int[] ints;

        @Setup
        public void setUp() {
            try {
                ints = new DaySeven().readInput(new File("src/main/java/com/example/adventofcode2021/week1/day7/input.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @TearDown
        public void nothing() {}
    }

    @Benchmark @BenchmarkMode(Mode.AverageTime) @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public long partOne(Input input) throws IOException {
        return new DaySeven().partOne(input.ints);
    }
    @Benchmark @BenchmarkMode(Mode.AverageTime) @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public long partTwo(Input input) throws IOException {
        return new DaySeven().partTwo(input.ints);
    }
}

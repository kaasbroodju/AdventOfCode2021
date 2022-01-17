package com.example.adventofcode2021.week2.day9;

import org.openjdk.jmh.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Fork(value = 1, warmups = 1)
public class BenchMarkIDayNine {


    @State(Scope.Thread)
    public static class Input {
        public byte[][] bytes;

        @Setup
        public void setUp() {
            try {
                bytes = new DayNine().readInput(new File("src/main/java/com/example/adventofcode2021/week2/day9/input.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @TearDown
        public void nothing() {}
    }

    @Benchmark @BenchmarkMode(Mode.AverageTime) @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public int partOne(Input input) throws IOException {
        return new DayNine().partOne(input.bytes);
    }
    @Benchmark @BenchmarkMode(Mode.AverageTime) @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public long partTwo(Input input) throws IOException {
        return new DayNine().partTwo(input.bytes);
    }
}

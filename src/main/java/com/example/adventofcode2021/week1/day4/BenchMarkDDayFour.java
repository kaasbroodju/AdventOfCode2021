package com.example.adventofcode2021.week1.day4;

import org.openjdk.jmh.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Fork(value = 1, warmups = 1)
public class BenchMarkDDayFour {

    @State(Scope.Thread)
    public static class Input {
        public DayFour.Draw draw;

        @Setup
        public void setUp() {
            try {
                draw = new DayFour().readInput(new File("src/main/java/com/example/adventofcode2021/week1/day4/input.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @TearDown
        public void nothing() {}
    }

    @Benchmark @BenchmarkMode(Mode.AverageTime) @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public long partOne(Input input) throws IOException {
        return new DayFour().partOne(input.draw);
    }
    @Benchmark @BenchmarkMode(Mode.AverageTime) @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public long partTwo(Input input) throws IOException {
        return new DayFour().partTwo(input.draw);
    }
}

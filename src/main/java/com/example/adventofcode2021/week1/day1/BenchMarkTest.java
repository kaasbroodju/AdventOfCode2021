package com.example.adventofcode2021.week1.day1;

import org.openjdk.jmh.annotations.*;

import java.io.File;
import java.util.List;

@Fork(value = 1, warmups = 1)
public class BenchMarkTest {

    @State(Scope.Thread)
    public static class Input {
        public File file;

        @Setup
        public void setUp() {
            file = new File("src/main/java/com/example/adventofcode2021/week1/day1/input.txt");
        }

        @TearDown
        public void nothing() {}
    }

    @Benchmark
    public int[] getArrayBack(Input input) {
        return Part2.readFile(input.file);
    }

    @Benchmark
    public List<Integer> getListBack(Input input) {
        return Part1.readFile(input.file);
    }
}

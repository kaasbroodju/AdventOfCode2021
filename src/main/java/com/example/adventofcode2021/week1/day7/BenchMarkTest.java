package com.example.adventofcode2021.week1.day7;

import org.openjdk.jmh.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.Random;

@Fork(value = 1, warmups = 1)
public class BenchMarkTest {


    @State(Scope.Thread)
    public static class Input {
        public File file;
        public int value;

        @Setup
        public void setUp() {
            file = new File("src/main/java/com/example/adventofcode2021/week1/day7/input.txt");
        }

        @TearDown
        public void nothing() {}
    }

//    @Benchmark
//    public long old(Input input) throws IOException {
//        return Part1.old(input.file);
//    }

//    @Benchmark
//    public long refactor(Input input) throws IOException {
//        return Part1.refactor(input.file);
//    }

    @Benchmark
    public long simd(Input input) throws IOException {
        return Part1.simd(input.file);
    }

//    @Benchmark
//    public long parallel(Input input) throws IOException {
//        return Part1.testparralelinner(input.file);
//    }
}

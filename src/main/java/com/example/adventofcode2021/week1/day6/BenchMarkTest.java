//package com.example.adventofcode2021.week1.day6;//package com.example.adventofcode2021.week1.day5;
//
//import org.openjdk.jmh.annotations.*;
//
//import java.io.File;
//import java.io.IOException;
//
//@Fork(value = 1, warmups = 1)
//public class BenchMarkTest {
//
//    @State(Scope.Thread)
//    public static class Input {
//        public File file;
//
//        @Setup
//        public void setUp() {
//            file = new File("src/main/java/com/example/adventofcode2021/week1/day6/input.txt");
//        }
//
//        @TearDown
//        public void nothing() {}
//    }
//
//    @Benchmark
//    public long old(Input input) throws IOException {
//        return Part1.old(input.file, 256);
//    }
//
//    @Benchmark
//    public long refactor(Input input) throws IOException {
//        return Part2.answer(input.file, 256);
//    }
//}

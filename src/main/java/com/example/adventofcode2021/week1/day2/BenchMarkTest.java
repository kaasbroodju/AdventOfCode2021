//package com.example.adventofcode2021.week1.day2;
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
//            file = new File("src/main/java/com/example/adventofcode2021/week1/day2/input.txt");
//        }
//
//        @TearDown
//        public void nothing() {}
//    }
//
//    @Benchmark
//    public int partOne(Input input) throws IOException {
//        return Part1.getAnswer(input.file);
//    }
//
//    @Benchmark
//    public int partTwo(Input input) throws IOException {
//        return Part2.getAnswer(input.file);
//    }
//}

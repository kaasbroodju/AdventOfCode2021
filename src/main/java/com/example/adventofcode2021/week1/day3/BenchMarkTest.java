//package com.example.adventofcode2021.week1.day3;
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
//            file = new File("src/main/java/com/example/adventofcode2021/week1/day3/input.txt");
//        }
//
//        @TearDown
//        public void nothing() {}
//    }
//
////    @Benchmark
////    public int old(Input input) throws IOException {
////        return Part1.old(input.file);
////    }
////
////    @Benchmark
////    public int refactor(Input input) throws IOException {
////        return Part1.refactor(input.file);
////    }
////
////    @Benchmark
////    public int refactorPlus(Input input) throws IOException {
////        return Part1.refactorPlus(input.file);
////    }
//
//    @Benchmark
//    public int oldPart2(Input input) throws IOException {
//        return Part2.old(input.file);
//    }
//
//    @Benchmark
//    public int refactorPart2(Input input) throws IOException {
//        return Part2.refactor(input.file);
//    }
//}

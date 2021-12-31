package com.example.adventofcode2021.week1.day7;

import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.VectorSpecies;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Part1 {
    public static void main(String[] args) throws IOException {
        System.out.println(old(
                new File("src/main/java/com/example/adventofcode2021/week1/day7/input.txt")
                )
        );

        System.out.println(refactor(
                new File("src/main/java/com/example/adventofcode2021/week1/day7/input.txt")
                )
        );
        System.out.println(simd(
                        new File("src/main/java/com/example/adventofcode2021/week1/day7/input.txt")
                )
        );

//        System.out.println(recursion(0, 11));
//        System.out.println((11*(11+1))/2);
    }

    public static long old(File file) throws IOException {
        int[] input = Arrays.stream(Files.readString(file.toPath()).split(",")).mapToInt(Integer::parseInt).toArray();
        int max = Arrays.stream(input).max().getAsInt();
        int min = Arrays.stream(input).min().getAsInt();

        long fuelRequired;
        long lowest = Long.MAX_VALUE;
        for (int i = min; i < max; i++) {
            fuelRequired = 0L;
            for (int number : input) {
                fuelRequired += formula(number, i);
            }
            if (fuelRequired < lowest) {
                lowest = fuelRequired;
            }
        }

        return lowest;
    }

    private static final VectorSpecies<Integer> SPECIES = IntVector.SPECIES_PREFERRED;

    public static long simd(File file) throws IOException {
        int[] input = Arrays.stream(Files.readString(file.toPath()).split(",")).mapToInt(Integer::parseInt).toArray();
//        Arrays.sort(input);
        int max = Arrays.stream(input).max().getAsInt();
        int min = Arrays.stream(input).min().getAsInt();
//        IntVector[] vectors = new IntVector[input.length/8];
////        System.out.println(SPECIES);
////        int j = 0;
//        int upperbound = SPECIES.loopBound(input.length);
//        for (int i = 0; i < input.length / 8; i++) {
//            vectors[i] = IntVector.fromArray(SPECIES, input, i*8);
////            System.out.println(vectors[i]);
//        }

        long fuelRequired;
        long lowest = Long.MAX_VALUE;
        int[] results = new int[input.length];
        for (int i = min; i < max; i++) {
            for (int j = 0; j < input.length/8; j+=8) {
                formulaSIMD(IntVector.fromArray(SPECIES, input, j).sub(i).abs()).intoArray(results, j);
            }
//            System.out.println(Arrays.toString(results));
            fuelRequired = Arrays.stream(results).asLongStream().sum();
            if (fuelRequired < lowest) {
                lowest = fuelRequired;
            }
        }

        return lowest;
    }


    public static long refactor(File file) throws IOException {
        int[] input = Arrays.stream(Files.readString(file.toPath()).split(",")).mapToInt(Integer::parseInt).toArray();

        int max = Arrays.stream(input).max().getAsInt();
        int min = Arrays.stream(input).min().getAsInt();

        long[] results = new long[max-min];
        IntStream.range(0, max).parallel().forEach(i -> results[i] = Arrays.stream(input).mapToLong(number -> formula(number, i)).sum());
        return Arrays.stream(results).min().getAsLong();
    }

    public static long formula(int amount, int i) {
        amount = Math.abs(amount - i);
        return amount * (amount + 1L) / 2;
    }

    public static IntVector formulaSIMD(IntVector vector) {
        return vector.mul(vector.add(1)).div(2);
    }
}

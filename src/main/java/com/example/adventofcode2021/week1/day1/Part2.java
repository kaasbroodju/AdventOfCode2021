package com.example.adventofcode2021.week1.day1;

import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorSpecies;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Part2 {

    public static void main(String[] args) throws IOException {
        System.out.println(getAnswer(new File("src/main/java/com/example/adventofcode2021/week1/day1/input.txt")));
        System.out.println(refactor(new File("src/main/java/com/example/adventofcode2021/week1/day1/input.txt")));

    }

    public static int getAnswer(File file) throws IOException {
        int counter = 0;
        int[] numbers = Files.newBufferedReader(file.toPath()).lines().mapToInt(Integer::parseInt).toArray();
//        boolean[] results = new boolean[numbers.length];
//        for (int i = 0; i < numbers.length-8; i+=8) {
////            System.out.println(i);
//            IntVector.fromArray(SPECIES, numbers,  i).compare(VectorOperators.LT, IntVector.fromArray(SPECIES, numbers, i + 3)).intoArray(results, i);
////            System.out.println(IntVector.fromArray(SPECIES, numbers,  i)  + ":" + IntVector.fromArray(SPECIES, numbers,  i+3));
//
//        }
        for (int i = 0; i < numbers.length-3; i++) {
//            System.out.println(i);
            if (numbers[i] < numbers[i+3]) counter++;
        }

//        for (boolean result : results) {
//            if (result) counter++;
//        }

        return counter;
    }

    private static final VectorSpecies<Integer> SPECIES = IntVector.SPECIES_PREFERRED;

    public static int refactor(File file) throws IOException {
        int counter = 0;
        int[] numbers = Files.newBufferedReader(file.toPath()).lines().mapToInt(Integer::parseInt).toArray();
        boolean[] results = new boolean[numbers.length];
        int i = 0;
        for (; i < numbers.length-8; i+=8) {
//            System.out.println(i);
            IntVector.fromArray(SPECIES, numbers,  i).compare(VectorOperators.LT, IntVector.fromArray(SPECIES, numbers, i + 3)).intoArray(results, i);
//            System.out.println(IntVector.fromArray(SPECIES, numbers,  i)  + ":" + IntVector.fromArray(SPECIES, numbers,  i+3));

        }
        for (; i < numbers.length-3; i++) {
//            System.out.println(i);
            if (numbers[i] < numbers[i+3]) results[i] = true;
        }

        for (boolean result : results) {
            if (result) counter++;
        }

        return counter;
    }
}

package com.example.adventofcode2021.week2.day8;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Part1 {

    private static final char[] possible = {'d', 'e', 'a', 'f', 'g', 'b', 'c'};


    public static void main(String[] args) throws IOException {
        System.out.println(
                old(
                        new File("src/main/java/com/example/adventofcode2021/week2/day8/input.txt")
                )
        );

    }

    public static int old(File file) throws IOException {
        return Files.readAllLines(file.toPath()).stream().mapToInt(entry -> {
            String[] temp = entry.split(" \\| ");
            String left = temp[0];
            String right = temp[1];
            String[] rightNumbers = right.split("\\s");
            String[] leftNumbers = left.split("\\s");
            char[][] tempChar = new char[possible.length][7];

            for (String leftNumber : leftNumbers) {
                change(tempChar, leftNumber);
            }
            System.out.println(Arrays.deepToString(tempChar));
            char[] constChar = getAlignment(tempChar);

            StringBuilder output = new StringBuilder();
            for (String rightNumber : rightNumbers) {
                System.out.println(toString(constChar));

                int[] indexes = Arrays.stream(rightNumber.split("")).mapToInt(s -> {
                    for (int i = 0; i < constChar.length; i++) {
//                        System.out.println(s);
                        if (constChar[i] == s.charAt(0)) return i;
                        System.out.println(constChar[i]);
                    }
                    System.out.println(":" +s);
                    return -1;
                }).filter(i -> i >= 0).sorted().toArray();

                if (rightNumber.length() == 2) {
                    output.append("1");
                } else if (rightNumber.length() == 3) {
                    output.append("7");
                } else if (rightNumber.length() == 4) {
                    output.append("4");
                } else if (rightNumber.length() == 5) {
                    if (Arrays.stream(indexes).noneMatch(i -> i == 2 || i == 4)) {//5
                        System.out.println(Arrays.toString(indexes) + " :5");
                        output.append("5");
                    } else if (Arrays.stream(indexes).noneMatch(i -> i == 1 || i == 5)) {// 2
                        System.out.println(Arrays.toString(indexes) + " :2");
                        output.append("2");
                    } else if (Arrays.stream(indexes).noneMatch(i -> i == 1 || i == 4)) {//3
                        System.out.println(Arrays.toString(indexes) + " :3");
                        output.append("3");
                    }
                } else if (rightNumber.length() == 6) {
                    if (Arrays.stream(indexes).noneMatch(i -> i == 2)) { //6
                        System.out.println(Arrays.toString(indexes) + " :6");
                        output.append("6");
                    } else if (Arrays.stream(indexes).noneMatch(i -> i == 4)) {//9
                        System.out.println(Arrays.toString(indexes) + " :9");
                        output.append("9");
                    } else if (Arrays.stream(indexes).noneMatch(i -> i == 3)) {//9
                        System.out.println(Arrays.toString(indexes) + " :0");
                        output.append("0");
                    }
                } else if (rightNumber.length() == 7) {
                    output.append("8");
//                    change(constChar, rightNumber);
                } else {
//                    System.out.println(constChar);
//                    System.out.println(indexes);
                    if (Arrays.stream(indexes).noneMatch(i -> i == 2)) {
                        System.out.println(Arrays.toString(indexes) + " :6");
                        output.append("6");
                    } else if (Arrays.stream(indexes).noneMatch(i -> i == 4)) {
                        System.out.println(Arrays.toString(indexes) + " :0");
                        output.append("0");
                    } else {
                        System.out.println("ow no");
//                        System.out.println();
//                        System.out.println(rightNumber);
                        System.out.println(Arrays.toString(indexes));
//                        System.out.println("not indexed");
                    }
                }
            }
            System.out.println(right);
            System.out.println(output);
            return Integer.parseInt(output.toString());
        }).sum();
    }

    private static void change(char[][] chars, String number) {
        if (number.length() == 2) {//1
            System.out.println(1);
            chars[2] = number.toCharArray();
            chars[5] = number.toCharArray();
        } else if (number.length() == 3) {//7
            System.out.println(7);
            chars[0] = number.toCharArray();
            chars[2] = number.toCharArray();
            chars[5] = number.toCharArray();
        } else if (number.length() == 4) {//4
            System.out.println(4);
            chars[1] = number.toCharArray();
            chars[2] = number.toCharArray();
            chars[3] = number.toCharArray();
            chars[5] = number.toCharArray();
        } else if (number.length() == 7) {//8
            for (int i = 0; i < 7; i++) {
                chars[i] = number.toCharArray();
            }
        }
    }

    private static char[] getAlignment(char[][] maps) {
        char[] chars = new char[7];

        System.out.println(Arrays.toString(maps[0]));


        System.out.println("end");
        return chars;
    }

    private static boolean correct(char[] chars) {
        Set<Character> set = new HashSet();
        for (char aChar : chars) {
            set.add(aChar);
        }
        return set.size() == 7;
    }

    private static void fillIn(Set<Character> set, char[] chars) {
        for (char aChar : chars) {
            set.add(aChar);
        }
    }



    private static String toString(char[] chars) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ").append(chars[0]).append(chars[0]).append(chars[0]).append(chars[0]).append(" ").append("\n");
        sb.append(chars[1]).append(" ").append(" ").append(" ").append(" ").append(chars[2]).append("\n");
        sb.append(chars[1]).append(" ").append(" ").append(" ").append(" ").append(chars[2]).append("\n");
        sb.append(" ").append(chars[3]).append(chars[3]).append(chars[3]).append(chars[3]).append(" ").append("\n");
        sb.append(chars[4]).append(" ").append(" ").append(" ").append(" ").append(chars[5]).append("\n");
        sb.append(chars[4]).append(" ").append(" ").append(" ").append(" ").append(chars[5]).append("\n");
        sb.append(" ").append(chars[6]).append(chars[6]).append(chars[6]).append(chars[6]).append(" ").append("\n");
        return sb.toString();
    }
}

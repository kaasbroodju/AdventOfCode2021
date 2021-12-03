package com.example.adventofcode2021.week1.day2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicInteger;

public class Part1 {
    private static final int lines = 1000;
    private static int horizontalP;
    private static int depth;

    public static void main(String[] args) {
        for (Command command : readFile(new File("src/main/java/com/example/adventofcode2021/week1/day2/input.txt"))) {
            addposition(command.getCommand(), command.getAmount());
        }
        System.out.println(horizontalP * depth);
    }

    public static void addposition(String command, int amount) {
        switch (command) {
            case "down" -> depth += amount;
            case "up" -> depth -= amount;
            case "forward" -> horizontalP += amount;
        }
    }

    public static Command[] readFile(File file) {
        Command[] output = new Command[lines];
        int i = 0;
//        AtomicInteger ai = new AtomicInteger();
        try {
            BufferedReader br = Files.newBufferedReader(file.toPath());
//            Files.newBufferedReader(file.toPath()).lines().forEach(
//                    s -> {
//                        String[] command = s.split(" ");
//                        output[ai.get()] = new Command(command[0], Integer.parseInt(command[1]));
//                        ai.getAndIncrement();
//                    }
//            );
            while (i < lines) {
                String[] command = br.readLine().split(" ");
                output[i] = new Command(command[0], Integer.parseInt(command[1]));
                i++;
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return output;
    }
}

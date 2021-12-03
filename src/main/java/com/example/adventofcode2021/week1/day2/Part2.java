package com.example.adventofcode2021.week1.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part2 {
    private static final int lines = 1000;
    private static int horizontalP;
    private static int depth;
    private static int aim = 0;

    public static void main(String[] args) {
        for (Command command : readFile(new File("src/main/java/com/example/adventofcode2021/week1/day2/input.txt"))) {
            addposition(command.getCommand(), command.getAmount());
        }
        System.out.println(horizontalP * depth);
    }

    public static void addposition(String command, int amount) {
        switch (command) {
            case "down" -> aim += amount;
            case "up" -> aim -= amount;
            case "forward" -> {
                horizontalP += amount;
                depth += aim * amount;
            }
        }
    }

    public static Command[] readFile(File file) {
        Command[] output = new Command[lines];
        int i = 0;
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String[] command = myReader.nextLine().split(" ");
                output[i] = new Command(command[0], Integer.parseInt(command[1]));
                i++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return output;
    }
}

package com.example.adventofcode2021.week1.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Part1 {
    private static int horizontalP;
    private static int depth;

    public static void main(String[] args) {
        for (Command command : readFile(new File("src/main/java/com/example/adventofcode2021/week1/day2/input.txt"))) {
            addposition(command.getCommand(), command.getAmount());
            System.out.println(horizontalP + " " + depth);
        }
        System.out.println(horizontalP * depth);
    }

    public static void addposition(String command, int amount) {
        switch (command) {
            case "down": depth+= amount; break;
            case "up": depth-= amount; break;
            case "forward": horizontalP += amount; break;
        }
    }

    public static List<Command> readFile(File file) {
        List<Command> output = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String[] command = myReader.nextLine().split(" ");
                output.add(new Command(command[0], Integer.parseInt(command[1])));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return output;
    }
}

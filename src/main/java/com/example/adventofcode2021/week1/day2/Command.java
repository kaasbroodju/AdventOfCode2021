package com.example.adventofcode2021.week1.day2;

public class Command {
    private final String command;
    private final int amount;

    public Command(String command, int amount) {
        this.command = command;
        this.amount = amount;
    }

    public String getCommand() {
        return command;
    }

    public int getAmount() {
        return amount;
    }
}

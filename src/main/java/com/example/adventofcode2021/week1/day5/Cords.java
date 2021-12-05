package com.example.adventofcode2021.week1.day5;

public record Cords(int x, int y) {

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}

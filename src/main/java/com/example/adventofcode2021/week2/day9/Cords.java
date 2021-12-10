package com.example.adventofcode2021.week2.day9;

import java.util.Objects;

public record Cords(int x, int y) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cords cords = (Cords) o;
        return x == cords.x && y == cords.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

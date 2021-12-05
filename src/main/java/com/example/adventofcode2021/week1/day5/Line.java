package com.example.adventofcode2021.week1.day5;

public class Line {
    private final Cords cord1;
    private final Cords cord2;

    public Line(Cords cord1, Cords cord2) {
        this.cord1 = cord1;
        this.cord2 = cord2;
    }

    public Cords[] getPoints() {
        if (cord1.x() == cord1.y() && cord2.x() == cord2.y()) {
            int length = cord2.x() - cord1.x();
            Cords[] cords = new Cords[Math.abs(length)+1];
            cords[0] = cord1;
            cords[cords.length-1] = cord2;
            if (length > 0) {
                for (int i = 1; i < Math.abs(length); i++) {
                    cords[i] = new Cords(cord1.x() + i, cord1.y() + i);
                }
            } else {
                for (int i = 1; i < Math.abs(length); i++) {
                    cords[i] = new Cords(cord1.x() - i, cord1.y() - i);

                }
            }
            return cords;
        } else if (cord1.x() == cord2.y() && cord1.y() == cord2.x()) {
            int length = cord2.y() - cord1.y();
            Cords[] cords = new Cords[Math.abs(length)+1];
            cords[0] = cord1;
            cords[cords.length-1] = cord2;
            if (length > 0) {
                for (int i = 1; i < Math.abs(length); i++) {
                    cords[i] = new Cords(cord1.x() - i, cord1.y() + i);
                }
            } else {
                for (int i = 1; i < Math.abs(length); i++) {
                    cords[i] = new Cords(cord1.x() + i, cord1.y() - i);

                }
            }
            return cords;
        } else if (cord1.x() == cord2.x()) {
            int length = cord2.y() - cord1.y();
            Cords[] cords = new Cords[Math.abs(length)+1];
            cords[0] = cord1;
            cords[cords.length-1] = cord2;
            if (length > 0) {
                for (int i = 1; i < Math.abs(length); i++) {
                    cords[i] = new Cords(cord1.x(), cord1.y() + i);
                }
            } else {
                for (int i = 1; i < Math.abs(length); i++) {
                    cords[i] = new Cords(cord1.x(), cord1.y() - i);
                }
            }
            return cords;
        } else if (cord1.y() == cord2.y()) {
            int length = cord2.x() - cord1.x();
            Cords[] cords = new Cords[Math.abs(length)+1];
            cords[0] = cord1;
            cords[cords.length-1] = cord2;
            if (length > 0) {
                for (int i = 1; i < Math.abs(length); i++) {
                    cords[i] = new Cords(cord1.x() + i, cord1.y());
                }
            } else {
                for (int i = 1; i < Math.abs(length); i++) {
                    cords[i] = new Cords(cord1.x() - i, cord1.y());
                }
            }
            return cords;
        } else if (Math.abs(cord1.x() - cord2.x()) == Math.abs(cord1.y() - cord2.y())) {
            int deltaX = cord2.x() - cord1.x();
            int deltaY = cord2.x() - cord1.x();

            Cords[] cords = new Cords[Math.abs(deltaX)+1];
            cords[0] = cord1;
            cords[cords.length-1] = cord2;
            System.out.println("start");
            if (deltaX > 0) {
                if (deltaY > 0) {
                    System.out.println("1");
                    for (int i = 1; i < Math.abs(deltaX); i++) {
                        cords[i] = new Cords(cord1.x() - (cord1.x() - cord2.x() > 0 ? i : -1 * i), cord1.y() - (cord1.y() - cord2.y() > 0 ? i : -1 * i));
                    }
                } else {
                    System.out.println("2");
                    for (int i = 1; i < Math.abs(deltaX); i++) {
                        cords[i] = new Cords(cord1.x() - (cord1.x() - cord2.x() > 0 ? i : -1 * i), cord1.y() - (cord1.y() - cord2.y() > 0 ? i : -1 * i));
                    }
                }


            } else {

                if (deltaY > 0) {
                    System.out.println("3");
                    for (int i = 1; i < Math.abs(deltaX); i++) {
                        cords[i] = new Cords(cord1.x() + (cord1.x() - cord2.x() > 0 ? i : -1 * i), cord1.y() + (cord1.y() - cord2.y() > 0 ? i : -1 * i));
                    }
                } else {
                    System.out.println("4");
                    for (int i = 1; i < Math.abs(deltaX); i++) {
                        cords[i] = new Cords(cord1.x() - (cord1.x() - cord2.x() > 0 ? i : -1 * i), cord1.y() - (cord1.y() - cord2.y() > 0 ? i : -1 * i));
                    }
                }
            }
            return cords;
        } else {
            return new Cords[0];
        }
    }

    @Override
    public String toString() {
        return  cord1 + " -> " + cord2;
    }
}

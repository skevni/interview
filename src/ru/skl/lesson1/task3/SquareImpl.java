package ru.skl.lesson1.task3;

public class SquareImpl implements Figure {
    private final double side;

    public SquareImpl(double side) {
        this.side = side;
    }

    @Override
    public double square() {
        return Math.pow(side, 2);
    }

    @Override
    public double length() {
        return 4 * side;
    }
}

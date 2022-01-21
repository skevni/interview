package ru.skl.lesson1.task3;

public class CircleImpl implements Figure {
    private final double radius;

    public CircleImpl(double radius) {
        this.radius = radius;
    }

    @Override
    public double square() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double length() {
        return 2 * Math.PI * radius;
    }

    public double getRadius() {
        return radius;
    }
}

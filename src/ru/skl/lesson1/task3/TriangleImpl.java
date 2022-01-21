package ru.skl.lesson1.task3;

public class TriangleImpl implements Triangle{
    private double height;
    private double base;
    private double side1;
    private double side2;
    private double angle;

    public TriangleImpl() {
    }

    public TriangleImpl height(double height) {
        this.height = height;
        return this;
    }

    public TriangleImpl base(double base) {
        this.base = base;
        return this;
    }

    public TriangleImpl side1(double side1) {
        this.side1 = side1;
        return this;
    }

    public TriangleImpl side2(double side2) {
        this.side2 = side2;
        return this;
    }

    public TriangleImpl angle(double angle) {
        this.angle = angle;
        return this;
    }

    @Override
    public double square() {
        return 0.5 * height * base ;
    }

    @Override
    public double length() {
        return base + side1 + side2;
    }

    public double getHeight() {
        return height;
    }

    public double getBase() {
        return base;
    }

    public double getSide1() {
        return side1;
    }

    public double getSide2() {
        return side2;
    }

    public double getAngle() {
        return angle;
    }


}

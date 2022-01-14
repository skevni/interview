package ru.skl.lesson1.task3;

public interface Triangle extends Figure {
    /**
     * Площадь треугольника по формуле Герона
     *
     */
    static double squareUsingPerimeter(double side1, double side2, double side3) {
        double perimeter = side1 + side2 + side3;
        return Math.sqrt(perimeter * (perimeter - side1) * (perimeter - side2) * (perimeter - side3));
    }

    /**
     * Площадь треугольника по двум сторонам и углу между ними
     *
     */
    static double squareUsingAngleAndTwoSide(double side1, double side2, double angle) {
        return 0.5 * side1 * side2 * Math.sin(Math.toRadians(angle));
    }
}

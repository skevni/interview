package ru.skl.lesson1.task3;

public class MainApp {
    public static void main(String[] args) {
        Figure circle = new CircleImpl(5);
        System.out.println(circle.square());
        System.out.println(circle.length());

        Figure square = new SquareImpl(4);
        System.out.println(square.square());
        System.out.println(square.length());

        Figure triangle = new TriangleImpl().base(4).height(2).side1(3).side2(5);

        System.out.println(triangle.square());
        System.out.println(triangle.length());

        System.out.println("Calculating the square given the perimeter...\n" + Triangle.squareUsingPerimeter(2,5,6));

        System.out.println("Calculating the square given two sides and the angle between them...\n" + Triangle.squareUsingAngleAndTwoSide(2,5, 30));
    }
}
